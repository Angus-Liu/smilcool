import HttpRequest from '@/libs/axios';
import config from '@/config';
import Vue from 'vue';

const baseUrl = process.env.NODE_ENV === 'development' ? config.baseUrl.dev : config.baseUrl.pro;

const axios = new HttpRequest(baseUrl);

Vue.prototype.$axios = axios;

export default axios;
