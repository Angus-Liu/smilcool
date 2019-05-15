<template>
  <div class="container">
    <Row>
      <iCol class="column" span="18">
        <article>
          <!-- 正文 -->
          <section>
            <h1 class="article-title">{{articlePage.article.title}}</h1>
            <p class="article-time">{{articlePage.article.createTime}}</p>
            <div class="markdown-body article-content" v-html="articlePage.article.htmlContent"></div>
            <div class="comment-input">
              <Input ref="commentInput" v-model="comment.value" type="textarea" :rows="3" placeholder="添加评论"
                     @on-enter="addComment"/>
            </div>
          </section>
          <!-- 中文结束 -->
          <!-- 评论 -->
          <section>
            <div class="article-comment">
              <h3 is="sui-header" dividing>评论列表</h3>
              <sui-comment-group>
                <sui-comment v-for="comment in articlePage.commentList" :key="comment.id">
                  <sui-comment-avatar :src="comment.postUser.avatar"/>
                  <sui-comment-content>
                    <a is="sui-comment-author">{{comment.postUser.nickname}}</a>
                    <sui-comment-metadata>
                      <Time :time="comment.createTime"/>
                    </sui-comment-metadata>
                    <sui-comment-text>{{comment.content}}</sui-comment-text>
                    <sui-comment-actions>
                      <sui-comment-action @click="reply(comment.id, comment.postUser)">回复</sui-comment-action>
                    </sui-comment-actions>
                  </sui-comment-content>
                  <sui-comment-group v-if="comment.children.length !== 0">
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
                          <sui-comment-action @click="reply(comment.id, child.postUser)">回复</sui-comment-action>
                        </sui-comment-actions>
                      </sui-comment-content>
                    </sui-comment>
                  </sui-comment-group>
                </sui-comment>
              </sui-comment-group>
            </div>
          </section>
          <!-- 评论结束 -->
        </article>
      </iCol>
      <iCol class="column" span="6">
        <!-- 发布用户信息 -->
        <sui-card class="card">
          <sui-image style="width: 100%;" :src="articlePage.user.avatar"/>
          <sui-card-content>
            <sui-card-header>{{articlePage.user.nickname}}</sui-card-header>
            <sui-card-meta>{{articlePage.user.username}}</sui-card-meta>
            <sui-card-description>{{articlePage.user.sign}}</sui-card-description>
          </sui-card-content>
          <sui-card-content extra>
            <sui-icon name="user"/>
            75 Friends
            <span slot="right">{{articlePage.user.createTime}}</span>
          </sui-card-content>
        </sui-card>
        <!-- 发布用户信息结束 -->
        <!-- 文章标签信息 -->
        <sui-card>
          <sui-card-content>
            <a is="sui-label" basic color="blue" v-for="(tag, index) in articlePage.article.tags" :key="index">
              {{tag}}
            </a>
          </sui-card-content>
        </sui-card>
        <!-- 文章标签信息结束 -->
        <sui-card>
          <sui-card-content>
            相关文章
          </sui-card-content>
        </sui-card>
      </iCol>
    </Row>
  </div>
</template>

<script>
import SimpleCard from '@/components/common/SimpleCard'

export default {
  name: 'Viewer',
  props: ['id'],
  components: {
    SimpleCard
  },
  data() {
    return {
      articlePage: {
        article: {
          'id': 1,
          'userId': 1,
          'resourceId': 4,
          'articleCategory': '校园文章',
          'title': '测试文章',
          'brief': '测试文章',
          'cover': 'http://img.angus-liu.cn/avatar/avatar01.jpg',
          'createTime': '2019-05-13',
          'updateTime': '2019-05-15',
          'tags': ['测试标签1', '测试标签2'],
          'markdownContent': '测试文章',
          'htmlContent': '测试文章'
        },
        user: {
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
          'updateTime': '2019-04-29'
        },
        resource: {
          'id': 4,
          'userId': 1,
          'resourceDicType': '文章类别',
          'resourceDicItem': '校园文章',
          'zanCount': 0,
          'pvCount': 0,
          'commentCount': 2,
          'state': '正常',
          'remark': null,
          'createTime': '2019-05-13 10:43:05',
          'updateTime': '2019-05-15 10:34:14',
          'deleted': false
        },
        commentList: [
          {
            'resourceId': 4,
            'parentId': null,
            'id': 6,
            'userId': 2,
            'postUser': {
              'id': 2,
              'username': 'angus',
              'nickname': 'Angus-Liu',
              'avatar': 'http://img.angus-liu.cn/avatar/avatar07.png'
            },
            'replyUserId': null,
            'replyUser': null,
            'content': '测试评论\n',
            'createTime': '2019-05-15 10:34:02',
            'children': [
              {
                'resourceId': 4,
                'parentId': 6,
                'id': 7,
                'userId': 2,
                'postUser': {
                  'id': 2,
                  'username': 'angus',
                  'nickname': 'Angus-Liu',
                  'avatar': 'http://img.angus-liu.cn/avatar/avatar07.png'
                },
                'replyUserId': 2,
                'replyUser': {
                  'id': 2,
                  'username': 'angus',
                  'nickname': 'Angus-Liu',
                  'avatar': 'http://img.angus-liu.cn/avatar/avatar07.png'
                },
                'content': '测试回复\n',
                'createTime': '2019-05-15 10:34:14',
                'children': null
              }
            ]
          }
        ],
      },
      comment: {
        resourceId: null,
        parentId: null,
        userId: 2,
        replyUserId: null,
        value: '',
        content: ''
      }
    };
  },
  methods: {
    reply(parentId, replyUser) {
      this.comment.parentId = parentId;
      this.comment.replyUserId = replyUser.id;
      // TODO 是否可以只改 placeholder 不用这么麻烦
      this.comment.value = `@${replyUser.nickname} `;
      this.$refs.commentInput.focus();
    },
    getArticle() {
      this.$axios.get(`/api/article/${this.id}/page`)
        .then(res => {
          let result = res.data;
          this.articlePage = result.data;
          this.articlePage.article.tags = JSON.parse(this.articlePage.article.tags);
          // this.articlePage.user.avatar += '?imageView2/1/w/50/h/50/q/75';
        });
    },
    getCommentList() {
      this.$axios.get(`/api/${this.articlePage.article.resourceId}/comment`)
        .then(res => {
          let result = res.data;
          this.articlePage.commentList = result.data;
        });
    }
    ,
    addComment() {
      this.comment.resourceId = this.articlePage.article.resourceId;
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
      console.log(this.comment.content);
      this.$axios.post('/api/comment', this.comment)
        .then(res => {
          this.comment = {
            resourceId: null,
            parentId: null,
            userId: 2,
            replyUserId: null,
            value: '',
            content: ''
          };
          let result = res.data;
          console.log(result);
          this.getCommentList();
        });
    }
  },
  mounted() {
    this.getArticle();
  }
};
</script>

<style lang="less" scoped>

.container {
  width: 1140px;
  margin: 10px auto;
  padding: 5px;
  /*background: #eee;*/

  .column {
    padding: 5px;
  }
}

article {
  section {
    margin-bottom: 10px;
    padding: 2em;
    background: #fefefe;
    border-radius: 2px;


    .article-title {
      text-align: center;
      padding-bottom: 10px;
      border-bottom: 1px dashed #eee;
    }

    .article-time {
      text-align: center;
      color: #aaa;
    }

    .article-comment {
    }
  }
}

.card {
  width: 100%;
}
</style>
