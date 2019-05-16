<template>
  <div class="container">
    <row>
      <iCol span="7">
        <sui-card class="moment-editor">
          <sui-card-content>
            <Input v-model="moment" type="textarea" :rows="4" placeholder="又遇到什么好事啦？快发表一条动态吧 : )"/>
            <div class="editor-toolbar">
              <sui-button class="toolbar-submit" basic primary>发布</sui-button>
            </div>
          </sui-card-content>
        </sui-card>
      </iCol>
      <iCol span="10">
        <sui-card class="moment-editor">
          <sui-card-content>
            <Input v-model="moment" type="textarea" :rows="4" placeholder="又遇到什么好事啦？快发表一条动态吧 : )"/>
            <div class="editor-toolbar">
              <sui-button class="toolbar-submit" basic primary>发布</sui-button>
            </div>
          </sui-card-content>
        </sui-card>
      </iCol>
      <iCol span="7">
        <sui-card class="moment-editor">
          <sui-card-content>
            <sui-image src="http://img.angus-liu.cn/avatar/avatar06.png?imageView2/1/w/50/h/50/q/75"
                       class="right floated"/>
            <sui-card-header>Elliot Fu</sui-card-header>
            <sui-card-meta>Friends of Veronika</sui-card-meta>
            <sui-card-description>
              Elliot requested permission to view your contact details
            </sui-card-description>
          </sui-card-content>
        </sui-card>
      </iCol>
    </row>
    <sui-menu pointing>
      <a is="sui-menu-item" v-for="item in items" :active="isActive(item)" :key="item" :content="item"
         @click="select(item)"/>
      <sui-menu-menu position="right">
        <sui-menu-item>
          <sui-input transparent icon="search" placeholder="搜索动态"/>
        </sui-menu-item>
      </sui-menu-menu>
    </sui-menu>
    <div class="moment-list">
      <sui-card class="moment-item" v-for="momentPage in momentPageList" :key="moment.id">
        <sui-card-content class="moment-item-wrapper">
          <sui-card-header class="moment-header">
            <sui-image class="moment-avatar" :src="momentPage.user.avatar" circular/>
            <span style="margin-left:5px; font-size: 16px">{{momentPage.user.nickname}}</span>
            <sui-button class="right floated" size="mini" basic positive>关注</sui-button>
          </sui-card-header>
          <sui-card-meta class="moment-time">
            <Time :time="momentPage.moment.createTime"/>
          </sui-card-meta>
          <sui-card-description class="moment-content">
            <p>{{momentPage.moment.content}}</p>
            <sui-image-group class="moment-image-group" v-if="momentPage.moment.images" size="tiny">
              <sui-image v-for="(image, index) in momentPage.moment.images" :src="image" :key="index"/>
            </sui-image-group>
          </sui-card-description>

          <span slot="right">
            <a is="sui-label" basic>👍 23</a>
            <a is="sui-label" basic @click="momentPage.show = !momentPage.show">💬 23</a>
            </span>
        </sui-card-content>
        <sui-card-content extra>
          <sui-input
            placeholder="添加评论"
            icon="paper plane outline"
            icon-position="left"
            transparent
          />
        </sui-card-content>
        <sui-card-content extra v-show="momentPage.show">
          评论列表
        </sui-card-content>
      </sui-card>
    </div>
    <sui-message>
      <p style="text-align: center">
        <a href="#">加载更多</a>
      </p>
    </sui-message>
  </div>
</template>

<script>

export default {
  name: 'Moment',
  data() {
    return {
      active: '最新',
      items: ['最新', '最热', '关注'],
      moment: '',
      momentPageList: [
        {
          'moment': {
            'id': 1,
            'userId': 1,
            'resourceId': 2,
            'resourceTypeId': null,
            'content': '校园动态测试',
            'images': [
              'http://img.angus-liu.cn/avatar/avatar01.jpg',
              'http://img.angus-liu.cn/avatar/avatar02.jpg',
              'http://img.angus-liu.cn/avatar/avatar03.jpg'
            ],
            'createTime': '2019-05-15 16:23:50'
          },
          'user': {
            'id': 1,
            'username': 'admin',
            'nickname': '管理员',
            'avatar': 'http://img.angus-liu.cn/avatar/avatar07.png'
          },
          'resource': {
            'id': 2,
            'userId': 1,
            'resourceDicType': '文件类别',
            'resourceDicItem': '计算机类',
            'zanCount': 0,
            'pvCount': 0,
            'commentCount': 0,
            'state': '正常',
            'remark': null,
            'createTime': '2019-05-13 09:18:13',
            'updateTime': '2019-05-13 09:18:13',
            'deleted': false
          },
          'commentList': []
        }
      ],
    }
  },
  methods: {
    isActive(name) {
      return this.active === name;
    },
    select(name) {
      this.active = name;
    },
    getMomentList() {
      this.$axios.get('/api/moment')
        .then(res => {
          // TODO 2019/5/8 数据结构改变，需要重构
          let result = res.data;
          this.momentPageList = result.data;
          this.momentPageList.forEach(momentPage => {
            momentPage.show = false;
            if (momentPage.moment.images !== null && momentPage.moment.images.length !== 0) {
              momentPage.moment.images = JSON.parse(momentPage.moment.images);
            }
          })
        });
    }
  },
  mounted() {
    this.getMomentList();
  }
}
</script>

<style lang="less" scoped>

.container {
  width: 1140px;
  margin: 10px auto;
  padding: 5px;
  /*background: #ccc;*/
}

.moment-editor {
  width: 100%;

  .editor-toolbar {
    margin-top: 10px;
    overflow: hidden;

    .toolbar-submit {
      float: right;
    }
  }
}

.moment-list {
  margin: 15px auto;
  column-count: 3;

  .moment-item {
    box-sizing: border-box;
    padding: 7px;
    width: 380px;
    height: 100%;
    overflow: auto;

    .moment-item-wrapper {
      padding: 20px 20px 5px 20px;

      .moment-avatar {
        height: 50px;
        width: 50px;
      }
    }


    .moment-image-group {
      margin-top: 15px;
    }

    .moment-time, .moment-content {
      margin-left: 55px;
    }

    .moment-button {
      margin-left: 5px;
    }
  }
}
</style>
