<template>
  <div class="container">
    <Row class="article-menu">
      <iCol class="article-category" span="18">
        <a is="sui-label" v-if="activeArticleCategory === '所有'" color="teal" @click="selectCategory('所有')">
          所有
        </a>
        <a is="sui-label" v-else @click="selectCategory('所有')">所有</a>
        <template v-for="item in articleCategory">
          <a is="sui-label" v-if="activeArticleCategory === item.name" color="teal"
             @click="selectCategory(item.name)">{{item.name}}</a>
          <a is="sui-label" v-else @click="selectCategory(item.name)">{{item.name}}</a>
        </template>
      </iCol>
      <iCol span="6">
        <sui-input class="fluid" placeholder="搜索文章" icon="search"
                   v-model="param.general" @keydown.enter="getArticlePage(param)"/>
      </iCol>
    </Row>
    <Row>
      <iCol>
        <sui-card class="fluid">
          <sui-card-content>
            <sui-feed class="article-feed">
              <sui-feed-event v-for="article in articlePage.records" :key="article.id">
                <sui-feed-label :image="article.user.avatar"/>
                <sui-feed-content>
                  <sui-feed-summary>
                    <router-link :to="'/user/' + article.user.id">{{article.user.nickname}}</router-link>
                    发表{{article.articleCategory}}
                    <router-link :to="'/article/' + article.id">{{article.title}}</router-link>
                    <sui-feed-date>
                      <Time :time="article.createTime"/>
                    </sui-feed-date>
                  </sui-feed-summary>
                  <sui-feed-extra text class="article-brief">
                    {{article.brief}}
                  </sui-feed-extra>
                  <sui-feed-meta>
                    <sui-feed-like>
                      👍 {{article.resource.zanCount}}
                    </sui-feed-like>
                    <sui-feed-like>
                      💬 {{article.resource.commentCount}}
                    </sui-feed-like>
                  </sui-feed-meta>
                </sui-feed-content>
              </sui-feed-event>
            </sui-feed>
          </sui-card-content>
        </sui-card>
        <sui-button basic content="加载更多" @click="loadMore"
                    :disabled="param.current >= articlePage.pages"/>
      </iCol>
    </Row>
  </div>
</template>

<script>
export default {
  name: 'Article',
  data() {
    return {
      activeArticleCategory: '所有',
      articleCategory: [{
        'name': '测试',
        'code': '-1'
      }],
      param: {
        // query 参数
        articleCategory: null,
        general: null,
        // page & order 参数
        desc: 'create_time',
        current: 1
      },
      articlePage: {
        'records': [{
          'id': -1,
          'userId': -1,
          'resourceId': -1,
          'articleCategory': '测试文章',
          'title': '测试标题',
          'brief': '测试标题',
          'createTime': '2019-05-13',
          'user': {
            'id': -1,
            'username': 'anonymous',
            'nickname': '游客',
            'avatar': require('../../assets/img/avatar/anonymous-avatar.jpg'),
            'sex': '保密',
            'sign': '一句话介绍自己'
          },
          'resource': {
            'id': -1,
            'zanCount': 0,
            'pvCount': 0,
            'commentCount': 0
          }
        }],
        'total': 7,
        'size': 10,
        'current': 1,
        'searchCount': true,
        'pages': 1
      },
    }
  },
  methods: {
    // 类别切换
    selectCategory(articleCategory) {
      this.activeArticleCategory = articleCategory;
      if (articleCategory === '所有') {
        this.param.articleCategory = null;
      } else {
        this.param.articleCategory = articleCategory;
      }
      this.param.current = 1;
      this.getArticlePage(this.param);
    },
    // 获取文章类别
    getArticleCategory() {
      this.$axios.get('/api/dic/item', { dicTypeCode: 'article-category' })
        .then(res => {
          let result = res.data;
          this.articleCategory = result.data;
        })
    },
    // 获取文章分页
    getArticlePage(param) {
      this.$axios.get('/api/article/page', param)
        .then(res => {
          let result = res.data;
          this.articlePage = result.data;
        });
    },
    // 加载更多
    loadMore() {
      this.param.current++;
      this.$axios.get('/api/article/page', this.param)
        .then(res => {
          let result = res.data;
          let morePage = result.data;
          this.articlePage.records.push(...morePage.records);
        });
    },
  },
  mounted() {
    this.getArticleCategory();
    this.getArticlePage(this.param);
  }
}
</script>

<style lang="less" scoped>
.container {
  width: 1140px;
  margin: 5px auto;

  .ivu-col {
    padding: 7px;
  }

  .article-menu {
    .article-category {
      .label {
        padding: 12px 15px;
        font-size: 1em;
        margin: 0 7px 7px 0;
      }
    }
  }

  .ui.feed > .event > .content .extra.text {
    max-width: 700px;
  }
}
</style>
