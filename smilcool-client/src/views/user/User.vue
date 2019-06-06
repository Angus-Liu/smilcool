<template>
  <div class="container">
    <Row>
      <iCol span="6">
        <!-- 用户简要信息 -->
        <sui-card class="fluid">
          <sui-image style="width: 100%;" :src="user.avatar"/>
          <sui-card-content>
            <sui-card-header>{{user.nickname}}</sui-card-header>
            <sui-card-meta>{{user.username}}</sui-card-meta>
            <sui-card-description>{{user.sign}}</sui-card-description>
          </sui-card-content>
          <sui-card-content extra>
            <sui-icon name="user"/>
            用户
            <span slot="right">{{user.createTime}} 加入</span>
          </sui-card-content>
        </sui-card>
        <!-- 用户简要信息 END -->
        <!-- 操作按钮 -->
        <div class="actions-buttons" v-if="$store.state.user && user.id !== $store.state.user.id">
          <sui-button-group>
            <sui-button basic color="red" icon="heart" content="关注"/>
            <sui-button basic color="blue" icon="envelope" content="私信"/>
          </sui-button-group>
        </div>
        <!-- 操作按钮 END -->
      </iCol>
      <iCol span="18">
        <!-- 用户详细信息 -->
        <sui-card class="fluid">
          <sui-message attached="top" content="详细信息"/>
          <sui-card-content>
            <Row>
              <iCol span="8">
                <span class="emoji">💕</span>性别：{{user.sex}}
              </iCol>
              <iCol span="8">
                <span class="emoji">🎂</span>生日：{{user.birthday}}
              </iCol>
              <iCol span="8">
                <span class="emoji">📧</span>邮箱：{{user.email}}
              </iCol>
            </Row>
            <Row>
              <iCol span="8">
                <span class="emoji">🏫</span>学院：{{user.college}}
              </iCol>
              <iCol span="8">
                <span class="emoji">👩‍🎓</span>年级：{{user.grade}}
              </iCol>
              <iCol span="8">
                <span class="emoji">📖</span>专业：{{user.major}}
              </iCol>
            </Row>
            <Row>
              <iCol span="8">
                <span class="emoji">📱</span>手机： {{user.phone}}
              </iCol>
              <iCol span="16">
                <span class="emoji">📷</span>简介：{{user.intro}}
              </iCol>
            </Row>
          </sui-card-content>
          <sui-button basic attached="bottom" icon="pencil alternate" content="修改个人信息"
                      v-if="$store.state.user && user.id === $store.state.user.id"
                      @click="userUpdateModal.show = true"/>
        </sui-card>
        <!-- 用户详细信息 END -->
        <sui-tab>
          <!-- 发表文章 -->
          <sui-tab-pane title="📰 发表文章">
            <ul class="list" v-if=" articlePage.records.length > 0">
              <li class="item" v-for="article in articlePage.records" :key="article.id">
                <span class="category">
                  <sui-label>{{article.articleCategory}}</sui-label>
                </span>
                <router-link :to="'/article/' + article.id">
                  <span class="title">{{article.title}}</span>
                </router-link>
                <span class="time">{{article.createTime}}</span>
              </li>
            </ul>
            <span v-else>暂无</span>
          </sui-tab-pane>
          <!-- 发表文章 END -->
          <!-- 上传文件 -->
          <sui-tab-pane title="📁 上传文件">
            <ul class="list" v-if="filePage.records.length > 0">
              <li class="item" v-for="file in filePage.records" :key="file.id">
                <span class="category">
                  <sui-label>{{file.fileCategory}}</sui-label>
                </span>
                <span class="title">{{file.title}}</span>
                <span class="time">{{file.createTime}}</span>
              </li>
            </ul>
            <span v-else>暂无</span>
          </sui-tab-pane>
          <!-- 上传文件 END -->
          <!-- 发布二手 -->
          <sui-tab-pane title="🛒 发布二手">
            <ul class="list" v-if="secondHandPage.records.length > 0">
              <li class="item" v-for="secondHand in secondHandPage.records" :key="secondHand.id">
                <span class="category">
                  <sui-label>{{secondHand.secondHandCategory}}</sui-label>
                </span>
                <span class="title">{{secondHand.title}}</span>
                <span class="time">{{secondHand.createTime}}</span>
              </li>
            </ul>
            <span v-else>暂无</span>
          </sui-tab-pane>
          <!-- 发布二手 END -->
          <!-- 发布失物寻物 -->
          <sui-tab-pane title="👜 发布失物寻物">
            <ul class="list" v-if="lostFoundPage.records.length > 0">
              <li class="item" v-for="lostFound in lostFoundPage.records" :key="lostFound.id">
                <span class="category">
                  <sui-label>{{lostFound.lostFoundCategory}}</sui-label>
                </span>
                <span class="title">{{lostFound.title}}</span>
                <span class="time">{{lostFound.createTime}}</span>
              </li>
            </ul>
            <span v-else>暂无</span>
          </sui-tab-pane>
          <!-- 发布失物寻物 END -->
          <!-- 发布帖子 -->
          <sui-tab-pane title="📃 发布帖子">
            <span>暂无</span>
          </sui-tab-pane>
          <!-- 发布帖子 END -->
        </sui-tab>
      </iCol>
    </Row>
    <!-- 修改个人信息模态框 -->
    <Modal v-model="userUpdateModal.show" title="更新信息" width="600">
      <Form :model="userUpdateModal.form" :label-width="50">
        <FormItem label="头像" required>
          <Upload :show-upload-list="false"
                  :format="['jpg','jpeg','png']"
                  :data="{type: 'avatar'}"
                  action="/api/local-storage/upload"
                  :on-success="res => userUpdateModal.form.avatar = res.data.url">
            <sui-image :src="userUpdateModal.form.avatar" size="tiny" vertical-align="top"/>
          </Upload>
        </FormItem>
        <FormItem label="昵称" required>
          <Input v-model="userUpdateModal.form.nickname" size="large"/>
        </FormItem>
        <FormItem label="性别" required>
          <Select v-model="userUpdateModal.form.sex" size="large">
            <Option v-for="(item, index) in sexOption" :value="item" :key="index">{{item}}</Option>
          </Select>
        </FormItem>
        <FormItem label="生日" required>
          <DatePicker :value="userUpdateModal.form.birthday" @on-change="time => userUpdateModal.form.birthday = time"
                      type="date" size="large" style="width: 100%"/>
        </FormItem>
        <FormItem label="学院" required>
          <Input v-model="userUpdateModal.form.college" size="large"/>
        </FormItem>
        <FormItem label="年级" required>
          <Input v-model="userUpdateModal.form.grade" size="large"/>
        </FormItem>
        <FormItem label="专业" required>
          <Input v-model="userUpdateModal.form.major" size="large"/>
        </FormItem>
        <FormItem label="手机" required>
          <Input v-model="userUpdateModal.form.phone" size="large"/>
        </FormItem>
        <FormItem label="签名" required>
          <Input v-model="userUpdateModal.form.sign" size="large"/>
        </FormItem>
        <FormItem label="简介" required>
          <Input v-model="userUpdateModal.form.intro" type="textarea" size="large"
                 :autosize="{minRows: 3, maxRows: 3}"/>
        </FormItem>
      </Form>
      <template #footer>
        <Button type="text" @click="resetUserUpdateModal">取消</Button>
        <Button @click="updateUser">确定更新</Button>
      </template>
    </Modal>
    <!-- 修改个人信息模态框 END -->
  </div>
