// vue.config.js
module.exports = {
  // 关闭eslint
  lintOnSave: false,
  devServer: {
    // 部署端口
    proxy: {
      // 接口代理 - 代理以 "/api" 开头的 url
      '/api': {
        // 后台服务器的地址
        target: 'http://localhost:8100',
        pathRewrite: { '^/api': '' },
        changeOrigin: true
      },
      // 静态资源代理 - 代理以 "/local-storage" 开头的 url
      '/local-storage': {
        // 后台服务器的地址
        target: 'http://localhost:8100',
        changeOrigin: true
      }
    }
  }
};
