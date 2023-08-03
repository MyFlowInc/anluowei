import request from '../request'

const Api = {
    page: '/api/sys/apitableDatasheetMeta/page',
    list: '/api/sys/apitableDatasheetMeta/list',
    remove: '/api/sys/apitableDatasheetMeta/remove',
    removeBatch: '/api/sys/apitableDatasheetMeta/removeBatch',
    save: '/api/sys/apitableDatasheetMeta/save',
    edit: '/api/sys/apitableDatasheetMeta/edit',
}

/** 数据元数据列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 数据元数据列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 数据元数据岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 数据元数据岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 数据元数据岗位 */
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
