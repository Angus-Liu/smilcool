<template>
  <div class="header">
    <div class="nav">
      <div class="nav-logo">
        <img class="nav-logo-img" src="../../assets/static/logo.png">
      </div>
      <div class="nav-avatar">
        <at-dropdown>
          <sui-image class="nav-avatar-img" circular src="http://img.angus-liu.cn/avatar.png"/>
          <at-dropdown-menu slot="menu">
            <at-dropdown-item>深圳</at-dropdown-item>
            <at-dropdown-item>广州</at-dropdown-item>
            <at-dropdown-item disabled>上海</at-dropdown-item>
            <at-dropdown-item divided>北京</at-dropdown-item>
            <at-dropdown-item>杭州</at-dropdown-item>
          </at-dropdown-menu>
        </at-dropdown>
      </div>
      <div class="nav-search">
        <at-input class="nav-search-input" v-model="input" placeholder="请输入内容" append-button size="large">
          <template slot="append">
            <i class="icon icon-search"></i>
          </template>
        </at-input>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Header',
  data () {
    return {
      input: ''
    };
  },
  mounted () {

  }
};
</script>

<style lang="less" scoped>
.header {
  position: fixed;
  right: 0;
  left: 0;
  height: 56px;
  background: #eee;
  border-bottom: 1px #eee solid;

  .nav {
    max-width: 1440px; // 1440px
    height: 100%;
    margin: 0 auto;
    padding: 0 3px;
    background: red;

    .nav-search {
      float: right;
      display: inline-block;
      width: 300px;
      height: 56px;

      .nav-search-input {
        margin-top: 12px;
      }
    }

    .nav-logo {
      float: left;
      height: 100%;

      .nav-logo-img {
        height: 100%;
        padding-top: 7px;
      }
    }

    .nav-avatar {
      float: right;
      height: 50px;
      padding: 3px;

      .nav-avatar-img {
        height: 50px;
        margin-bottom: 5px;
      }
    }
  }
}

</style>
