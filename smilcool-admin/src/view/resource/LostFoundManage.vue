<template>
  <Card>
    <!-- 查询表单 -->
    <Form ref="queryForm" :model="queryForm" inline :label-width="70" @submit.native.prevent>
      <FormItem label="类别" prop="lostFoundCategory">
        <Select class="form-item" v-model="queryForm.lostFoundCategory" placeholder="请选择失物寻物类别">
          <Option v-for="item in category" :key="item.name" :value="item.name" :label="item.name"/>
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
  name: 'LostFoundManage',
  data() {
    return {
      category: [{
        name: '测试类别',
        code: '-1'
      }],
      queryForm: {
        lostFoundCategory: null,
        general: null,
        current: 1,
        size: 10
      },
      // 表格设置
      loading: true,
      // 表格数据
      columns: [
        { type: 'index', width: 60, align: 'center', fixed: 'left' },
        { title: '类别', key: 'lostFoundCategory', width: '100' },
        { title: '标题', key: 'title', width: '200' },
        { title: '物品', key: 'itemName', width: '100' },
        { title: '时间', key: 'time', width: '200' },
        { title: '地点', key: 'address', width: '200' },
        { title: '发布用户', slot: 'user', width: '100' },
        { title: '发布时间', key: 'createTime', align: 'center', width: '150' },
        { title: '描述', key: 'description', width: '200', tooltip: true },
        { title: '点赞量', slot: 'zanCount', align: 'center', width: '100' },
        { title: '评论量', slot: 'commentCount', align: 'center', width: '100' },
        { title: '操作', slot: 'action', align: 'center', width: '150', fixed: 'right' }
      ],
      // 分页对象
      page: {
        records: [{

          'id': 1,
          'userId': 1,
          'resourceId': 16,
          'lostFoundCategory': '寻物启事',
          'title': '四食堂丢失一个钱包',
          'description': '如图，钱包是黑色的，里面有少量现金和一张工行的卡',
          'itemName': '钱包',
          'time': '2019年05月17日  12:10',
          'address': '四食堂',
          'createTime': '2019-05-17 15:39:45',
          'images': '["https://img12.360buyimg.com/n7/jfs/t22831/61/2367409622/289066/5d9c3f/5b7d10feNe776b305.jpg"]',
          'user': {
            'id': 1,
            'username': 'admin',
            'nickname': '超级管理员',
            'avatar': '/local-storage/avatar/f24ae620091cd6efb7cf596cfb7ca717.png',
            'sign': '终其一生，我们都在自我救赎'
          },
          'resource': {
            'id': 16,
            'zanCount': 1,
            'pvCount': 0,
            'commentCount': 0
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
    getCategory() {
      this.$axios.get('/api/dic/item', { dicTypeCode: 'lost-found-category' })
        .then(res => {
          let result = res.data;
          this.category = result.data;
        })
    },
    getPage() {
      this.loading = true;
      this.$axios.get('/api/lost-found/page', this.queryForm)
        .then(res => {
          let result = res.data;
          this.page = result.data;
          this.loading = false;
        })
        .catch(err => {
          this.loading = false;
        })
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
    this.getCategory();
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
