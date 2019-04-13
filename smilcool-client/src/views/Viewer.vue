<template>
  <div class="viewer" >
    <div class="markdown-body" v-html="article.htmlContent"></div>
  </div>
</template>

<script>
export default {
  name: 'Viewer',
  data() {
    return {
      article: {}
    };
  },
  mounted() {
    this.$axios.get(`/api/article/1`)
      .then(res => {
        let result = res.data;
        this.article = result.data;
      });
  }
};
</script>

<style lang="less" scoped>
.viewer {
  height: 100%;
  width: 960px;
  margin: 0 auto;
  padding: 1em 2em;
  background: #fefefe;
}
</style>
