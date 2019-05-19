<template>
  <div class="container">
    <!-- 标题栏 -->
    <div class="article-info-title">
      <Input v-model="articleAddForm.title" placeholder="输入文章标题" size="large">
        <template #append>
          <Button icon="md-create" @click="articleInfoModal.show = true">提交</Button>
        </template>
      </Input>
    </div>
    <!-- 文章信息模态框 -->
    <Modal v-model="articleInfoModal.show" title="文章信息" :mask-closable="false" :width="600">
      <Row>
        <iCol span="8">
          <sui-image :src="articleAddForm.cover" size="medium" rounded/>
          <Upload action="//jsonplaceholder.typicode.com/posts/">
            <Button icon="ios-cloud-upload-outline">上传文章封面</Button>
          </Upload>
        </iCol>
        <iCol span="16">
          <div class="article-info-row">
            <Select v-model="articleAddForm.articleCategory" placeholder="请选择文章类别" size="large">
              <Option v-for="item in articleCategory" :key="item.name" :value="item.name" :label="item.name"/>
            </Select>
          </div>
          <div class="article-info-row">
            <Input type="textarea" v-model="articleAddForm.brief" size="large" :autosize="{minRows: 5,maxRows: 10}"
                   placeholder="请输入文章简介"/>
          </div>
          <div class="article-info-row">
            <Input class="article-info-tag" prefix="ios-bookmark-outline" v-model="tag" placeholder="添加标签"
                   @on-enter="addTag"/>
            <Tag class="article-info-tag" v-for="(item, index) in tags" :key="index" :name="item"
                 @on-close="removeTag" closable type="dot" color="primary">
              {{item}}
            </Tag>
          </div>
        </iCol>
      </Row>
      <template #footer>
        <Button type="text" @click="articleInfoModal.show = false">取消</Button>
        <Button type="primary" @click="submitArticle" :loading="articleInfoModal.loading">确认提交</Button>
      </template>
    </Modal>
    <!-- 编辑器 -->
    <mavon-editor class="editor" v-model="articleAddForm.markdownContent"
                  @change="contentChange" :boxShadow="false" style="z-index: 100"/>
  </div>
</template>

<script>
export default {
  name: 'Editor',
  data() {
    return {
      articleInfoModal: {
        show: false,
        loading: false
      },
      tag: '',
      tags: [],
      articleAddForm: {
        articleCategory: '',
        title: '',
        brief: '',
        cover: 'http://img.angus-liu.cn/avatar/avatar01.jpg',
        tags: '',
        markdownContent: '',
        htmlContent: ''
      },
      articleCategory: [{
        name: '测试类别',
        code: '-1'
      }]
    };
  },
  methods: {
    // 获取文章类别
    getArticleCategory() {
      this.$axios.get('/api/dic/article-category/item')
        .then(res => {
          let result = res.data;
          this.articleCategory = result.data;
        })
    },
    contentChange(value, render) {
      this.articleAddForm.markdownContent = value;
      this.articleAddForm.htmlContent = render;
    },
    saveArticle(value, render) {
      console.log('保存草稿');
    },
    addTag() {
      this.tags.push(this.tag);
      this.tag = '';
    },
    removeTag(event, name) {
      const index = this.tags.indexOf(name);
      this.tags.splice(index, 1);
    },
    submitArticle() {
      this.articleInfoModal.loading = true;
      this.articleAddForm.tags = JSON.stringify(this.tags);
      this.$axios.post('/api/article', this.articleAddForm)
        .then(res => {
          this.articleInfoModal.show = false;
          this.articleInfoModal.loading = false;
        })
        .catch(error => {
          this.articleInfoModal.loading = false;
        });
    }
  },
  mounted() {
    this.getArticleCategory();
  }
};
</script>

<style lang="less" scoped>
.container {
  width: 1260px;
  margin: 20px auto;
  /*background: #ccc;*/

  .article-info-title {
    margin-bottom: 7px;
  }

  .editor {
    min-height: 750px;
  }
}

.article-info-row {
  margin-bottom: 10px;

  .article-info-tag {
    margin-bottom: 5px;
  }
}
</style>
