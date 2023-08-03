import request from '../request'

const Api = {
    page: '/api/sys/sysUserGrade/page',
    list: '/api/sys/sysUserGrade/list',
    remove: '/api/sys/sysUserGrade/remove',
    removeBatch: '/api/sys/sysUserGrade/removeBatch',
    save: '/api/sys/sysUserGrade/save',
    edit: '/api/sys/sysUserGrade/edit',
}

/** 会员等级列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 会员等级列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 会员等级岗位 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 会员等级岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 会员等级岗位 */
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
