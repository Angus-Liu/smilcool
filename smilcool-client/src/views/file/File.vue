<template>
  <div class="container">
    <Row>
      <iCol class="column" span="17">
        <!-- 文件菜单栏 -->
        <sui-card class="fluid">
          <sui-card-content>
            <dl class="cate-list">
              <dt>文件类别：</dt>
              <dd>
                <ul>
                  <li v-for="(item, index) in fileCategory" :key="index">
                    <a href="#">{{item.name}}</a>
                  </li>
                </ul>
              </dd>
            </dl>
            <dl class="file-search">
              <dt>文件搜索：</dt>
              <dd>
                <Input v-model="key">
                  <Button slot="append" icon="ios-search"></Button>
                </Input>
              </dd>
            </dl>
          </sui-card-content>
        </sui-card>
        <!-- 文件菜单栏 END -->
        <!-- 文件列表 -->
        <sui-card class="fluid">
          <sui-message attached="top">文件列表</sui-message>
          <sui-card-content>
            <sui-item-group>
              <sui-item v-for="filePage in filePageList" :key="filePage.file.id">
                <sui-icon :name="getFileIcon(filePage.file.name)"/>
                <sui-item-content>
                  <sui-item-header>{{filePage.file.title}}</sui-item-header>
                  <sui-item-meta>
                    <span>{{filePage.file.name}}</span>
                  </sui-item-meta>
                  <sui-item-description>
                    <p>{{filePage.file.description}}</p>
                  </sui-item-description>
                  <sui-item-extra>
                    大小：{{filePage.file.size}}
                  </sui-item-extra>
                </sui-item-content>
              </sui-item>
            </sui-item-group>
          </sui-card-content>
          <sui-button-group attached="bottom" basic>
            <sui-button content="上一页" icon="left chevron" label-position="left"/>
            <sui-button-or/>
            <sui-button content="下一页" icon="right chevron" label-position="right"/>
          </sui-button-group>
        </sui-card>
        <!-- 文件列表 END -->
      </iCol>
      <iCol class="column" span="7">
        <!-- 消息提示 -->
        <sui-message dismissable>
          <sui-message-header>
            <i class="handshake outline icon"/> 分享学习资料，让更多人获益。
          </sui-message-header>
          <p style="text-align:justify">
            一个人独行可以走得很快，一群人才会走得更远！我们鼓励学习交流，更鼓励资源分享。
            如果手里有比较好的学习资料，希望你能分享出来，让大家从中受益。
            与此同时，你也将会在这个平台上找到你所需要的学习资料。“分享”帮助你我探索更广阔的世界 🧐
          </p>
          <sui-button fluid basic color="teal" icon="upload" content="上传文件"/>
        </sui-message>
        <!-- 消息提示 END -->
      </iCol>
    </Row>
  </div>
</template>

<script>

export default {
  name: 'File',
  data() {
    return {
      key: '',
      fileCategory: [
        {
          name: '全部',
          code: 'all'
        }
      ],
      filePageList: [
        {
          'file': {
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
            'createTime': '2019-05-13T09:18:13.000+0000'
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
      ]
    }
  },
  methods: {
    getFileCategory() {
      this.$axios.get('/api/dic/file-category/item')
        .then(res => {
          let result = res.data;
          this.fileCategory = result.data;
        });
    },
    getFilePageList() {
      this.$axios.get('/api/file/page')
        .then(res => {
          let result = res.data;
          this.filePageList = result.data;
        })
    },
    // 根据文件名获取文件图标样式
    getFileIcon(filename) {
      let index = filename.lastIndexOf('.');
      let suffix = filename.substr(index + 1);
      switch (suffix) {
        case 'ppt':
        case 'pptx':
          return 'file powerpoint outline yellow';
        case 'doc':
        case 'docx':
          return 'file word outline blue';
        case 'xls':
        case 'xlsx':
          return 'file excel outline green';
        case 'pdf':
          return 'file pdf outline orange';
        default:
          return 'file alternate outline';
      }
    }
  },
  mounted() {
    this.getFileCategory();
    this.getFilePageList();
  }
}
</script>

<style lang="less" scoped>
.container {
  width: 1200px;
  margin: 10px auto;
  padding: 5px;
  /*background: #ccc;*/

  .card {
    .items {
      padding: 0 10px;

      .item {
        margin: 20px 0;
        padding-bottom: 10px;
        border-bottom: 1px dashed #ddd;

        i {
          font-size: 5em;
          line-height: 1;
          vertical-align: middle;
        }
      }

      .item:last-child {
        padding-bottom: 0;
        border-bottom: none;
      }
    }


  }

  .column {
    padding: 5px;
  }

  dl {
    margin-top: 15px;
    padding-bottom: 5px;
    border-bottom: 1px dashed #e6e6e6;
    overflow: auto;

    dt {
      float: left;
      width: 80px;
      color: #999;

    }

    dd {
      float: left;
      width: calc(100% - 80px);

      ul {
        list-style: none;

        li {
          display: inline-block;
          margin-right: 15px;
          margin-bottom: 10px;
          color: #546a7e;
        }
      }
    }
  }

  dl:last-child {
    border-bottom: none;
  }

  dl.file-search {
    line-height: 32px;
  }
}
</style>
