import axios from 'axios';
import store from '@/store';
import { Message } from 'iview';
import Log from './log';

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
      const { method, data } = config;
      log.info('Request --->');
      log.info('Method', method);
      log.info('URL', this.baseUrl + url);
      log.info('Param', data);

      return config;
    }, error => {
      return Promise.reject(error);
    });
    // 响应拦截
    instance.interceptors.response.use(res => {
      this.destroy(url);
      const { status, data } = res;

      // 打印响应参数
      log.info('Response <---');
      log.info('URL', this.baseUrl + url);
      log.info('Status', status);
      log.info('Data', data);

      // 处理后台返回的错误信息
      // data.success 存在且为 false（为了兼容模拟端口，后期删除）
      // if (data.success !== undefined && data.success !== null && !data.success) {
      //   // 全局提示
      //   Message.error(data.msg);
      // }
      // 后台接口成功响应，直接返回响应体
      // 此时 res 结构如下
      // {
      //   "success": "请求情况",
      //   "code": "状态码",
      //   "data": "反馈数据",
      //   "msg": "反馈消息",
      //   "timestamp": "时间戳"
      // }
      // return data;
      return { status, data };
    }, error => {
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
}

export default HttpRequest;
