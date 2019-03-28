import axios from 'axios'
import store from '@/store'
import Log from './log'

// 自定义日志工具
const log = new Log('src/libs/axios.js')

const addErrorLog = errorInfo => {

  log.info('errorInfo', JSON.stringify(errorInfo))

  const { statusText, status, request: { responseURL } } = errorInfo
  let info = {
    type: 'ajax',
    code: status,
    mes: statusText,
    url: responseURL
  }
  if (!responseURL.includes('save_error_logger')) store.dispatch('addErrorLog', info)
}

class HttpRequest {
  constructor (baseUrl = baseURL) {
    this.baseUrl = baseUrl
    this.queue = {}
  }

  getInsideConfig () {
    return {
      baseURL: this.baseUrl,
      headers: {
        //
      }
    }
  }

  destroy (url) {
    delete this.queue[url]
  }

  interceptors (instance, url) {
    // 请求拦截
    instance.interceptors.request.use(config => {
      this.queue[url] = true

      // 打印请求参数
      const { method, data } = config
      log.info('Request --->')
      log.info('Method', method)
      log.info('URL', this.baseUrl + url)
      log.info('Data', JSON.stringify(data))

      return config
    }, error => {
      return Promise.reject(error)
    })
    // 响应拦截
    instance.interceptors.response.use(res => {
      this.destroy(url)
      const { data, status } = res

      // 打印响应参数
      console.log('[src/libs/axios.js] Response <---')
      console.log(`[src/libs/axios.js] URL: ${this.baseUrl + url}`)
      console.log(`[src/libs/axios.js] Status: ${status}`)
      console.log(`[src/libs/axios.js] Data: ${JSON.stringify(data)}`)

      return { data, status }
    }, error => {
      this.destroy(url)
      let errorInfo = error.response

      log.info('errorInfo', JSON.stringify(errorInfo))

      if (!errorInfo) {
        const { request: { statusText, status }, config } = JSON.parse(JSON.stringify(error))
        errorInfo = {
          statusText,
          status,
          request: { responseURL: config.url }
        }
      }
      addErrorLog(errorInfo)
      return Promise.reject(error)
    })
  }

  request (options) {
    const instance = axios.create()
    options = Object.assign(this.getInsideConfig(), options)
    this.interceptors(instance, options.url)
    return instance(options)
  }
}

export default HttpRequest
