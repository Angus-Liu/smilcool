<template>
  <div>
    <article>
      <div class="child-container">
        <h1 class="article-title">{{article.title}}</h1>
        <div class="author"></div>
        <div class="markdown-body" v-html="article.htmlContent"></div>
        <div class="article-comment">
          <h3 is="sui-header" dividing>评论</h3>
          <sui-comment-group>
            <sui-comment>
              <sui-comment-avatar src="http://img.angus-liu.cn/avatar.png"/>
              <sui-comment-content>
                <a is="sui-comment-author">文刀</a>
                <sui-comment-metadata>
                  <span>5分钟前</span>
                </sui-comment-metadata>
                <sui-comment-text>写的太好了，为你点赞👍</sui-comment-text>
                <sui-comment-actions>
                  <sui-comment-action @click="reply">回复</sui-comment-action>
                </sui-comment-actions>
              </sui-comment-content>
            </sui-comment>

            <sui-comment>
              <sui-comment-avatar src="http://img.angus-liu.cn/avatar1.png"/>
              <sui-comment-content>
                <a is="sui-comment-author">赫本</a>
                <sui-comment-metadata>
                  <span>今天下午 5:42</span>
                </sui-comment-metadata>
                <sui-comment-text>不错不错，学到了👏</sui-comment-text>
                <sui-comment-actions>
                  <sui-comment-action @click="reply">回复</sui-comment-action>
                </sui-comment-actions>
              </sui-comment-content>
              <sui-comment-group>
                <sui-comment>
                  <sui-comment-avatar src="http://img.angus-liu.cn/avatar.png"/>
                  <sui-comment-content>
                    <a is="sui-comment-author">文刀</a>
                    <sui-comment-metadata>
                      <span>5分钟前</span>
                    </sui-comment-metadata>
                    <sui-comment-text>@赫本 哇，女神，居然能在这碰到你😍</sui-comment-text>
                    <sui-comment-actions>
                      <sui-comment-action @click="reply">回复</sui-comment-action>
                    </sui-comment-actions>
                  </sui-comment-content>
                </sui-comment>
                <sui-comment id="comment-12">
                  <sui-comment-avatar src="http://img.angus-liu.cn/avatar1.png"/>
                  <sui-comment-content>
                    <a is="sui-comment-author">赫本</a>
                    <sui-comment-metadata>
                      <span>刚刚</span>
                    </sui-comment-metadata>
                    <sui-comment-text>@文刀 说明很有缘分嘛😉</sui-comment-text>
                    <sui-comment-actions>
                      <sui-comment-action @click="reply">回复</sui-comment-action>
                    </sui-comment-actions>
                  </sui-comment-content>
                </sui-comment>
              </sui-comment-group>
            </sui-comment>
          </sui-comment-group>
        </div>
      </div>
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
      article: {}
    };
  },
  methods: {
    reply() {
      alert('回复');
    }
  },
  mounted() {
    this.$axios.get(`/api/article/${this.id}`)
      .then(res => {
        let result = res.data;
        this.article = result.data;
      });
  }
};
</script>

<style lang="less" scoped>
article {

  .child-container {
    width: 960px;
    margin: 10px;
    background: #fefefe;
  }

  .child-container {
    padding: 1em 2em;

    .article-title {
      padding-bottom: 10px;
      border-bottom: 1px dashed #eee;
    }

    .article-comment {
      margin-top: 30px;
    }
  }

}
</style>
