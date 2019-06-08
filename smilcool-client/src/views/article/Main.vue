<template>
  <div class="container">
    <Row>
      <iCol span="14">
        <!-- 轮播图 -->
        <Carousel class="carousel-container" loop autoplay :autoplay-speed="5000" radius-dot>
          <CarouselItem v-for="(item, index) in carouselList" :key="index">
            <div class="carousel-item">
              <a href="http://cst.nuc.edu.cn/" target="_blank">
                <img class="carousel-img" :src="item">
              </a>
            </div>
          </CarouselItem>
        </Carousel>
        <!-- 轮播图 END -->
        <!-- 文章选项菜单 -->
        <sui-menu secondary>
          <sui-menu-item link v-for="item in menu.items" :key="item" :content="item"
                         :active="item === menu.active" @click="select(item)"/>
        </sui-menu>
        <!-- 文章选项菜单 END -->
        <!-- 文章列表 -->
        <sui-card class="fluid">
          <sui-message attached="top">
            <sui-message-header>
              <sui-icon name="list alternate" color="blue"/>
              文章列表
            </sui-message-header>
          </sui-message>
          <sui-card-content>
            <ul class="article-list">
              <li class="article-item" v-for="article in articlePage.records" :key="article.id">
                <span class="article-category">
                  <sui-label tag>{{article.articleCategory}}</sui-label>
                </span>
                <!--<a class="article-title" :href="'/article/' + article.id">{{article.title}}</a>-->
                <router-link class="article-title" :to="'/article/' + article.id">{{article.title}}</router-link>
                <span class="article-time">{{article.createTime}}</span>
              </li>
            </ul>
          </sui-card-content>
          <sui-card-content extra>
            <template #right>
              <router-link to="/article">
                查看更多
              </router-link>
            </template>
          </sui-card-content>
        </sui-card>
        <!-- 文章列表 END -->
      </iCol>
      <iCol span="10">
        <!-- 系统通告 -->
        <sui-message negative dismissable>
          <sui-message-header>Q：Smilcool是什么？</sui-message-header>
          <p style="text-align:justify">
            Smilcool 旨在以“资源分享”作为开端，以“校园社交”为目的，建立一个面向资讯发布及资源分享的网络
            社区，建立一个面向知识问答及内容讨论的交流平台，建立一个面向个人博客及好友交互的社交空间。提
            供一个纯粹的分享交流空间，挖掘校园社交新动力。
          </p>
          <sui-message-header>Q：如何发表文章？</sui-message-header>
          <p style="text-align:justify">
            注册并登录后，在头像下拉菜单中即可找到“发表文章功能”。请注意，本系统注重平等分享，友善交流，
            不要发布极具煽动性或个人主观色彩的文章。原则上，我们坚决反对抄袭行为，请知晓。快来写下你的第
            一篇文章吧。
          </p>
        </sui-message>
        <!-- 系统通告 END -->
        <!-- 文章类别 -->
        <sui-card class="fluid">
          <sui-message attached="top">
            <sui-message-header>
              <sui-icon name="bookmark" color="teal"/>
              文章类别
            </sui-message-header>
          </sui-message>
          <sui-card-content>
            <div class="article-category">
              <a is="sui-label" basic image v-for="(item, index) in articleCategory" :key="index"
                 style="margin:5px 10px 5px 0">
                <img :src="item.img">{{item.name}}
              </a>
            </div>
          </sui-card-content>
        </sui-card>
        <!-- 文章类别 END-->
        <!-- 热门标签 -->
        <sui-card class="fluid">
          <sui-message attached="top">
            <sui-message-header>
              <sui-icon name="tags" color="orange"/>
              热门标签
            </sui-message-header>
          </sui-message>
          <sui-card-content>
            <div class="article-hot-tag">
              <Tag color="orange" v-for="(tag, index) in hotTagList" :key="index" @click.native="toSearch(tag.name)">
                {{tag.name}}-{{tag.count}}
              </Tag>
            </div>
          </sui-card-content>
        </sui-card>
        <!-- 热门标签 END -->
        <!-- 最新评论 -->
        <sui-card class="fluid">
          <sui-message attached="top">
            <sui-message-header>
              <sui-icon name="comments" color="green"/>
              最新评论
            </sui-message-header>
          </sui-message>
          <sui-card-content>
            <sui-feed class="latest-comment-feed">
              <sui-feed-event v-for="item in articleLatestComment" :key="item.commentId">
                <sui-feed-label :image="item.userAvatar"/>
                <sui-feed-content>
                  <sui-feed-summary>
                    <router-link :to="'/user/' + item.userId">{{item.userNickname}}</router-link>
                    评论
                    <router-link :to="'/article/' + item.articleId">{{item.articleTitle}}</router-link>
                  </sui-feed-summary>
                  <sui-feed-extra text :content="item.commentContent"/>
                  <sui-feed-meta>
                    <Time :time="item.commentCreateTime"/>
                  </sui-feed-meta>
                </sui-feed-content>
              </sui-feed-event>
            </sui-feed>
          </sui-card-content>
        </sui-card>
        <!-- 最新评论 END -->
      </iCol>
    </Row>
  </div>
