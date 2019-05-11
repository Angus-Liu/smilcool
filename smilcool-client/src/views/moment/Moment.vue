<template>
  <div class="container">
    <div class="dock">
      <nav class="dock-nav">
        <ul class="nav-list">
          <li class="nav-item active"><a href="/pins/recommended">推荐</a></li>
          <li class="nav-item"><a href="#">热门</a></li>
          <li class="nav-item"><a href="#">关注</a></li>
          <li class="nav-item"><a href="#">开源推荐</a></li>
          <li class="nav-item"><a href="#">内推招聘</a></li>
          <li class="nav-item"><a href="#">掘金相亲</a></li>
          <li class="nav-item"><a href="#">上班摸鱼</a></li>
          <li class="nav-item"><a href="#">应用安利</a></li>
          <li class="nav-item"><a href="#">开发工具</a></li>
          <li class="nav-item"><a href="#">New资讯</a></li>
        </ul>
      </nav>
    </div>
    <div class="moment">
      <Card class="moment-editor">
        <Input v-model="moment" type="textarea" :rows="4" placeholder="又遇到什么好事啦？快发表一条动态吧 : )"/>
        <div class="editor-toolbar">
          <sui-button class="toolbar-submit" basic primary>发布</sui-button>
        </div>
      </Card>
      <div class="moment-list">
        <sui-card class="moment-item" v-for="moment in momentList" :key="moment.id">
          <sui-card-content class="moment-item-wrapper">
            <sui-card-header class="moment-header">
              <sui-image class="moment-avatar" :src="moment.postUser.avatar" circular/>
              <span style="margin-left:5px; font-size: 16px">{{moment.postUser.nickname}}</span>
              <sui-button class="right floated" size="mini" basic positive>关注</sui-button>
            </sui-card-header>
            <sui-card-meta class="moment-time">
              <Time :time="moment.createTime"/>
            </sui-card-meta>
            <sui-card-description class="moment-content">
              <p>{{moment.content}}</p>
              <sui-image-group class="moment-image-group" v-if="moment.imageArr" size="small">
                <sui-image v-for="(image, index) in moment.imageArr" :src="image" :key="index"/>
              </sui-image-group>
            </sui-card-description>

            <span slot="right">
              <Button class="moment-button" type="text" shape="circle" size="large">
                <sui-icon name="thumbs up outline"/>13
              </Button>
              <Button class="moment-button" type="text" shape="circle" size="large">
                <sui-icon name="comment outline"/>5
              </Button>
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
        </sui-card>
      </div>
    </div>
    <div class="sidebar">
      <div class="sidebar-user">
        user
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Moment',
  data() {
    return {
      moment: '',
      momentList: [{
        id: -1,
        userId: 1,
        postUser: {
          id: 1,
          username: 'admin',
          nickname: '管理员',
          avatar: 'http://img.angus-liu.cn/avatar/avatar07.png'
        },
        resourceId: 11,
        resourceTypeId: 13,
        content: '这是一条微博啦',
        image: '["http://img.angus-liu.cn/avatar/avatar01.jpg", "http://img.angus-liu.cn/avatar/avatar02.jpg", "http://img.angus-liu.cn/avatar/avatar03.jpg"]',
        imageArr: ['http://img.angus-liu.cn/avatar/avatar01.jpg', 'http://img.angus-liu.cn/avatar/avatar02.jpg', 'http://img.angus-liu.cn/avatar/avatar03.jpg'],
        createTime: '2019-05-03 09:18:52'
      }]
    }
  },
  methods: {
    getMomentList() {
      this.$axios.get('/api/moment')
        .then(res => {
          // TODO 2019/5/8 数据结构改变，需要重构
          let result = res.data;
          this.momentList = result.data;
          this.momentList.forEach(moment => {
            if (moment.image !== null && moment.image.length !== 0) {
              moment.imageArr = JSON.parse(moment.image);
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
  display: flex;
}

.dock {
  width: 250px;
  margin-right: 15px;
}

.dock-nav {
  width: 8em;
  border-radius: .2em;
  background-color: #fff;
  position: fixed;
  left: 350px;
  top: 90px;
  box-shadow: 1px 1px 2px #eee;

  .nav-list {
    list-style: none;
    height: 100%;
    display: flex;
    flex-direction: column;
    padding: 1em;

    .nav-item {
      height: 30px;
      display: flex;
      align-items: center;
      justify-content: center;
      border-radius: 3px;
      transition: background-color .2s, color .2s;

      a {
        color: #909090;
      }
    }

    .nav-item:not(:first-child) {
      margin-top: .83em;
    }

    .nav-item:nth-child(3) {
      border-bottom: 1px solid #eee;
    }

    .active {
      background-color: #007fff;

      a {
        color: #fff;
      }
    }
  }
}

.moment {
  width: 600px;
  margin-right: 20px;
  /*background: #ccc;*/

  .moment-editor {
    .editor-toolbar {
      margin-top: 10px;
      overflow: hidden;

      .toolbar-submit {
        float: right;
      }
    }
  }

  .moment-list {
    margin: 15px 0;

    .moment-item {
      width: 100%;

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

  .sidebar {
    width: 248px;
    flex: 1;

    .sidebar-user {
      width: 248px;
      display: flex;
      flex-direction: column;
      background-color: #fff;
      border-radius: .2rem;
      margin-bottom: 8px;
    }
  }
}
</style>
