import { useEffect, useState } from 'react'
import { Button, Tabs } from 'antd'
import styled from 'styled-components'
import AvatarSetting from '../components/Setting/AvatarSetting'
import NameSetting from '../components/Setting/NameSetting'
import PhoneSetting from '../components/Setting/PhoneSetting'
import PwdSetting from '../components/Setting/PwdSetting'
import AccountType from '../components/Setting/AccountType'
// import SubScription from '../components/Setting/SubScription'
import { useHistory } from 'react-router'
import { logout } from '../api/user'
import { freshGradeList } from '../store/globalSlice'
import { useAppDispatch } from '../store/hooks'

const UIROOT = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  /* justify-content: center; */
  width: 100%;
  height: 100%;
  min-height: 700px;
  overflow: auto;
  background-color: #fff;
  border-radius: 4px;
  .divider {
    border-bottom: 1px solid #e5e6eb;
  }
  .buy-card {
    margin-top: 32px;
  }
  .avator-setting {
    margin-top: 40px;
  }
  .name-setting {
    margin-top: 20px;
  }
  .phone-setting {
    margin-top: 20px;
  }
  .pwd-setting {
    margin-top: 20px;
  }
  .account-setting {
    margin-top: 20px;
  }
  .login-out {
    margin-top: 20px;
    color: #2845d4 !important;
  }
  .ant-btn-default:not(:disabled):hover {
    color: #2845d4 !important;
  }
`

const Setting = () => {
  // const [isModalOpen, setIsModalOpen] = useState(false)
  const history = useHistory()
  const dispatch = useAppDispatch()

  const logoutHandler = async () => {
    await logout()
    history.push('/login')
  }

  useEffect(() => {
    dispatch(freshGradeList())
  }, [])

  return (
    <UIROOT className="setting">
      <AvatarSetting className="avator-setting divider" />
      <NameSetting className="name-setting divider" />
      <AccountType className="name-setting divider" />
      <PhoneSetting className="phone-setting divider" />
      <PwdSetting className="pwd-setting divider" />
    
      <Button className="login-out" type="default" onClick={logoutHandler}>
        退出登录
      </Button>
      {/* 购买modal */}
      {/* <SubScription {...{ isModalOpen, setIsModalOpen }} /> */}
    </UIROOT>
  )
}

export default Setting
