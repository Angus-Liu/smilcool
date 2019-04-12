<template>
  <div class="container">
    <mavon-editor class="editor" v-model="content" @change="handleChange" @save="handleSave"/>
  </div>
</template>

<script>
import { mavonEditor } from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';

export default {
  name: 'Editor',
  components: {
    mavonEditor
  },
  data() {
    return {
      content: ''
    };
  },
  methods: {
    handleChange(value, render) {
      console.log(value);
      console.log(render);
    },
    handleSave(value, render) {
      console.log("保存草稿");
    }
  },
  mounted() {
    this.$axios.get('/api/user').then(res => {
      console.log(res);
    });
  }
};
</script>

<style lang="less" scoped>
.container {
  margin: auto;
  width: 100%;
  height: 580px;

  .editor {
    height: 100%;
  }
}
</style>
