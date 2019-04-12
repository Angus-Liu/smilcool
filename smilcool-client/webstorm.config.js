// 该文件用于解决 WebStorm 关于 Webpack 路径别名问题（实际就是未暴露的 webpack.config.js 写法）
// WebStorm 配置项: File > Settings > Languages & FrameWorks > JavaScript > Webpack
// 无该文件不影响项目运行（vue.config.js 已有相关配置），只是 WebStorm 无法识别路径别名
'use strict';
const path = require('path');

function resolve (dir) {
  return path.join(__dirname, '.', dir);
}

module.exports = {
  context: path.resolve(__dirname, './'),
  resolve: {
    extensions: ['.js', '.vue', '.json'],
    alias: {
      '@': resolve('src')
    }
  }
};
