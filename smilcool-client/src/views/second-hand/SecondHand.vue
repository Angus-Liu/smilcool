<template>
  <div class="container">
    <!-- 提示 -->
    <sui-message class="second-hand-message" dismissable warning>
      <sui-message-header>
        <sui-icon name="shopping cart"/>
        欢迎来到校园跳蚤市场
      </sui-message-header>
      <p>在这里，你可以搜罗全校同学贩卖的好物，也可以发布自己闲置的物品，赚取可观的零花钱。</p>
    </sui-message>
    <!-- 提示 END -->
    <!-- 菜单 -->
    <Row class="second-hand-menu">
      <iCol class="second-hand-category" span="15">
        <div>
          <a is="sui-label" v-if="name === '所有'" color="orange" @click="name='所有'">所有</a>
          <a is="sui-label" v-else @click="name='所有'">所有</a>
          <template v-for="item in secondHandCategory">
            <a is="sui-label" v-if="name === item.name" color="orange" @click="name = item.name">{{item.name}}</a>
            <a is="sui-label" v-else @click="name = item.name">{{item.name}}</a>
          </template>
        </div>
      </iCol>
      <iCol span="6">
        <sui-input class="fluid" placeholder="搜好物" icon="search"/>
      </iCol>
      <iCol class="second-hand-add" span="3">
        <sui-button fluid color="orange" content="发布闲置"/>
      </iCol>
    </Row>
    <!-- 菜单 END -->
    <!-- 二手列表 -->
    <div class="second-hand-list">
      <sui-card class="fluid second-hand-item" v-for="secondHand in secondHandPage.records" :key="secondHand.id">
        <sui-image class="second-hand-image" :src="secondHand.images[0]"/>
        <sui-card-content>
          <a is="sui-card-header">{{secondHand.title}}</a>
          <sui-card-description class="second-hand-description">{{secondHand.description}}</sui-card-description>
          <span class="second-hand-price">￥{{secondHand.price}}</span>
          <span slot="right">
              <a is="sui-label">👍 {{secondHand.resource.zanCount}}</a>
              <a is="sui-label">💬 {{secondHand.resource.commentCount}}</a>
            </span>

        </sui-card-content>
        <sui-card-content extra>
          <sui-image :src="secondHand.user.avatar" avatar/>
          <span style="line-height: 28px">{{secondHand.user.nickname}}</span>
          <span slot="right" style="line-height: 28px"><Time :time="secondHand.createTime"/></span>
        </sui-card-content>
      </sui-card>
    </div>
    <!-- 二手列表 END -->
  </div>
</template>

<script>
export default {
  name: 'SecondHand',
  data() {
    return {
      name: '所有',
      secondHandCategory: [{
        'name': '测试',
        'code': '-1'
      }],
      param: {
        /* query 参数 */
        /* page & order 参数 */
        desc: 'create_time',
        current: 1
      },
      secondHandPage: {
        'records': [
          {
            'id': 1,
            'userId': 1,
            'resourceId': 37,
            'secondHandCategory': '数码',
            'title': '小米手机9，低价转',
            'name': '小米手机9',
            'price': 599,
            'address': '文瀛13#105',
            'description': '买了华为P30，这部小米手机就低价转了，9.5成新',
            'images': ['https://g-search2.alicdn.com/img/bao/uploaded/i4/i2/1714128138/O1CN018kA6uv29zFiGLMEsL_!!0-item_pic.jpg_250x250.jpg'],
            'createTime': '2019-05-23 15:56:27',
            'user': {
              'id': 1,
              'username': 'admin',
              'nickname': '管理员',
              'avatar': 'http://img.angus-liu.cn/avatar/avatar07.png',
              'sign': '一句话介绍自己'
            },
            'resource': {
              'id': 37,
              'zanCount': 0,
              'pvCount': 0,
              'commentCount': 0
            }
          }
        ],
        'total': 20,
        'size': 1,
        'current': 1,
        'searchCount': true,
        'pages': 20
      }
    }
  },
  methods: {
    // 获取二手交易类别
    getSecondHandCategory() {
      this.$axios.get('/api/dic/second-hand-category/item')
        .then(res => {
          let result = res.data;
          this.secondHandCategory = result.data;
        })
    },
    // 获取二手交易分页
    getSecondHandPage(param) {
      this.$axios.get('/api/second-hand/page', param)
        .then(res => {
          let result = res.data;
          this.secondHandPage = result.data;
          this.secondHandPage.records.forEach(secondHand => {
            if (secondHand.images && secondHand.images.length > 0) {
              secondHand.images = JSON.parse(secondHand.images);
              console.log(secondHand.images);
            }
          });
        });
    }
  },
  mounted() {
    this.getSecondHandCategory();
    this.getSecondHandPage(this.param);
  }
}
</script>

<style lang="less" scoped>
.container {
  width: 1140px;
  margin: 10px auto;
  padding: 5px;
  overflow-x: hidden;

  .ivu-col {
    padding: 5px;
  }

  .second-hand-message {
    margin-top: 7px;
  }

  .second-hand-menu {
    .second-hand-category {
      padding-left: 0;
      padding-bottom: 0;

      .label {
        padding: 12px 15px;
        font-size: 1em;
        margin: 0 7px 7px 0;
      }
    }

    .second-hand-add {
      padding-right: 0;

      .button {
        padding: 12px;
      }
    }
  }

  .second-hand-list {
    margin: 15px auto 25px;
    padding: 1px;
    width: 1150px;
    overflow: auto;

    .second-hand-item {
      width: 216px;
      height: 373px;
      overflow: auto;
      margin: 0 12px 12px 0;
      float: left;

      .second-hand-image {
        width: 216px;
        height: 216px;
      }
    }

    .header {
      font-size: 1em;
    }

    .second-hand-description {
      font-size: 0.9em;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
      margin-bottom: 10px;
    }

    .second-hand-price {
      display: inline-block;
      color: red;
      font-size: 1.1em;
      padding-top: 2px
    }
  }
}
</style>
