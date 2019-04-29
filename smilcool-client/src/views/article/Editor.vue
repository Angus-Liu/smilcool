<template>
  <Tabs class="tabs-container" :animated="false">
    <TabPane label="信息">
      <div class="info-container">
        <Form ref="articleAddForm" :model="articleAddForm" :label-width="40">
          <FormItem label="标题" prop="title">
            <Input class="form-item" v-model="articleAddForm.title" placeholder="Enter something..."></Input>
          </FormItem>
          <FormItem label="简介" prop="brief">
            <Input class="form-item" type="textarea" v-model="articleAddForm.brief"
                   placeholder="Enter something..."></Input>
          </FormItem>
          <FormItem label="封面" prop="cover">
            <Upload action="//jsonplaceholder.typicode.com/posts/">
              <Button icon="ios-cloud-upload-outline">Upload files</Button>
            </Upload>
          </FormItem>
          <FormItem label="标签" prop="label">
            <Tag v-for="(item, index) in labelArr" :key="index" :name="item" @on-close="removeLabel"
                 closable type="dot" color="primary">{{item}}
            </Tag>
            <Input prefix="ios-bookmark-outline" v-model="label" placeholder="添加标签" style="width: 100px"
                   @on-enter="addLabel"/>
          </FormItem>
          <FormItem>
            <Button shape="circle" icon="ios-cloud-upload-outline" @click="submitArticle">提交</Button>
          </FormItem>
        </Form>
      </div>
    </TabPane>
    <TabPane label="正文">
      <div class="editor-container">
        <mavon-editor class="editor" v-model="articleAddForm.markdownContent" @change="contentChange" :boxShadow="false"/>
      </div>
    </TabPane>
  </Tabs>
</template>

<script>
export default {
  name: 'Editor',
  data() {
    return {
      label: '',
      labelArr: ['java', 'c++', '计算机'],
      articleAddForm: {
        userId: 1,
        resourceTypeId: 5,
        title: '',
        brief: '',
        label: '',
        cover: 'http://img.angus-liu.cn/hepburn.jpg',
        markdownContent: '',
        htmlContent: '',
        remark: ''
      }
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
    }
  },
  mounted() {
  }
};
</script>

<style lang="less" scoped>
.tabs-container {
  width: 1440px;
  margin: 0 auto;
  padding: 5px 0;

  .info-container {
    margin: 20px auto;
    height: 600px;
  }

  .editor-container {
    margin: 0 10px;
    height: 600px;

    .editor {
      height: 100%;
    }
  }
}
</style>
