<template>
  <div class="container">
    <div class="article-info-title">
      <Input v-model="articleAddForm.title" placeholder="输入文章标题" size="large">
        <template #append>
          <Button icon="md-create" @click="articleInfoModal.show = true">提交</Button>
        </template>
      </Input>
    </div>
    <Modal v-model="articleInfoModal.show" title="文章信息" @on-ok="submitArticle" @on-cancel="cancel"
           :mask-closable="false">
      <Row>
        <iCol span="8">
          <sui-image :src="articleAddForm.cover" size="medium" rounded/>
          <Upload action="//jsonplaceholder.typicode.com/posts/">
            <Button icon="ios-cloud-upload-outline">上传文章封面</Button>
          </Upload>
        </iCol>
        <iCol span="16">
          <div class="article-info-row">
            <Select v-model="articleAddForm.resourceTypeId">
              <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
          </div>
          <div class="article-info-row">
            <Input type="textarea" v-model="articleAddForm.brief" placeholder="请输入文章简介"/>
          </div>
          <div class="article-info-row">
            <Input class="article-info-tag" prefix="ios-bookmark-outline" v-model="label" placeholder="添加标签"
                   @on-enter="addLabel"/>
            <Tag class="article-info-tag" v-for="(item, index) in labelArr" :key="index" :name="item"
                 @on-close="removeLabel"
                 closable type="dot" color="primary">{{item}}
            </Tag>
          </div>
        </iCol>
      </Row>

    </Modal>
    <mavon-editor class="editor"
                  v-model="articleAddForm.markdownContent"
                  @change="contentChange"
                  :boxShadow="false"
                  style="z-index: 100"/>
  </div>
</template>

<script>
export default {
  name: 'Editor',
  data() {
    return {
      label: '',
      labelArr: ['java', 'c++', '计算机'],
      articleInfoModal: {
        show: false
      },
      articleAddForm: {
        userId: 1,
        resourceTypeId: 5,
        title: '',
        brief: '',
        label: '',
        cover: 'http://img.angus-liu.cn/avatar/avatar01.jpg',
        markdownContent: '',
        htmlContent: '',
        remark: ''
      },
      cityList: [{
        value: 4,
        label: '社团文章'
      }, {
        value: 5,
        label: '校园文章'
      }],
      model1: ''
    };
  },
  methods: {
    contentChange(value, render) {
      this.articleAddForm.markdownContent = value;
      this.articleAddForm.htmlContent = render;
    },
    saveArticle(value, render) {
      console.log('保存草稿');
    },
    addLabel() {
      this.labelArr.push(this.label);
      this.label = '';
    },
    removeLabel(event, name) {
      const index = this.labelArr.indexOf(name);
      this.labelArr.splice(index, 1);
    },
    submitArticle() {
      this.articleAddForm.label = JSON.stringify(this.labelArr);
      this.$axios.post('/api/article', this.articleAddForm)
        .then(res => {
          console.log(res.data);
        });
    },
    ok() {
    },
    cancel() {
    }
  },
  mounted() {
  }
};
</script>

<style lang="less" scoped>
.container {
  height: 100%;

  .article-info-title {
    margin-bottom: 2px;
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
