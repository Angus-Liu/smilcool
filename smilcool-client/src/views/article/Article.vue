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
                <router-link class="article-title" :to="'/article/' + article.id">{{article.title}}</router-link>
                <span class="article-time">{{article.createTime}}</span>
              </li>
            </ul>
          </sui-card-content>
          <sui-card-content extra>
            <a slot="right" href="#">查看更多</a>
          </sui-card-content>
        </sui-card>
        <!-- 文章列表 END -->
      </iCol>
      <iCol span="10">
        <!-- 系统通告 -->
        <sui-message negative dismissable>
          <sui-message-header>Q：Smilcool是什么？</sui-message-header>
          <p style="text-align:justify">
            进入你想发帖的版面，页面底部就能快捷发帖。 输入标题、正文，点击发布就能成功发帖！
            如果你想体验插入图片、上传附件等功能，可以点击发帖框右上角的链接切换到完整模式。
            注意，发帖前千万先阅读版规，不符合规定的帖子会被版务删除，你在版面的发帖权限也有可能被封禁哦。
          </p>
          <sui-message-header>Q：如何发表文章？</sui-message-header>
          <p style="text-align:justify">
            每个版面都有一个精华区，精华区内分门别类保存着版面讨论精华及相关文件，由版务进行整理维护。
            不同版面的精华区作用也各不相同，想了解更多的话，就选择感兴趣的版面，进精华区看看吧。
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
            <div class="article-tags">
              <Tag type="dot" :color="item.color" v-for="(item,index) in articleTags" :key="index">{{item.name}}</Tag>
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
                    <sui-feed-user>{{item.userNickname}}</sui-feed-user>
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
        active: '推荐',
        items: ['推荐', '最新', '最热']
      },
      carouselList: [
        'http://cst.nuc.edu.cn/__local/6/B5/CA/0C7872A9A43129FBF22FFD81FD6_A7D26F04_11FD7.jpg',
        'http://cst.nuc.edu.cn/__local/4/BD/0A/EC16F73EAA614E63429BACDBE1D_5AC56599_AEDFD.png',
        'http://cst.nuc.edu.cn/__local/2/36/5A/EAA656684EBA55E5462A8B8D583_C411B084_1A136.jpg',
        'http://cst.nuc.edu.cn/__local/9/57/7B/1A89800BB01F84F9FAD9E8D979A_02F9A36A_11E41.jpg'
      ],
      param: {
        /* page & order 参数 */
        desc: 'create_time',
        size: 20
      },
      articlePage: {
        'records': [{
          'id': 2,
          'userId': 6,
          'resourceId': 5,
          'articleCategory': '校园文章',
          'title': '雷锋斌副校长指导大数据学院 “改革创新 奋发有为”大讨论专题组织生活会',
          'createTime': '2019-05-13',
          'user': {
            'id': 6,
            'username': '1713862733',
            'nickname': '双击666',
            'avatar': 'http://img.angus-liu.cn/avatar/avatar03.jpg',
            'sign': '一句话介绍自己'
          },
          'resource': {
            'id': 5,
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
      articleTags: [{
        name: '中北奖章',
        color: 'primary'
      }, {
        name: 'Android实验室',
        color: '#27bb5b'
      }, {
        name: '考试信息',
        color: 'warning'
      }, {
        name: '求职经历分享',
        color: 'error'
      }, {
        name: '中北大学',
        color: 'success'
      }, {
        name: '大数据学院',
        color: '#db415f'
      }]
    };
  },
  methods: {
    select(item) {
      this.menu.active = item;
      if (item === '推荐' || item === '最新') {
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
    }
  },
  mounted() {
    this.getArticlePage(this.param);
    this.getArticleLatestComment();
  }
};
</script>

<style lang="less" scoped>
.container {
  width: 1200px;
  margin: 10px auto;
  /*background: #ccc;*/


  .ivu-col {
    padding: 7px;
  }

  .carousel-container {
    margin-bottom: 15px;

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
        color: #ff8364;
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
  }
}
</style>
