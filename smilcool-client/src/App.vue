<template>

  <div id="app">
    <router-view/>
    <BackTop class="back-top">
      <Icon type="ios-arrow-up" color="#ff8364" size="30"/>
      <p class="up">UP</p>
    </BackTop>
  </div>
</template>

<style lang="less">

html, body {
  background: #f1f1f1;
}

#app {
  width: 100%;
  height: 100%;
  background: #f1f1f1;
}

.back-top {
  text-align: center;

  .up {
    margin-top: -10px;
    font-weight: bold;
    color: #fff;
  }
}

</style>
