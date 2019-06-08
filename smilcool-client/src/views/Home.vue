<template>
  <div class="container">
    <Header/>
    <div class="body">
      <router-view/>
    </div>
    <Footer/>
  </div>
</template>

<script>
import Header from '@/components/common/Header';
import Footer from '@/components/common/Footer';

export default {
  name: 'home',
  components: {
    Header,
    Footer
  },
  methods: {
    // 从后台获取用户是否登录
    getCurrentUser() {
      // 获取登录用户信息
      this.$axios.get('/api/user/current')
        .then(res => {
          let result = res.data;
          this.$store.commit('userUpdate', result.data);
        })
    }
  },
  mounted() {
    // TODO 2019/5/25 启动 WebSocket
    console.log('启动 WebSocket');
    this.getCurrentUser();
  }
};
</script>

<style lang="less" scoped>
.container {
  height: 100%;
  width: 100%;

  .body {
    margin: 10px 0;
    min-height: calc(100% - 90px);
  }
}

</style>
