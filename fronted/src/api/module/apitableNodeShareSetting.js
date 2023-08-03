import request from '../request'

const Api = {
    page: '/api/sys/apitableNodeShareSetting/page',
    list: '/api/sys/apitableNodeShareSetting/list',
    remove: '/api/sys/apitableNodeShareSetting/remove',
    removeBatch: '/api/sys/apitableNodeShareSetting/removeBatch',
    save: '/api/sys/apitableNodeShareSetting/save',
    edit: '/api/sys/apitableNodeShareSetting/edit',
}

/** 格基础设置列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 格基础设置列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 格基础设置岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 格基础设置岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 格基础设置岗位 */
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
