import { Component, useState, useMemo, memo, useEffect } from 'react'
import { Button, Tabs } from 'antd'
import styled from 'styled-components'
import TabOperation from '../components/Notify/TabOperation'
import NotifyHeader from '../components/Notify/NotifyHeader'
import NotifyCard from '../components/Notify/NotifyCard'
import Cooperation from '../components/Notify/Cooperation'
import { getInviteList } from '../api/apitable/ds-share'
import _ from 'lodash'
import { useAppSelector } from '../store/hooks'
import { selectUser } from '../store/globalSlice'
const UIROOT = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  background-color: #fff;
  border-radius: 4px;
  .notify-header {
    width: 100%;
  }
  .divider {
    border-bottom: 1px solid #e5e6eb;
  }
  .notify-card {
    margin-top: 12px;
  }
  .content {
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
`

const Notify = () => {
  const [isModalOpen, setIsModalOpen] = useState(false)
  const [inviteList, setInviteList] = useState<any[]>([])
  const [tabState, setTabState] = useState<'undo' | 'redo'>('undo')
  const user = useAppSelector(selectUser)
  const onTabChange = (key: string) => {
    setTabState(key as 'undo' | 'redo')
  };
  const fetchInviteList = async (options: any = {}) => {
    const res = await getInviteList(options)
    const list = _.get(res, 'data.record') || []
    setInviteList(list)
    console.log('fetchInviteList', list)
  }

  useEffect(() => {
    console.log('Notify useEffect', tabState)
    if(tabState === 'undo') {
      fetchInviteList()
    } else {
      fetchInviteList({ignoreMsg :1})
    }
  }, [tabState])

  return (
    <UIROOT className="notify">
      <Tabs
        className="notify-header"
        defaultActiveKey="1"
        centered
        onChange={onTabChange}
        tabBarExtraContent={
          <TabOperation {...{ isModalOpen, setIsModalOpen }} />
        }
        items={[
          {
            label: '未处理',
            key: 'undo',
          },
          {
            label: '已处理',
            key: 'done',
          },
        ]}
      />
      <div className="content">
        <NotifyHeader />
        {inviteList.length === 0 && <div>暂无邀请信息</div>}
        {inviteList.length > 0 &&
          inviteList.map((item, index) => {
            return (
              <NotifyCard
                className="notify-card divider"
                info={item}
                key={'invite_list_' + index}
                freshInviteList={fetchInviteList}
                curUser={user}
              />
            )
          })}
      </div>
      <Cooperation {...{ isModalOpen, setIsModalOpen }} />
    </UIROOT>
  )
}

export default Notify
