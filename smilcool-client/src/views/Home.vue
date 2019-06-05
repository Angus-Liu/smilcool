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
  mounted() {
    // TODO 2019/5/25 启动 websoket
    console.log("启动 websoket");
  }
};
</script>

<style lang="less" scoped>
.container {
  height: 100%;
  width: 100%;

  .body {
    margin: 10px 0;
    min-height: calc(100% - 190px);
  }
}

</style>
