<template>
  <div class="home">
    <div class="header">
      <Header/>
    </div>
    <div class="tabs-container">
      <router-view/>
    </div>
    <div class="header">
      <Footer/>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
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
.home {
  width: 1440px;
  margin: 0 auto;

  .tabs-container {
    height: 900px;
    width: 1440px;
    background: #2c3e50;
  }
}

</style>
