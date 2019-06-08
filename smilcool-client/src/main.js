import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
// iView: https://iviewui.com/docs/guide/install
import iView from 'iview';
import 'iview/dist/styles/iview.css';
import { directive as clickOutside } from 'v-click-outside-x';
// Semantic UI Vue: https://semantic-ui-vue.github.io
import SuiVue from 'semantic-ui-vue';
import 'semantic-ui-css/semantic.min.css';
// axios: https://github.com/axios/axios
import HttpRequest from '@/utils/axios';
// mavonEditor: https://github.com/hinesboy/mavonEditor
import mavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';
// Qiniu-JavaScript-SDK : https://developer.qiniu.com/kodo/sdk/1283/javascript
import * as qiniu from 'qiniu-js'

Vue.config.productionTip = false;

// iView
Vue.use(iView);
// Semantic UI Vue
Vue.use(SuiVue);
// mavonEditor
Vue.use(mavonEditor);

// axios
Vue.prototype.$axios = HttpRequest;
// qiniu
Vue.prototype.$qiniu = qiniu;

Vue.directive('clickOutside', clickOutside);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
