import request from '../request'

const Api = {
    page: '/api/sys/apitableDeveloper/page',
    list: '/api/sys/apitableDeveloper/list',
    remove: '/api/sys/apitableDeveloper/remove',
    removeBatch: '/api/sys/apitableDeveloper/removeBatch',
    save: '/api/sys/apitableDeveloper/save',
    edit: '/api/sys/apitableDeveloper/edit',
}

/** 格协作者列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 格协作者列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 格协作者岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 格协作者岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 格协作者岗位 */
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