</template>

<script>
export default {
  name: 'User',
  props: ['id'],
  data() {
    return {
      sexOption: ['男', '女', '保密'],
      // 用户信息
      user: {
        'id': -1,
        'username': 'anonymous',
        'nickname': '游客',
        'avatar': require('../../assets/img/avatar/anonymous-avatar.jpg'),
        'sex': '保密',
        'birthday': '1970-01-01',
        'sign': '一句话介绍自己',
        'intro': '这个人很神秘，什么也没写',
        'grade': '未填写',
        'college': '未填写',
        'major': '未填写',
        'phone': '未填写',
        'email': 'admin@admin.com',
        'createTime': '2019-03-28'
      },
      // 用户信息更新模态框
      userUpdateModal: {
        show: false,
        form: {
          'nickname': '游客',
          'avatar': require('../../assets/img/avatar/anonymous-avatar.jpg'),
          'sex': '保密',
          'birthday': '1970-01-01',
          'college': '未填写',
          'grade': '未填写',
          'major': '未填写',
          'phone': '未填写',
          'sign': '一句话介绍自己',
          'intro': '这个人很神秘，什么也没写',
        }

      },
      // 查询参数
      param: {
        // query 参数
        userId: this.id,
        // page & order 参数
        desc: 'create_time',
        size: 10
      },
      // 文章分页
      articlePage: {
        'records': [{
          'id': -1,
          'userId': -1,
          'resourceId': -1,
          'articleCategory': '校园文章',
          'title': '文章测试',
          'createTime': '2019-05-13'
        }],
        'total': 7,
        'size': 10,
        'current': 1,
        'searchCount': true,
        'pages': 1
      },
      // 文件分页
      filePage: {
        'records': [
          {
            'id': 1,
            'userId': 1,
            'resourceId': 2,
            'fileCategory': '计算机类',
            'title': '计算机类全套PPT，你值得拥有',
            'description': '计算机类全套PPT，你值得拥有',
            'name': '中北大学计算机类.ppt',
            'size': '200MB',
            'url': 'http://bkt.angus-liu.cn/中北大学计算机类.ppt',
            'downloadCount': 0,
            'createTime': '2019-05-13 09:18:13'
          }],
        'total': 10,
        'size': 10,
        'current': 1,
        'searchCount': true,
        'pages': 1
      },
      // 二手交易分页
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
            'createTime': '2019-05-23 15:56:27'
          }],
        'total': 20,
        'size': 1,
        'current': 1,
        'searchCount': true,
        'pages': 20
      },
      // 失物寻物分页
      lostFoundPage: {
        'records': [{
          'id': 1,
          'userId': 1,
          'resourceId': 16,
          'lostFoundCategory': '寻物启事',
          'title': '四食堂丢失一个钱包',
          'description': '如图，钱包是黑色的，里面有少量现金和一张工行的卡',
          'itemName': '钱包',
          'time': '2019-05-17 12:10:01',
          'address': '四食堂',
          'createTime': '2019-05-17 15:39:45',
          'images': ['https://img12.360buyimg.com/n7/jfs/t22831/61/2367409622/289066/5d9c3f/5b7d10feNe776b305.jpg']
        }],
        'total': 5,
        'size': 1,
        'current': 1,
        'searchCount': true,
        'pages': 5
      },
    }
  },
  methods: {
    // 重置用户信息更新模态框
    resetUserUpdateModal() {
      this.userUpdateModal = {
        show: false,
        form: { ...this.user }
      }
    },
    // 获取用户信息
    getUser() {
      this.$axios.get(`/api/user/${this.id}`)
        .then(res => {
          let result = res.data;
          this.user = result.data;
          this.userUpdateModal.form = { ...this.user };
        });
    },
    updateUser() {
      this.$axios.put('/api/user', this.userUpdateModal.form)
        .then(res => {
          let result = res.data;
          this.user = result.data;
          this.resetUserUpdateModal();
          this.$store.commit('userUpdate', this.user);
        });
    },
    // 获取文章分页
    getArticlePage(param) {
      this.$axios.get('/api/article/page', param)
        .then(res => {
          let result = res.data;
          this.articlePage = result.data;
        });
    },
    // 获取文件分页
    getFilePage(param) {
      this.$axios.get('/api/file/page', param)
        .then(res => {
          let result = res.data;
          this.filePage = result.data;
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
            }
          });
        });
    },
    // 获取失物寻物分页
    getLostFoundPage(param) {
      this.$axios.get('/api/lost-found/page', param)
        .then(res => {
          let result = res.data;
          this.lostFoundPage = result.data;
          this.lostFoundPage.records.forEach(lostFound => {
            if (lostFound.images && lostFound.images.length > 0) {
              lostFound.images = JSON.parse(lostFound.images);
            }
          })
        })
    },
  },
  mounted() {
    this.getUser();
    this.getArticlePage(this.param);
    this.getFilePage(this.param);
    this.getSecondHandPage(this.param);
    this.getLostFoundPage(this.param);
  }
}
</script>

<style lang="less" scoped>
.container {
  width: 1140px;
  margin: 0 auto;
  padding: 5px;

  .ivu-col {
    padding: 7px;

    .actions-buttons {
      text-align: center;

      .button {
        width: 134.5px;
      }
    }

    .emoji {
      display: inline-block;
      width: 30px;
      text-align: center;
      font-size: 1.2em;
    }
  }

  .list {
    margin: 10px 0 5px 0;
    list-style: none;

    .item {
      display: block;
      margin: 5px 0;
      line-height: 2.5em;
      overflow: auto;

      .category {
        float: left;
        width: 100px;
        margin-right: 10px;
        text-align: center;
      }

      .title {
        display: inline-block;
        max-width: 400px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        font-size: 1.1em;
        color: #5c5c5c;
      }

      .title:hover {
        color: #ff8364;
        cursor: pointer;
      }

      .time {
        color: #aaa;
        float: right;
      }
    }
  }
}
</style>
