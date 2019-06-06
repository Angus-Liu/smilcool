import Vue from 'vue';
import Vuex from 'vuex';
import Log from './utils/log';
// vuex 持久化插件 - https://github.com/robinvdvleuten/vuex-persistedstate
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

let log = new Log('src/store.js');

export default new Vuex.Store({
  state: {
    user: {
      id: -1,
      username: 'anonymous',
      nickname: '游客',
      avatar: require('./assets/img/avatar/anonymous-avatar.jpg'),
      sex: '保密',
      birthday: '1970-01-01',
      sign: '一句话介绍自己',
      intro: '这个人很神秘，什么也没写',
      grade: '未填写',
      college: '未填写',
      major: '未填写',
      phone: null,
      email: 'anonymous@example.com',
      state: 1,
      remark: null,
      createTime: '1970-01-01 00:00:00',
      updateTime: '1970-01-01 00:00:00',
      roles: ['role_anonymous'],
      permissions: []
    }
  },
  getters: {
    // getUser: (state) => {
    //   // 从 localStorage 中获取用户信息
    //   if (state.user == null) {
    //     state.user = storage.get('user')
    //   }
    //   log.info('this.$store.getters.getUser', state.user);
    //   return state.user;
    // }
  },
  mutations: {
    userUpdate(state, user) {
      log.info('this.$store.commit.userUpdate', user);
      state.user = user;
    }
  },
  actions: {},
  // 用法参考 - https://juejin.im/post/5b62999fe51d4519610e336e
  plugins: [createPersistedState()]
});
