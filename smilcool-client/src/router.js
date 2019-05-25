import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';

Vue.use(Router);

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      children: [
        {
          path: 'main',
          name: 'main',
          meta: {
            title: '文章资讯'
          },
          component: () => import('@/views/article/Main'),
        }, {
          path: 'article',
          name: 'article',
          meta: {
            title: '文章资讯'
          },
          component: () => import('@/views/article/Article')
        }, {
          path: 'article/editor',
          name: 'article-editor',
          meta: {
            title: '编辑文章'
          },
          component: () => import('@/views/article/Editor')
        }, {
          path: 'article/:id',
          name: 'viewer',
          props: true,
          meta: {
            title: '文章详情'
          },
          component: () => import('@/views/article/Viewer')
        }, {
          path: 'moment',
          name: 'moment',
          meta: {
            title: '校园动态'
          },
          component: () => import('@/views/moment/Moment')
        }, {
          path: 'file',
          name: 'file',
          meta: {
            title: '文件仓库'
          },
          component: () => import('@/views/file/File')
        }, {
          path: 'second-hand',
          name: 'second-hand',
          meta: {
            title: '二手交易'
          },
          component: () => import('@/views/second-hand/SecondHand')
        }, {
          path: 'lost-found',
          name: 'lost-found',
          meta: {
            title: '失物寻物'
          },
          component: () => import('@/views/lost-found/LostFound')
        }, {
          path: 'forum',
          name: 'forum',
          meta: {
            title: '校内论坛'
          },
          component: () => import('@/views/forum/Forum')
        }, {
          path: 'message',
          name: 'message',
          meta: {
            title: '消息中心'
          },
          component: () => import('@/views/message/Message')
        }, {
          path: 'user/:id',
          name: 'user',
          props: true,
          meta: {
            title: '用户主页'
          },
          component: () => import('@/views/user/User')
        }, {
          path: 'search',
          name: 'search',
          meta: {
            title: '搜索详情'
          },
          component: () => import('@/views/search/Search'),
        }, {
          path: '*',
          name: '404',
          meta: {
            title: '404'
          },
          component: () => import('@/views/error/NotFound')
        }
      ]
    }
  ],
  scrollBehavior(to, from, savedPosition) {
      return { x: 0, y: 0 }
  }
});

router.beforeEach((to, form, next) => {
  // 路由变化修改title
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next();
});

export default router;
