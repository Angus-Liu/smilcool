<template>
  <div class="container">
    <Header/>
    <div class="article-container">
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

  .article-container {
    width: 1440px;
    min-height: calc(100% - 170px);
    margin: 0 auto;
  }
}

</style>
