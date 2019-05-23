<template>
  <div class="container">
    <Row>
      <iCol span="16">
        <!-- 失物寻物菜单 -->
        <sui-menu>
          <sui-menu-item link v-for="item in menu.items" :key="item" :content="item"
                         :active="item === menu.active" @click="select(item)"/>
          <sui-menu-item position="right">
            <sui-input transparent icon="search" placeholder="搜索"/>
          </sui-menu-item>
        </sui-menu>
        <!-- 失物寻物菜单 END -->
        <!-- 失物寻物列表 -->
        <sui-card class="fluid" v-for="lostFound in lostFoundPage.records" :key="lostFound.id">
          <sui-card-content>
            <sui-feed>
              <sui-feed-event>
                <sui-feed-label :image="lostFound.user.avatar"/>
                <sui-feed-content>
                  <sui-feed-summary>
                    <sui-label basic :color="lostFound.lostFoundCategory === '寻物启事'? 'red':'green'">
                      {{lostFound.lostFoundCategory}}
                    </sui-label>
                    <a href="#">{{lostFound.title}}</a>
                    <sui-feed-date>
                      <Time :time="lostFound.createTime"/>
                    </sui-feed-date>
                  </sui-feed-summary>
                  <sui-feed-extra text>{{lostFound.description}}</sui-feed-extra>
                  <sui-feed-extra images v-if="lostFound.images && lostFound.images.length > 0">
                    <img v-for="img in lostFound.images" :src="img">
                  </sui-feed-extra>
                  <sui-feed-meta>
                    <sui-feed-like>
                      👍 {{lostFound.resource.zanCount}}
                    </sui-feed-like>
                    <sui-feed-like>
                      💬 {{lostFound.resource.commentCount}}
                    </sui-feed-like>
                  </sui-feed-meta>
                </sui-feed-content>
              </sui-feed-event>
            </sui-feed>
          </sui-card-content>
        </sui-card>
        <!-- 失物寻物列表 END -->
        <!-- 加载更多 -->
        <sui-button class="fluid" basic content="加载更多"/>
        <!-- 加载更多 END -->
      </iCol>
      <iCol span="8">
        <!-- 寻物启事提示 -->
        <sui-card class="fluid">
          <sui-message attached="top" icon="eye" negative>
            <sui-message-header>寻物启事小贴士</sui-message-header>
            <p>什么，有东西丢啦？不要怕，快来发布一条寻物启事吧，很快就会有好心人联系你啦。对啦，一定要核实信息，准确后再发布哦 😃</p>
          </sui-message>
          <sui-button attached="bottom" icon="add" content="发布寻物启事" @click="showLostFoundModal('寻物启事')"/>
        </sui-card>
        <!-- 寻物启事提示 END -->
        <!-- 失物招领提示 -->
        <sui-card class="fluid">
          <sui-message attached="top" icon="bullhorn" positive>
            <sui-message-content>
              <sui-message-header>失物招领小贴士</sui-message-header>
              <p>捡到哪位同学不小信掉落的物品啦？快发布一条失物招领吧，失主一定会非常非常感激你呢 😘</p>
            </sui-message-content>
          </sui-message>
          <sui-button attached="bottom" icon="add" content="发布失物招领" @click="showLostFoundModal('失物招领')"/>
        </sui-card>
        <!-- 失物招领提示 END -->
      </iCol>
    </Row>
    <!-- 失物寻物添加模态框 -->
    <Modal v-model="lostFoundAddModal.show" :title="lostFoundAddModal.title"
           :closable="false" :mask-closable="false" width="600">
      <Form :model="lostFoundAddModal.form" :label-width="50">
        <FormItem label="标题" required>
          <Input v-model="lostFoundAddModal.form.title" size="large" placeholder="清晰的标题能让更多人注意到"/>
        </FormItem>
        <FormItem label="物品" required>
          <Input v-model="lostFoundAddModal.form.itemName" size="large" placeholder="请填写物品名"/>
        </FormItem>
        <FormItem label="时间" required>
          <DatePicker :value="lostFoundAddModal.form.time" @on-change="time => lostFoundAddModal.form.time = time"
                      type="datetime" size="large" :placeholder="lostFoundAddModal.placeholder.time"
                      style="width: 100%"/>
        </FormItem>
        <FormItem label="地点" required>
          <Input v-model="lostFoundAddModal.form.address" size="large"
                 :placeholder="lostFoundAddModal.placeholder.address"/>
        </FormItem>
        <FormItem label="描述" required>
          <Input v-model="lostFoundAddModal.form.description" type="textarea" size="large"
                 :autosize="{minRows: 5,maxRows: 10}" placeholder="输入物品描述，有助于他人获取信息"/>
        </FormItem>
        <FormItem label="图片" style="margin-bottom: 0">
          <ImageUploader v-if="lostFoundAddModal.show"
                         @images-change="images => lostFoundAddModal.form.images = images"/>
        </FormItem>
      </Form>
      <template #footer>
        <Button type="text" @click="lostFoundAddModal.show = false">取消</Button>
        <Button @click="addLostFound">确定发布</Button>
      </template>
    </Modal>
    <!-- 失物寻物添加模态框 END -->
  </div>
