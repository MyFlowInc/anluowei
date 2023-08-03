import { useEffect } from 'react'
import DashboardContainer from '../../components/Dashboard/DashboardContainer'
import { User, selectUser } from '../../store/globalSlice'
import { useAppSelector } from '../../store/hooks'
import { createWebSocket } from '../../api/apitable/room-server'
import { selectCurFlowDstId } from '../../store/workflowSlice'


const Page: React.FC = () => {
  const curDstId = useAppSelector(selectCurFlowDstId)
  const user = useAppSelector(selectUser)

  useEffect(() => {
    console.log('Dashboard 初始化')
    
    if (window.ws) {
      window.ws.close() // 主动close掉
      console.log('清除上一个client 连接...')
    }
    if (curDstId && user) {
      console.log('启用新的client 连接...')
      createWebSocket(user, curDstId)
    }
    return () => {
      console.log('Dashboard 销毁')
      if (window.ws) {
        window.ws.close() // 主动close掉
        console.log('client 连接已关闭...')
      }
    }
  }, [curDstId])

  return <DashboardContainer />
}

export default Page
