<template>
  <div class="container">
    <Row>
      <iCol>
        <sui-message>
          文章搜索结果：共检索到 {{searchResult.totalElements}} 条记录
        </sui-message>
        <sui-card class="fluid">
          <sui-card-content>
            <sui-feed class="article-feed">
              <sui-feed-event style="margin-bottom: 20px" v-for="article in searchResult.content" :key="article.id">
                <sui-feed-content>
                  <sui-feed-summary>
                    <router-link :to="'/article/' + article.id">
                      <span class="article-title" v-html="article.title"/>
                    </router-link>
                    <sui-feed-date>
                      <Time :time="article.createTime"/>
                    </sui-feed-date>
                  </sui-feed-summary>
                  <sui-feed-extra>
                    <Tag color="blue">{{article.articleCategory}}</Tag>
                    <Tag v-for="(tag, index) in article.tagList" :key="index">{{tag}}</Tag>
                  </sui-feed-extra>
                  <sui-feed-extra class="article-brief">
                    <p v-html="article.brief"/>
                  </sui-feed-extra>
                </sui-feed-content>
              </sui-feed-event>
            </sui-feed>
          </sui-card-content>
        </sui-card>
      </iCol>
    </Row>
  </div>
</template>

<script>
export default {
  name: 'Search',
  props: ['q'],
  data() {
    return {
      param: {
        page: 0,
        size: 20
      },
      searchResult: {
        'content': [
          {
            'id': 5,
            'userId': 3,
            'resourceId': 8,
            'articleCategory': '学院新闻',
            'title': '2018/2019中北奖章与<em>校长</em>奖章答辩会成功举办',
            'brief': '2018/2019中北奖章与<em>校长</em>奖章答辩会成功举办',
            'cover': 'http://img.angus-liu.cn/avatar/avatar01.jpg',
            'tags': '["中北奖章", "校长奖章"]',
            'tagList': [
              '中北奖章',
              '校长奖章'
            ],
            'markdownContent': '2018年10月30日下午2:30，我校大数据学院于主楼919召开中北奖章和校长奖章公开答辩会，本次答辩评委由我院老师组成。  \n\n![](http://cst.nuc.edu.cn/__local/0/9C/7A/DE3869C06C95263D790A94B9A61_71A56AC7_BFB3C.png)\n\n会上，首先是竞选校长奖章的优秀同学就自己的学习，体育，获奖经历，未来展望等各方面展开激烈而精彩的答辩。  \n\n![](http://cst.nuc.edu.cn/__local/D/1D/68/0FD8609810C2A079726727FEF07_DF4F9F56_EFF31.png)\n\n其中王雪婷同学在答辩时提:自己坚持早起，觉得这是自己偷来的时光。坚持锻炼，累计运动天数385天，身体素质越来越强。她对时间视如珍宝，她对运动付诸热爱。有志者事竟成，如今优秀的她便是对逝去日子努力的最好体现。接着，中北奖章竞选班级代表分别就自己班的制度，成绩，班级活动，班里成员获奖经历等展开答辩。流利自信的言语，精彩纷呈的解说，各不相同的班级特色，积极优良的精神风貌，充分展现出我院优秀班级的风采。  \n\n![](http://cst.nuc.edu.cn/__local/5/4D/7A/8855A2D9DDAC1D055D5B60EE5B6_CBEFCF08_CE081.png)\n\n校长奖章是用于奖励在校学生的最高荣誉，激励了一大批优秀同学更好的投入学习，社交。这是对他们学习生活，思想实践等方面的高度肯定。中北奖章引导整个班级成员坚定理想信念，刻苦学习，团结在一起。他们都是我们中北人的榜样！  ',
            'htmlContent': '<p>2018年10月30日下午2:30，我校大数据学院于主楼919召开中北奖章和校长奖章公开答辩会，本次答辩评委由我院老师组成。</p>\n<p><img src="http://cst.nuc.edu.cn/__local/0/9C/7A/DE3869C06C95263D790A94B9A61_71A56AC7_BFB3C.png" alt="" /></p>\n<p>会上，首先是竞选校长奖章的优秀同学就自己的学习，体育，获奖经历，未来展望等各方面展开激烈而精彩的答辩。</p>\n<p><img src="http://cst.nuc.edu.cn/__local/D/1D/68/0FD8609810C2A079726727FEF07_DF4F9F56_EFF31.png" alt="" /></p>\n<p>其中王雪婷同学在答辩时提:自己坚持早起，觉得这是自己偷来的时光。坚持锻炼，累计运动天数385天，身体素质越来越强。她对时间视如珍宝，她对运动付诸热爱。有志者事竟成，如今优秀的她便是对逝去日子努力的最好体现。接着，中北奖章竞选班级代表分别就自己班的制度，成绩，班级活动，班里成员获奖经历等展开答辩。流利自信的言语，精彩纷呈的解说，各不相同的班级特色，积极优良的精神风貌，充分展现出我院优秀班级的风采。</p>\n<p><img src="http://cst.nuc.edu.cn/__local/5/4D/7A/8855A2D9DDAC1D055D5B60EE5B6_CBEFCF08_CE081.png" alt="" /></p>\n<p>校长奖章是用于奖励在校学生的最高荣誉，激励了一大批优秀同学更好的投入学习，社交。这是对他们学习生活，思想实践等方面的高度肯定。中北奖章引导整个班级成员坚定理想信念，刻苦学习，团结在一起。他们都是我们中北人的榜样！</p>\n',
            'createTime': '2019-05-15',
            'updateTime': '2019-05-26T15:19:27.000+0000'
          }
        ],
        'totalElements': 5,
        'totalPages': 5,
        'number': 0,
        'size': 1,
      }
    };
  },
  methods: {
    search() {
      this.param.q = this.q;
      this.$axios.get('/api/article/search', this.param)
        .then(res => {
          let result = res.data;
          this.searchResult = result.data;
        });
    }
  },
  watch: {
    q() {
      this.search();
    }
  },
  mounted() {
    this.search();
  }
}
</script>

<style scoped>
.container {
  width: 1140px;
  margin: 0 auto;
  padding: 5px;

}

.ivu-col {
  padding: 7px;
}

.article-title >>> em, .article-brief >>> em {
  font-style: normal;
  color: #ff8364;
}


</style>