</template>

<script>
import ImageUploader from '@/components/common/ImageUploader'

export default {
  name: 'LostFound',
  components: {
    ImageUploader
  },
  data() {
    return {
      menu: {
        active: '查看所有',
        items: ['查看所有', '失物招领', '寻物启事'],
      },
      param: {
        /* query 参数 */
        lostFoundCategory: null,
        /* page & order 参数 */
        desc: 'create_time',
        current: 1
      },
      lostFoundPage: {
        'records': [
          {
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
            'images': ['https://img12.360buyimg.com/n7/jfs/t22831/61/2367409622/289066/5d9c3f/5b7d10feNe776b305.jpg'],
            'user': {
              'id': 1,
              'username': 'admin',
              'nickname': '管理员',
              'avatar': 'http://img.angus-liu.cn/avatar/avatar07.png',
              'sign': '一句话介绍自己'
            },
            'resource': {
              'id': 16,
              'zanCount': 0,
              'pvCount': 0,
              'commentCount': 0
            }
          }
        ],
        'total': 5,
        'size': 1,
        'current': 1,
        'searchCount': true,
        'pages': 5
      },
      lostFoundAddModal: {
        show: false,
        title: '发布寻物启事',
        placeholder: {
          time: '请选择丢失时间',
          address: '请填写丢失地点'
        },
        form: {
          lostFoundCategory: '寻物启事',
          title: '',
          itemName: '',
          time: null,
          address: '',
          description: '',
          images: null,
        }
      },
    }
  },
  methods: {
    // 切换菜单
    select(item) {
      this.menu.active = item;
      switch (item) {
        case '查看所有':
          this.param.lostFoundCategory = null;
          break;
        case '失物招领':
          this.param.lostFoundCategory = '失物招领';
          break;
        case '寻物启事':
          this.param.lostFoundCategory = '寻物启事';
          break;
      }
      this.param.current = 1;
      this.getLostFoundPage(this.param);
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
    // 配置失物寻物添加模态框
    showLostFoundModal(type) {
      if (type === '寻物启事') {
        this.lostFoundAddModal = {
          show: true,
          title: '发布寻物启事',
          placeholder: {
            time: '请选择丢失时间',
            address: '请填写丢失地点'
          },
          form: {
            lostFoundCategory: '寻物启事',
            title: '',
            itemName: '',
            time: null,
            address: '',
            description: '',
            images: null,
          }
        }
      } else {
        this.lostFoundAddModal = {
          show: true,
          title: '发布失物招领',
          placeholder: {
            time: '请选择拾取时间',
            address: '请填写拾取地点'
          },
          form: {
            lostFoundCategory: '失物招领',
            title: '',
            itemName: '',
            time: null,
            address: '',
            description: '',
            images: null,
          }
        }
      }
    },
    // 添加失物寻物
    addLostFound() {
      let lostFoundAddForm = this.lostFoundAddModal.form;
      lostFoundAddForm.images = JSON.stringify(lostFoundAddForm.images);
      this.$axios.post('/api/lost-found', lostFoundAddForm)
        .then(res => {
          let result = res.data;
          if (result.success) {
            this.$Notice.success({ title: 'Bingo', desc: '发布成功' });
            this.lostFoundAddModal.show = false;
            this.getLostFoundPage();
          }
        })
    }
  },
  mounted() {
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
  }

  .tab-container {
    background: #fff;
    width: 100%;
    padding: 1rem;
  }
}
</style>
