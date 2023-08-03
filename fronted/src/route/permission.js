import permissionRoutes from './module/main-routes'
import NProgress from "nprogress";
import store from "@/store";
import router from "@/route/index";
import { inCloudRoute } from "@/tools/router";
import _ from 'lodash';
/** 路由信息 */
export const generateRoute = (menus) => {
  // 2022年09月01日 new features: 注入 分类 和 文章
  // injectMockRoutes(menus)
  const userRoutes = menus.map(menu => {
    const { parent, icon, component, children = [], path, hidden = false, title, i18n, id } = menu
    const currentMenu = { path, component, hidden, parent, meta: { key: id, title, i18n, icon }, children: children.length === 0 ? [] : generateRoute(children) }
    if (children.length <= 0) {
      delete currentMenu.children
    }
    return currentMenu
  })
  return userRoutes
}

/** 权限信息 */
export const generatePower = (powers) => {
  const userPowers = powers.map(power => {
    return power.code
  })
  return userPowers;
}


export const setUserRouteComponent = routes => {
  routes.forEach(r => {
    r.component = r.parent == '0' ? permissionRoutes.Layout : permissionRoutes[r.component]
    if (r.children && r.children.length > 0) {
      setUserRouteComponent(r.children)
    }
  })
}

const setDocumentTitle = title => {
  document.title = `PRO - ${title}`
}

/**
 * 前置拦截器
 *
 * @param from
 * @param next
 */
export const permissionController = async (to, from, next) => {

  NProgress.start();
  const { meta: { title = '' } } = to
  setDocumentTitle(title)
  await store.dispatch('app/execCancelToken')

  if (!to.path.includes('login') && !localStorage.getItem('token') && !localStorage.getItem('token_key')) {
    next({ path: '/login' })

  } else {

    if (!router.getRoutes().map(it => it.path).includes(to.path)) {

      await store.dispatch('user/addRoute')
      await store.dispatch('user/addPower')

      const userRoutes = JSON.parse(JSON.stringify(store.getters.menu))

      const hasRoute = inCloudRoute(userRoutes, to.path)

      if (hasRoute) {
        setUserRouteComponent(userRoutes)
        userRoutes.forEach(r => { router.addRoute(r) })
        next(to.fullPath)
      } else {
        next('/error/404')
      }
    } else {
      next()
    }
  }
}
function getRoutesFlatArrRef (allRoutes) {
  const arrRef = [];
  const stack = [...allRoutes]
  while (stack.length > 0) {
    const item = stack.shift();
    arrRef.push(item)
    if (item.children && item.children.length > 0) {
      stack.unshift(...item.children)
    }
  }
  return arrRef
}
// 2022年09月01日 废弃
const injectMockRoutes = (allRoutes) => {
  const categoryMenu = {
    "createBy": null,
    "createTime": null,
    "updateBy": null,
    "updateTime": null,
    "deleted": false,
    "remark": null,
    "parent": "7",
    "children": [],
    "id": + new Date() + '', // TODO:
    "component": "category",
    "code": null,
    "path": "/system/category",
    "type": "1",
    "icon": "DashboardOutlined",
    "title": "分类管理",
    "sort": 1,
    "link": null,
    "i18n": "system-category",
    "hidden": null,
    "enable": true
  }
  const flatArr = getRoutesFlatArrRef(allRoutes)
  const systemMenuRef = _.find(flatArr, { "path": "/system" })
  if(!systemMenuRef){
    return
  }
  const postMenuIdx = _.findIndex(systemMenuRef.children, { "path": "/system/post" })
  systemMenuRef.children.splice(postMenuIdx+1,0, categoryMenu)
}
