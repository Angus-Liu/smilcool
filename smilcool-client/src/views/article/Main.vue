<template>
  <div class="container">
    <Row>
      <iCol class="column" span="14">
        <!-- 轮播图 -->
        <Carousel class="carousel" loop>
          <CarouselItem v-for="(item, index) in carouselList" :key="index">
            <div class="carousel-item">
              <img class="carousel-img" :src="item">
            </div>
          </CarouselItem>
        </Carousel>
        <SimpleCard class="card-container">
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
        </SimpleCard>
      </iCol>
      <iCol class="column" span="10">
        <Card title="系统通告">
          <div>
            <b>Q：怎么发帖？</b>
            <p>
              进入你想发帖的版面，页面底部就能快捷发帖。 输入标题、正文，点击发布就能成功发帖！
              如果你想体验插入图片、上传附件等功能，可以点击发帖框右上角的链接切换到完整模式。
              注意，发帖前千万先阅读版规，不符合规定的帖子会被版务删除，你在版面的发帖权限也有可能被封禁哦。
            </p>
            <b>Q：精华区是什么？</b>
            <p>
              每个版面都有一个精华区，精华区内分门别类保存着版面讨论精华及相关文件，由版务进行整理维护。
              不同版面的精华区作用也各不相同，想了解更多的话，就选择感兴趣的版面，进精华区看看吧。
            </p>
          </div>
        </Card>
        <sui-card>
          <sui-card-content>
            文章分类
          </sui-card-content>
        </sui-card>
        <sui-card>
          <sui-card-content>
            热门标签
          </sui-card-content>
        </sui-card>
      </iCol>
    </Row>
  </div>
</template>

<script>
import SimpleCard from '@/components/common/SimpleCard'

export default {
  name: 'Main',
  components: {
    SimpleCard
  },
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
  width: 1140px;
  margin: 10px auto;
  background: #ccc;
}

.column {
  padding: 5px;
}

.carousel {
  margin-bottom: 5px;

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

.card-container {
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
</style>
