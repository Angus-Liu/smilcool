<template>
  <div class="container">
    <Row>
      <iCol span="17">
        <!-- 文件操作菜单 -->
        <sui-menu>
          <a is="sui-menu-item" v-for="item in items" :active="item === active" :key="item" :content="item"
             @click="select(item)"/>
          <sui-menu-item position="right">
            <sui-input transparent icon="search" placeholder="搜索"/>
          </sui-menu-item>
        </sui-menu>
        <!-- 文件操作菜单 END -->
        <!-- 文件类别 -->
        <div class="file-category">
          <Tag type="dot" v-if="name === '所有'" color="#32C2BC" @click.native="name ='所有'">所有</Tag>
          <Tag type="dot" v-else @click.native="name ='所有'">所有</Tag>
          <template v-for="(item, index) in fileCategory">
            <Tag type="dot" v-if="item.name === name" color="#32C2BC" @click.native="name = item.name">
              {{item.name}}
            </Tag>
            <Tag type="dot" v-else @click.native="name = item.name">{{item.name}}</Tag>
          </template>
        </div>
        <!-- 文件类别 END -->
        <!-- 文件列表 -->
        <sui-card class="fluid">
          <sui-message attached="top">文件列表</sui-message>
          <sui-card-content>
            <sui-item-group>
              <sui-item v-for="filePage in filePageList" :key="filePage.file.id">
                <sui-icon :name="getFileIcon(filePage.file.name)"/>
                <sui-item-content>
                  <sui-item-header>{{filePage.file.title}}</sui-item-header>
                  <sui-item-meta>
                    <span>{{filePage.file.name}}</span>
                  </sui-item-meta>
                  <sui-item-description>
                    <p>{{filePage.file.description}}</p>
                  </sui-item-description>
                  <sui-item-extra>
                    大小：{{filePage.file.size}}
                  </sui-item-extra>
                </sui-item-content>
              </sui-item>
            </sui-item-group>
          </sui-card-content>
          <sui-button-group attached="bottom" basic>
            <sui-button content="上一页" icon="left chevron" label-position="left"/>
            <sui-button-or/>
            <sui-button content="下一页" icon="right chevron" label-position="right"/>
          </sui-button-group>
        </sui-card>
        <!-- 文件列表 END -->
      </iCol>
      <iCol span="7">
        <!-- 消息提示 -->
        <sui-message dismissable>
          <sui-message-header>
            <i class="handshake outline icon"/> 分享学习资料，让更多人获益。
          </sui-message-header>
          <p style="text-align:justify">
            一个人独行可以走得很快，一群人才会走得更远！我们鼓励学习交流，更鼓励资源分享。
            如果手里有比较好的学习资料，希望你能分享出来，让大家从中受益。
            与此同时，你也将会在这个平台上找到你所需要的学习资料。“分享”帮助你我探索更广阔的世界 🧐
          </p>
          <sui-button fluid basic color="teal" icon="upload" content="上传文件" @click="fileAddModal.show = true"/>
        </sui-message>
        <!-- 消息提示 END -->
      </iCol>
    </Row>
    <!-- 文件上传模态框 -->
    <Modal v-model="fileAddModal.show" title="上传文件" :mask-closable="false" width="600">
      <Form :model="fileAddModal.form" :label-width="50">
        <FormItem label="类别">
          <Select v-model="fileAddModal.form.fileCategory" size="large">
            <Option v-for="item in fileCategory" :key="item.name" :value="item.name" :label="item.name"/>
          </Select>
        </FormItem>
        <FormItem label="标题">
          <Input v-model="fileAddModal.form.title" size="large" placeholder="醒目的标题才能吸引人"/>
        </FormItem>
        <FormItem label="描述">
          <Input v-model="fileAddModal.form.description" type="textarea" size="large"
                 :autosize="{minRows: 5,maxRows: 10}" placeholder="简短的描述有助于他人了解资料的用途"/>
        </FormItem>
        <FormItem label="文件" style="margin-bottom: 0">
          <Upload
            ref="upload"
            type="drag"
            :action="localStorage"
            :data="{type: 'file'}"
            :before-upload="handleBeforeUpload"
            :on-success="handleSuccess"
            :on-error="handleError">
            <div style="padding: 20px 0">
              <Icon type="ios-cloud-upload" size="52" style="color: #32C2BC"/>
              <p>点击或拖拽文件进行上传</p>
            </div>
          </Upload>
        </FormItem>
      </Form>
      <template #footer>
        <Button type="text" @click="fileAddModal.show = false">取消</Button>
        <Button @click="addFile">确定分享</Button>
      </template>
    </Modal>
    <!-- 文件上传模态框 END -->
  </div>
</template>

<script>

