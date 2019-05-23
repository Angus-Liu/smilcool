<template>
  <div>
    <!-- 上传图片列表 -->
    <div class="upload-list" v-for="item in fileList">
      <!-- 上传成功 -->
      <template v-if="item.status === 'finished'">
        <img :src="item.url">
        <div class="upload-list-cover">
          <!-- 预览 -->
          <Icon type="ios-eye-outline" @click.native="handleView(item.url)"></Icon>
          <!-- 删除 -->
          <Icon type="ios-trash-outline" @click.native="handleRemove(item)"></Icon>
        </div>
      </template>
      <!-- 上传中 -->
      <template v-else>
        <Progress v-if="item.showProgress" :percent="item.percentage" hide-info></Progress>
      </template>
    </div>
    <!-- 上传图片列表 END -->
    <!-- 图片上传 -->
    <Upload class="upload" ref="upload"
            :action="localStorage"
            :data="data"
            :show-upload-list="false"
            :format="['jpg','jpeg','png']"
            :on-format-error="handleFormatError"
            :on-success="handleSuccess"
            :on-error="handleError"
            multiple
            type="drag">
      <Icon type="ios-camera" size="20"></Icon>
    </Upload>
    <!-- 图片上传 END -->
    <!-- 图片预览 Modal -->
    <Modal v-model="showImgModal.visible" footer-hide :closable="false">
      <img :src="showImgModal.url" style="width: 100%">
    </Modal>
    <!-- 图片预览 Modal END -->
  </div>
</template>

<script>
export default {
  name: 'ImageUploader',
  data() {
    return {
      // 已上传文件
      fileList: [],
      // 上传地址
      localStorage: '/api/local-storage/upload',
      // 上传时额外附带参数
      data: {
        type: 'img'
      },
      // 图片预览 Modal 参数
      showImgModal: {
        visible: false,
        url: ''
      }
    }
  },
  methods: {
    // 预览图片
    handleView(url1) {
      this.showImgModal.url = url1;
      this.showImgModal.visible = true;
    },
    // 删除图片
    handleRemove(file) {
      const fileList = this.$refs.upload.fileList;
      this.$refs.upload.fileList.splice(fileList.indexOf(file), 1);
      this.updateImages(fileList);
    },
    // 图片格式错误
    handleFormatError(file) {
      this.$Notice.warning({
        title: '文件格式错误',
        desc: file.name + ' 不是 jpg、jpeg 或 png 格式的图片'
      });
    },
    // 上传成功
    handleSuccess(res, file, fileList) {
      let uploadInfo = res.data;
      file.url = uploadInfo.url;
      // 因为异步问题，不能直接 watch uploadList (fileList 改变时，还未从后端获取结果)
      // 这里主动调用，通知父组件更新 images
      this.updateImages(fileList);
    },
    // 上传失败
    handleError(err, res, file) {
      this.$Notice.error({
        title: 'Oops',
        desc: file.name + ' 上传失败'
      });
    },
    // 触发 images-change 事件，用于更新 images
    updateImages(uploadList) {
      let images = uploadList.map(item => item.url);
      this.$emit('images-change', images);
    },
    // 用于清楚图片列表
    clearImages() {
      this.$refs.upload.clearFiles();
      this.fileList = [];
    }
  },
  mounted() {
    // 保留上传文件列表的引用
    this.fileList = this.$refs.upload.fileList;
  }
}
</script>

<style lang="less" scoped>
.upload {
  display: inline-block;
  width: 58px;

  i {
    width: 58px;
    height: 58px;
    line-height: 58px;
  }
}

.upload-list {
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
  box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
  margin-right: 4px;

  img {
    width: 100%;
    height: 100%;
  }

  .upload-list-cover {
    display: none;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0, 0, 0, .6);

    i {
      color: #fff;
      font-size: 20px;
      cursor: pointer;
      margin: 0 2px;
    }
  }
}

.upload-list:hover .upload-list-cover {
  display: block;
}
</style>
