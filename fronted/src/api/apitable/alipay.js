import request from '../request'

export function get() {
  return request.request({
    url: 'api/aliPay',
    params: "",
    method: 'get'
  })
}

export function update(data) {
  return request.request({
    url: 'api/aliPay',
    data,
    method: 'put'
  })
}

// 支付
export function toAliPay(url, data) {
  return request.request({
    url: 'api/' + url,
    data,
    method: 'post'
  })
}