export default {
  name: 'File',
  data() {
    return {
      active: '最新',
      items: ['最新', '最热'],
      localStorage: '/api/local-storage/upload',
      name: '所有',
      fileCategory: [{
        name: '测试',
        code: 'test'
      }],
      filePageList: [
        {
          'file': {
            'id': 1,
            'userId': 1,
            'resourceId': 2,
            'fileCategory': '计算机类',
            'title': '计算机类全套PPT，你值得拥有',
            'description': '计算机类全套PPT，你值得拥有',
            'name': '中北大学计算机类.ppt',
            'size': '200MB',
            'url': 'http://bkt.angus-liu.cn/中北大学计算机类.ppt',
            'downloadCount': 0,
            'createTime': '2019-05-13T09:18:13.000+0000'
          },
          'user': {
            'id': 1,
            'username': 'admin',
            'nickname': '管理员',
            'avatar': 'http://img.angus-liu.cn/avatar/avatar07.png',
            'sex': '保密',
            'birthday': '1970-01-01',
            'sign': '一句话介绍自己',
            'intro': '这个人很神秘，什么也没写',
            'grade': '未填写',
            'college': '未填写',
            'major': '未填写',
            'phone': null,
            'email': 'admin@admin.com',
            'state': 1,
            'remark': '超级管理员账户，由系统内定，请勿修改',
            'createTime': '2019-03-28',
            'updateTime': '2019-05-17'
          },
          'resource': {
            'id': 2,
            'userId': 1,
            'resourceDicType': '文件类别',
            'resourceDicItem': '计算机类',
            'zanCount': 0,
            'pvCount': 0,
            'commentCount': 0,
            'state': '正常',
            'remark': null,
            'createTime': '2019-05-13 09:18:13',
            'updateTime': '2019-05-13 09:18:13',
            'deleted': false
          },
          'commentList': []
        }
      ],
      fileAddModal: {
        show: false,
        form: {
          fileCategory: '',
          title: '',
          description: '',
          name: '',
          size: '',
          url: ''
        }
      }
    }
  },
  methods: {
    select(name) {
      this.active = name;
    },
    // 初始化
    init() {
      this.fileAddModal = {
        show: false,
        form: {
          fileCategory: '',
          title: '',
          description: '',
          name: '',
          size: '',
          url: ''
        }
      };
      // 清空已上传文件
      this.$refs.upload.clearFiles();
      // 获取文件页面
      this.getFilePageList();
    },
    // 获取文件类别
    getFileCategory() {
      this.$axios.get('/api/dic/file-category/item')
        .then(res => {
          let result = res.data;
          this.fileCategory = result.data;
        });
    },
    // 获取文件页面
    getFilePageList() {
      this.$axios.get('/api/file/page')
        .then(res => {
          let result = res.data;
          this.filePageList = result.data;
        })
    },
    // 根据文件名获取文件图标样式
    getFileIcon(filename) {
      let index = filename.lastIndexOf('.');
      let suffix = filename.substr(index + 1);
      switch (suffix) {
        case 'ppt':
        case 'pptx':
          return 'file powerpoint outline yellow';
        case 'doc':
        case 'docx':
          return 'file word outline blue';
        case 'xls':
        case 'xlsx':
          return 'file excel outline green';
        case 'pdf':
          return 'file pdf outline orange';
        default:
          return 'file alternate outline';
      }
    },
    // 文件上传前的回调
    handleBeforeUpload() {
      // 限制上传文件数量
      if (this.$refs.upload.fileList.length > 0) {
        this.$Notice.warning({ title: 'Warning', desc: '一次只能上传一份资料，若要更换，请删除已上传文件' });
        return false;
      }
      return true;
    },
    // 文件上传成功时的回调
    handleSuccess(res, file, fileList) {
      let uploadInfo = res.data;
      this.fileAddModal.form.name = uploadInfo.name;
      this.fileAddModal.form.size = uploadInfo.size;
      this.fileAddModal.form.url = uploadInfo.url;
    },
    // 文件上传失败时的回调
    handleError(err, res, file) {
      this.$Notice.error({ title: 'Oops', desc: file.name + ' 上传失败' });
    },
    // 文件添加
    addFile() {
      this.$axios.post('/api/file', this.fileAddModal.form)
        .then(res => {
          let result = res.data;
          if (result.success) {
            this.$Notice.success({ title: 'Bingo', desc: '分享成功' });
            this.init();
          }
        })
    }
  },
  mounted() {
    this.getFileCategory();
    this.getFilePageList();
  }
}
</script>

<style lang="less" scoped>
.container {
  width: 1200px;
  margin: 10px auto;
  padding: 5px;
  /*background: #ccc;*/

  .ivu-col {
    padding: 5px;
  }

  .file-category {
    .ivu-tag {
      border: 1px solid rgba(34, 36, 38, .15);
      box-shadow: 0 1px 2px 0 rgba(34, 36, 38, .15);
    }
  }

  .card {
    .items {
      padding: 0 10px;

      .item {
        margin: 20px 0;
        padding-bottom: 10px;
        border-bottom: 1px dashed #ddd;

        i {
          font-size: 5em;
          line-height: 1;
          vertical-align: middle;
        }
      }

      .item:last-child {
        padding-bottom: 0;
        border-bottom: none;
      }
    }
  }

  ul {
    list-style: none;

    li {
      display: inline-block;
      margin-right: 15px;
      color: #546a7e;
    }
  }
}
</style>
