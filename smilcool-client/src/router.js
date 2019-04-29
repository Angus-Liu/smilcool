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
          path: 'main',
          name: 'main',
          component: () => import('@/views/main/Main')
        },
        {
          path: 'editor',
          name: 'editor',
          component: () => import('@/views/article/Editor')
        }, {
          path: 'viewer/:id',
          name: 'viewer',
          component: () => import('@/views/article/Viewer'),
          props: true
        }
      ]
    }
  ]
});
