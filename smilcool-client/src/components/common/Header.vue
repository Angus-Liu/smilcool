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
          <sui-image class="avatar-img" circular src="http://img.angus-liu.cn/avatar/avatar07.png"/>
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

    <!-- 登录注册对话框 -->
    <Modal v-model="loginRegisterModel.show" :title="loginRegisterModel.title" width="320" footer-hide>
      <!-- 登录表单 -->
      <sui-form v-if="loginRegisterModel.isLogin">
        <sui-form-field>
          <sui-input placeholder="请输入用户名或邮箱"/>
        </sui-form-field>
        <sui-form-field>
          <sui-input type="password" placeholder="请输入密码"/>
        </sui-form-field>
        <sui-form-field>
          <sui-button style="width: 100%" basic primary>登录</sui-button>
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
          <sui-button style="width: 100%" basic primary>注册</sui-button>
        </sui-form-field>
        <sui-form-field>
          <div style="overflow: hidden">
            <a style="float:right" @click="showLoginForm">已有账号登录</a>
          </div>
        </sui-form-field>
      </sui-form>
    </Modal>
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
      }

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
}
</style>
