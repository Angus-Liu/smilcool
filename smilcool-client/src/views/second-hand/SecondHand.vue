<template>
  <div class="container">
    <!-- 提示 -->
    <sui-message class="second-hand-message" dismissable warning>
      <sui-message-header>
        <sui-icon name="shopping cart"/> 欢迎来到校园跳蚤市场
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
      <sui-card class="fluid second-hand-item" v-for="i in 5" :key="i">
        <sui-image
          src="https://img.alicdn.com/bao/uploaded/i3/O1CN019XvWiU1PVy3w3Yb06_!!0-fleamarket.jpg_728x728.jpg"/>
        <sui-card-content>
          <sui-card-header>黑色短袖低价甩卖{{i}}</sui-card-header>
          <sui-card-description>
            买来没穿过几次，有点大了，低价出，可小刀
          </sui-card-description>
          <sui-card-description style="margin-top: 10px">
            <span style="display: inline-block; color: red; font-size: 1.1em; padding-top: 5px">
            ￥54.3
          </span>
            <span style="float: right">
              <a is="sui-label">👍 1</a>
              <a is="sui-label">💬 1</a>
            </span>
          </sui-card-description>
        </sui-card-content>
        <sui-card-content extra>
          <sui-image src="http://img.angus-liu.cn/avatar/avatar06.png" avatar/>
          <span style="line-height: 28px">赫本</span>
          <span slot="right" style="line-height: 28px">5分钟前</span>
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
      secondHandCategory: [
        {
          'name': '书籍',
          'code': '1'
        },
        {
          'name': '数码',
          'code': '2'
        },
        {
          'name': '服装',
          'code': '3'
        }
      ]
    }
  },
  methods: {
    getSecondHandCategory() {
      this.$axios.get('/api/dic/second-hand-category/item')
        .then(res => {
          let result = res.data;
          this.secondHandCategory = result.data;
        })
    }
  },
  mounted() {
    this.getSecondHandCategory();
  }
}
</script>

<style lang="less" scoped>
.container {
  width: 1140px;
  margin: 10px auto;
  padding: 5px;

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
    column-count: 5;

    .second-hand-item {
      height: 100%;
      overflow: auto;
      break-inside: avoid;

      .header {
        font-size: 1em;
      }

      .description {
        font-size: 0.9em;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
      }
    }
  }
}
</style>
