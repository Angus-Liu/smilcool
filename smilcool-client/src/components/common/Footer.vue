<template>
    <footer>
      <div class="container">
        我是页脚，页脚是我
      </div>
    </footer>
</template>

<script>
export default {
  name: 'Footer'
};
</script>

<style lang="less" scoped>
footer {
  height: 100px;
  background: #ccc;
  margin-top: 5px;

  .container {
    height: 100%;
    width: 1440px;
    margin: 0 auto;
    background: #aaa;
  }
}

</style>
