import request from '../request'

/** 接口管理 */
const Api = {
    page: '/api/gen/page',
    list: '/api/gen/list',
    DBPage: '/api/gen/db/page',
    add: '/api/gen/import/table',
    remove: '/api/gen/remove',
    removeBatch: '/api/gen/removeBatch',
    edit: '/api/gen/edit',
    previewCode: '/api/gen/preview',
    download: '/api/gen/download',
    columnList: '/api/gen/column/list',
    genCode: '/api/gen/genCode',
}
/** 删除岗位 */
export const columnList = data => {
    return request.request({
        url: Api.columnList,
        params: data,
        method: 'GET'
    })
}

export const download = async data => {
    const result = await request.request({
        url: Api.download,
        params: data,
        method: 'GET',
        responseType: 'blob',
    });
    if (!result) return;
    let url = window.URL.createObjectURL(new Blob([result]));
    let link = document.createElement('a');
    link.style.display = 'none';
    link.href = url;
    link.setAttribute('download', 'autoCode.zip');
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  
}
export const genCode = data => {
    return request.request({
        url: Api.genCode,
        params: data,
        method: 'GET'
    })
}


/** 删除岗位 */
export const previewCode = data => {
    return request.request({
        url: Api.previewCode,
        params: data,
        method: 'GET'
    })
}

/** 删除岗位 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'POST'
    })
}

/** 删除岗位 */
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


/** 配置列表 (分页) */
export const add = data => {
    return request.request({
        url: Api.add,
        data: data,
        method: 'POST'
    })
}


/** 配置列表 (分页) */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

export const DBPage = data => {
    return request.request({
        url: Api.DBPage,
        params: data,
        method: 'GET'
    })
}

/** 配置列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}
