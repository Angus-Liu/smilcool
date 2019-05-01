<template>
  <header>
    <div class="container">
      <img class="logo" src="../../assets/logo.png">
      <nav>
        <ul>
          <li><a href="#">文章动态</a></li>
          <li><a href="#">资料分享</a></li>
          <li><a href="#">物品小站</a></li>
          <li><a href="#">校内论坛</a></li>
        </ul>
      </nav>
      <div class="avatar">
        <Dropdown @on-click="handleClick">
          <sui-image class="avatar-img" circular :src="user.avatar"/>
          <DropdownMenu slot="list">
            <DropdownItem name="login">临时登录</DropdownItem>
            <DropdownItem name="login2">临时登录</DropdownItem>
            <DropdownItem>豆汁儿</DropdownItem>
            <DropdownItem>冰糖葫芦</DropdownItem>
            <DropdownItem divided>退出登录</DropdownItem>
          </DropdownMenu>
        </Dropdown>
      </div>
      <div class="search">
        <Input search placeholder="Enter something..."/>
      </div>
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
          <sui-button style="width: 100%" basic primary @click.native="login">登录</sui-button>
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
          <sui-input placeholder="请输入邮箱"/>
        </sui-form-field>
        <sui-form-field>
          <sui-input type="password" placeholder="请输入密码"/>
        </sui-form-field>
        <sui-form-field>
          <sui-input type="password" placeholder="请确认密码"/>
        </sui-form-field>
        <sui-form-field>
          <sui-button style="width: 100%" basic primary @click.native="register">注册</sui-button>
        </sui-form-field>
        <sui-form-field>
          <div style="overflow: hidden">
            <a style="float:right" @click="showLoginForm">已有账号登录</a>
          </div>
        </sui-form-field>
      </sui-form>

      <!-- 登录表单 -->
      <!--<Form ref="loginForm" v-if="loginRegisterModel.isLogin" :model="loginForm" :rules="rules">-->
      <!--  <FormItem prop="用户名">-->
      <!--    <Input type="text" v-model="loginForm.username" size="large" placeholder="请输入用户名或邮箱"/>-->
      <!--  </FormItem>-->
      <!--  <FormItem prop="密码">-->
      <!--    <Input type="password" v-model="loginForm.password" size="large" placeholder="请输入密码"/>-->
      <!--  </FormItem>-->
      <!--  <FormItem>-->
      <!--    <Button type="primary" size="large" ghost long @click="login">登录</Button>-->
      <!--  </FormItem>-->
      <!--  <div style="font-size: 1rem">-->
      <!--    <span>没有账号？<a @click="showRegisterForm">注册</a></span>-->
      <!--    <a style="float:right" href="#">忘记密码</a>-->
      <!--  </div>-->
      <!--</Form>-->

      <!-- 注册表单 -->
      <!--<Form ref="registerForm" v-else :model="registerForm" :rules="rules">-->
      <!--  <FormItem prop="email">-->
      <!--    <Input type="text" v-model="registerForm.email" size="large" placeholder="请输入邮箱"/>-->
      <!--  </FormItem>-->
      <!--  <FormItem prop="password">-->
      <!--    <Input type="password" v-model="registerForm.password" size="large" placeholder="请输入密码"/>-->
      <!--  </FormItem>-->
      <!--  <FormItem prop="repassword">-->
      <!--    <Input type="password" v-model="registerForm.password" size="large" placeholder="请确认密码"/>-->
      <!--  </FormItem>-->
      <!--  <FormItem>-->
      <!--    <Button type="primary" size="large" ghost long @click="register">注册</Button>-->
      <!--  </FormItem>-->
      <!--  <div style="font-size: 1rem; overflow: hidden">-->
      <!--    <a style="float:right" @click="showLoginForm">已有账号登录</a>-->
      <!--  </div>-->
      <!--</Form>-->
    </Modal>
  </header>
</template>

<script>
export default {
  name: 'Header',
  data() {
    return {
      input: '',
      user: {
        id: -1,
        username: 'anonymous',
        nickname: '游客',
        avatar: require('../../assets/anonymous-avatar.png'),
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
      },
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
        email: '',
        password: ''
      },
      rules: {}

    };
  },
  methods: {
    handleClick(dropdownItemName) {
      switch (dropdownItemName) {
        case 'login':
          console.log('执行登录操作');
          this.loginRegisterModel.show = true;
          break;
        case 'login2':
          console.log('执行登录操作');
          break;
        default:
          console.log('执行其他操作');
      }
    },
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
        this.$axios.post('/api/user/login', this.loginForm)
          .then(res => {
            let result = res.data;
            if (result.success) {
              this.loginRegisterModel.show = false;
              this.user = result.data;
            }
          });
      }
    },
    register() {
      alert('注册');
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
  box-shadow: 0 1px 3px #eee;
  z-index: 999;
  margin-bottom: 5px;
}

.container {
  width: 1440px;
  margin: 0 auto;

  .logo {
    float: left;
    height: 55px;
    margin-top: 10px;
  }

  nav {
    float: left;
    margin-left: 30px;
    line-height: 70px;

    ul {
      list-style-type: none;
      padding-top: 5px;
      font-size: 16px;

      li {
        display: inline-block;
        margin-right: 20px;

        a {
          text-decoration: none;
        }
      }
    }
  }

  .avatar {
    float: right;

    .avatar-img {
      height: 58px;
      margin-top: 6px;
    }
  }

  .search {
    width: 300px;
    float: right;
    margin-top: 20px;
    margin-right: 30px;
  }

  .ivu-form-item:last-child {
    margin-bottom: 0;
  }
}
</style>
