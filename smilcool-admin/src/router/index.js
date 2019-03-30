import Vue from 'vue';
import Router from 'vue-router';
import routes from './routers';
import store from '@/store';
import { LoadingBar } from 'iview';
import { setToken, getToken, canTurnTo, setTitle } from '@/libs/util';
import config from '@/config';

const { homeName } = config;

Vue.use(Router);
const router = new Router({
  // 使用浏览器 History 模式
  mode: 'history',
  routes
});
const LOGIN_PAGE_NAME = 'login';

// 路由跳转前进行权限控制
const turnTo = (to, access, next) => {
  if (canTurnTo(to.name, access, routes)) {
    // 有权限，可访问
    next();
  } else {
    // 无权限，重定向到401页面
    next({ replace: true, name: 'error-401' });
  }
};

router.beforeEach((to, from, next) => {
  LoadingBar.start();
  const token = getToken();
  if (!token && to.name !== LOGIN_PAGE_NAME) {
    // 未登录且要跳转的页面不是登录页，跳转到登录页
    next({ name: LOGIN_PAGE_NAME });
  } else if (!token && to.name === LOGIN_PAGE_NAME) {
    // 未登陆且要跳转的页面是登录页，跳转到登录页
    next();
  } else if (token && to.name === LOGIN_PAGE_NAME) {
    // 已登录且要跳转的页面是登录页，跳转到homeName页
    next({ name: homeName });
  } else {
    if (store.state.user.hasGetInfo) {
      turnTo(to, store.state.user.access, next);
    } else {
      store.dispatch('getUserInfo').then(user => {
        // 拉取用户信息，通过用户权限和跳转的页面的name来判断是否有权限访问;access必须是一个数组，如：['super_admin'] ['super_admin', 'admin']
        turnTo(to, user.access, next);
      }).catch(() => {
        setToken('');
        next({
          name: 'login'
        });
      });
    }
  }
});

router.afterEach(to => {
  setTitle(to, router.app);
  LoadingBar.finish();
  window.scrollTo(0, 0);
});

export default router;
