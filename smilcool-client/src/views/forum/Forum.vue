<template>
  <div class="container">
    <!-- 论坛提示 -->
    <Row>
      <iCol class="column">
        <sui-message dismissable>
          <div style="width: 480px; margin:0px auto; overflow: hidden;">
            <img src="../../assets/img/logo/forum.svg" style="width: 55px; float: left">
            <div style="line-height: 180%; text-align: center;">
              <div style="font-size: 15px">
                校内论坛 <a> Smilcool Forum </a> 正式上线！快来畅所欲言吧~
              </div>
              <p style="padding: 0 10px;">老司机不要过来开车哦，会被查水表滴 😂</p>
            </div>
          </div>
        </sui-message>
      </iCol>
    </Row>
    <!-- 论坛版块 -->
    <Row>
      <iCol class="column" span="6">
        <a is="sui-card" class="fluid topic">
          <sui-card-content>
            <sui-icon name="wifi teal"/>
          </sui-card-content>
          <sui-card-content extra>
            <p>校园热点</p>
          </sui-card-content>
        </a>
      </iCol>
      <iCol class="column" span="6">
        <a is="sui-card" class="fluid topic">
          <sui-card-content>
            <sui-icon name="comments outline olive"/>
          </sui-card-content>
          <sui-card-content extra>
            <p>话题讨论</p>
          </sui-card-content>
        </a>
      </iCol>
      <iCol class="column" span="6">
        <a is="sui-card" class="fluid topic">
          <sui-card-content>
            <sui-icon name="lightbulb outline yellow"/>
          </sui-card-content>
          <sui-card-content extra>
            <p>经历分享</p>
          </sui-card-content>
        </a>
      </iCol>
      <iCol class="column" span="6">
        <a is="sui-card" class="fluid topic">
          <sui-card-content>
            <sui-icon name="flag outline orange"/>
          </sui-card-content>
          <sui-card-content extra>
            <p>精华文章</p>
          </sui-card-content>
        </a>
      </iCol>
    </Row>

    <Row>
      <!-- 论坛帖子 -->
      <iCol class="column" span="18">
        <sui-card class="fluid">
          <sui-message attached="top">
            论坛精华帖
          </sui-message>
          <sui-card-content>
            <sui-feed v-for="(item, index) in post" :key="index">
              <sui-feed-event>
                <sui-feed-label>
                  <sui-image :src="item.avatar"/>
                </sui-feed-label>
                <sui-feed-content v-html="item.content"/>
              </sui-feed-event>
            </sui-feed>
          </sui-card-content>
        </sui-card>
      </iCol>
      <iCol class="column" span="6">
        <sui-card>
          <sui-message attached="top">
            每日一句
          </sui-message>
          <sui-card-content>
            Talk is cheap, show me your code.
          </sui-card-content>
        </sui-card>
      </iCol>
    </Row>
  </div>
</template>

<script>
export default {
  name: 'Forum',
  data() {
    return {
      post: [{
        avatar: 'http://img.angus-liu.cn/avatar/avatar06.png',
        content: '<a>赫本</a> 发布了精华帖 <a>Thinking in java 解读</a>'
      }, {
        avatar: 'http://img.angus-liu.cn/avatar/avatar01.jpg',
        content: '<a>老王本王</a> 回复了帖子 <a>如何使用 Smilcool</a>'
      }, {
        avatar: 'http://img.angus-liu.cn/avatar/avatar02.jpg',
        content: '<a>一双毛腿肩上扛</a> 点赞了帖子 <a>如何使用 Smilcool</a>'
      }, {
        avatar: 'http://img.angus-liu.cn/avatar/avatar03.jpg',
        content: '<a>666</a> 点赞了帖子 <a>Thinking in java 解读</a>'
      }, {
        avatar: 'http://img.angus-liu.cn/avatar/avatar04.jpg',
        content: '<a>007</a> 点赞了帖子 <a>Thinking in java 解读</a>'
      }, {
        avatar: 'http://img.angus-liu.cn/avatar/avatar05.jpg',
        content: '<a>安妮</a> 发布了帖子 <a>BAT 面试宝典，点进来你不会后悔</a>'
      }, {
        avatar: 'http://img.angus-liu.cn/avatar/avatar06.png',
        content: '<a>赫本</a> 发布了精华帖 <a>Thinking in java 解读</a>'
      }, {
        avatar: 'http://img.angus-liu.cn/avatar/avatar01.jpg',
        content: '<a>老王本王</a> 回复了帖子 <a>如何使用 Smilcool</a>'
      }, {
        avatar: 'http://img.angus-liu.cn/avatar/avatar02.jpg',
        content: '<a>一双毛腿肩上扛</a> 点赞了帖子 <a>如何使用 Smilcool</a>'
      }, {
        avatar: 'http://img.angus-liu.cn/avatar/avatar03.jpg',
        content: '<a>666</a> 点赞了帖子 <a>Thinking in java 解读</a>'
      }, {
        avatar: 'http://img.angus-liu.cn/avatar/avatar04.jpg',
        content: '<a>007</a> 点赞了帖子 <a>Thinking in java 解读</a>'
      }, {
        avatar: 'http://img.angus-liu.cn/avatar/avatar05.jpg',
        content: '<a>安妮</a> 发布了帖子 <a>BAT 面试宝典，点进来你不会后悔</a>'
      }]
    }
  }
}
</script>

<style lang="less" scoped>
.container {
  width: 1140px;
  margin: 0 auto;
  /*background: #aaa;*/
  padding: 5px;

  .column {
    padding: 5px;

    .topic {
      .content {
        text-align: center;

        i {
          font-size: 60px;
          line-height: 1;
          vertical-align: center;
          margin: 8px;
        }
      }
    }
  }
}
</style>
