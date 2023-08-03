import request from '../request'

const Api = {
    page: '/api/sys/apitableDatasheet/page',
    list: '/api/sys/apitableDatasheet/list',
    remove: '/api/sys/apitableDatasheet/remove',
    removeBatch: '/api/sys/apitableDatasheet/removeBatch',
    save: '/api/sys/apitableDatasheet/save',
    edit: '/api/sys/apitableDatasheet/edit',
}

/** 数据格列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 数据格列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 数据格岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 数据格岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 数据格岗位 */
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
