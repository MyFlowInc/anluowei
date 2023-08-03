import request from '../request'

const Api = {
    tree: '/cms/category/tree',
    save: '/cms/category/save',
    edit: '/cms/category/edit',
    remove: '/cms/category/remove',
    totalCount: '/cms/category/getRecentSearchTotal',
    selectCateByParentId: '/cms/category/selectCateByParentId',
}

export const selectCateByParentId = data => {
    return request.request({
        url: Api.selectCateByParentId,
        params: data,
        method: 'GET'
    })
}

export const totalCount = data => {
    return request.request({
        url: Api.totalCount,
        params: data,
        method: 'GET'
    })
}

export const tree = data => {
    return request.request({
        url: Api.tree,
        params: data,
        method: 'GET'
    })
}

export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

export const remove = data => {
    return request.request({
        url: Api.remove,
        params: data,
        method: 'DELETE'
    })
}


