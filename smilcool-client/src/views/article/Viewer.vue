<template>
  <div>
    <article>
      <section>
        <h1 class="article-title">{{articleInfo.article.title}}</h1>
        <span class="author"></span>
        <p class="markdown-body" v-html="articleInfo.article.htmlContent"></p>
        <div class="comment-input">
          <label>
            <Input ref="commentInput" v-model="comment.value" type="textarea" :rows="3" placeholder="添加评论"
                   @on-enter="addComment"/>
          </label>
        </div>
      </section>
      <section>
        <div class="article-comment">
          <h3 is="sui-header" dividing>评论列表</h3>
          <sui-comment-group>
            <sui-comment v-for="comment in articleInfo.commentList" :key="comment.id">
              <sui-comment-avatar :src="comment.postUser.avatar"/>
              <sui-comment-content>
                <a is="sui-comment-author">{{comment.postUser.nickname}}</a>
                <sui-comment-metadata>
                  <span>{{comment.createTime}}</span>
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
                      <span>{{child.createTime}}</span>
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
    </article>
    <aside>

    </aside>
  </div>
</template>

<script>
export default {
  name: 'Viewer',
  props: ['id'],
  data() {
    return {
      articleInfo: {
        article: {
          id: 1,
          userId: 1,
          resourceId: 1,
          resourceTypeId: 4,
          title: '2019-2020 设计趋势·图形篇',
          brief: '2019-2020 设计趋势报告，源自我们平时日常设计工作的总结归纳，本次由ISUX首尔主笔新视角。',
          label: '',
          cover: 'http://img.angus-liu.cn/cover01.jpg',
          createTime: '2019-04-12 17:30:46',
          updateTime: '2019-04-29 18:39:09',
          markdownContent: '',
          htmlContent: ''
        },
        commentList: [{
          resourceId: 1,
          parentId: null,
          id: 1,
          userId: 1,
          postUser: {
            id: 1,
            username: 'admin',
            nickname: '管理员',
            avatar: 'http://img.angus-liu.cn/avatar/avatar07.png'
          },
          replyUserId: null,
          replyUser: null,
          content: '写的太好了，为你点赞👍',
          createTime: '2019-04-23 20:59:24',
          children: []
        }]
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
      this.comment.value = `@${replyUser.nickname} `;
      this.$refs.commentInput.focus();
    },
    getArticle() {
      this.$axios.get(`/api/article/${this.id}`)
        .then(res => {
          let result = res.data;
          this.articleInfo = result.data;
        });
    },
    getCommentList() {
      this.$axios.get(`/api/${this.articleInfo.article.resourceId}/comment`)
        .then(res => {
          let result = res.data;
          this.articleInfo.commentList = result.data;
        });
    }
    ,
    addComment() {
      this.comment.resourceId = this.articleInfo.article.resourceId;
      if (this.comment.replyUserId !== null) {
        let index = this.comment.value.indexOf(' ');
        this.comment.content = this.comment.value.substr(index + 1);
      } else {
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
article {
  width: 960px;

  section {
    margin: 10px;
    padding: 2em;
    background: #fefefe;
    border-radius: 2px;

    .article-title {
      padding-bottom: 10px;
      border-bottom: 1px dashed #eee;
    }

    .article-comment {
    }
  }
}
</style>
