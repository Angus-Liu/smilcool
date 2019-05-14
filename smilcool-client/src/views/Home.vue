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
  }
};
</script>

<style lang="less" scoped>
.container {
  width: 100%;
  height: 100%;
  overflow: auto;

  .body {
    margin: 10px 0;
    min-height: calc(100% - 190px);
  }
}

</style>
