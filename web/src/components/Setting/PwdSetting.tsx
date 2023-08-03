import { Component, useState, useMemo, memo } from 'react'
import { Button, Form, Input, Tabs } from 'antd'
import styled from 'styled-components'
import Svg1 from './assets/avatar.svg'
import UpdateModal from './UpdateModal'
import { useAppSelector } from '../../store/hooks'
import { selectUser } from '../../store/globalSlice'
import { pwdUpdate, userUpdate } from '../../api/user'

const UIROOT = styled.div`
  display: flex;
  width: fit-content;
  align-items: flex-end;
  background-color: #fff;
  border-radius: 4px;
  overflow: hidden;
  .container {
    width: 596px;
    height: 68px;
    border-radius: 4px;
    display: flex;
    position: relative;
    justify-content: space-between;
    align-items: center;
  }

  .img-container {
    position: relative;
  }
  .left {
    display: flex;
    .word {
      display: flex;
      flex-direction: column;
      justify-content: center;
    }
    .title {
      font-size: 14px;
      font-weight: 500;
      line-height: 20px;
      letter-spacing: 0px;
      color: #000000;
    }
    .content {
      font-size: 14px;
      font-weight: normal;
      line-height: 20px;
      letter-spacing: 0px;
      color: #666666;
    }
  }
`
const PwdSetting = (props: any) => {
  const { className } = props
  const user = useAppSelector(selectUser)
  const [open, setOpen] = useState(false)

  const clickHandler = () => {
    setOpen(true)
  }

  const resetPwdHandler = async (data: any) => {
    await pwdUpdate({ oldPassword: data.oldPassword, newPassword: data.newPassword, userId: user.id  })
  }

  const PwdItem = () => (
    <>
      <Form.Item
        label="旧密码"
        name="oldPassword"
        rules={[{ required: true, message: '请输入值' }]}
      >
        <Input />
      </Form.Item>
      <Form.Item
        label="新密码"
        name="newPassword"
        rules={[{ required: true, message: '请输入值' }]}
      >
        <Input />
      </Form.Item>
    </>
  )
  return (
    <UIROOT className={className}>
      <div className="container">
        <div className="left">
          <div className="word">
            <div className="title">修改密码</div>
            <div className="content">设置独一无二的密码保护你的账户</div>
          </div>
        </div>

        <Button type="default" onClick={clickHandler}>
          修改密码
        </Button>
      </div>
      <UpdateModal
        {...{ open, setOpen }}
        title="修改密码"
        updateKey="oldPassword,newPassword"
        label="请输入新密码"
        renderItem={PwdItem}
        updateApi={resetPwdHandler}
      />
    </UIROOT>
  )
}

export default PwdSetting
