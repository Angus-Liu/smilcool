<template>
  <div class="container" ref="container">
    <Row>
      <iCol span="17">
        <!-- 文件操作菜单 -->
        <sui-menu>
          <a is="sui-menu-item" v-for="item in menu.items" :key="item" :content="item"
             :active="item === menu.active" @click="select(item)"/>
          <sui-menu-item position="right">
            <sui-input transparent icon="search" placeholder="搜索"
                       v-model="param.general" @keydown.enter="getFilePage(param)"/>
          </sui-menu-item>
        </sui-menu>
        <!-- 文件操作菜单 END -->
        <!-- 文件类别 -->
        <div class="file-category">
          <Tag type="dot" v-if="activeFileCategory === '所有'" color="#32C2BC" @click.native="selectCategory('所有')">所有
          </Tag>
          <Tag type="dot" v-else @click.native="selectCategory('所有')">所有</Tag>
          <template v-for="item in fileCategory">
            <Tag type="dot" v-if="item.name === activeFileCategory" color="#32C2BC"
                 @click.native="selectCategory(item.name)">
              {{item.name}}
            </Tag>
            <Tag type="dot" v-else @click.native="selectCategory(item.name)">{{item.name}}</Tag>
          </template>
        </div>
        <!-- 文件类别 END -->
        <!-- 文件列表 -->
        <sui-card class="fluid file-card">
          <sui-message attached="top">文件列表</sui-message>
          <sui-card-content>
            <!-- 文件项 -->
            <sui-item-group>
              <sui-item v-for="file in filePage.records" :key="file.id">
                <sui-icon :name="getFileIcon(file.name)"/>
                <sui-item-content>
                  <sui-item-header>
                    <router-link :to="'/file/' + file.id" style="color: #000;">{{file.title}}</router-link>
                    <sui-label basic color="teal">{{file.fileCategory}}</sui-label>
                  </sui-item-header>
                  <sui-item-meta>
                    <span class="file-user-nickname">
                      <router-link :to="'/user/' + file.user.id" style="color: #555">{{file.user.nickname}}</router-link>
                    </span>
                    <span class="file-create-time"><Time :time="file.createTime"/></span>
                    <a :href="file.url" :download="file.name" @click="downloadFile(file)">⏬ {{file.name}}</a>
                  </sui-item-meta>
                  <sui-item-description>
                    <p>{{file.description}}</p>
                  </sui-item-description>
                  <sui-item-extra>
                    <span class="file-size">大小：{{file.size}}</span>
                    <span class="file-download-count">下载：{{file.downloadCount}}</span>
                    <span class="file-resource-zan-count" @click="addZan(file.resource)">
                      👍 {{file.resource.zanCount}}
                    </span>
                    <span class="file-resource-comment-count" @click="getCommentList(file.resource)">
                      💬 {{file.resource.commentCount}}
                    </span>
                  </sui-item-extra>
                </sui-item-content>
              </sui-item>
            </sui-item-group>
            <!-- 文件项 END -->
          </sui-card-content>
          <sui-button-group attached="bottom" basic>
            <sui-button content="上一页" icon="left chevron" label-position="left" @click="previousPage"
                        :disabled="this.param.current <= 1"/>
            <sui-button-or/>
            <sui-button content="下一页" icon="right chevron" label-position="right" @click="nextPage"
                        :disabled="this.param.current >= this.filePage.pages"/>
          </sui-button-group>
        </sui-card>
        <!-- 文件列表 END -->
      </iCol>
      <iCol span="7">
        <!-- 消息提示 -->
        <sui-message>
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
    <Modal v-model="fileAddModal.show" title="上传文件" width="600" scrollable>
      <Form :model="fileAddModal.form" :label-width="50">
        <FormItem label="类别" required>
          <Select v-model="fileAddModal.form.fileCategory" size="large">
            <Option v-for="item in fileCategory" :key="item.name" :value="item.name" :label="item.name"/>
          </Select>
        </FormItem>
        <FormItem label="标题" required>
          <Input v-model="fileAddModal.form.title" size="large" placeholder="醒目的标题能吸引更多人下载"/>
        </FormItem>
        <FormItem label="描述" required>
          <Input v-model="fileAddModal.form.description" type="textarea" size="large"
                 :autosize="{minRows: 5,maxRows: 10}" placeholder="简短的描述有助于他人了解资料的用途"/>
        </FormItem>
        <FormItem label="文件" required style="margin-bottom: 0">
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
        <Button type="text" @click="resetFileAddModal">取消</Button>
        <Button @click="addFile">确定分享</Button>
      </template>
    </Modal>
    <!-- 文件上传模态框 END -->
    <!-- 评论模态框 -->
    <Modal v-model="commentAddModal.show" title="发表评论" width="600" footer-hide>
      <Input ref="commentInput" v-model="commentAddModal.form.value" type="textarea" :rows="3" placeholder="添加评论"
             @on-enter="addComment"/>
      <sui-comment-group class="comment-group">
        <sui-comment v-for="comment in commentList" :key="comment.id">
          <sui-comment-avatar :src="comment.postUser.avatar"/>
          <sui-comment-content>
            <a is="sui-comment-author">{{comment.postUser.nickname}}</a>
            <sui-comment-metadata>
              <Time :time="comment.createTime"/>
            </sui-comment-metadata>
            <sui-comment-text>{{comment.content}}</sui-comment-text>
            <sui-comment-actions>
              <sui-comment-action @click="replyComment(comment.id, comment.postUser)">回复</sui-comment-action>
            </sui-comment-actions>
          </sui-comment-content>
          <!-- 子评论 -->
          <sui-comment-group v-if="comment.children.length > 0">
            <sui-comment v-for="child in comment.children" :key="child.id">
              <sui-comment-avatar :src="child.postUser.avatar"/>
              <sui-comment-content>
                <a is="sui-comment-author">{{child.postUser.nickname}}</a>
                <sui-comment-metadata>
                  <Time :time="child.createTime"/>
                </sui-comment-metadata>
                <sui-comment-text>
                  <a :href="child.replyUser.id">@{{child.replyUser.nickname}}</a>
                  {{child.content}}
                </sui-comment-text>
                <sui-comment-actions>
                  <sui-comment-action @click="replyComment(comment.id, child.postUser)">回复</sui-comment-action>
                </sui-comment-actions>
              </sui-comment-content>
            </sui-comment>
          </sui-comment-group>
          <!-- 子评论 END -->
        </sui-comment>
      </sui-comment-group>
    </Modal>
    <!-- 评论模态框 END -->
  </div>
