<template>
  <div class="container">
    <!-- 消息提示 -->
    <sui-message info dismissable>
      <sui-message-header>如何发表动态？</sui-message-header>
      <p>
        成功登录 Smilcool 系统后，在头像下拉菜单中可以找到 “发表动态” 选项，点击即可发表动态啦。快来发布你的校园动态吧 😆
      </p>
    </sui-message>
    <!-- 消息提示 END -->
    <!-- 动态菜单栏 -->
    <sui-menu pointing>
      <a is="sui-menu-item" v-for="item in menu.items" :key="item" :content="item"
         :active="item === menu.active" @click="select(item)"/>
      <sui-menu-menu position="right">
        <sui-button basic attached="right" icon="paper plane outline" content="发布动态"
                    @click="momentAddModal.show = true"/>
      </sui-menu-menu>
    </sui-menu>
    <!-- 动态菜单栏 END -->
    <!-- 动态列表 -->
    <div class="moment-list">
      <sui-card class="fluid moment-item" v-for="moment in momentPage.records" :key="moment.id">
        <sui-card-content class="moment-item-wrapper">
          <sui-card-header class="moment-header">
            <sui-image class="moment-avatar" :src="moment.user.avatar" circular/>
            <span style="margin-left:5px; font-size: 16px">{{moment.user.nickname}}</span>
            <sui-button class="right floated" size="mini" basic positive>关注</sui-button>
          </sui-card-header>
          <sui-card-meta class="moment-time">
            <Time :time="moment.createTime"/>
          </sui-card-meta>
          <sui-card-description class="moment-content">
            <p>{{moment.content}}</p>
            <sui-image-group class="moment-image-group"
                             v-if="moment.images && moment.images.length > 0" size="tiny">
              <sui-image v-for="(image, index) in moment.images" :key="index" :src="image"/>
            </sui-image-group>
          </sui-card-description>

          <span slot="right">
            <a is="sui-label" basic>
              👍 {{moment.resource.zanCount}}
            </a>
            <a is="sui-label" basic @click="moment.show = !moment.show">
              💬 {{moment.resource.commentCount}}
            </a>
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
    <!-- 动态列表 END -->
    <!-- 加载更多 -->
    <sui-button class="fluid" color="grey" basic content="加载更多"/>
    <!-- 加载更多 END -->
    <!-- 发布动态模态框 -->
    <Modal v-model="momentAddModal.show" title="发布动态" :closable="false" :mask-closable="false" width="600">
      <Form :model="momentAddModal.form" :label-width="50">
        <FormItem label="类别" required>
          <Select v-model="momentAddModal.form.momentCategory" size="large">
            <Option value="校园动态">校园动态</Option>
          </Select>
        </FormItem>
        <FormItem label="内容" required>
          <Input v-model="momentAddModal.form.content" type="textarea" size="large" :autosize="{minRows: 5,maxRows: 10}"
                 placeholder="输入你发现的趣事吧~"/>
        </FormItem>
        <FormItem label="图片" style="margin-bottom: 0">
          <ImageUploader v-if="momentAddModal.show" @images-change="images => momentAddModal.form.images = images"/>
        </FormItem>
      </Form>
      <template #footer>
        <Button type="text" @click="resetMomentAddModal">取消</Button>
        <Button @click="addMoment">确定发布</Button>
      </template>
    </Modal>
    <!-- 发布动态模态框 END -->
  </div>
</template>

<script>
import ImageUploader from '@/components/common/ImageUploader'

export default {
  name: 'Moment',
  components: {
    ImageUploader
  },
  data() {
    return {
      menu: {
        active: '最新',
        items: ['最新', '最热', '关注']
      },
      page: {
        desc: 'create_time',
        size: 20,
        current: 1
      },
      momentPage: {
        'records': [{
          'id': 1,
          'userId': 1,
          'resourceId': 2,
          'momentCategory': '校园动态',
          'content': '校园动态测试',
          'createTime': '2019-05-15 16:23:50',
          'images': ['http://img.angus-liu.cn/avatar/avatar01.jpg', 'http://img.angus-liu.cn/avatar/avatar03.jpg'],
          'user': {
            'id': 1,
            'username': 'admin',
            'nickname': '管理员',
            'avatar': 'http://img.angus-liu.cn/avatar/avatar07.png',
            'sign': '一句话介绍自己'
          },
          'resource': {
            'id': 2,
            'zanCount': 0,
            'pvCount': 0,
            'commentCount': 0
          }
        }],
        'total': 18,
        'size': 10,
        'current': 1,
        'searchCount': true,
        'pages': 2
      },
      momentAddModal: {
        show: false,
        form: {
          momentCategory: '校园动态',
          content: '',
          images: []
        }
      }
    }
  },
  methods: {
    // 重置动态添加模态框
    resetMomentAddModal() {
      this.momentAddModal = {
        show: false,
        form: {
          momentCategory: '校园动态',
          content: '',
          images: []
        }
      };
    },
    // 切换菜单
    select(item) {
      this.menu.active = item;
      if (item === '最新') {
        this.page.desc = 'create_time';
      } else {
        this.page.desc = 'comment_count,zan_count';
      }
      // 重新获取数据
      this.getMomentPage(this.page)
    },
    // 获取动态分页
    getMomentPage(param) {
      this.$axios.get('/api/moment/page', param)
        .then(res => {
          let result = res.data;
          this.momentPage = result.data;
          this.momentPage.records.forEach(moment => {
            if (moment.images && moment.images.length > 0) {
              moment.images = JSON.parse(moment.images);
            }
          })
        });
    },
    // 动态添加
    addMoment() {
      let momentAddForm = this.momentAddModal.form;
      momentAddForm.images = JSON.stringify(momentAddForm.images);
      this.$axios.post('/api/moment', momentAddForm)
        .then(res => {
          let result = res.data;
          if (result.success) {
            this.$Notice.success({ title: 'Bingo', desc: '发布成功' });
            this.resetMomentAddModal();
            this.getMomentPage();
          }
        })
    }
  },
  mounted() {
    this.getMomentPage(this.page);
  }
}
</script>

<style lang="less" scoped>
.container {
  width: 1140px;
  margin: 20px auto;
  padding: 5px;

  .moment-list {
    margin: 15px auto 25px;
    column-count: 3;

    .moment-item {
      break-inside: avoid;
      padding: 7px;

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

      .moment-time {
        margin-top: -10px;
        margin-left: 55px;
      }

      .moment-content {
        margin-left: 55px;
      }

      .moment-button {
        margin-left: 5px;
      }
    }
  }
}
</style>
