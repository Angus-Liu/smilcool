<template>
  <Card>
    <!-- 筛选栏 -->
    <Form ref="queryForm" :model="queryForm" inline :label-width="70">
      <FormItem label="用户名" prop="username">
        <Input class="form-item" v-model="queryForm.username" placeholder="Enter something..."></Input>
      </FormItem>
      <FormItem label="邮箱" prop="email">
        <Input class="form-item" v-model="queryForm.email" placeholder="Enter something..."></Input>
      </FormItem>
      <FormItem label="昵称" prop="nickname">
        <Input class="form-item" v-model="queryForm.nickname" placeholder="Enter something..."></Input>
      </FormItem>
      <FormItem label="角色" prop="role">
        <Select class="form-item" v-model="queryForm.role">
          <Option v-for="item in sex" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
      </FormItem>
      <FormItem label="状态" prop="state">
        <Select class="form-item" v-model="queryForm.state">
          <Option v-for="item in state" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
      </FormItem>
      <span v-show="more.show">
            <FormItem label="手机" prop="phone">
              <Input class="form-item" v-model="queryForm.phone" placeholder="Enter something..."/>
            </FormItem>
            <FormItem label="性别" prop="sex">
              <Select class="form-item" v-model="queryForm.sex">
                <Option v-for="item in sex" :value="item.value" :key="item.value">{{ item.label }}</Option>
              </Select>
            </FormItem>
            <FormItem label="年级" prop="grade">
              <DatePicker class="form-item" type="year" :value="queryForm.grade" placeholder="Select grade"
                          @on-change="(value) => queryForm.grade = (value !== ''? value : null)"/>
            </FormItem>
            <FormItem label="学院" prop="college">
              <Select class="form-item" v-model="queryForm.college">
                <Option v-for="item in sex" :value="item.value" :key="item.value">{{ item.label }}</Option>
              </Select>
            </FormItem>
            <FormItem label="专业" props="major">
              <Select class="form-item" v-model="queryForm.major">
                <Option v-for="item in sex" :value="item.value" :key="item.value">{{ item.label }}</Option>
              </Select>
            </FormItem>
          </span>
      <FormItem>
        <Button class="btn btn-search" type="primary" icon="ios-search" @click="getPage">筛选</Button>
        <Button class="btn" @click="handleReset">清空</Button>
        <a class="drop-down" @click="showMore">{{ more.content }}
          <Icon :type="more.icon"/>
        </a>
      </FormItem>
    </Form>
    <!-- 筛选栏 END -->
    <!-- 表格 -->
    <Table :columns="columns" :data="page.records" :loading="loading" border stripe>
      <template slot-scope="{ row }" slot="username">
        <strong>{{ row.username }}</strong>
      </template>
      <template slot-scope="{ row, index }" slot="roles">
        <Tag v-for="(role, index) in row.roles" :key="index" type="border" color="primary">{{ role }}</Tag>
      </template>
      <template slot-scope="{ row }" slot="state">
        <Tag :color="state[row.state].color">{{state[row.state].label}}</Tag>
      </template>
      <template slot-scope="{ row, index }" slot="action">
        <Button type="warning" size="small" @click="show(index)">配置角色</Button>
      </template>
    </Table>
    <!-- 表格 END -->
    <!-- 分页 -->
    <Row class="page" justify="end" type="flex">
      <Page :total="page.total" :current="page.current" :page-size="page.size"
            @on-change="handleCurrentChange" @on-page-size-change="handlePageSizeChange"
            show-sizer show-total show-elevator/>
    </Row>
  </Card>
</template>

<script>
export default {
  name: 'UserManage',
  data() {
    return {
      value1: false,
      // 表单设置
      more: {
        show: false,
        content: '展开',
        icon: 'ios-arrow-down'
      },
      // 查询表单
      state: [
        { value: 0, label: '未激活', color: 'orange' },
        { value: 1, label: '正常', color: 'green' },
        { value: 2, label: '停用', color: 'default' },
      ],
      sex: [
        { value: '男', label: '男' },
        { value: '女', label: '女' },
        { value: '保密', label: '保密' },
      ],
      // 查询表单
      queryForm: {
        username: null,
        nickname: null,
        sex: null,
        grade: null,
        college: null,
        major: null,
        phone: null,
        email: null,
        state: null,
        role: null,
        current: 1,
        size: 10
      },
      // 表格设置
      loading: true,
      // 表格数据
      columns: [
        { type: 'index', width: 60, align: 'center', fixed: 'left' },
        { title: '用户名', slot: 'username', width: '150', fixed: 'left' },
        { title: '昵称', key: 'nickname', width: '100', tooltip: true },
        { title: '性别', key: 'sex', align: 'center', width: '80' },
        { title: '生日', key: 'birthday', align: 'center', width: '100' },
        { title: '年级', key: 'grade', align: 'center', width: '100' },
        { title: '学院', key: 'college', align: 'center', width: '100' },
        { title: '专业', key: 'major', align: 'center', width: '100' },
        { title: '手机', key: 'phone', align: 'center', width: '100' },
        { title: '邮箱', key: 'email', width: '150' },
        { title: '状态', slot: 'state', align: 'center', width: '100' },
        { title: '角色', slot: 'roles', align: 'center', width: '150' },
        { title: '备注', key: 'remark', width: '100', tooltip: true },
        { title: '创建时间', key: 'createTime', align: 'center', width: '150' },
        { title: '更新时间', key: 'updateTime', align: 'center', width: '150' },
        { title: '操作', slot: 'action', align: 'center', width: '150', fixed: 'right' }
      ],
      // 分页对象
      page: { records: [], total: 0, size: 10, current: 1, pages: 1 }
    };
  },
  methods: {
    // 表单操作
    showMore() {
      if (!this.more.show) {
        this.more = {
          show: true,
          content: '收起',
          icon: 'ios-arrow-up'
        };
      } else {
        this.more = {
          show: false,
          content: '展开',
          icon: 'ios-arrow-down'
        };
      }
    },
    show(index) {
      this.$Modal.info({
        title: 'User Info',
        content: `User Name：${this.page.records[index].username}<br>
                  Nick Name：${this.page.records[index].nickname}<br>
                  Sex：${this.page.records[index].sex}`
      });
    },
    getPage() {
      this.loading = true;
      this.$axios.get('/api/user/page', this.queryForm)
        .then(res => {
          // 获取响应体中统一接口交互对象 result
          let result = res.data;
          // 获取分页对象 page
          this.page = result.data;
          this.loading = false;
        });
    },
    // 表单操作
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
    // 请求数据
    this.getPage();
  }
};
</script>

<style scoped>
  .page {
    margin-top: 1em;
  }

  .form-item {
    width: 150px;
  }

  .btn {
    margin-right: 5px;
  }

  .btn-search {
    margin-left: -35px;
  }
</style>
