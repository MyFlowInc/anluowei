import request from '../request'

export function get() {
  return request.request({
    url: 'api/email',
    method: 'get'
  })
}

export function update(data) {
  return request.request({
    url: 'api/email',
    data,
    method: 'put'
  })
}

export function send(data) {
  return request.request({
    url: 'api/email',
    data,
    method: 'post'
  })
}
