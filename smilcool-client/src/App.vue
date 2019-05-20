<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style lang="less">
.size {
  width: 100%;
  height: 100%;
}

html, body {
  .size;
  overflow: hidden;
  margin: 0;
  padding: 0;
}

#app {
  .size;
  /*background: #EFF3F5;*/
  background: #f1f1f1;
}
</style>
