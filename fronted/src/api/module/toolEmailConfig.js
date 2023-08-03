import request from '../request'

const Api = {
    page: '/api/sys/toolEmailConfig/page',
    list: '/api/sys/toolEmailConfig/list',
    remove: '/api/sys/toolEmailConfig/remove',
    removeBatch: '/api/sys/toolEmailConfig/removeBatch',
    save: '/api/sys/toolEmailConfig/save',
    edit: '/api/sys/toolEmailConfig/edit',
}

/** 邮箱配置列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 邮箱配置列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 邮箱配置岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 邮箱配置岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 邮箱配置岗位 */
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
