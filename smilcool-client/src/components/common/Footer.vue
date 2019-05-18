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
  margin-top: 5px;
  background: #bbb;

  .container {
    height: 100%;
    width: 1140px;
    margin: 0 auto;
    padding-top: 10px;
    text-align: center;
    background: #aaa;
  }
}

</style>
