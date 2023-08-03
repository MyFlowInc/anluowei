import request from '../request'

const Api = {
  page: '/api/sys/sysCaptcha/page',
  list: '/api/sys/sysCaptcha/list',
  remove: '/api/sys/sysCaptcha/remove',
  removeBatch: '/api/sys/sysCaptcha/removeBatch',
  save: '/api/sys/sysCaptcha/save',
  edit: '/api/sys/sysCaptcha/edit',
  send: '/api/sys/sysCaptcha/send',
}

/** 【请填写功能名称】列表 */
export const page = data => {
  return request.request({
    url: Api.page,
    params: data,
    method: 'GET'
  })
}

/** 【请填写功能名称】列表 */
export const list = data => {
  return request.request({
    url: Api.list,
    params: data,
    method: 'GET'
  })
}

/** 【请填写功能名称】岗位 */
export const save = data => {
  return request.request({
    url: Api.save,
    data: data,
    method: 'POST'
  })
}

/** 【请填写功能名称】岗位 */
export const send = data => {
  return request.request({
    url: Api.send,
    data: data,
    method: 'POST'
  })
}

/** 【请填写功能名称】岗位 */
export const edit = data => {
  return request.request({
    url: Api.edit,
    data: data,
    method: 'PUT'
  })
}

/** 【请填写功能名称】岗位 */
export const remove = data => {
  return request.request({
    url: Api.remove,
    params: data,
    method: 'DELETE'
  })
}

/** 批量删除 */
export const removeBatch = data => {
  return request.request({
    url: Api.removeBatch,
    params: data,
    method: 'DELETE'
  })
}
