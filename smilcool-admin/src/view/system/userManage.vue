<template>
  <div>
    <Card>
      <!-- 筛选栏 -->
      <div>
        <Form :model="userSearchForm" inline :label-width="70">
          <FormItem label="用户名" prop="username">
            <Input class="form-item" v-model="userSearchForm.username" placeholder="Enter something..."></Input>
          </FormItem>
          <FormItem label="邮箱" prop="email">
            <Input class="form-item" v-model="userSearchForm.email" placeholder="Enter something..."></Input>
          </FormItem>
          <FormItem label="昵称" prop="nickname">
            <Input class="form-item" v-model="userSearchForm.nickname" placeholder="Enter something..."></Input>
          </FormItem>
          <FormItem label="角色" prop="role">
            <Select class="form-item" v-model="userSearchForm.role">
              <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
          </FormItem>
          <FormItem label="状态" prop="state">
            <Select class="form-item" v-model="userSearchForm.state">
              <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
          </FormItem>
          <span v-show="more.show">
            <FormItem label="手机" prop="phone">
              <Input class="form-item" v-model="userSearchForm.phone" placeholder="Enter something..."></Input>
            </FormItem>
            <FormItem label="性别" prop="sex">
              <Select class="form-item" v-model="userSearchForm.sex">
                <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
              </Select>
            </FormItem>
            <FormItem label="年级" prop="grade">
              <DatePicker class="form-item" type="year" v-model="userSearchForm.grade"
                          placeholder="Select grade"></DatePicker>
            </FormItem>
            <FormItem label="学院" prop="college">
              <Select class="form-item" v-model="userSearchForm.college">
                <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
              </Select>
            </FormItem>
            <FormItem label="专业" props="major">
              <Select class="form-item" v-model="userSearchForm.major">
                <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
              </Select>
            </FormItem>
          </span>
          <FormItem>
            <Button class="btn-search" type="primary" icon="ios-search" @click="getUserPage">筛选</Button>
            <Button @click="handleReset">清空</Button>
            <a class="drop-down" @click="showMore">
              {{ more.content }}
              <Icon :type="more.icon"/>
            </a>
          </FormItem>
        </Form>
      </div>
      <div>
        <Table :columns="columns" :data="page.records" :loading="loading" border stripe>
          <template slot-scope="{ row }" slot="username">
            <strong>{{ row.username }}</strong>
          </template>
          <template slot-scope="{ row, index }" slot="roles">
            <Tag v-for="(role, index) in row.roles" :key="index" color="success">{{ role }}</Tag>
          </template>
          <template slot-scope="{ row, index }" slot="action">
            <Button type="primary" size="small" style="margin-right: 5px" @click="show(index)">详细信息</Button>
          </template>
        </Table>
      </div>
      <!-- 分页 -->
      <Row class="page" justify="end" type="flex">
        <Page :total="page.total" :current="page.current" :page-size="page.size"
              @on-change="handleCurrentChange" @on-change-size="handlePageSizeChange"
              show-sizer show-total show-elevator/>
      </Row>
    </Card>
  </div>
</template>
<script>
export default {
  data () {
    return {
      // 表单设置
      more: {
        show: false,
        content: '展开',
        icon: 'ios-arrow-down'
      },
      // 表格设置
      loading: true,
      // 表单数据
      cityList: [{
        value: 'New York',
        label: 'New York'
      }],
      model1: '',
      // 表格数据
      columns: [
        { title: '用户名', slot: 'username', width: '150', fixed: 'left' },
        { title: '昵称', key: 'nickname', width: '150' },
        { title: '性别', key: 'sex', align: 'center', width: '80' },
        { title: '生日', key: 'birthday', align: 'center', width: '100' },
        { title: '年级', key: 'grade', align: 'center', width: '100' },
        { title: '学院', key: 'college', align: 'center', width: '100' },
        { title: '专业', key: 'major', align: 'center', width: '100' },
        { title: '手机', key: 'phone', align: 'center', width: '100' },
        { title: '邮箱', key: 'email', width: '150' },
        { title: '状态', key: 'state', align: 'center', width: '100' },
        { title: '角色', slot: 'roles', align: 'center', width: '200' },
        { title: '备注', key: 'remark', width: '100', tooltip: true },
        { title: '创建时间', key: 'createTime', align: 'center', width: '150' },
        { title: '更新时间', key: 'updateTime', align: 'center', width: '150' },
        { title: '操作', slot: 'action', align: 'center', width: '150', fixed: 'right' }
      ],
      rows: [],
      // 查询表单
      userSearchForm: {
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
      // 分页对象
      page: {
        records: [],
        total: 0,
        size: 10,
        current: 1,
        pages: 1
      }
    };
  },
  methods: {
    // 表单操作
    showMore () {
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
    show (index) {
      this.$Modal.info({
        title: 'User Info',
        content: `Name：${this.rows[index].name}<br>Age：${this.rows[index].age}<br>Address：${this.rows[index].address}`
      });
    },
    getUserPage () {
      this.loading = true;
      this.$axios.get('/api/user/condition', this.userSearchForm)
        .then(res => {
          // 获取响应体中统一接口交互对象 result
          let result = res.data;
          // 获取分页对象 page
          this.page = result.data;

          this.loading = false;
        });
    },
    // 表单操作
    handleReset () {
      this.userSearchForm = {
        username: null,
        nickname: null,
        sex: null,
        grade: null,
        college: null,
        major: null,
        phone: null,
        email: null,
        state: null,
        current: this.page.current,
        size: this.page.size
      };
    },
    // 分页操作
    handleCurrentChange (current) {
      this.userSearchForm.current = current;
      this.getUserPage();
    },
    handlePageSizeChange (size) {
      this.userSearchForm.current = 1;
      this.userSearchForm.size = size;
      this.getUserPage();
    }
  },
  mounted () {
    // 请求数据
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

  .btn-search {
    margin-left: -35px;
    margin-right: 5px;
  }
</style>
