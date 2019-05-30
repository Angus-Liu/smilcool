<template>
  <Card>
    <!-- 查询表单 -->
    <Form ref="queryForm" :model="queryForm" inline :label-width="70" @submit.native.prevent>
      <FormItem label="类别" prop="articleCategory">
        <Select class="form-item" v-model="queryForm.articleCategory" placeholder="请选择文章类别">
          <Option v-for="item in articleCategory" :key="item.name" :value="item.name" :label="item.name"/>
        </Select>
      </FormItem>
      <FormItem label="关键字" prop="general">
        <Input class="form-item" v-model="queryForm.general" placeholder="请输入关键字"/>
      </FormItem>
      <Button class="btn btn-search" type="primary" icon="ios-search" @click="getPage">筛选</Button>
      <Button class="btn" @click="handleReset">清空</Button>
    </Form>
    <!-- 查询表单 END -->
    <!-- 数据表格 -->
    <Table :columns="columns" :data="page.records" :loading="loading" border stripe>
      <template slot-scope="{ row }" slot="user">{{ row.user.nickname }}</template>
      <template slot-scope="{ row }" slot="zanCount">{{ row.resource.zanCount }}</template>
      <template slot-scope="{ row }" slot="commentCount">{{ row.resource.commentCount }}</template>
      <template slot-scope="{ row, index }" slot="action">
        <Button type="primary" size="small" style="margin-right: 5px" @click="show(index)">详细信息</Button>
      </template>
    </Table>
    <!-- 数据表格结束 -->
    <!-- 分页 -->
    <Row class="page" justify="end" type="flex">
      <Page :total="page.total" :current="page.current" :page-size="page.size"
            @on-change="handleCurrentChange" @on-page-size-change="handlePageSizeChange"
            show-sizer show-total show-elevator/>
    </Row>
    <!-- 分页 END -->
  </Card>
</template>

<script>
export default {
  name: 'ArticleManage',
  data() {
    return {
      articleCategory: [{
        name: '测试类别',
        code: '-1'
      }],
      queryForm: {
        articleCategory: null,
        general: null,
        current: 1,
        size: 10
      },
      // 表格设置
      loading: true,
      // 表格数据
      columns: [
        { type: 'index', width: 60, align: 'center', fixed: 'left' },
        { title: '类别', key: 'articleCategory', width: '100' },
        { title: '标题', key: 'title', width: '200' },
        { title: '标签', key: 'tags', width: '150' },
        { title: '发布用户', slot: 'user', width: '100' },
        { title: '发布时间', key: 'createTime', align: 'center', width: '150' },
        { title: '点赞量', slot: 'zanCount', align: 'center', width: '100' },
        { title: '评论量', slot: 'commentCount', align: 'center', width: '100' },
        { title: '简介', key: 'brief' },
        { title: '操作', slot: 'action', align: 'center', width: '150', fixed: 'right' }
      ],
      // 分页对象
      page: {
        records: [{
          'id': 1,
          'userId': 7,
          'resourceId': 4,
          'articleCategory': '校园新闻',
          'title': '测试文章',
          'brief': '测试文章',
          'createTime': '2019-05-13',
          'tags': '["测试标签1", "测试标签2"]',
          'user': {
            'id': 7,
            'username': '007',
            'nickname': '漫步金星',
            'avatar': 'http://img.angus-liu.cn/avatar/avatar04.jpg',
            'sign': '一句话介绍自己'
          },
          'resource': {
            'id': 4,
            'zanCount': 2,
            'pvCount': 0,
            'commentCount': 2
          }
        }],
        total: 0,
        size: 10,
        current: 1,
        pages: 1
      }
    }
  },
  methods: {
    getArticleCategory() {
      this.$axios.get('/api/dic/item', { dicTypeCode: 'article-category' })
        .then(res => {
          let result = res.data;
          this.articleCategory = result.data;
        })
    },
    getPage() {
      this.loading = true;
      this.$axios.get('/api/article/page', this.queryForm)
        .then(res => {
          let result = res.data;
          this.page = result.data;
          this.loading = false;
        });
    },
    handleReset() {
      this.$refs.queryForm.resetFields();
      this.queryForm.current = 1;
      this.queryForm.size = 10;
      this.getPage();
    },
    // 分页操作
    handleCurrentChange(current) {
      this.queryForm.current = current;
      this.getPage();
    },
    handlePageSizeChange(size) {
      this.queryForm.current = 1;
      this.queryForm.size = size;
      this.getPage();
    }
  },
  mounted() {
    this.getArticleCategory();
    this.getPage();
  }
}
</script>

<style scoped>
  .form-item {
    width: 150px;
  }

  .btn {
    margin-right: 5px;
  }

  .page {
    margin-top: 1em;
  }
</style>
