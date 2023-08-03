import request from '../request'

const Api = {
    page: '/api/sys/omsOrderReturnApply/page',
    list: '/api/sys/omsOrderReturnApply/list',
    remove: '/api/sys/omsOrderReturnApply/remove',
    removeBatch: '/api/sys/omsOrderReturnApply/removeBatch',
    save: '/api/sys/omsOrderReturnApply/save',
    edit: '/api/sys/omsOrderReturnApply/edit',
}

/** 订单退货申请列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 订单退货申请列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 订单退货申请岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 订单退货申请岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 订单退货申请岗位 */
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
