import styled from 'styled-components'
import DefaultAvatarSvg from './assets/avatar.svg'
import { IonIcon } from '@ionic/react'
import { logOutOutline } from 'ionicons/icons'
import { Menu, Modal } from 'antd'
import { useState } from 'react'
import { ExclamationCircleFilled } from '@ant-design/icons'
import { useHistory } from 'react-router-dom'
import { useAppSelector } from '../../store/hooks'
import { selectUser } from '../../store/globalSlice'
export const UserRoot = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 100%;
  /* padding: 0px 16px; */
  .user-left {
    display: flex;
    align-items: center;

    .user-info {
      margin-left: 8px;
      .user-name {
        font-size: 14px;
        font-weight: 500;
        line-height: 20px;
        letter-spacing: 0px;
      }
      .user-level {
        font-size: 10px;
      }
    }
  }
  .user-log-out {
    margin-left: 16px;
    .log-out-logo {
      width: 18px;
      height: 18px;
    }
  }
`

const { confirm } = Modal

const showConfirm = ({ history }: any) => {
  confirm({
    title: '确认要退出登录吗?',
    icon: <ExclamationCircleFilled />,
    okText: '确认',
    cancelText: '取消',
    onOk() {
      history.push('/login')
    },
    onCancel() {
      console.log('Cancel')
    },
  })
}

function UserCard() {
  const user = useAppSelector(selectUser)

  return (
    <Menu
      mode="inline"
      selectable={false}
      items={[
        {
          key: 'hiflowLogo',
          icon: (
            <img
              src={user.avatar || DefaultAvatarSvg}
              className="user-logo"
              style={{ width: '36px', height: '36px', borderRadius: '100px' }}
            />
          ),
          label: (
            <UserRoot className="user">
              <div className="user-left">
                <div className="user-info">
                  <div className="user-name">{user.nickname}</div>
                </div>
              </div>
              {/* <div
                className="user-log-out"
                onClick={() => {
                  showConfirm({ history })
                }}
              >
                <IonIcon
                  className="log-out-logo"
                  ios={logOutOutline}
                  md={logOutOutline}
                />
              </div> */}
            </UserRoot>
          ),
        },
      ]}
    />
  )
}

export default UserCard
