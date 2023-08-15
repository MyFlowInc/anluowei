import { Route, Switch, useHistory, useLocation } from 'react-router'
import FlowMenu, { MenuRef } from '../components/Menu/FlowMenu'
import Dashboard from '../pages/Dashboard'
import FlowItemSetting from '../pages/FlowItemSetting'
import { useAppSelector } from '../store/hooks'
import {
  selectCollapsed,
  selectIsShowTour,
  selectUser,
  setIsShowTour,
  setUser,
} from '../store/globalSlice'
import _ from 'lodash'
import { useEffect, useRef, useState } from 'react'
import { userProfile } from '../api/user'
import { useDispatch } from 'react-redux'
import { Spin, Tour } from 'antd'
import { delay } from '../util/delay'
import {
  setWorkflowList,
  WorkFlowInfo,
  updateCurFlowDstId,
} from '../store/workflowSlice'

import { MenuButton } from '../BaseUI/MobileMenuButton'
import { LoadingRoot, RouterContainer } from './style'

import { Layout, theme } from 'antd'
import AppHeader from '../components/layout/AppHeader'
import Notify from '../pages/Notify'
import Setting from '../pages/Setting'
import TourContext from '../context/tour'
 
import { fetchAllWorkflowList } from '../controller/dsTable'

const { Sider, Content } = Layout

const DashboardRouterOutlet: React.FC = () => {
  const dispatch = useDispatch()
  const user = useAppSelector(selectUser)
  const isShowTour = useAppSelector(selectIsShowTour)
  const isEmpty = _.isEmpty(user)
  const [loading, setLoading] = useState(isEmpty)
  const location = useLocation()
  const history = useHistory()
  const menuRef = useRef<MenuRef | null>(null)
  const [showMenu, setShowMenu] = useState<'block' | 'none'>('none')
  const {
    token: { colorBgContainer },
  } = theme.useToken()
  const collapsed = useAppSelector(selectCollapsed)
  const [tourRefs, setTourRefs] = useState([])
  const [open, setOpen] = useState<boolean>(false)
  const getSteps: () => any = () => {
    let res: any = []
    tourRefs.map((item: any) => {
      if (item.current && item.current.id === 'add_flow_menu') {
        res.push({
          target: () => item.current,
          title: '点击这里新建项目',
          description: '每个项目都是独立的哦',
          placement: 'right',
        })
      }
      if (item.current && item.current.id === 'add_flow_item') {
        res.push({
          target: () => item.current,
          title: '点这里开始录入工单',
          description: '完全的自定义，支持数十种数据类型',
          placement: 'right',
        })
      }
    })

    return res
  }
  const collapseHandle = () => {
    console.log('collapseHandle', menuRef)
    menuRef.current?.hideMenuHandle()
    setShowMenu('none')
  }
  const expandHandle = () => {
    console.log('expandHandle', menuRef)
    menuRef.current?.showMenuHandle()
    setShowMenu('block')
  }
  const getFlowList = async () => {
    try {
      const list = await fetchAllWorkflowList()
      dispatch(setWorkflowList(list))
      if (list.length > 0) {
        const item0 = list[0]
        dispatch(updateCurFlowDstId(item0.dstId))
        history.push(item0.url)
      }
    } catch (error) {
      console.log('error', error)
      dispatch(setWorkflowList([]))
    } finally{
      setLoading(false)
    }
  }
  const checkUser = async () => {
    await delay()
    let userInfo = user
    if (isEmpty) {
      const response = await userProfile()
      dispatch(setUser(response.data))
      userInfo = response.data
    }
    getFlowList()
  }
  // 菜单初始化
  useEffect(() => {
    checkUser()
  }, [])

  useEffect(() => {
    // console.log('tourRefs', tourRefs)
    if (isShowTour) {
      return
    }
    const handle = async () => {
      await delay(1000)
      const isShowTour = localStorage.getItem('isShowTour') 
      if (isShowTour !== '1' &&  tourRefs.length > 0) {
        setOpen(true)
        localStorage.setItem('isShowTour', '1')
        dispatch(setIsShowTour(true))
      }
    }
    handle()
  }, [tourRefs])

  if (loading) {
    // console.log('render spin')
    return (
      <LoadingRoot>
        <Spin />
      </LoadingRoot>
    )
  }

  return (
    <RouterContainer display={showMenu || 'none'} className="router-container">
      <TourContext.Provider value={{ tourRefs, setTourRefs }}>
        <Layout>
          <Sider
            theme="light"
            className="flow-sider"
            width={210}
            trigger={null}
            collapsible
            collapsed={collapsed}
          >
            <FlowMenu ref={menuRef} path={location.pathname} />
          </Sider>
          <Layout className="site-layout">
            <AppHeader />
            <Content
              style={{
                minHeight: 280,
                display: 'flex',
                background: colorBgContainer,
                flex: 1,
              }}
            >
              <div className="router-content">
                <MenuButton
                  collapseHandle={collapseHandle}
                  expandHandle={expandHandle}
                />
                <Switch>
                  <Route path="/dashboard/workflow-view/:dstId" exact={true}>
                    <Dashboard />
                  </Route>
                  {/* <Route path="/dashboard/workflow-add" exact={true}>
                    <FlowItemSetting />
                  </Route> */}
                  <Route path="/dashboard/workflow-edit/:dstId" exact={true}>
                    <FlowItemSetting />
                  </Route>
                  <Route path="/dashboard/notification" exact={true}>
                    <Notify />
                  </Route>
                  <Route path="/dashboard/setting" exact={true}>
                    <Setting />
                  </Route>
                </Switch>
              </div>
            </Content>
          </Layout>
        </Layout>
      </TourContext.Provider>
      <Tour open={open} onClose={() => setOpen(false)} steps={getSteps()} />
    </RouterContainer>
  )
}

export default DashboardRouterOutlet
