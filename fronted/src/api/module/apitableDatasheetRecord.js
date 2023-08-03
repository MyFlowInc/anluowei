import request from '../request'

const Api = {
    page: '/api/sys/apitableDatasheetRecord/page',
    list: '/api/sys/apitableDatasheetRecord/list',
    remove: '/api/sys/apitableDatasheetRecord/remove',
    removeBatch: '/api/sys/apitableDatasheetRecord/removeBatch',
    save: '/api/sys/apitableDatasheetRecord/save',
    edit: '/api/sys/apitableDatasheetRecord/edit',
}

/** 数据单记录列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 数据单记录列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 数据单记录岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 数据单记录岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 数据单记录岗位 */
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
