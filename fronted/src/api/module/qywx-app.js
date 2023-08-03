import request from '../request'

const Api = {
    page: '/api/wx/app/page',
    list: '/api/wx/app/page',
    edit: '/api/wx/app/edit',
    save: '/api/wx/app/save',
    remove: '/api/wx/app/remove',
    removeBatch: '/api/wx/app/removeBatch'
}

/** 文件列表 */
export const save = data => {
    return request.request({
        url: Api.save,
        params: data,
        method: 'POST'
    })
}

/** 文件列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}
/** 文件列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}
/** 文件上传 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        params: data,
        method: 'PUT'
    })
}
/** 文件上传 */
export const remove = data => {
    return request.request({
        url: Api.remove,
        params: data,
        method: 'DELETE'
    })
}
/** 文件上传 */
export const removeBatch = data => {
    return request.request({
        url: Api.remove,
        params: data,
        method: 'DELETE'
    })
}