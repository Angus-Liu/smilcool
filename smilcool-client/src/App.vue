<template>

  <div id="app">
    <router-view/>
    <BackTop>
      <Icon type="ios-arrow-up" color="#ff8364" size="30"/>
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

</style>
