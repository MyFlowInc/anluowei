import request from '../request'

const Api = {
    page: '/api/sys/omsOrderItem/page',
    list: '/api/sys/omsOrderItem/list',
    remove: '/api/sys/omsOrderItem/remove',
    removeBatch: '/api/sys/omsOrderItem/removeBatch',
    save: '/api/sys/omsOrderItem/save',
    edit: '/api/sys/omsOrderItem/edit',
}

/** 订单中所包含的商品列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 订单中所包含的商品列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 订单中所包含的商品岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 订单中所包含的商品岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 订单中所包含的商品岗位 */
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
