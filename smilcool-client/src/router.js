import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';

Vue.use(Router);

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      children: [
        {
          path: 'article',
          name: 'article',
          component: () => import('@/views/article/Main'),
        }, {
          path: 'article/editor',
          name: 'article-editor',
          component: () => import('@/views/article/Editor')
        }, {
          path: 'article/:id',
          name: 'viewer',
          component: () => import('@/views/article/Viewer'),
          props: true
        }, {
          path: 'moment',
          name: 'moment',
          component: () => import('@/views/moment/Moment')
        }
      ]
    }
  ]
});
