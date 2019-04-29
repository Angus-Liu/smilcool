<template>
  <div class="container">
    <Row>
      <iCol span="10">
        <Card title="热点资讯">
          <template #extra>
            <router-link to="">查看更多</router-link>
          </template>
          <ul class="article-list">
            <li class="article-item" v-for="article in articleList" :key="article.id">
              <div class="article">
                <img class="article-cover" :src="article.cover">
                <div class="article-content">
                  <router-link :to="'/viewer/' + article.id">
                    <h3 class="article-title">{{article.title}}</h3>
                  </router-link>
                  <p class="article-brief">{{article.brief}}</p>
                  <span class="article-author">
                  <sui-image src="http://img.angus-liu.cn/avatar.png" avatar/>
                  <span>文刀</span>
                </span>
                </div>
              </div>
            </li>
          </ul>
        </Card>
      </iCol>
      <iCol span="7" offset="1">
        <Card title="今日要闻">
          <template #extra>
            <router-link to="">查看更多</router-link>
          </template>
          <div style="text-align:center">
            <h3>A high quality UI Toolkit based on Vue.js</h3>
          </div>
        </Card>
      </iCol>
    </Row>
  </div>
</template>

<script>
export default {
  name: 'Main',
  data() {
    return {
      articleList: [{
        id: 1,
        userId: 1,
        resourceId: 3,
        resourceTypeId: 4,
        title: '2019-2020 设计趋势·图形篇',
        brief: '2019-2020 设计趋势报告，源自我们平时日常设计工作的总结归纳，本次由ISUX首尔主笔新视角。',
        label: '',
        cover: 'http://img.angus-liu.cn/cover01.jpg',
        createTime: '2019-04-12T17:30:46.000+0000',
        updateTime: '2019-04-29T10:29:19.000+0000',
      }]
    };
  },
  methods: {
    getArticleList() {
      this.$axios.get('/api/article')
        .then(res => {
          let result = res.data;
          this.articleList = result.data;
        });
    }
  },
  mounted() {
    this.getArticleList();
  }
};
</script>

<style lang="less" scoped>
.container {
  width: 100%;
  /*height: 1000px;*/
  background: #ccc;
  padding: 5px;
}

.article-list {
  list-style-type: none;

  .article-item {
    margin-bottom: 12px;
    padding-bottom: 12px;
    border-bottom: 1px dashed #ccc;
  }

  .article-item:last-child {
    border-bottom: none;
    margin-bottom: 0;
    padding-bottom: 0;
  }
}

.article {
  overflow: hidden;

  .article-cover {
    float: left;
    height: 100px;
    width: 133px;
    border-radius: 3px;
    margin-right: 5px;
  }

  .article-content {
    width: calc(100% - 140px);
    float: left;

    .article-title {
      margin-top: 0;
      margin-bottom: 14px;
    }

    .article-brief {
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }
  }
}

</style>
