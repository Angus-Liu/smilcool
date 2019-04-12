import axios from 'axios';
import Log from './log';

// 自定义日志工具
const log = new Log('src/utils/axios.js');

class HttpRequest {
  interceptors(instance, url) {
    // 请求拦截
    instance.interceptors.request.use(config => {
      // 打印请求参数
      const { method, params, data } = config;
      log.info('Request --->');
      log.info('Method', method);
      log.info('URL', url);
      log.info('Params', params);
      log.info('Data', data);

      return config;
    }, error => {
      return Promise.reject(error);
    });
    // 响应拦截
    instance.interceptors.response.use(res => {
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
      log.info('URL', url);
      log.info('Status', status);
      log.info('Data', data);

      switch (status) {
        case 401:
          log.info('跳转到登录页面');
          break;
        case 403:
          log.info('权限不足');
          break;
        case 500:
          log.info('服务端异常');
          break;
        default:
      }
      return Promise.reject(error);
    });
  }

  request(options) {
    const instance = axios.create();
    this.interceptors(instance, options.url);
    return instance(options);
  }

  get(url, params) {
    return this.request({
      method: 'get',
      url,
      params
    });
  }

  post(url, data) {
    return this.request({
      method: 'post',
      url,
      data
    });
  }

  put(url, data) {
    return this.request({
      method: 'put',
      url,
      data
    });
  }
}

export default new HttpRequest();
