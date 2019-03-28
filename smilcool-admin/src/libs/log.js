// 自定义日志工具，后续还可以改进

export default class Log {
  constructor (tag = 'Log') {
    this.tag = tag
  }

  info (key, value) {
    if (value) {
      console.log(`[${this.tag}] ${key}:${value}`)
    } else {
      console.log(`[${this.tag}] ${key}`)
    }
  }
}
