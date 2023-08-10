import styled from 'styled-components'
import { useHistory, useLocation } from 'react-router-dom'
import UserCard from './UserCard'
import HiFlowLogo from '../../BaseUI/HiFlowLogo/HiFlowLogo'
import { useEffect, useState } from 'react'
import {
  IAttachedWorkflowList,
  removeWorkflowList,
  selectAllWorkflowList,
  selectAttachedWorkflowList,
  selectCurFlowDstId,
  selectWorkflowList,
  setWorkflowList,
  updateCurFlowDstId,
  WorkFlowInfo,
} from '../../store/workflowSlice'
import { useAppDispatch, useAppSelector } from '../../store/hooks'
import { FlowMenuItem } from './FlowMenuItem/FlowMenuItem'
import { MenuRoot } from './style'
import _ from 'lodash'
import { forwardRef, useImperativeHandle } from 'react'
import { message, Menu, MenuProps } from 'antd'
import {
  BellOutlined,
  FileTextOutlined,
  PartitionOutlined,
  SettingOutlined,
} from '@ant-design/icons'
import AddFlowMenu from './AddFlowMenu'
import {
  deleteWorkFlow,
  fetchWorkflowList,
  updateWorkFlow,
} from '../../api/apitable/ds-table'
import RenameModal from './FlowMenuItem/RenameModal'

type MenuItem = Required<MenuProps>['items'][number]

function getItem(
  label: React.ReactNode,
  key: React.Key,
  icon?: React.ReactNode,
  children?: MenuItem[],
  type?: 'group'
): MenuItem {
  return {
    key,
    icon,
    children,
    label,
    type,
  } as MenuItem
}

const UserCardContainer = styled.div`
  margin-top: 24px;
  margin-bottom: 24px;
`
export interface MenuRef {
  hideMenuHandle: () => void
  showMenuHandle: () => void
  rootDisplay: 'block' | 'none'
}

const FlowMenu = forwardRef<
  MenuRef,
  {
    path: string
  }
