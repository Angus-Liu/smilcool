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
        <sui-card v-for="(lostFoundPage, index) in lostFoundPageList" :key="index">
          <sui-card-content>
            <sui-feed>
              <sui-feed-event>
                <sui-feed-label :image="lostFoundPage.user.avatar"/>
                <sui-feed-content>
                  <sui-feed-summary>
                    <sui-label basic :color="lostFoundPage.lostFound.lostFoundCategory === '寻物启事'? 'red':'green'">
                      {{lostFoundPage.lostFound.lostFoundCategory}}
                    </sui-label>
                    <a href="#">{{lostFoundPage.lostFound.title}}</a>
                    <sui-feed-date>
                      <Time :time="lostFoundPage.lostFound.createTime"/>
                    </sui-feed-date>
                  </sui-feed-summary>
                  <sui-feed-extra text>{{lostFoundPage.lostFound.description}}</sui-feed-extra>
                  <sui-feed-extra images
                                  v-if="lostFoundPage.lostFound.images && lostFoundPage.lostFound.images.length > 0">
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
        <!-- 失物寻物列表 END -->
        <!-- 加载更多 -->
        <sui-button class="fluid" basic content="加载更多"/>
        <!-- 加载更多 END -->
      </iCol>
      <iCol span="8">
        <!-- 寻物启事提示 -->
        <sui-card>
          <sui-message attached="top" icon="eye" negative>
            <sui-message-header>寻物启事小贴士</sui-message-header>
            <p>什么，有东西丢啦？不要怕，快来发布一条寻物启事吧，很快就会有好心人联系你啦。对啦，一定要核实信息，准确后再发布哦 😃</p>
          </sui-message>
          <sui-button attached="bottom" icon="add" content="发布寻物启事" @click="showLostFoundModal('found')"/>
        </sui-card>
        <!-- 寻物启事提示 END -->
        <!-- 失物招领提示 -->
        <sui-card>
          <sui-message attached="top" icon="bullhorn" positive>
            <sui-message-content>
              <sui-message-header>失物招领小贴士</sui-message-header>
              <p>捡到哪位同学不小信掉落的物品啦？快发布一条失物招领吧，失主一定会非常非常感激你呢 😘</p>
            </sui-message-content>
          </sui-message>
          <sui-button attached="bottom" icon="add" content="发布失物招领" @click="showLostFoundModal('lost')"/>
        </sui-card>
        <!-- 失物招领提示 END -->
      </iCol>
    </Row>
    <!-- 失物寻物模态框 -->
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
        <Button @click="addLostFoundAddModal">确定发布</Button>
      </template>
    </Modal>
    <!-- 失物寻物模态框 END -->
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
    showLostFoundModal(type) {
      if (type === 'found') {
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
    select(item) {
      this.menu.active = item;
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
    },
    addLostFoundAddModal() {
      let lostFoundAddForm = this.lostFoundAddModal.form;
      lostFoundAddForm.images = JSON.stringify(lostFoundAddForm.images);
      this.$axios.post('/api/lost-found', lostFoundAddForm)
        .then(res => {
          let result = res.data;
          if (result.success) {
            this.$Notice.success({ title: 'Bingo', desc: '发布成功' });
            this.lostFoundAddModal.show = false;
            this.getLostFoundPageList();
          }
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

  .ivu-col {
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