</template>

<script>
export default {
  name: 'Main',
  data() {
    return {
      menu: {
        active: '最新',
        items: ['最新', '最热']
      },
      carouselList: [
        '/local-storage/img/banner01.jpg',
        '/local-storage/img/banner02.jpg',
        '/local-storage/img/banner03.jpg',
        '/local-storage/img/banner04.jpg',
        '/local-storage/img/banner05.jpg',
        '/local-storage/img/banner06.jpg',
        '/local-storage/img/banner07.jpg',
        '/local-storage/img/banner08.jpg',
        '/local-storage/img/banner09.jpg',
        '/local-storage/img/banner10.jpg',
        '/local-storage/img/banner11.jpg',
        '/local-storage/img/banner12.jpg',
      ],
      param: {
        // page & order 参数
        desc: 'create_time',
        size: 20
      },
      articlePage: {
        'records': [{
          'id': -1,
          'userId': -1,
          'resourceId': -1,
          'articleCategory': '测试文章',
          'title': '测试标题',
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
      articleLatestComment: [{
        'articleId': '-1',
        'articleTitle': '测试文章',
        'userId': 1,
        'userNickname': '管理员',
        'userAvatar': 'http://img.angus-liu.cn/avatar/avatar07.png',
        'commentId': '-1',
        'commentContent': '测试回复',
        'commentCreateTime': '2019-05-22 09:57:25'
      }],
      // 文章类别
      articleCategory: [{
        name: '系统通知',
        img: 'http://img.angus-liu.cn/avatar/avatar01.jpg'
      }, {
        name: '校园新闻',
        img: 'http://img.angus-liu.cn/avatar/avatar02.jpg'
      }, {
        name: '学院新闻',
        img: 'http://img.angus-liu.cn/avatar/avatar03.jpg'
      }, {
        name: '社团动态',
        img: 'http://img.angus-liu.cn/avatar/avatar04.jpg'
      }, {
        name: '个人博客',
        img: 'http://img.angus-liu.cn/avatar/avatar05.jpg'
      }, {
        name: '校务通知',
        img: 'http://img.angus-liu.cn/avatar/avatar06.png'
      }],
      hotTagList: [{
        'name': '中北奖章',
        'count': 1
      }, {
        'name': '学院新闻',
        'count': 1
      }, {
        'name': '校长奖章',
        'count': 1
      }]
    };
  },
  methods: {
    select(item) {
      this.menu.active = item;
      if (item === '最新') {
        this.param.desc = 'create_time';
      } else {
        this.param.desc = 'comment_count,zan_count';
      }
      this.getArticlePage(this.param)
    },
    // 获取文章分页
    getArticlePage(param) {
      this.$axios.get('/api/article/page', param)
        .then(res => {
          let result = res.data;
          this.articlePage = result.data;
        });
    },
    // 获取最新评论
    getArticleLatestComment() {
      this.$axios.get('/api/article/latest-comment')
        .then(res => {
          let result = res.data;
          this.articleLatestComment = result.data;
        })
    },
    // 获取热门标签
    getHotTag() {
      this.$axios.get('/api/article/hot-tag')
        .then(res => {
          let result = res.data;
          this.hotTagList = result.data;
        })
    },
    toSearch(q) {
      this.$router.push({ name: 'search', query: { q } })
    }
  },
  mounted() {
    this.getArticlePage(this.param);
    this.getArticleLatestComment();
    this.getHotTag();
  }
};
</script>

<style lang="less" scoped>
.container {
  width: 1200px;
  margin: 10px auto;


  .ivu-col {
    padding: 7px;
  }

  .carousel-container {
    margin-bottom: 15px;
    box-shadow: 0 1px 2px 0 rgba(34, 36, 38, .15);

    .carousel-item {
      width: 100%;
      height: 400px;

      .carousel-img {
        min-height: 400px;
        width: 100%;
      }
    }
  }

  .article-list {
    margin: 10px 0 5px 0;
    list-style: none;

    .article-item {
      display: block;
      margin: 5px 0;
      line-height: 2.5em;
      overflow: auto;

      .article-category {
        float: left;
        width: 100px;
        margin-right: 10px;
      }

      .article-title {
        display: inline-block;
        max-width: 400px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        font-size: 1.1em;
        color: #5c5c5c;
      }

      .article-title:hover {
        color: #FF8364;
      }

      .article-time {
        color: #aaa;
        float: right;
      }
    }
  }

  .latest-comment-feed {
    .summary {
      max-width: 400px;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }

    a {
      color: #455A64;
    }

    a:hover {
      color: #FF8364;
    }
  }
}
</style>
