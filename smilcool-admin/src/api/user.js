import axios from '@/libs/api.request';

/**
 * 用户登录
 *
 * @param username
 * @param password
 */
export const login = ({ username, password }) => {
  const data = { username, password };
  return axios.request({
    method: 'post',
    url: '/api/user/login',
    data
  });
};

// export const getUserInfo = (token) => {
//   return axios.request({
//     url: 'get_info',
//     params: {
//       token
//     },
//     method: 'get'
//   });
// };

export const getUserInfo = (token) => {
  return axios.request({
    url: `/api/user/${token}`,
    method: 'get'
  });
};

export const logout = (token) => {
  return axios.request({
    url: 'logout',
    method: 'post'
  });
};

export const getUnreadCount = () => {
  return axios.request({
    url: 'message/count',
    method: 'get'
  });
};

export const getMessage = () => {
  return axios.request({
    url: 'message/init',
    method: 'get'
  });
};

export const getContentByMsgId = msg_id => {
  return axios.request({
    url: 'message/content',
    method: 'get',
    params: {
      msg_id
    }
  });
};

export const hasRead = msg_id => {
  return axios.request({
    url: 'message/has_read',
    method: 'post',
    data: {
      msg_id
    }
  });
};

export const removeReaded = msg_id => {
  return axios.request({
    url: 'message/remove_readed',
    method: 'post',
    data: {
      msg_id
    }
  });
};

export const restoreTrash = msg_id => {
  return axios.request({
    url: 'message/restore',
    method: 'post',
    data: {
      msg_id
    }
  });
};
