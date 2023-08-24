import axios, {
  AxiosInstance,
  AxiosRequestConfig,
  CreateAxiosDefaults,
} from 'axios'

export class AxiosFactory {
  interceptors(instance: AxiosInstance) {
    /// 请求拦截
    instance.interceptors.request.use(
      (config) => {
        /// 权鉴相关
        const token = localStorage.getItem('Authorization')
        const tokenKey = localStorage.getItem('Authorization-key')

        if (!config.headers) {
          config.headers = {}
        }
        if (token) {
          config.headers['Authorization'] = token
        }
        if (tokenKey) {
          config.headers['Authorization-key'] = tokenKey
        }
        return config
      },
      (error) => {
        return Promise.reject(error)
      }
    )
    /// 响应拦截
    instance.interceptors.response.use(
      (response) => {
        if ([502, 503].includes(response.data.code)) {
          console.error('登录过期')
          // TODO: redirect
          // window.history.pushState(null, '', '/login')
          window.location.href = '/login'
        }
        if ([500].includes(response.data.code)) {
          return Promise.reject({
            response,
            message: response.data.msg,
          })
        }
        return response.data
      },
      (error: any) => {
        this.errorHandle(error)
        return Promise.reject(error)
      }
    )
  }

  errorHandle = function (error: any) {
    var response = error.response
    var message = error.message
    console.log('errorHandle', message)
    if (response) {
      if (response.status === 404) console.error('接口不存在')
    } else {
      if (message === 'Network Error') console.error('连接异常')

      if (message.includes('timeout')) console.error('请求超时')

      if (message.includes('Request failed with status code'))
        console.error('接口异常')
    }
  }

  getInstance() {
    const instance = axios.create()
    this.interceptors(instance)
    return instance
  }
}

const baseURL = process.env.REACT_APP_BASE_SERVER_URL

const flowConfig: CreateAxiosDefaults = {
  timeout: 5000,
  withCredentials: true,
  baseURL,
  headers: {
    'Content-Type': 'application/json; charset=utf-8',
  },
}

const axiosFactory = new AxiosFactory()
const ins = axiosFactory.getInstance()

export function apiCall(options: AxiosRequestConfig): Promise<any> {
  const requestOptions = Object.assign({}, flowConfig, options)
  return ins(requestOptions)
}

const uploadConfig: CreateAxiosDefaults = {
  timeout: 5000,
  withCredentials: true,
  baseURL,
  headers: {
    'Content-Type': 'multipart/form-data',
  },
}

export function uploadApiCall(options: AxiosRequestConfig): Promise<any> {
  const requestOptions = Object.assign({}, uploadConfig, options)
  return ins(requestOptions)
}

export function noLoginApiCall(options: AxiosRequestConfig): Promise<any> {
  const requestOptions = Object.assign({}, flowConfig, options)
  return axios(requestOptions)
}
