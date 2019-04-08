import axios from 'axios';
import store from '@/store';
import router from '../router/index';
import Log from './log';
import { Message } from 'iview';

// 自定义日志工具
const log = new Log('src/libs/axios.js');

const addErrorLog = errorInfo => {
  const { statusText, status, request: { responseURL } } = errorInfo;
  let info = {
    type: 'ajax',
    code: status,
    mes: statusText,
    url: responseURL
  };
  if (!responseURL.includes('save_error_logger')) store.dispatch('addErrorLog', info);
};

class HttpRequest {
  constructor (baseUrl = baseURL) {
    this.baseUrl = baseUrl;
    this.queue = {};
  }

  getInsideConfig () {
    const config = {
      baseURL: this.baseUrl,
      headers: {
        //
      }
    };
    return config;
  }

  destroy (url) {
    delete this.queue[url];
  }

  interceptors (instance, url) {
    // 请求拦截
    instance.interceptors.request.use(config => {
      this.queue[url] = true;

      // 打印请求参数
      const { method, params, data } = config;
      log.info('Request --->');
      log.info('Method', method);
      log.info('URL', this.baseUrl + url);
      log.info('Params', params);
      log.info('Data', data);

      return config;
    }, error => {
      return Promise.reject(error);
    });
    // 响应拦截
    instance.interceptors.response.use(res => {
      this.destroy(url);
      const { status, data } = res;
      // 打印响应参数
      log.info('Success Response <---');
      log.info('URL', this.baseUrl + url);
      log.info('Status', status);
      log.info('Data', data);
      return { status, data };

    }, error => {
      const { status, data } = error.response;
      log.info('Error Response <---');
      log.info('URL', this.baseUrl + url);
      log.info('Status', status);
      log.info('Data', data);

      switch (status) {
        case 401:
          log.info('跳转到登录页面');
          router.push('/login');
          break;
        case 403:
          log.info('权限不足');
          break;
        case 500:
          log.info('服务端异常');
          break;
        default:
      }
      // 提示错误信息
      Message.error(data.msg);
      this.destroy(url);
      let errorInfo = error.response;
      if (!errorInfo) {
        const { request: { statusText, status }, config } = JSON.parse(JSON.stringify(error));
        errorInfo = {
          statusText,
          status,
          request: { responseURL: config.url }
        };
      }
      addErrorLog(errorInfo);
      return Promise.reject(error);
    });
  }

  request (options) {
    const instance = axios.create();
    options = Object.assign(this.getInsideConfig(), options);
    this.interceptors(instance, options.url);
    return instance(options);
  }

  get (url, params) {
    return this.request({
      method: 'get',
      url,
      params
    });
  }

  post (url, data) {
    return this.request({
      method: 'post',
      url,
      data
    });
  }

  put (url, data) {
    return this.request({
      method: 'put',
      url,
      data
    });
  }
}

export default HttpRequest;
