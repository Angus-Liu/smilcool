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
      <a is="sui-menu-item" v-for="item in items" :active="isActive(item)" :key="item" :content="item"
         @click="select(item)"/>
      <sui-menu-menu position="right">
        <sui-menu-item>
          <sui-input transparent icon="search" placeholder="搜索动态"/>
        </sui-menu-item>
        <sui-button basic attached="right" icon="paper plane outline" content="发布动态" @click="momentModal.show = true"/>
      </sui-menu-menu>
    </sui-menu>
    <!-- 动态菜单栏 END -->
    <!-- 动态列表 -->
    <div class="moment-list">
      <sui-card class="fluid moment-item" v-for="momentPage in momentPageList" :key="momentPage.moment.id">
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
            <a is="sui-label" basic>
              👍 {{momentPage.resource.zanCount}}
            </a>
            <a is="sui-label" basic @click="momentPage.show = !momentPage.show">
              💬 {{momentPage.resource.commentCount}}
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
        <sui-card-content extra v-show="momentPage.show">
          评论列表
        </sui-card-content>
      </sui-card>
    </div>
    <!-- 动态列表 END -->
    <!-- 加载更多 -->
    <sui-button class="fluid" color="grey" basic content="加载更多"/>
    <!-- 加载更多 END -->
    <!-- 发布动态模态框 -->
    <Modal v-model="momentModal.show" title="发布动态" :mask-closable="false" width="600" footer-hide>
      <Form :model="momentModal.form" :label-width="50">
        <FormItem label="类别">
          <Select v-model="momentModal.form.momentCategory" size="large">
            <Option value="beijing">New York</Option>
            <Option value="shanghai">London</Option>
            <Option value="shenzhen">Sydney</Option>
          </Select>
        </FormItem>
        <FormItem label="内容">
          <Input v-model="momentModal.form.content" type="textarea" size="large" :autosize="{minRows: 5,maxRows: 10}"
                 placeholder="输入你发现的趣事吧~"></Input>
        </FormItem>
        <FormItem label="图片">
          <div class="demo-upload-list" v-for="item in uploadList">
            <template v-if="item.status === 'finished'">
              <img :src="item.url">
              <div class="demo-upload-list-cover">
                <Icon type="ios-eye-outline" @click.native="handleView(item.name)"></Icon>
                <Icon type="ios-trash-outline" @click.native="handleRemove(item)"></Icon>
              </div>
            </template>
            <template v-else>
              <Progress v-if="item.showProgress" :percent="item.percentage" hide-info></Progress>
            </template>
          </div>
          <Upload
            ref="upload"
            :show-upload-list="false"
            :default-file-list="defaultList"
            :on-success="handleSuccess"
            :format="['jpg','jpeg','png']"
            :max-size="2048"
            :on-format-error="handleFormatError"
            :on-exceeded-size="handleMaxSize"
            :before-upload="handleBeforeUpload"
            multiple
            type="drag"
            action="//jsonplaceholder.typicode.com/posts/"
            style="display: inline-block;width:58px;">
            <div style="width: 58px;height:58px;line-height: 58px;">
              <Icon type="ios-camera" size="20"></Icon>
            </div>
          </Upload>
          <Modal title="View Image" v-model="visible">
            <img :src="'https://o5wwk8baw.qnssl.com/' + imgName + '/large'" v-if="visible" style="width: 100%">
          </Modal>
        </FormItem>
      </Form>
    </Modal>
    <!-- 发布动态模态框 END -->
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
      momentModal: {
        show: false,
        form: {
          momentCategory: '',
          content: '',
          images: []
        }
      },
      // 上传图片
      defaultList: [
        {
          'name': 'a42bdcc1178e62b4694c830f028db5c0',
          'url': 'https://o5wwk8baw.qnssl.com/a42bdcc1178e62b4694c830f028db5c0/avatar'
        },
        {
          'name': 'bc7521e033abdd1e92222d733590f104',
          'url': 'https://o5wwk8baw.qnssl.com/bc7521e033abdd1e92222d733590f104/avatar'
        }
      ],
      imgName: '',
      visible: false,
      uploadList: []
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
          let result = res.data;
          this.momentPageList = result.data;
          this.momentPageList.forEach(momentPage => {
            if (momentPage.moment.images !== null && momentPage.moment.images.length !== 0) {
              momentPage.moment.images = JSON.parse(momentPage.moment.images);
            }
          })
        });
    },
    // 上传图片
    handleView (name) {
      this.imgName = name;
      this.visible = true;
    },
    handleRemove (file) {
      const fileList = this.$refs.upload.fileList;
      this.$refs.upload.fileList.splice(fileList.indexOf(file), 1);
    },
    handleSuccess (res, file) {
      file.url = 'https://o5wwk8baw.qnssl.com/7eb99afb9d5f317c912f08b5212fd69a/avatar';
      file.name = '7eb99afb9d5f317c912f08b5212fd69a';
    },
    handleFormatError (file) {
      this.$Notice.warning({
        title: 'The file format is incorrect',
        desc: 'File format of ' + file.name + ' is incorrect, please select jpg or png.'
      });
    },
    handleMaxSize (file) {
      this.$Notice.warning({
        title: 'Exceeding file size limit',
        desc: 'File  ' + file.name + ' is too large, no more than 2M.'
      });
    },
    handleBeforeUpload () {
      const check = this.uploadList.length < 5;
      if (!check) {
        this.$Notice.warning({
          title: 'Up to five pictures can be uploaded.'
        });
      }
      return check;
    }
  },
  mounted() {
    this.getMomentList();
    // 上传图片
    this.uploadList = this.$refs.upload.fileList;
  }
}
</script>

<style lang="less" scoped>

.container {
  width: 1140px;
  margin: 20px auto;
  padding: 5px;
}

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

/*上传图片*/
.demo-upload-list{
  display: inline-block;
  width: 60px;
  height: 60px;
  text-align: center;
  line-height: 60px;
  border: 1px solid transparent;
  border-radius: 4px;
  overflow: hidden;
  background: #fff;
  position: relative;
  box-shadow: 0 1px 1px rgba(0,0,0,.2);
  margin-right: 4px;
}
.demo-upload-list img{
  width: 100%;
  height: 100%;
}
.demo-upload-list-cover{
  display: none;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0,0,0,.6);
}
.demo-upload-list:hover .demo-upload-list-cover{
  display: block;
}
.demo-upload-list-cover i{
  color: #fff;
  font-size: 20px;
  cursor: pointer;
  margin: 0 2px;
}
</style>
