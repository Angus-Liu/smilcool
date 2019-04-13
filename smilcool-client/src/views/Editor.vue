<template>
  <Tabs class="tabs-container" :animated="false">
    <TabPane label="信息" name="name1">
      <Form ref="articleAddForm" :model="articleAddForm" :label-width="40">
        <FormItem label="标题" prop="title">
          <Input v-model="articleAddForm.title" placeholder="Enter something..."></Input>
        </FormItem>
        <FormItem label="简介" prop="brief">
          <Input type="textarea" v-model="articleAddForm.brief" placeholder="Enter something..."></Input>
        </FormItem>
        <FormItem label="封面" prop="cover">
          <Upload action="//jsonplaceholder.typicode.com/posts/">
            <Button icon="ios-cloud-upload-outline">Upload files</Button>
          </Upload>
        </FormItem>
        <FormItem label="标签" prop="label">
          <Row>
            <Tag v-for="(item, index) in labelArr" :key="index" type="dot" color="primary" :name="item"
                 closable @on-close="removeLabel">
              {{item}}
            </Tag>
            <Input prefix="ios-bookmark-outline" v-model="label" placeholder="添加标签" style="width: 100px"
                   @on-enter="addLabel"/>
          </Row>
        </FormItem>
        <FormItem>
          <Button shape="circle" icon="ios-cloud-upload-outline" @click="submitArticle">提交</Button>
        </FormItem>
      </Form>
    </TabPane>
    <TabPane label="正文" name="name2">
      <div class="editor-container">
        <mavon-editor class="editor" v-model="articleAddForm.markdownContent" @change="contentChange"/>
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

  .action-container {
    height: 50px;
    margin-bottom: 10px;
  }

  .editor-container {
    padding: 0 7px;
    height: 800px;

    .editor {
      height: 100%;
    }
  }
}
</style>
