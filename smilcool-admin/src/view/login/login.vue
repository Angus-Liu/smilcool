<style lang="less">
  @import './login.less';
</style>

<template>
  <div class="login">
    <div class="login-con">
      <Card icon="log-in" title="欢迎登录" :bordered="false">
        <div class="form-con">
          <login-form @on-success-valid="handleSubmit"></login-form>
          <p class="login-tip">输入任意用户名和密码即可</p>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
import LoginForm from '_c/login-form';
import { mapActions } from 'vuex';

export default {
  components: {
    LoginForm
  },
  methods: {
    ...mapActions([
      'handleLogin',
      'getUserInfo'
    ]),
    handleSubmit ({ username, password }) {
      username = username.trim();
      this.handleLogin({ username, password })
        .then(res => {
          // 登录成功打开默认页
          this.$router.push({
            name: this.$config.homeName
          });
          // this.getUserInfo().then(res => {
          //   // 登录成功打开默认页
          //   this.$router.push({
          //     name: this.$config.homeName
          //   })
          // })
        });
    }
  }
};
</script>

<style>

</style>
