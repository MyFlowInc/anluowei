import request from '../request'

const Api = {
    sendMessage: '/api/wx/sendMessage',
    sendNewsMessage: '/api/wx/sendNewsMessage',
    sendImageMessage: '/api/wx/sendImageMessage'
}

/** 文件列表 */
export const sendMessage = data => {
    return request.request({
        url: Api.sendMessage,
        params: data,
        method: 'POST'
    })
}

/** 文件列表 */
export const sendNewsMessage = data => {
    return request.request({
        url: Api.sendNewsMessage,
        params: data,
        method: 'POST'
    })
}

/** 文件上传 */
export const sendImageMessage = data => {
    return request.request({
        url: Api.sendImageMessage,
        params: data,
        method: 'POST'
    })
}