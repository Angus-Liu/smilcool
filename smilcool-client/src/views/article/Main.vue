<template>
  <div class="container">
    <Row>
      <iCol span="14">
        <!-- 轮播图 -->
        <Carousel class="carousel-container" loop autoplay :autoplay-speed="5000" radius-dot>
          <CarouselItem v-for="(item, index) in carouselList" :key="index">
            <div class="carousel-item">
              <img class="carousel-img" :src="item">
            </div>
          </CarouselItem>
        </Carousel>
        <!-- 轮播图 END -->
        <!-- 文章选项卡 -->
        <div>
          <sui-tab :menu="{ secondary: true }">
            <sui-tab-pane class="tab-pane" title="推荐" :attached="false">
              <div class="card-header">
                <h3>猜你喜欢</h3>
              </div>
              <div class="card-body">
                <ul class="content-list">
                  <li class="content-item" v-for="article in articleList" :key="article.id">
                <span class="article-category">
                  <a is="sui-label" tag>{{article.articleCategory}}</a>
                </span>
                    <router-link class="article-title" :to="'/article/' + article.id">{{article.title}}</router-link>
                    <span class="article-time">{{article.createTime}}</span>
                  </li>
                </ul>
              </div>
              <div class="card-footer">
                <a href="#">查看更多</a>
              </div>
            </sui-tab-pane>
            <sui-tab-pane class="tab-pane" title="最新" :attached="false">
              <div class="card-header">
                <h3>最新发布</h3>
              </div>
              <div class="card-body">
                <ul class="content-list">
                  <li class="content-item" v-for="article in articleList" :key="article.id">
                <span class="article-category">
                  <a is="sui-label" tag>{{article.articleCategory}}</a>
                </span>
                    <router-link class="article-title" :to="'/article/' + article.id">{{article.title}}</router-link>
                    <span class="article-time">{{article.createTime}}</span>
                  </li>
                </ul>
              </div>
              <div class="card-footer">
                <a href="#">查看更多</a>
              </div>
            </sui-tab-pane>
            <sui-tab-pane class="tab-pane" title="最热" :attached="false">
              <div class="card-header">
                <h3>热门文章</h3>
              </div>
              <div class="card-body">
                <ul class="content-list">
                  <li class="content-item" v-for="article in articleList" :key="article.id">
                <span class="article-category">
                  <a is="sui-label" tag>{{article.articleCategory}}</a>
                </span>
                    <router-link class="article-title" :to="'/article/' + article.id">{{article.title}}</router-link>
                    <span class="article-time">{{article.createTime}}</span>
                  </li>
                </ul>
              </div>
              <div class="card-footer">
                <a href="#">查看更多</a>
              </div>
            </sui-tab-pane>
          </sui-tab>
        </div>
        <!-- 文章选项卡 END -->
      </iCol>
      <iCol span="10">
        <!-- 系统通告 -->
        <sui-message warning dismissable>
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
          <sui-card-content>
            <div class="card-header">
              <h3>文章类别</h3>
            </div>
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
          <sui-card-content>
            <div class="card-header">
              <h3>热门标签</h3>
            </div>
            <div class="article-tags">
              <Tag type="dot" :color="item.color" v-for="(item,index) in articleTags" :key="index">{{item.name}}</Tag>
            </div>
          </sui-card-content>
        </sui-card>
        <!-- 热门标签 END -->
        <!-- 最新评论 -->
        <sui-card class="fluid">
          <sui-card-content>
            <div class="card-header">
              <h3>最新评论</h3>
            </div>
            <div class="card-body">
              <ul class="content-list">
                <li class="content-item" v-for="article in articleList" :key="article.id">
                  <router-link class="article-title" :to="'/article/' + article.id">{{article.title}}</router-link>
                  <span class="article-time">{{article.createTime}}</span>
                </li>
              </ul>
            </div>
            <div class="card-footer">
              <a href="#">查看更多</a>
            </div>
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
      carouselList: [
        'http://cst.nuc.edu.cn/__local/6/B5/CA/0C7872A9A43129FBF22FFD81FD6_A7D26F04_11FD7.jpg',
        'http://cst.nuc.edu.cn/__local/4/BD/0A/EC16F73EAA614E63429BACDBE1D_5AC56599_AEDFD.png',
        'http://cst.nuc.edu.cn/__local/2/36/5A/EAA656684EBA55E5462A8B8D583_C411B084_1A136.jpg'
      ],
      articleList: [{
        'id': 1,
        'articleCategory': '校园文章',
        'title': '柴锐博士做客大数据学院“博士下午茶”活动',
        'createTime': '2019-05-13'
      }, {
        'id': 2,
        'articleCategory': '校园文章',
        'title': '第五期精英论坛之就业英雄2成功举办',
        'createTime': '2019-05-13'
      }, {
        'id': 3,
        'articleCategory': '校园文章',
        'title': '大数据学院举行首届第二次博士论坛',
        'createTime': '2019-05-13'
      }],
      articleCategory: [{
        name: '校园新闻',
        img: 'http://img.angus-liu.cn/avatar/avatar01.jpg'
      }, {
        name: '学院新闻',
        img: 'http://img.angus-liu.cn/avatar/avatar02.jpg'
      }, {
        name: '社团文章',
        img: 'http://img.angus-liu.cn/avatar/avatar03.jpg'
      }, {
        name: '只是论坛',
        img: 'http://img.angus-liu.cn/avatar/avatar04.jpg'
      }, {
        name: '个人博客',
        img: 'http://img.angus-liu.cn/avatar/avatar05.jpg'
      }, {
        name: '系统通知',
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
      background: #aaa;


      .carousel-img {
        width: 100%;
        min-height: 400px;
      }
    }
  }

  .tab-pane {
    overflow: hidden;
    padding: 20px;
  }

  .tab-pane, .card {

    .card-header {
      padding-bottom: 10px;
      margin-bottom: 10px;
      border-bottom: 1px dashed #ccc;
      color: #404040;
    }

    .card-body {
      margin: 10px 0 5px 0;

      .content-list {
        list-style: none;

        .content-item {
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
    }

    .card-footer {
      float: right;
    }
  }
}
</style>
