import React, { useEffect } from 'react'
import { LockOutlined, UserOutlined } from '@ant-design/icons'
import { Button, Checkbox, Form, Input, message } from 'antd'
import { useHistory } from 'react-router'
import { userLoginmail, userProfile } from '../../api/user'
import { useDispatch } from 'react-redux'
import { loginSuccess } from '../../store/globalSlice'
import { TopBarUI } from '../../components/TopBar/TopBar'
import { Container, FormRoot, LoginRoot } from './styles'
import { BeiAnUI } from '../../components/TabBar/BeiAn'
import { LogoUI2 } from '../../components/TopBar/Logo'

const Login: React.FC = () => {
  const history = useHistory()
  const dispatch = useDispatch()
  const [form] = Form.useForm()
  const [messageApi, contextHolder] = message.useMessage()
  useEffect(() => {
    const email = localStorage.getItem('user_email')
    if (form && email) {
      form.setFieldValue('email', email || '')
    }
  }, [form])

  const checkLogin = () => {
    form
      .validateFields()
      .then(async () => {
        const data = form.getFieldsValue(['email', 'password'])
        try {
          const response = (await userLoginmail(data)) as any
          if (response.code !== 200) {
            throw new Error(response.msg)
          }

          const { token, tokenKey } = response.data
          if (token && tokenKey) {
            localStorage.setItem('Authorization', token)
            localStorage.setItem('Authorization-key', tokenKey)
            localStorage.setItem('user_email', data.email)

            const res = await userProfile()
            console.log('userProfile', res)
            dispatch(loginSuccess(res.data))
            messageApi
              .open({
                type: 'success',
                content: '登录成功!',
                duration: 1,
              })
              .then(() => {
                history.push('/dashboard')
              })
          }
        } catch (e: any) {
          console.log(e)
          messageApi.open({
            type: 'error',
            content: '登录失败,' + e.message,
            duration: 1,
          })
        }
      })
      .catch(() => {
        return
      })
  }
  const jump2Register = () => {
    history.push('/register')
  }
  const onFinish = (values: any) => {
    console.log('Received values of form: ', values)
  }
  const onFinishFailed = () => {
    console.error('Submit failed!')
  }

  return (
    <LoginRoot>
      {contextHolder}
      <TopBarUI />

      <Container>
        {/* <LogoUI2 className="logo_2" /> */}
        <FormRoot>
          <div className="login_tilte">登录</div>
          <Form
            form={form}
            name="normal_login"
            className="login-form"
            initialValues={{ remember: true }}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
            autoComplete="off"
          >
            <Form.Item
              name="email"
              rules={[{ required: true, message: '用户名不能为空!' }]}
            >
              <Input
                prefix={<UserOutlined className="site-form-item-icon" />}
                placeholder="请输入邮箱地址"
              />
            </Form.Item>
            <Form.Item
              name="password"
              rules={[{ required: true, message: '密码不能为空!' }]}
            >
              <Input
                prefix={<LockOutlined className="site-form-item-icon" />}
                type="password"
                placeholder="请输入密码"
              />
            </Form.Item>
            <Form.Item className="add-layout">
              <Form.Item name="remember" valuePropName="checked" noStyle>
                <Checkbox>自动登录</Checkbox>
              </Form.Item>

              <div className="login-form-forgot">忘记密码</div>
            </Form.Item>

            <Form.Item>
              <Button
                onClick={checkLogin}
                type="primary"
                htmlType="submit"
                className="login-button"
              >
                登录
              </Button>
            </Form.Item>
            {/* <Form.Item>
              <div>
                暂无账号,
                <div className="login-form-forgot" onClick={jump2Register}>
                  <> </>点击注册
                </div>
              </div>
            </Form.Item> */}
          </Form>
        </FormRoot>
      </Container>

      <BeiAnUI />
    </LoginRoot>
  )
}

export default Login
