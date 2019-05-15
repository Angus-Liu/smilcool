<template>
  <div class="simple-card">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: 'SimpleCard'
}
</script>

<style lang="less" scoped>
.simple-card {
  padding: 15px;
  overflow: hidden;
  background: #fff;
  box-shadow: 1px 1px 2px #ddd;
}
</style>
