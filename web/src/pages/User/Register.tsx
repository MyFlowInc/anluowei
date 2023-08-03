import React, { useState } from 'react'
import { LockOutlined } from '@ant-design/icons'
import { Button, Checkbox, Form, Input, message } from 'antd'
import styled from 'styled-components'
import { useHistory } from 'react-router'
import { sendCaptcha, userRegister, userRegisterEmail } from '../../api/user'
import { Container, FormRoot, RegisterRoot } from './styles'
import { BeiAnUI } from '../../components/TabBar/BeiAn'
import { TopBarUI } from '../../components/TopBar/TopBar'
import { LogoUI2 } from '../../components/TopBar/Logo'

const Register: React.FC = () => {
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

  const checkRegister = async () => {
    try {
      const data = await form.validateFields([
        'email',
        'emailCode',
        'password',
        'confirm',
      ])
      const { username, password, email, emailCode } = data
      const temp = {
        email,
        code: emailCode,
        nickname: email,
        password: password,
      }
      const res = (await userRegisterEmail(temp)) as any
      console.log(111, res)
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
            content: '注册成功,请登录!',
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
          <div className="login_tilte">注册</div>
          <Form
            form={form}
            name="normal_register"
            className="register-form"
            initialValues={{ remember: true, prefix: '86' }}
          >
            {/* <Form.Item
          name="phone"
          rules={[
            { required: true, message: "请输入手机号!" },
            {
              pattern:
                /^1(3[0-9]|4[01456879]|5[0-3,5-9]|6[2567]|7[0-8]|8[0-9]|9[0-3,5-9])\d{8}$/,
              message: "请输入正确的手机号",
            },
          ]}
        >
          <Input addonBefore={prefixSelector} style={{ width: "100%" }} />
        </Form.Item> */}

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

            {/* <Form.Item
          name="username"
          rules={[{ required: true, message: "请输入用户名!" }]}
        >
          <Input
            prefix={<UserOutlined className="site-form-item-icon" />}
            placeholder="用户名"
          />
        </Form.Item> */}
            <Form.Item
              name="password"
              rules={[{ required: true, message: '请输入密码!' }]}
            >
              <Input
                prefix={<LockOutlined className="site-form-item-icon" />}
                type="password"
                placeholder="密码"
              />
            </Form.Item>

            <Form.Item
              name="confirm"
              dependencies={['password']}
              hasFeedback
              rules={[
                {
                  required: true,
                  message: '请二次确认你的密码!',
                },
                ({ getFieldValue }) => ({
                  validator(_, value) {
                    if (!value || getFieldValue('password') === value) {
                      return Promise.resolve()
                    }
                    return Promise.reject(new Error('两次密码不一致!'))
                  },
                }),
              ]}
            >
              <Input.Password
                prefix={<LockOutlined className="site-form-item-icon" />}
                placeholder="确认密码"
              />
            </Form.Item>

            <Form.Item>
              <Form.Item name="remember" valuePropName="checked" noStyle>
                <Checkbox>我已阅读并同意</Checkbox>
              </Form.Item>
              <a className="register-form-forgot" href="">
                《服务条款》、《隐私政策》
              </a>
            </Form.Item>

            <Form.Item>
              <Button
                onClick={checkRegister}
                type="primary"
                htmlType="submit"
                className="register-button"
              >
                注册
              </Button>
            </Form.Item>
          </Form>
        </FormRoot>
      </Container>

      <BeiAnUI />
    </RegisterRoot>
  )
}

export default Register
