<template>
  <header>
    <div class="container">
      <!-- LOGO -->
      <router-link to="/">
        <img class="logo" src="../../assets/logo-3.png">
      </router-link>
      <!-- LOGO END -->
      <!-- 菜单 -->
      <nav>
        <ul>
          <li>
            <router-link to="/article">文章资讯</router-link>
          </li>
          <li>
            <router-link to="/moment">校园动态</router-link>
          </li>
          <li>
            <router-link to="/file">文件仓库</router-link>
          </li>
          <li>
            <router-link to="/second-hand">二手交易</router-link>
          </li>
          <li>
            <router-link to="/lost-found">失物寻物</router-link>
          </li>
          <li>
            <router-link to="/forum">校内论坛</router-link>
          </li>
        </ul>
      </nav>
      <!-- 菜单 END -->
      <!-- 头像 -->
      <div class="avatar">
        <Dropdown v-if="user">
          <sui-image class="avatar-img" circular :src="user.avatar"/>
          <DropdownMenu class="avatar-menu-list" slot="list">
            <router-link :to=" '/user/' + $store.state.user.id ">
              <DropdownItem>
                <sui-icon name="user circle"/>
                我的主页
              </DropdownItem>
            </router-link>
            <router-link to="/article/editor">
              <DropdownItem>
                <sui-icon name="pencil alternate"/>
                发表文章
              </DropdownItem>
            </router-link>
            <DropdownItem>
              <sui-icon name="star"/>
              收藏列表
            </DropdownItem>
            <DropdownItem>
              <sui-icon name="key"/>
              账号管理
            </DropdownItem>
            <DropdownItem divided @click.native="$store.commit('userLogout')">
              <sui-icon name="power off"/>
              退出登录
            </DropdownItem>
          </DropdownMenu>
        </Dropdown>
        <sui-image class="avatar-img" v-else circular @click.native="loginRegisterModel.show = true"
                   :src="require('../../assets/anonymous-avatar.png')"/>
      </div>
      <!-- 头像 END -->
      <!-- 消息通知 -->
      <div class="notification">
        <router-link to="/message">
          <Badge dot>
            <Icon type="ios-notifications-outline" size="26"></Icon>
          </Badge>
        </router-link>
      </div>
      <!-- 消息通知 END -->
      <!-- 搜索框 -->
      <div class="ui search search-container">
        <div class="ui icon input">
          <input class="prompt" type="text" placeholder="搜索校园资讯">
          <i class="search icon"></i>
        </div>
        <div class="results">
          这里会显示推荐结果
        </div>
      </div>
      <!-- 搜索框 END -->
    </div>
    <!-- 登录注册模态框 -->
    <Modal v-model="loginRegisterModel.show" :title="loginRegisterModel.title" :mask-closable="false" width="320"
           footer-hide>
      <!-- 登录表单 -->
      <sui-form v-if="loginRegisterModel.isLogin">
        <sui-form-field>
          <sui-input placeholder="请输入用户名或邮箱" v-model="loginForm.username"/>
        </sui-form-field>
        <sui-form-field>
          <sui-input type="password" placeholder="请输入密码" v-model="loginForm.password"/>
        </sui-form-field>
        <sui-form-field>
          <sui-button fluid basic primary @click.prevent="login">登录</sui-button>
        </sui-form-field>
        <sui-form-field>
          <div>
            <span>没有账号？<a @click="showRegisterForm">注册</a></span>
            <a style="float:right" href="#">忘记密码</a>
          </div>
        </sui-form-field>
      </sui-form>
      <!-- 注册表单 -->
      <sui-form v-else>
        <sui-form-field>
          <sui-input placeholder="请输入用户名" v-model="registerForm.username"/>
        </sui-form-field>
        <sui-form-field>
          <sui-input type="email" placeholder="请输入邮箱" v-model="registerForm.email"/>
        </sui-form-field>
        <sui-form-field>
          <sui-input type="password" placeholder="请输入密码" v-model="registerForm.password"/>
        </sui-form-field>
        <sui-form-field>
          <sui-input type="password" placeholder="请确认密码" v-model="registerForm.rePassword"/>
        </sui-form-field>
        <sui-form-field>
          <sui-button style="width: 100%" basic primary @click.prevent="register">注册</sui-button>
        </sui-form-field>
        <sui-form-field>
          <div style="overflow: hidden">
            <a style="float:right" @click="showLoginForm">已有账号登录</a>
          </div>
        </sui-form-field>
      </sui-form>
      <!-- 注册表单结束 -->
    </Modal>
    <!-- 登录注册模态框 END -->
  </header>
