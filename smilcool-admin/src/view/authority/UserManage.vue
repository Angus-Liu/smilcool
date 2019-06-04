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
        <Button class="btn btn-search" type="primary" icon="ios-search" @click="getUserPage">筛选</Button>
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
        <Tag class="role-tag" v-for="(role, index) in row.roleList" :key="index" type="border" color="primary">
          {{ role.description }}
        </Tag>
      </template>
      <template slot-scope="{ row }" slot="state">
        <Tag :color="state[row.state].color">{{state[row.state].label}}</Tag>
      </template>
      <template slot-scope="{ row }" slot="action">
        <Button type="warning" size="small" @click="showRoleModal(row)">配置角色</Button>
      </template>
    </Table>
    <!-- 表格 END -->
    <!-- 分页 -->
    <Row class="page" justify="end" type="flex">
      <Page :total="page.total" :current="page.current" :page-size="page.size"
            @on-change="handleCurrentChange" @on-page-size-change="handlePageSizeChange"
            show-sizer show-total show-elevator/>
    </Row>
    <!-- 分页 END -->
    <!-- 角色配置模态框 -->
    <Modal class="role-modal" title="角色配置" v-model="roleModal.show" :mask-closable="false" :width="450">
      <Tree class="role-tree" :data="roleList" show-checkbox check-directly check-strictly/>
      <div slot="footer">
        <Button type="text" @click="roleModal.show = false">关闭</Button>
        <Button type="primary" @click="handleSubmit" :loading="roleModal.submitLoading">提交</Button>
      </div>
    </Modal>
    <!-- 角色配置模态框 END -->
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
      page: { records: [], total: 0, size: 10, current: 1, pages: 1 },
      // 角色列表
      roleList: [],
      // 角色模态框
      roleModal: {
        show: false,
        submitLoading: false
      }
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
    // 角色配置
    showRoleModal(user) {
      let roleIdList = [];
      user.roleList.forEach(role => roleIdList.push(role.id));
      this.roleList.forEach(role => role.checked = roleIdList.includes(role.id));
      this.roleModal.show = true;
    },
    // 获取角色列表
    getRoleList() {
      this.$axios.get('/api/role')
        .then(res => {
          // 获取响应体中统一接口交互对象 result
          let result = res.data;
          // 获取角色列表
          result.data.forEach(role => {
            role.title = role.description;
            role.checked = false;
          });
          this.roleList = result.data;
        });
    },
    // 获取用户分页
    getUserPage() {
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
      this.getUserPage();
    },
    // 分页操作
    handleCurrentChange(current) {
      this.queryForm.current = current;
      this.getUserPage();
    },
    // 分页大小调整
    handlePageSizeChange(size) {
      this.queryForm.current = 1;
      this.queryForm.size = size;
      this.getUserPage();
    },
    handleSubmit() {

    }
  },
  mounted() {
    this.getRoleList();
    this.getUserPage();
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

  .role-tag {
    margin-top: 5px;
  }

  .role-tag:first-child {
    margin-top: 10px;
  }

  .role-tag:last-child {
    margin-bottom: 10px;
  }
</style>