</template>

<script>

export default {
  name: 'File',
  data() {
    return {
      menu: {
        active: '最新',
        items: ['最新', '最热']
      },
      localStorage: '/api/local-storage/upload',
      activeFileCategory: '所有',
      fileCategory: [{
        name: '测试',
        code: 'test'
      }],
      param: {
        // 查询参数
        fileCategory: null,
        general: null,
        // page & order 参数
        desc: 'create_time',
        current: 1
      },
      filePage: {
        'records': [
          {
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
            'createTime': '2019-05-13 09:18:13',
            'user': {
              'id': 1,
              'username': 'admin',
              'nickname': '管理员',
              'avatar': 'http://img.angus-liu.cn/avatar/avatar07.png',
              'sign': '一句话介绍自己'
            },
            'resource': {
              'id': 2,
              'zanCount': 0,
              'pvCount': 0,
              'commentCount': 0
            }
          }],
        'total': 10,
        'size': 10,
        'current': 1,
        'searchCount': true,
        'pages': 1
      },
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
      },
      commentList: [{
        id: -1,
        content: '测试评论',
        createTime: '2019-05-21 20:15:39',
        postUser: {
          id: -1,
          username: 'admin',
          nickname: '管理员',
          avatar: 'http://img.angus-liu.cn/avatar/avatar07.png'
        },
        children: [
          {
            id: -2,
            content: '测试回复',
            createTime: '2019-05-21 20:15:46',
            postUser: {
              id: 1,
              username: 'admin',
              nickname: '管理员',
              avatar: 'http://img.angus-liu.cn/avatar/avatar07.png'
            },
            replyUser: {
              id: 1,
              username: 'admin',
              nickname: '管理员',
              avatar: 'http://img.angus-liu.cn/avatar/avatar07.png'
            }
          }
        ]
      }],
      commentAddModal: {
        show: false,
        form: {
          parentId: null,
          resourceId: null,
          replyUserId: null,
          value: '',
          content: ''
        }
      },
      currentResource: {}
    }
  },
  methods: {
    // 菜单切换
    select(item) {
      this.menu.active = item;
      if (item === '最新') {
        this.param.desc = 'create_time';
      } else {
        this.param.desc = 'download_count, comment_count, zan_count';
      }
      this.param.current = 1;
      this.getFilePage(this.param);
    },
    // 类别切换
    selectCategory(fileCategory) {
      this.activeFileCategory = fileCategory;
      if (fileCategory === '所有') {
        this.param.fileCategory = null;
      } else {
        this.param.fileCategory = fileCategory;
      }
      this.param.current = 1;
      this.getFilePage(this.param);
    },
    // 初始化文件添加模态框
    resetFileAddModal() {
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
    },
    // 获取文件类别
    getFileCategory() {
      this.$axios.get('/api/dic/item', { dicTypeCode: 'file-category' })
        .then(res => {
          let result = res.data;
          this.fileCategory = result.data;
        });
    },
    // 获取文件分页
    getFilePage(param) {
      this.$axios.get('/api/file/page', param)
        .then(res => {
          let result = res.data;
          this.filePage = result.data;
        })
    },
    // 根据文件名获取文件图标样式
    getFileIcon(filename) {
      let suffix = filename.substr(filename.lastIndexOf('.') + 1);
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
            this.resetFileAddModal();
            this.getFilePage(this.param);
          }
        })
    },
    // 文件下载
    downloadFile(file) {
      this.$axios.put(`/api/file/${file.id}/download-count`)
        .then(res => file.downloadCount++);
    },
    // 点赞
    addZan(resource) {
      this.$axios.post('/api/zan', { resourceId: resource.id })
        .then(res => resource.zanCount++);
    },
    // 获取评论
    getCommentList(resource) {
      this.currentResource = resource;
      this.$axios.get(`/api/comment/${resource.id}`)
        .then(res => {
          let result = res.data;
          this.commentList = result.data;
          // 展示评论
          this.commentAddModal.show = true;
        });
    },
    // 初始化评论
    initComment() {
      this.commentAddModal.form = {
        resourceId: null,
        parentId: null,
        replyUserId: null,
        value: '',
        content: ''
      }
    },
    // 添加评论
    addComment() {
      // 设置资源 ID
      this.commentAddModal.form.resourceId = this.currentResource.id;
      // 判断是评论还是回复
      if (this.commentAddModal.form.value.startsWith('@')
        && this.commentAddModal.form.replyUserId !== null) {
        // 回复时去掉评论内容中的回复用户名
        let index = this.commentAddModal.form.value.indexOf(' ');
        this.commentAddModal.form.content = this.commentAddModal.form.value.substr(index + 1);
      } else {
        this.commentAddModal.form.parentId = null;
        this.commentAddModal.form.replyUserId = null;
        this.commentAddModal.form.content = this.commentAddModal.form.value;
      }
      this.$axios.post('/api/comment', this.commentAddModal.form)
        .then(res => {
          this.initComment();
          this.currentResource.commentCount++;
          this.getCommentList(this.currentResource);
        });
    },
    // 回复评论
    replyComment(parentId, replyUser) {
      this.commentAddModal.form.parentId = parentId;
      this.commentAddModal.form.replyUserId = replyUser.id;
      this.commentAddModal.form.value = `@${replyUser.nickname} `;
      this.$refs.commentInput.focus();
    },
    // 上一页
    previousPage() {
      this.param.current--;
      this.getFilePage(this.param);
      this.$refs.container.scrollIntoView();
    },
    // 下一页
    nextPage() {
      this.param.current++;
      this.getFilePage(this.param);
      this.$refs.container.scrollIntoView();
    }
  },
  mounted() {
    this.getFileCategory();
    this.getFilePage(this.param);
  }
}
</script>

<style lang="less" scoped>
.container {
  width: 1200px;
  margin: 10px auto;

  .ivu-col {
    padding: 7px;
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

        .header {
          line-height: 30px;
        }

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

  .file-card {
    .file-user-nickname,
    .file-create-time,
    .file-size,
    .file-download-count,
    .file-resource-zan-count,
    .file-resource-comment-count {
      display: inline-block;
      width: 150px;
    }

    .file-resource-zan-count,
    .file-resource-comment-count {
      width: 60px;
      cursor: pointer;
    }

    .file-resource-zan-count:hover,
    .file-resource-comment-count:hover {
      color: #000;
    }
  }
}

.comment-group {
  max-height: 600px;
  overflow: auto;
}
</style>
