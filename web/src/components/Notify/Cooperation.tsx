import { Dropdown, MenuProps, Modal } from 'antd'
import styled from 'styled-components'
import _ from 'lodash'
import { DownOutlined, ExclamationCircleFilled } from '@ant-design/icons'
import { useEffect, useState } from 'react'
import {
  apitableDeveloperUserList,
  deleteInviteUser,
  editInviteUser,
} from '../../api/apitable/ds-share'
import { useAppSelector } from '../../store/hooks'
import { selectCurWorkflow } from '../../store/workflowSlice'
const { confirm } = Modal
const UIListItem = styled.div`
  display: flex;
  width: fit-content;
  align-items: flex-end;
  background-color: #fff;
  border-radius: 4px;
  overflow: hidden;
  .container {
    /* 550 - 42  -42 */
    width: 466px;
    height: 68px;
    border-radius: 4px;
    display: flex;
    position: relative;
    justify-content: space-between;
    align-items: center;
  }

  .img-container {
    position: relative;
    .image {
      width: 42px;
      height: 42px;
    }
  }
  .left {
    display: flex;
    .word {
      margin-left: 20px;
      display: flex;
      flex-direction: column;
      justify-content: center;
    }
    .title {
      font-size: 14px;
      font-weight: bold;
      line-height: 24px;
      letter-spacing: 0em;
      color: #3d3d3d;
    }
    .content {
      font-size: 14px;
      font-weight: normal;
      line-height: 24px;
      letter-spacing: 0em;
      color: #666666;
    }
  }
`
const ListItem = (props: any) => {
  const { className } = props
  const { info, freshUserList } = props
  const { isCreator } = info
  const items = [
    {
      label: <div>仅查看</div>,
      key: 'allowWatch',
    },
    {
      label: <div>可编辑</div>,
      key: 'allowEdit',
    },
    {
      label: <div>移除</div>,
      key: 'remove',
    },
  ]
  const showDeleteConfirm = (info: any, key: string) => {
    confirm({
      title: '确认移除成员吗?',
      icon: <ExclamationCircleFilled />,
      okText: '删除',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        deleteHandler(info, key)
      },
      onCancel() {},
    } as any)
  }

  const updateHandler: MenuProps['onClick'] = async ({ key }) => {
    console.log('MenuProps', info, key)
    if (key === 'remove') {
      showDeleteConfirm(info, key)
      return
    }
    try {
      if (key === 'allowWatch' && info.allowEdit === 1) {
        await editInviteUser({
          id: info.id,
          allowEdit: 0,
        })
        await freshUserList()
      }

      if (key === 'allowEdit' && info.allowEdit !== 1) {
        editInviteUser({
          id: info.id,
          allowEdit: 1,
        })
        await freshUserList()
      }
    } catch (error) {
      console.log('error', error)
    }
  }
  const deleteHandler = async (info: any, key: string) => {
    try {
      await deleteInviteUser({
        id: info.id,
      })
      await freshUserList()
    } catch (error) {
      console.log('error', error)
    }
  }
  const getAccessTitle = (info: any) => {
    if (info.allowEdit === 1) {
      return '可编辑'
    }
    return '仅查看'
  }
  if (isCreator) {
    return (
      <UIListItem className={className}>
        <div className="container">
          <div className="left">
            <div className="img-container">
              <img src={_.get(info, 'userInfo.avatar')} className="image" />
            </div>

            <div className="word">
              <div className="title">{_.get(info, 'userInfo.nickname')} </div>
              <div className="content">{_.get(info, 'userInfo.phone')} </div>
            </div>
          </div>
          <div>
            <span style={{ marginRight: '22px' }}>创建者</span>
          </div>
        </div>
      </UIListItem>
    )
  }
  return (
    <UIListItem className={className}>
      <div className="container">
        <div className="left">
          <div className="img-container">
            <img src={_.get(info, 'userInfo.avatar')} className="image" />
          </div>

          <div className="word">
            <div className="title">{_.get(info, 'userInfo.nickname')} </div>
            <div className="content">{_.get(info, 'userInfo.phone')} </div>
          </div>
        </div>

        <Dropdown menu={{ items, onClick: updateHandler }} trigger={['click']}>
          <div onClick={(e) => e.preventDefault()}>
            <div>
              <span style={{ marginRight: '8px' }}>{getAccessTitle(info)}</span>
              <DownOutlined />
            </div>
          </div>
        </Dropdown>
      </div>
    </UIListItem>
  )
}

const UIROOT = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 0px 0px 0px 18px;
  .list-header {
    margin-top: 12px;

    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  .list-content {
    margin-top: 12px;
    width: 466px;
  }
  .divider {
    border-bottom: 1px solid #e5e6eb;
  }
`

const Cooperation = (props: any) => {
  const { className } = props
  const { isCooperationModalOpen, setIsCooperationModalOpen, editDstId } = props
  const [userList, setUserList] = useState<any[]>([])
  const curWorkFlow = useAppSelector(selectCurWorkflow)

  const handleOk = () => {
    setIsCooperationModalOpen(false)
  }

  const handleCancel = () => {
    setIsCooperationModalOpen(false)
  }

  const fetchUserList = async () => {
    const res = await apitableDeveloperUserList(editDstId)
    const list = []
    if (_.get(res, 'data.record')) {
      list.push(...res.data.record)
    }
    if (curWorkFlow) {
      const createUserInfo = _.get(curWorkFlow, 'createUserInfo')
      list.unshift({
        ...(createUserInfo as any),
        userInfo: createUserInfo,
        isCreator: true,
      })
    }
    setUserList(list)
  }

  useEffect(() => {
    isCooperationModalOpen && fetchUserList()
  }, [isCooperationModalOpen])
  return (
    <Modal
      title="成员列表"
      open={isCooperationModalOpen}
      width={566}
      onOk={handleOk}
      onCancel={handleCancel}
    >
      <UIROOT className={className}>
        <div className="list-header">
          <div>用户信息</div>
          <div style={{ marginRight: '48px' }}>权限</div>
        </div>
        <div className="list-content">
          {userList.map((item, index) => {
            return (
              <ListItem key={index} info={item} freshUserList={fetchUserList} />
            )
          })}
        </div>
      </UIROOT>
    </Modal>
  )
}

export default Cooperation