>((props, ref) => {
  const location = useLocation()
  const history = useHistory()
  const flowList = useAppSelector(selectWorkflowList)
  const attachedFlowList = useAppSelector(selectAttachedWorkflowList)
  const allFlowList = useAppSelector(selectAllWorkflowList)

  const curFlowDstId = useAppSelector(selectCurFlowDstId)
  const dispatch = useAppDispatch()
  const [isRenameModalOpen, setIsRenameModalOpen] = useState(false)
  const [curRenameFlow, setCurRenameFlow] = useState<WorkFlowInfo | null>(null)

  const setCurFlowDstId = (value: string | null) => {
    dispatch(updateCurFlowDstId(value))
  }

  const [rootDisplay, setRootDisplay] = useState<'block' | 'none'>('none')

  const hideMenuHandle = () => {
    setRootDisplay('none')
  }

  const showMenuHandle = () => {
    setRootDisplay('block')
  }

  // for mobile
  useImperativeHandle(ref, () => ({
    hideMenuHandle,
    showMenuHandle,
    rootDisplay,
  }))

  const [messageApi, contextHolder] = message.useMessage()

  const openRenameModal = async (e: any, flowInfo: any) => {
    console.log('openRenameModal:', e, flowInfo)
    if (!flowInfo && !flowInfo.id) {
      return
    }
    setCurRenameFlow(flowInfo)
    setIsRenameModalOpen(true)
  }

  const renameWorkFlowHandler = async (id: string, dstName: string) => {
    try {
      await updateWorkFlow({
        id,
        dstName,
      })
    } catch (error) {
      console.log('error', error)
    }
  }
  // delete work flow
  const deleteWorkFlowHandler = async (id: string) => {
    try {
      await deleteWorkFlow(id)
      dispatch(removeWorkflowList(id))
      // update flow list
      const list = await freshWorkFlowList()
      const curFlowId = _.find(flowList, { dstId: curFlowDstId })?.id

      if (list.length > 0 ) {
        if(curFlowId === id) {
          const item0 = list[0]
          history.push(item0.url)
          dispatch(updateCurFlowDstId(item0.dstId))
        }
   
      }
      messageApi.open({
        type: 'success',
        content: '删除成功成功!',
        duration: 1,
      })
    } catch (error) {
      console.log('error', error)
    }
  }
  // 刷新列表
  const freshWorkFlowList = async () => {
    const response = await fetchWorkflowList({ pageNum: 1, pageSize: 999 })
    const data = response.data.record as WorkFlowInfo[]
    const list = data.map((item) => ({
      name: item.dstName,
      url: '/dashboard/workflow-view/' + item.dstId,
      ...item,
    }))
    dispatch(setWorkflowList(list))
    return list
  }

  // 自己创建的表格
  const MenuItemList = flowList.map((item, index) => {
    return {
      icon: <FileTextOutlined style={{ display: 'block' }} />,
      label: (
        <FlowMenuItem
          key={'item_' + item.id}
          workflowInfo={item}
          curFlowDstId={curFlowDstId}
          setCurFlowDstId={setCurFlowDstId}
          deleteHandler={deleteWorkFlowHandler}
          openRenameModal={openRenameModal}
        />
      ),
      key: item.id,
    }
  })

  // 参与外部的表格
  const generateOtherMenuItemList = (list: IAttachedWorkflowList[]) => {
    const children = list.map((item, index) => {
      const nickname = item.nickname
      return getItem(
        nickname,
        item.id,
        null,
        item.children.map((ele: any) =>
          getItem(
            <FlowMenuItem
              key={'item_' + ele.id}
              workflowInfo={ele}
              curFlowDstId={curFlowDstId}
              setCurFlowDstId={setCurFlowDstId}
              deleteHandler={deleteWorkFlowHandler}
              openRenameModal={openRenameModal}
            />,
            ele.id
          )
        ),
        'group'
      )
    })
    return getItem('参与的项目', '参与的项目', <PartitionOutlined />, children)
  }

  const OtherMenuItemList = generateOtherMenuItemList(attachedFlowList)
  // menu router jump
  const routerJumpHandler = (key: string) => {
    if (key === 'notification') {
      setCurFlowDstId('')
      history.push('/dashboard/notification')
      return
    }
    if (key === 'setting') {
      setCurFlowDstId('')
      history.push('/dashboard/setting')
      return
    }

    const item: WorkFlowInfo | undefined = _.find(allFlowList, { id: key })
    if (!item || !item.id) {
      return
    }
    const path = '/dashboard/workflow-view/' + item.dstId
    if (path !== location.pathname) {
      history.push(path)
      setCurFlowDstId(item.dstId)
    }
  }

  return (
    <MenuRoot display={rootDisplay}>
      {contextHolder}
      <RenameModal
        {...{
          isRenameModalOpen,
          setIsRenameModalOpen,
          flowInfo: curRenameFlow,
          renameWorkFlowHandler,
        }}
      />
      <HiFlowLogo />
      <div className="menu-content">
        <div className="menu-content-list">
          <AddFlowMenu />
          <Menu
            mode="inline"
            selectedKeys={[curFlowDstId || '']}
            onClick={({ key }) => {
              routerJumpHandler(key)
            }}
            items={[
              ...MenuItemList,
              // { type: 'divider' },
              OtherMenuItemList as any,
              {
                key: 'grp',
                type: 'group',
                children: [
                  {
                    key: 'notification',
                    icon: <BellOutlined />,
                    label: '通知',
                  },
                  {
                    key: 'setting',
                    icon: <SettingOutlined />,
                    label: '设置',
                  },
                ],
              },
            ]}
          />
        </div>

        <div>
          <UserCardContainer>
            <UserCard />
          </UserCardContainer>
        </div>
      </div>
    </MenuRoot>
  )
})

export default FlowMenu
