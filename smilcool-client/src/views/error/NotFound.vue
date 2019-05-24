<template>
  <div class="container">
    <h1>404!</h1>
    <blockquote>
      <p>你到了知识的荒原 : )</p>
      <p>You are in the wasteland of knowledge.</p>
    </blockquote>
  </div>
</template>

<script>
export default {
  name: 'NotFound'
}
</script>

<style lang="less" scoped>
.container {
  width: 1140px;
  margin: 0 auto;
  padding: 15px;

  blockquote {
    border-left: .25em solid #dfe2e5;
    color: #6a737d;
    padding: 0 1em;
    margin-bottom: 16px;
  }
}
</style>
