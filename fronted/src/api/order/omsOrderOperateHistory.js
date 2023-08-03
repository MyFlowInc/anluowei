import request from '../request'

const Api = {
    page: '/api/sys/omsOrderOperateHistory/page',
    list: '/api/sys/omsOrderOperateHistory/list',
    remove: '/api/sys/omsOrderOperateHistory/remove',
    removeBatch: '/api/sys/omsOrderOperateHistory/removeBatch',
    save: '/api/sys/omsOrderOperateHistory/save',
    edit: '/api/sys/omsOrderOperateHistory/edit',
}

/** 订单操作历史记录列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 订单操作历史记录列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 订单操作历史记录岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 订单操作历史记录岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 订单操作历史记录岗位 */
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
