<template>
  <div class="container">
    <Row>
      <iCol span="18">
        <!-- 失物寻物详情 -->
        <article>

          <sui-message>
            物品详情
          </sui-message>

          <sui-card class="fluid file-card">
            <sui-card-content class="file-content">
              <!-- 评论列表 -->
              <div class="file-comment">
                <!-- 评论框 -->
                <sui-form class="comment-input-form">
                  <sui-form-field>
                    <Input ref="commentInput" v-model="comment.value" type="textarea" :rows="3" placeholder="添加评论"
                           @keydown.ctrl.enter.native="addComment"/>
                  </sui-form-field>
                  <sui-form-field>
                    <span class="resource-info-item" @click="addZan(lostFound.resource)">
                      👍 {{lostFound.resource.zanCount}}
                    </span>
                    <span class="resource-info-item">
                      💬 {{lostFound.resource.commentCount}}
                    </span>
                    <sui-button basic floated="right" content="评论(Ctrl+Enter)" @click.prevent="addComment"/>
                  </sui-form-field>
                </sui-form>
                <!-- 评论框END -->
                <!-- 父评论 -->
                <sui-comment-group>
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
                            <sui-comment-action @click="replyComment(comment.id, child.postUser)">回复
                            </sui-comment-action>
                          </sui-comment-actions>
                        </sui-comment-content>
                      </sui-comment>
                    </sui-comment-group>
                    <!-- 子评论 END -->
                  </sui-comment>
                </sui-comment-group>
                <!-- 父评论 END -->
              </div>
              <!-- 评论列表 END -->
            </sui-card-content>
          </sui-card>
        </article>
        <!-- 失物寻物详情 END -->
      </iCol>
      <iCol span="6">
        <!-- 发布用户信息 -->
        <sui-card class="fluid user-info-card">
          <sui-image style="width: 100%;" :src="lostFound.user.avatar"/>
          <sui-card-content>
            <sui-card-header>
              <router-link :to="'/user/' + lostFound.user.id">
                {{lostFound.user.nickname}}
              </router-link>
            </sui-card-header>
            <sui-card-meta>{{lostFound.user.username}}</sui-card-meta>
            <sui-card-description>{{lostFound.user.sign}}</sui-card-description>
          </sui-card-content>
          <sui-card-content extra>
            <sui-icon name="user"/>
            发布用户
          </sui-card-content>
        </sui-card>
        <!-- 发布用户信息 END -->
        <!-- 操作按钮 -->
        <div class="actions-buttons" v-if="$store.state.user && lostFound.userId === $store.state.user.id">
          <sui-button-group>
            <sui-button icon="pencil" content="编辑" basic positive fluid/>
            <sui-button icon="delete" content="删除" basic negative fluid/>
          </sui-button-group>
        </div>
        <!-- 操作按钮 END -->
        <!-- 相关文件 -->
        <sui-card class="fluid">
          <sui-message attached="top" content="他的闲置"/>
          <sui-card-content>
            暂无更多二手物品
          </sui-card-content>
        </sui-card>
        <!-- 相关文件 END -->
      </iCol>
    </Row>
  </div>
</template>

<script>
export default {
  name: 'Detail',
  props: ['id'],
  data() {
    return {
      // 文件信息
      lostFound: {
        'id': 1,
        'userId': 1,
        'resourceId': 16,
        'lostFoundCategory': '寻物启事',
        'title': '四食堂丢失一个钱包',
        'description': '如图，钱包是黑色的，里面有少量现金和一张工行的卡',
        'itemName': '钱包',
        'time': '2019年05月17日  12:10',
        'address': '四食堂',
        'createTime': '2019-05-17 15:39:45',
        'images': '["https://img12.360buyimg.com/n7/jfs/t22831/61/2367409622/289066/5d9c3f/5b7d10feNe776b305.jpg"]',
        'user': {
          'id': 1,
          'username': 'admin',
          'nickname': '文刀',
          'avatar': 'http://img.angus-liu.cn/avatar/avatar07.png',
          'sign': '终其一生，我们都在自我救赎'
        },
        'resource': {
          'id': 16,
          'zanCount': 1,
          'pvCount': 0,
          'commentCount': 0
        }
      },
      // 评论信息
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
      comment: {
        parentId: null,
        resourceId: null,
        replyUserId: null,
        value: '',
        content: ''
      }
    };
  },
  methods: {
    // 获取文件信息
    getLostFound() {
      this.$axios.get(`/api/lost-found/${this.id}`)
        .then(res => {
          let result = res.data;
          this.lostFound = result.data;
          if (this.lostFound.images && this.lostFound.images.length > 0) {
            this.lostFound.images = JSON.parse(this.lostFound.images);
          }
          // 获取评论信息
          this.getCommentList();
        });
    },
    // 获取评论信息
    getCommentList() {
      this.$axios.get(`/api/comment/${this.lostFound.resourceId}`)
        .then(res => {
          let result = res.data;
          this.commentList = result.data;
        });
    },
    // 初始化评论
    initComment() {
      this.comment = {
        resourceId: null,
        parentId: null,
        replyUserId: null,
        value: '',
        content: ''
      }
    },
    // 添加评论
    addComment() {
      this.comment.resourceId = this.lostFound.resourceId;
      // 判断是评论还是回复
      if (this.comment.value.startsWith('@') && this.comment.replyUserId !== null) {
        // 回复时去掉评论内容中的回复用户名
        let index = this.comment.value.indexOf(' ');
        this.comment.content = this.comment.value.substr(index + 1);
      } else {
        this.comment.parentId = null;
        this.comment.replyUserId = null;
        this.comment.content = this.comment.value;
      }
      this.$axios.post('/api/comment', this.comment)
        .then(res => {
          this.initComment();
          this.getCommentList();
          this.lostFound.resource.commentCount++;
        });
    },
    // 回复评论
    replyComment(parentId, replyUser) {
      this.comment.parentId = parentId;
      this.comment.replyUserId = replyUser.id;
      this.comment.value = `@${replyUser.nickname} `;
      this.$refs.commentInput.focus();
    },
    // 点赞
    addZan(resource) {
      this.$axios.post('/api/zan', { resourceId: resource.id })
        .then(res => resource.zanCount++);
    }
  },
  mounted() {
    this.getLostFound();
  }
};
</script>

<style lang="less" scoped>
.container {
  width: 1140px;
  margin: 0 auto;
  padding: 5px;

  .ivu-col {
    padding: 7px;
  }
}

.file-card {
  margin-bottom: 10px;

  .file-content {
    padding: 2em 3em;
  }

  .resource-info-item {
    margin-right: 15px;
    cursor: pointer;
  }

  .resource-info-item:hover {
    color: #ff8364;
  }

  .file-comment {
    padding-top: 2em;

    .comment-input-form {
      overflow: hidden;
    }
  }
}

.user-info-card {
  .header {
    a {
      color: #373737;
    }
  }
}

.actions-buttons {
  text-align: center;

  .button {
    width: 134px;
  }
}
</style>
