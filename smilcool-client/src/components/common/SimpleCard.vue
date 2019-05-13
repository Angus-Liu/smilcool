<template>
  <div class="container">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: 'SimpleCard'
}
</script>

<style lang="less" scoped>
.container {
  padding: 10px;
  overflow: hidden;
  border: 1px solid #eee;
  background: #fff;
}
</style>
