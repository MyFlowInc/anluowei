import request from '../request'

const Api = {
    page: '/api/sys/apitableDatasheetChangeset/page',
    list: '/api/sys/apitableDatasheetChangeset/list',
    remove: '/api/sys/apitableDatasheetChangeset/remove',
    removeBatch: '/api/sys/apitableDatasheetChangeset/removeBatch',
    save: '/api/sys/apitableDatasheetChangeset/save',
    edit: '/api/sys/apitableDatasheetChangeset/edit',
}

/** 变更集收集列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 变更集收集列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 变更集收集岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 变更集收集岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 变更集收集岗位 */
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
