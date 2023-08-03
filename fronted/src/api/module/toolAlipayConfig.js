import request from '../request'

const Api = {
    page: '/api/sys/toolAlipayConfig/page',
    list: '/api/sys/toolAlipayConfig/list',
    remove: '/api/sys/toolAlipayConfig/remove',
    removeBatch: '/api/sys/toolAlipayConfig/removeBatch',
    save: '/api/sys/toolAlipayConfig/save',
    edit: '/api/sys/toolAlipayConfig/edit',
}

/** 支付宝配置类列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 支付宝配置类列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 支付宝配置类岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 支付宝配置类岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 支付宝配置类岗位 */
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
