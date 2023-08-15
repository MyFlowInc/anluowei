import React from 'react'
import { useHistory } from 'react-router'
import { useDispatch } from 'react-redux'
import { TopBarUI } from '../../components/TopBar/TopBar'
import { BeiAnUI } from '../../components/TabBar/BeiAn'
import styled from 'styled-components'

export const RootUI = styled.div`
  display: flex;
  flex-direction: column;
  background-color: #ffffff;
  height: 100%;

  .login-form {
    width: 100%;
    max-width: 328px;
  }

  .ant-form-item-control-input-content {
    display: flex;
    justify-content: space-between;
  }
  .login-button {
    width: 100%;
    background: #2845d4;
  }
  .login-form-forgot {
    display: inline;
    color: #1677ff;
  }
`

export const Container = styled.div`
  padding-top: 46px;
  padding-left: 10%;
  padding-right: 10%;
  align-items: center;
  width: 100%;
  background: #ffffff;
  display: flex;
  justify-content: center;
  flex: 1;
  overflow: overlay;
  .logo_2 {
    max-width: 40%;
    margin-right: 154px;
    flex: 1;
  }
  .triangle {
    border-width: 0px 0px 64px 64px;
  }

  @media (max-width: 800px) {
    .logo_2 {
      display: none;
    }
  }
`

const Login: React.FC = () => {
  const history = useHistory()
  const dispatch = useDispatch()

  return (
    <RootUI>
      <TopBarUI />
      <Container>
        <section className="bg-white dark:bg-gray-900 h-full">
          <div className="py-8 px-4 mx-auto max-w-screen-xl text-center lg:py-16 lg:px-12">
            <h1 className=" mb-16 text-4xl font-extrabold tracking-tight leading-none text-gray-900 md:text-5xl lg:text-6xl dark:text-white">
              面试邀请函
            </h1>
            <p className=" text-left mb-4 text-lg font-normal text-gray-500 lg:text-xl sm:px-16 xl:px-48 dark:text-gray-400">
              XX 先生/女士：
            </p>

            <p className=" text-left mb-4 text-lg font-normal text-gray-500 lg:text-xl sm:px-16 xl:px-48 dark:text-gray-400">
              您好！
              我司人力资源部已初审您的简历，经过初步沟通，认为您基本具备XXX岗位的任职资格，因此正式通知您来我公司参加面试。
            </p>
            {/* <p className=" text-left mb-4 text-lg font-normal text-gray-500 lg:text-xl sm:px-16 xl:px-48 dark:text-gray-400">
              面试时间：xxxxx
            </p>
            <p className=" text-left mb-4 text-lg font-normal text-gray-500 lg:text-xl sm:px-16 xl:px-48 dark:text-gray-400">
              面试地点：xxxxx
            </p>
            <p className=" text-left mb-4 text-lg font-normal text-gray-500 lg:text-xl sm:px-16 xl:px-48 dark:text-gray-400">
              联系人：xxxxx
            </p> */}
            <div className="flex flex-col mb-4 lg:mb-16 space-y-4 sm:flex-row sm:justify-center sm:space-y-0 sm:space-x-4">
              <a
                href="#"
                className="inline-flex justify-center items-center py-3 px-5 text-base font-medium text-center text-gray-900 rounded-lg border border-gray-300 hover:bg-gray-100 focus:ring-4 focus:ring-gray-100 dark:text-white dark:border-gray-700 dark:hover:bg-gray-700 dark:focus:ring-gray-800"
              >
                拒绝
              </a>
              <a
                href="#"
                className="inline-flex justify-center items-center py-3 px-5 text-base font-medium text-center text-white rounded-lg bg-primary-700 hover:bg-primary-800 focus:ring-4 focus:ring-primary-300 dark:focus:ring-primary-900"
              >
                接受
              </a>
            </div>
          </div>
        </section>
      </Container>

      <BeiAnUI />
    </RootUI>
  )
}

export default Login
