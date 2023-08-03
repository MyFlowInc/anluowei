import request from '../request'

const Api = {
    tree: '/api/sys/dept/tree',
    save: '/api/sys/dept/save',
    edit: '/api/sys/dept/edit',
    remove: '/api/sys/dept/remove',
    removeBatch: '/api/sys/dept/removeBatch',
    syncDept: '/api/sys/dept/saveSync',
    givePower: '/api/cms/power/save',
    powerList: '/api/cms/power/list',

}

export const powerList = data => {
    return request.request({
        url: Api.powerList,
        params: data,
        method: 'get'
    })
}


export const givePower = data => {
    return request.request({
        url: Api.givePower,
        data: data,
        method: 'POST',
    })
}

export const syncDept = data => {
    return request.request({
        url: Api.syncDept,
        params: data,
        method: 'post'
    })
}

/** 部门树 */
export const tree = data => {
    return request.request({
        url: Api.tree,
        params: data,
        method: 'get'
    })
}

/** 新增部门 */
export const save = data => { 
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 修改部门 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 删除部门 */
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