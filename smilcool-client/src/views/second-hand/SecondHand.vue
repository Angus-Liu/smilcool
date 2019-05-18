<template>
  <div class="container">
    <Row>
      <iCol>
        <sui-message>
          这个是二手交易
        </sui-message>
      </iCol>
    </Row>
    <div class="second-hand-list">
      <sui-card class="fluid second-hand-item" v-for="i in count" :key="i">
        <sui-image src="https://img.alicdn.com/bao/uploaded/i3/O1CN019XvWiU1PVy3w3Yb06_!!0-fleamarket.jpg_728x728.jpg"/>
        <sui-card-content>
          <sui-card-header>黑色短袖低价甩卖{{i}}</sui-card-header>
          <sui-card-description>
            买来没穿过几次，有点大了，低价出，可小刀
          </sui-card-description>
          <sui-card-description style="margin-top: 10px">
            <span style="color: red; font-size: 1.2em">
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
    <button @click="count++">123</button>
  </div>
</template>

<script>
import SuiCardContent from 'semantic-ui-vue/dist/commonjs/views/Card/CardContent';

export default {
  name: 'SecondHand',
  components: { SuiCardContent },
  data() {
    return {
      count: 5
    }
  }
}
</script>

<style lang="less" scoped>
.container {
  width: 1140px;
  margin: 10px auto;
  /*background: #aaa;*/
  padding: 5px;

  .second-hand-list {
    margin: 15px auto 25px;
    column-count: 4;

    .second-hand-item {
      break-inside: avoid;
    }
  }
}
</style>
