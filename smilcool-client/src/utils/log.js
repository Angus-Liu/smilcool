// 自定义日志工具，后续还可以改进

export default class Log {
  constructor(tag = 'Log') {
    this.tag = tag;
  }

  info(key, value) {
    if (value) {
      if (typeof value === 'object') {
        value = JSON.stringify(value);
      }
      console.log(`[${this.tag}] ${key}: ${value}`);
    } else {
      console.log(`[${this.tag}] ${key}`);
    }
  }

  static info(tag, key, value) {
    if (value) {
      if (typeof value === 'object') {
        value = JSON.stringify(value);
      }
      console.log(`[${tag}] ${key}: ${value}`);
    } else {
      console.log(`[${tag}] ${key}`);
    }
  }
}
