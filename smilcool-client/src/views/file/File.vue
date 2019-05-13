<template>
  <div class="container">
    <Row>
      <iCol span="16">
        <simple-card class="cate-card">
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
        </simple-card>
        <simple-card class="file-card">
          <sui-item-group>
            <sui-item>
              <sui-item-image src="static/images/wireframes/image.png"/>
              <sui-item-content>
                <sui-item-header>Header</sui-item-header>
                <sui-item-meta>
                  <span>Description</span>
                </sui-item-meta>
                <sui-item-description>
                  <p>
                    Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor
                    invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.
                  </p>
                </sui-item-description>
                <sui-item-extra>
                  Additional Details
                </sui-item-extra>
              </sui-item-content>
            </sui-item>
            <sui-item>
              <sui-item-image src="static/images/wireframes/image.png"/>
              <sui-item-content>
                <sui-item-header>Header</sui-item-header>
                <sui-item-meta>
                  <span>Description</span>
                </sui-item-meta>
                <sui-item-description>
                  <p>
                    Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor
                    invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.
                  </p>
                </sui-item-description>
                <sui-item-extra>
                  Additional Details
                </sui-item-extra>
              </sui-item-content>
            </sui-item>
          </sui-item-group>
        </simple-card>
      </iCol>
      <iCol span="7" offset="1">
        <simple-card class="user-card">
          个人信息
        </simple-card>
      </iCol>
    </Row>
  </div>
</template>

<script>
import SimpleCard from '@/components/common/SimpleCard'

export default {
  name: 'File',
  components: { SimpleCard },
  data() {
    return {
      key: '',
      fileCategory: [
        {
          name: '全部',
          code: 'all'
        },
        {
          name: '测试类1',
          code: 'test1'
        }, {
          name: '测试类2',
          code: 'test2'
        }, {
          name: '全部',
          code: 'all'
        },
        {
          name: '测试类1',
          code: 'test1'
        }, {
          name: '测试类2',
          code: 'test2'
        }, {
          name: '全部',
          code: 'all'
        }, {
          name: '测试类1',
          code: 'test1'
        }, {
          name: '测试类2',
          code: 'test2'
        }, {
          name: '全部',
          code: 'all'
        }, {
          name: '测试类1',
          code: 'test1'
        }, {
          name: '测试类2',
          code: 'test2'
        }, {
          name: '全部',
          code: 'all'
        },
        {
          name: '测试类1',
          code: 'test1'
        }, {
          name: '测试类2',
          code: 'test2'
        }, {
          name: '全部',
          code: 'all'
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
    }
  },
  mounted() {
    this.getFileCategory();
  }
}
</script>

<style lang="less" scoped>
.container {
  width: 100%;

  .cate-card {
    padding: 10px 20px;

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

  .file-card {
    margin-top: 10px;
    padding: 20px;
  }

  .user-card {

  }
}
</style>
