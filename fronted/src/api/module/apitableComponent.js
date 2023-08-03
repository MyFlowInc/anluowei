import request from '../request'

const Api = {
    page: '/api/sys/apitableComponent/page',
    list: '/api/sys/apitableComponent/list',
    remove: '/api/sys/apitableComponent/remove',
    removeBatch: '/api/sys/apitableComponent/removeBatch',
    save: '/api/sys/apitableComponent/save',
    edit: '/api/sys/apitableComponent/edit',
}

/** 格组件列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 格组件列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 格组件岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 格组件岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 格组件岗位 */
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
