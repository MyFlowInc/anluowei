import { IonIcon, IonLabel } from '@ionic/react'
import React, { SyntheticEvent } from 'react'
import { useHistory, useLocation } from 'react-router'
import styled from 'styled-components'
import { WorkFlowInfo } from '../../../store/workflowSlice'
import { documentTextOutline, settingsOutline } from 'ionicons/icons'
import { Dropdown, MenuProps, Modal } from 'antd'
import { ExclamationCircleFilled, MoreOutlined } from '@ant-design/icons'
import Cooperation from '../../Notify/Cooperation'
import Invite from '../../Notify/Invite'

const genDropItems: (isCreator: boolean)=> MenuProps['items'] = (isCreator: boolean)=>{
  if(isCreator){
    return  [
      {
        key: 'edit',
        label: <div>岗位设置</div>,
      },
      {
        key: 'rename',
        label: <div>重命名</div>,
      },
      {
        key: 'invite',
        label: <div>邀请成员</div>,
      },
      {
        key: 'cooperation',
        label: <div>成员列表</div>,
      },
      {
        key: 'delete',
        label: <div>删除</div>,
      },
    ]
  } else {
    return [
      {
        key: 'edit',
        label: <div>岗位设置</div>,
      }
    ]
  }

}

interface MenuItemProps {
  workflowInfo: WorkFlowInfo
  curFlowDstId: string | undefined
  setCurFlowDstId: (id: string | null) => void
  deleteHandler: (id: string) => void
  openRenameModal: (event: any, data: any) => void
}
const MenuItemRoot = styled.div`
  padding: 12px 0px;
  align-items: center;
  display: flex;
  :hover {
    /* background-color: rgba(56, 128, 255, 0.1); */
    cursor: pointer;
  }
  .icon-1 {
    width: 24px;
    height: 24px;
    opacity: 0.6;
    margin-right: 32px;
  }
  .title {
    display: flex;
    align-items: center;
    width: 142px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .title-select {
    color: rgba(56, 128, 255, 1);
  }
  .icon-edit {
    width: 14px;
    height: 14px;
    opacity: 0.6;
  }
`
export const FlowMenuItem: React.FC<MenuItemProps> = (props) => {
  const {
    workflowInfo,
    curFlowDstId,
    setCurFlowDstId,
    deleteHandler,
    openRenameModal,
  } = props
  const [isCooperationModalOpen, setIsCooperationModalOpen] =
    React.useState(false)
  const [isInviteModalOpen, setIsInviteModalOpen] = React.useState(false)
  const [editDstId, setEditDstId] = React.useState<string | null>(null)

  const history = useHistory()

  const titleClass = ['title']
  if (curFlowDstId === workflowInfo.dstId) {
    titleClass.push('title-select')
  }

  const editHandler = (
    e: React.MouseEvent<HTMLElement> | React.KeyboardEvent<HTMLElement>,
    item: WorkFlowInfo
  ) => {
    e.stopPropagation()
    const path = '/dashboard/workflow-edit/' + item.dstId
    history.push(path)
    setCurFlowDstId(item.dstId)
  }

  const { confirm } = Modal
  const onClick: MenuProps['onClick'] = ({ key, domEvent }) => {
    domEvent.stopPropagation()
    if (key === 'edit') {
      editHandler(domEvent, workflowInfo)
    }
    if (key === 'rename') {
      openRenameModal(domEvent, workflowInfo)
    }

    if (key === 'invite') {
      setIsInviteModalOpen(true)
      setEditDstId(workflowInfo.dstId)
    }

    if (key === 'cooperation') {
      setIsCooperationModalOpen(true)
      setEditDstId(workflowInfo.dstId)
    }
    if (key === 'delete') {
      confirm({
        title: '是否确认删除?',
        icon: <ExclamationCircleFilled />,
        okText: '确认',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
          console.log('OK')
          deleteHandler(workflowInfo.id)
        },
        onCancel() {
          console.log('Cancel')
        },
      })
    }
  }

  return (
    <MenuItemRoot>
      <div className={titleClass.join(' ')}>{workflowInfo.dstName}</div>
      <Dropdown menu={{ items:genDropItems(workflowInfo.isCreator), onClick }} placement="bottomLeft">
        <MoreOutlined />
      </Dropdown>

      {/* 邀请协作 */}
      <Invite {...{ isInviteModalOpen, setIsInviteModalOpen, editDstId }} />
      {/* 成员列表 */}
      <Cooperation
        {...{ isCooperationModalOpen, setIsCooperationModalOpen, editDstId }}
      />
    </MenuItemRoot>
  )
}
