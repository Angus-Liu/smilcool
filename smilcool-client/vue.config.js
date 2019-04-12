// vue.config.js
module.exports = {
  // 关闭eslint
  lintOnSave: false,
  devServer: {
    // 部署端口
    proxy: {
      // 代理以 "/api" 开头的 url
      '/api': {
        // 后台服务器的ip地址
        target: 'http://localhost:8100',
        pathRewrite: { '^/api': '' },
        changeOrigin: true
      }
    }
  }
};
