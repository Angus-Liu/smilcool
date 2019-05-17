<template>
  <div class="container">
    <Row>
      <iCol class="column" span="16">
        <sui-menu>
          <a is="sui-menu-item" v-for="item in items" :active="isActive(item)" :key="item" :content="item"
             @click="select(item)"/>
          <sui-menu-menu position="right">
            <sui-menu-item>
              <sui-input transparent icon="search" placeholder="搜索"/>
            </sui-menu-item>
          </sui-menu-menu>
        </sui-menu>
        <sui-card v-for="(lostFoundPage, index) in lostFoundPageList" :key="index">
          <sui-card-content>
            <sui-feed>
              <sui-feed-event>
                <sui-feed-label :image="lostFoundPage.user.avatar"/>
                <sui-feed-content>
                  <sui-feed-summary>
                    <sui-label class="category-label" basic
                               :color="lostFoundPage.lostFound.lostFoundCategory === '寻物启事'? 'red':'green'">
                      {{lostFoundPage.lostFound.lostFoundCategory}}
                    </sui-label>
                    <a href="#">{{lostFoundPage.lostFound.title}}</a>
                    <sui-feed-date>
                      <Time :time="lostFoundPage.lostFound.createTime"/>
                    </sui-feed-date>
                  </sui-feed-summary>
                  <sui-feed-extra text>{{lostFoundPage.lostFound.description}}</sui-feed-extra>
                  <sui-feed-extra images
                                  v-if="lostFoundPage.lostFound.images && lostFoundPage.lostFound.images.length !== 0">
                    <img v-for="img in lostFoundPage.lostFound.images" :src="img">
                  </sui-feed-extra>
                  <sui-feed-meta>
                    <sui-feed-like>
                      👍 {{lostFoundPage.resource.zanCount}}
                    </sui-feed-like>
                    <sui-feed-like>
                      💬 {{lostFoundPage.resource.commentCount}}
                    </sui-feed-like>
                  </sui-feed-meta>
                </sui-feed-content>
              </sui-feed-event>
            </sui-feed>
          </sui-card-content>
        </sui-card>
      </iCol>
      <iCol class="column" span="8">
        <sui-card>
          <sui-message attached="top" icon="eye" negative>
            <sui-message-header>寻物启事小贴士</sui-message-header>
            <p>什么，你东西丢啦？不要怕，快来发布一条寻物启事吧，很快就会有好心人联系你啦。对啦，一定要核实信息，准确后再发布哦 😃</p>
          </sui-message>
          <sui-button attached="bottom">
            <sui-icon name="add"/>
            发布寻物启事
          </sui-button>
        </sui-card>
        <sui-card>
          <sui-message attached="top" icon="bullhorn" positive>
            <sui-message-content>
              <sui-message-header>失物招领小贴士</sui-message-header>
              <p>捡到哪位同学不小信掉落的物品啦？快发布一条失物招领吧，失主一定会非常非常感激你呢 😘</p>
            </sui-message-content>
          </sui-message>
          <sui-button attached="bottom">
            <sui-icon name="add"/>
            发布失物招领
          </sui-button>
        </sui-card>
      </iCol>
    </Row>
  </div>
</template>

<script>
export default {
  name: 'LostFound',
  data() {
    return {
      active: '失物招领',
      items: ['失物招领', '寻物启事'],
      lostFoundPageList: [{
        'lostFound': {
          'id': 1,
          'userId': 1,
          'resourceId': 16,
          'lostFoundCategory': '寻物启事',
          'title': '四食堂丢失一个钱包',
          'description': '如图，钱包是黑色的，里面有少量现金和一张工行的卡',
          'itemName': '钱包',
          'time': '2019-05-17T12:10:01.000+0000',
          'address': '四食堂',
          'createTime': '2019-05-17 15:39:45',
          'images': [
            'https://img12.360buyimg.com/n7/jfs/t22831/61/2367409622/289066/5d9c3f/5b7d10feNe776b305.jpg',
            'https://img12.360buyimg.com/n7/jfs/t22831/61/2367409622/289066/5d9c3f/5b7d10feNe776b305.jpg'
          ]
        },
        'user': {
          'id': 1,
          'username': 'admin',
          'nickname': '管理员',
          'avatar': 'http://img.angus-liu.cn/avatar/avatar07.png',
          'sex': '保密',
          'birthday': '1970-01-01',
          'sign': '一句话介绍自己',
          'intro': '这个人很神秘，什么也没写',
          'grade': '未填写',
          'college': '未填写',
          'major': '未填写',
          'phone': null,
          'email': 'admin@admin.com',
          'state': 1,
          'remark': '超级管理员账户，由系统内定，请勿修改',
          'createTime': '2019-03-28',
          'updateTime': '2019-05-17'
        },
        'resource': {
          'id': 16,
          'userId': 1,
          'resourceDicType': '失物寻物类别',
          'resourceDicItem': '寻物启事',
          'zanCount': 0,
          'pvCount': 0,
          'commentCount': 0,
          'state': '正常',
          'remark': null,
          'createTime': '2019-05-17 15:39:45',
          'updateTime': '2019-05-17 15:39:45',
          'deleted': false
        },
        'commentList': []
      }]
    }
  },
  methods: {
    isActive(name) {
      return this.active === name;
    },
    select(name) {
      this.active = name;
    },
    getLostFoundPageList() {
      this.$axios.get('/api/lost-found/page')
        .then(res => {
          let result = res.data;
          this.lostFoundPageList = result.data;
          this.lostFoundPageList.forEach(lostFoundPage => {
            if (lostFoundPage.lostFound.images !== null && lostFoundPage.lostFound.images.length !== 0) {
              lostFoundPage.lostFound.images = JSON.parse(lostFoundPage.lostFound.images);
            }
          })
        })
    }
  },
  mounted() {
    this.getLostFoundPageList();
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
    padding: 7px;
  }

  .tab-container {
    background: #fff;
    width: 100%;
    padding: 1rem;
  }

  .card {
    width: 100%;

    .category-label {
      margin-right: 10px;
    }
  }
}
</style>
