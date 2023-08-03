import request from '../request'

const Api = {
    page: '/api/sys/omsOrder/page',
    list: '/api/sys/omsOrder/list',
    remove: '/api/sys/omsOrder/remove',
    removeBatch: '/api/sys/omsOrder/removeBatch',
    save: '/api/sys/omsOrder/save',
    edit: '/api/sys/omsOrder/edit',
}

/** 订单列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 订单列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 订单岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 订单岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 订单岗位 */
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