</template>

<script>
export default {
  name: 'Header',
  data() {
    return {
      input: '',
      loginRegisterModel: {
        show: false,
        title: '登录',
        isLogin: true,
      },
      loginForm: {
        username: '',
        password: ''
      },
      registerForm: {
        username: '',
        email: '',
        password: '',
        rePassword: ''
      },
      rules: {}

    };
  },
  computed: {
    user() {
      return this.$store.state.user;
    }
  },
  methods: {
    showLoginForm() {
      this.loginRegisterModel.title = '登录';
      this.loginRegisterModel.isLogin = true;
    },
    showRegisterForm() {
      this.loginRegisterModel.title = '注册';
      this.loginRegisterModel.isLogin = false;
    },
    showErrorNotice(title, desc) {
      this.$Notice.error({ title, desc });
    },
    login() {
      if (this.loginForm.username.length === 0) {
        this.showErrorNotice('登录失败', '请填写用户名或邮箱');
      } else if (this.loginForm.password.length === 0) {
        this.showErrorNotice('登录失败', '请填写密码');
      } else {
        // TODO 后期需要搬到 vuex
        this.$axios.post('/api/user/login', this.loginForm)
          .then(res => {
            let result = res.data;
            if (result.success) {
              this.loginRegisterModel.show = false;
              // 保存到 vuex
              this.$store.commit('userLogin', result.data);
              this.loginForm = {
                username: '',
                password: ''
              }
            }
          });
      }
    },
    register() {
      if (this.registerForm.username.length === 0) {
        this.showErrorNotice('注册失败', '请填写用户名');
      } else if (this.registerForm.email.length === 0
        || !/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(this.registerForm.email)) {
        this.showErrorNotice('注册失败', '请填写合法的邮箱');
      } else if (this.registerForm.password.length === 0
        || this.registerForm.password !== this.registerForm.rePassword) {
        this.showErrorNotice('注册失败', '两次输入的密码不一致');
      } else {
        // TODO 后期需要搬到 vuex
        this.$axios.post('/api/user/register', this.registerForm)
          .then(res => {
            let result = res.data;
          });
      }
    }
  },
  mounted() {
  }
};
</script>

<style lang="less" scoped>
header {
  /*position: fixed;*/
  width: 100%;
  height: 70px;
  background: #fff;
  box-shadow: 0 0 8px 0 rgba(0, 0, 0, .1);
  z-index: 999;
  margin-bottom: 5px;
}

.container {
  width: 1200px;
  margin: 0 auto;

  .logo {
    float: left;
    height: 55px;
    margin-top: 10px;
  }

  nav {
    float: left;
    margin-left: 30px;
    line-height: 65px;

    ul {
      list-style-type: none;
      padding-top: 5px;
      font-size: 16px;

      li {
        display: inline-block;
        margin-right: 20px;

        a {
          color: #aaa;
        }
      }
    }
  }

  .avatar {
    float: right;
    width: 58px;
    height: 58px;
    margin-top: 6px;
  }

  .notification {
    float: right;
    margin-top: 22px;
    margin-right: 30px;
  }

  .search-container {
    float: right;
    margin-top: 18px;
    margin-right: 25px;
  }

  .ivu-form-item:last-child {
    margin-bottom: 0;
  }

  .router-link-active {
    color: #ff8364;
  }
}
</style>
