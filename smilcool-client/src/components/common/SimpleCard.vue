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
  padding: 10px;
  overflow: hidden;
  border: 1px solid #eee;
  background: #fff;
}
</style>
