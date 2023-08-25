import React, { useState } from 'react'
import { LockOutlined } from '@ant-design/icons'
import { Button, Form, Input, message } from 'antd'
import { useHistory } from 'react-router'
import { resetPwd, sendCaptcha } from '../../api/user'
import { Container, FormRoot, RegisterRoot } from './styles'
import { BeiAnUI } from '../../components/TabBar/BeiAn'
import { TopBarUI } from '../../components/TopBar/TopBar'
import { LogoUI2 } from '../../components/TopBar/Logo'

const ResetPwd: React.FC = () => {
  const [form] = Form.useForm()
  const [messageApi, contextHolder] = message.useMessage()
  const history = useHistory()
  const [isShowCode, setIsShowCode] = useState<boolean>(false)
  const [time, setTime] = useState(60)

  // 发送邮箱验证码
  const sendEmail = async () => {
    const fileds = await form.validateFields(['account', 'email'])
    console.log('validateFields', fileds)
    const { email } = fileds
    if (!email) {
      return
    }

    if (isShowCode) {
      // 倒计时未结束,不能重复点击
      return
    }
    setIsShowCode(true)
    // 倒计时
    const active = setInterval(() => {
      setTime((preSecond) => {
        if (preSecond <= 1) {
          setIsShowCode(false)
          clearInterval(active)
          // 重置秒数
          return 60
        }
        return preSecond - 1
      })
    }, 1000)

    const res = (await sendCaptcha({ email })) as any
    console.log(1111, res)
    if (res.code === 200) {
      messageApi.open({
        type: 'success',
        content: '发送成功,请填写收到的验证码',
        duration: 1,
      })
    }
    if (res.code === 3001) {
      messageApi.open({
        type: 'error',
        content: res.smg,
        duration: 1,
      })
    }
  }

  const resetPassward = async () => {
    try {
      const data = await form.validateFields(['email', 'emailCode', 'password'])
      const { password, email, emailCode } = data
      const temp = {
        email,
        code: emailCode,
        password: password,
      }
      const res = (await resetPwd(temp)) as any
      console.log('res', res)
      if (res.code === 500) {
        messageApi.open({
          type: 'error',
          content: res.msg,
          duration: 1,
        })
        return
      }
      if (res.code === 200) {
        messageApi
          .open({
            type: 'success',
            content: '重置成功,请登录!',
            duration: 1,
          })
          .then(() => {
            history.push('/login')
          })
      }
    } catch (e) {
      messageApi.open({
        type: 'error',
        content: '输入错误!',
        duration: 1,
      })
    }
  }
  return (
    <RegisterRoot>
      {contextHolder}
      <TopBarUI />

      <Container>
        <LogoUI2 className="logo_2" />
        <FormRoot>
          {/* <Triangle className="triangle" /> */}
          <div className="login_tilte">重置密码</div>
          <Form
            form={form}
            name="normal_register"
            className="register-form"
            initialValues={{ remember: true, prefix: '86' }}
          >
            <Form.Item
              name="email"
              rules={[
                {
                  type: 'email',
                  message: '邮箱格式不正确!',
                },
                {
                  required: true,
                  message: 'Please input your E-mail!',
                },
              ]}
            >
              <Input placeholder="请输入邮箱地址" />
            </Form.Item>
            <Form.Item
              name="emailCode"
              rules={[{ required: true, message: '请输入邮箱验证码！' }]}
            >
              <Input
                placeholder="获取验证码"
                maxLength={6}
                suffix={
                  <a onClick={() => sendEmail()}>
                    {isShowCode ? `${time}秒后重新发送` : '发送验证码'}
                  </a>
                }
              />
            </Form.Item>

            <Form.Item
              name="password"
              rules={[{ required: true, message: '请输入新密码!' }]}
            >
              <Input
                prefix={<LockOutlined className="site-form-item-icon" />}
                type="password"
                placeholder="新密码"
              />
            </Form.Item>

            <Form.Item>
              <Button
                onClick={resetPassward}
                type="primary"
                htmlType="submit"
                className="register-button"
              >
                确认重置
              </Button>
            </Form.Item>
          </Form>
        </FormRoot>
      </Container>

      <BeiAnUI />
    </RegisterRoot>
  )
}

export default ResetPwd
