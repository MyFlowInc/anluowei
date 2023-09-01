import React, { useEffect } from 'react'
import { useHistory, useLocation } from 'react-router'
import { useDispatch } from 'react-redux'
import { TopBarUI } from '../../components/TopBar/TopBar'
import { BeiAnUI } from '../../components/TabBar/BeiAn'
import styled from 'styled-components'
import { getRecord, setRecordValue } from '../../api/apitable/ds-record'

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

const AcceptUI = () => {
  return (
    <div className="bg-gray-100">
      <div className="bg-white p-6  md:mx-auto">
        <div className="text-center">
          <h3 className="md:text-2xl text-base text-green-600 font-semibold text-center">
            您已同意面试邀请
          </h3>
          <p className="text-gray-600 my-2">
            请耐心等待HR与您联系，如有疑问请联系HR。
          </p>
          <p> 祝您生活愉快! </p>
        </div>
      </div>
    </div>
  )
}

const RejectUI = () => {
  return (
    <div className="bg-gray-100">
      <div className="bg-white p-6  md:mx-auto">
        <div className="text-center">
          <h3 className="md:text-2xl text-base text-red-600 font-semibold text-center">
            您已拒绝面试邀请。
          </h3>
          <p className="text-gray-600 my-2">
            感谢您对我们公司的关注，祝您生活愉快!
          </p>
        </div>
      </div>
    </div>
  )
}
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

const Invite: React.FC = () => {
  const history = useHistory()
  const location = useLocation()
  const recordId = new URLSearchParams(location.search).get('recordId')
  const inviteFieldId =
    new URLSearchParams(location.search).get('inviteFieldId') || ''
  const nameFieldId =
    new URLSearchParams(location.search).get('nameFieldId') || ''
  const [name, setName] = React.useState('')
  const [inviteStatus, setInviteStatus] = React.useState('')

  const fetchInviteState = async () => {
    try {
      const res = await getRecord({
        recordId: recordId,
      })
      const record = JSON.parse(res.data.data.data)
      console.log('res ', record)
      setName(record[nameFieldId])
      setInviteStatus(record[inviteFieldId])
    } catch (error) {
      console.log('error', error)
      history.push('/invite_error')
    }
  }
  const freshInviteState = async () => {
    const res = await getRecord({
      recordId: recordId,
    })
    const record = JSON.parse(res.data.data.data)
    console.log('res ', record)
    setName(record[nameFieldId])
    setInviteStatus(record[inviteFieldId])
  }

  useEffect(() => {
    if (!recordId || !inviteFieldId || !nameFieldId) {
      history.push('/invite_error')
    }
  }, [])

  useEffect(() => {
    fetchInviteState()
  }, [])

  const rejectHandle = async () => {
    console.log('rejectHandle')
    try {
      await setRecordValue({
        recordId: recordId,
        filedKey: inviteFieldId,
        filedValue: '已拒绝',
      })
      await freshInviteState()
    } catch (error) {
      console.log('error', error)
    }
  }

  const acceptHandle = async () => {
    console.log('acceptHandle')
    try {
      await setRecordValue({
        recordId: recordId,
        filedKey: inviteFieldId,
        filedValue: '已同意',
      })
      await freshInviteState()
    } catch (error) {
      console.log('error', error)
    }
  }

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
              {name} 先生/女士：
            </p>

            <p className=" text-left mb-4 text-lg font-normal text-gray-500 lg:text-xl sm:px-16 xl:px-48 dark:text-gray-400">
              您好！
              我司人力资源部已初审您的简历，经过初步沟通，认为您基本具备XXX岗位的任职资格，因此正式通知您来我公司参加面试。
            </p>
            <div className="flex flex-col mb-4 lg:mb-16 space-y-4 sm:flex-row sm:justify-center sm:space-y-0 sm:space-x-4">
              {inviteStatus === '已同意' && AcceptUI()}
              {inviteStatus === '已拒绝' && RejectUI()}
              {['未邀请', '未接受'].includes(inviteStatus) && (
                <>
                  <span
                    onClick={rejectHandle}
                    className="inline-flex justify-center items-center py-3 px-5 text-base font-medium text-center text-gray-900 rounded-lg border border-gray-300 hover:bg-gray-100 focus:ring-4 focus:ring-gray-100 dark:text-white dark:border-gray-700 dark:hover:bg-gray-700 dark:focus:ring-gray-800"
                  >
                    拒绝
                  </span>
                  <span
                    onClick={acceptHandle}
                    className="inline-flex justify-center items-center py-3 px-5 text-base font-medium text-center text-white rounded-lg bg-primary-700 hover:bg-primary-800 focus:ring-4 focus:ring-primary-300 dark:focus:ring-primary-900"
                  >
                    接受
                  </span>
                </>
              )}
            </div>
          </div>
        </section>
      </Container>

      <BeiAnUI />
    </RootUI>
  )
}

export default Invite
