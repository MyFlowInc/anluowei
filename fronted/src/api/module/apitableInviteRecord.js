import request from '../request'

const Api = {
    page: '/api/sys/apitableInviteRecord/page',
    list: '/api/sys/apitableInviteRecord/list',
    remove: '/api/sys/apitableInviteRecord/remove',
    removeBatch: '/api/sys/apitableInviteRecord/removeBatch',
    save: '/api/sys/apitableInviteRecord/save',
    edit: '/api/sys/apitableInviteRecord/edit',
}

/** 邀请同意记录列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 邀请同意记录列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 邀请同意记录岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 邀请同意记录岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 邀请同意记录岗位 */
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
