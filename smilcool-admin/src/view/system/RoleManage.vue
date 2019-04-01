<template>
  <Card>
    <!-- 操作栏 -->
    <Row class="row-action">
      <Button class="btn" @click="addRole" type="primary" icon="md-add">添加角色</Button>
      <Button class="btn" @click="getRoleList" icon="md-refresh">刷新</Button>
    </Row>
    <!-- 表格 -->
    <Table :columns="columns" :data="roleList" border stripe>
      <template slot-scope="{ row }" slot="state">
        <Tag :color="row.state === 1? 'green': 'red'">{{row.state === 1? '正常': '停用'}}</Tag>
      </template>
      <template slot-scope="{ row, index }" slot="action">
        <Button class="btn" type="primary" size="small" @click="editRole(index)">编辑</Button>
        <Button class="btn" type="warning" size="small" @click="editPermission(index)">权限配置</Button>
      </template>
    </Table>
    <!-- 角色模态框 -->
    <Modal :title="roleModal.title" v-model="roleModal.show" :mask-closable="false" :width="450">
      <Form :model="roleModal.role" :label-width="50">
        <FormItem label="名称" prop="name">
          <Input class="form-item" v-model="roleModal.role.name" placeholder="Enter something..."></Input>
        </FormItem>
        <FormItem label="描述" prop="description">
          <Input class="form-item" v-model="roleModal.role.description" placeholder="Enter something..."></Input>
        </FormItem>
        <FormItem label="状态" prop="state">
          <Select class="form-item" v-model="roleModal.role.state">
            <Option v-for="item in state" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </FormItem>
        <FormItem label="备注" prop="remark">
          <Input class="form-item" v-model="roleModal.role.remark" placeholder="Enter something..."></Input>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="roleModal.show = false">关闭</Button>
        <Button type="primary" @click="handleSubmit" :loading="roleModal.submitLoading">提交</Button>
      </div>
    </Modal>
    <!-- 权限模态框 -->
  </Card>
</template>

<script>
export default {
  name: 'RoleManage',
  data () {
    return {
      // 表格数据
      columns: [
        { type: 'index', width: 60, align: 'center' },
        { title: '名称', key: 'name', width: '150' },
        { title: '描述', key: 'description', align: 'center', width: '150' },
        { title: '状态', slot: 'state', align: 'center', width: '150' },
        { title: '备注', key: 'remark', tooltip: true },
        { title: '创建时间', key: 'createTime', align: 'center', width: '150' },
        { title: '更新时间', key: 'updateTime', align: 'center', width: '150' },
        { title: '操作', slot: 'action', align: 'center', width: '200', fixed: 'right' }
      ],
      roleList: [],
      // 角色模态框数据
      roleModal: {
        isAdd: true, // 是否为添加模式
        submitLoading: false,
        show: false,
        title: '添加角色',
        role: {
          id: null,
          name: '',
          description: '',
          state: 1,
          remark: ''
        }
      },
      state: [
        { value: 1, label: '正常' },
        { value: -1, label: '停用' }
      ]
    };
  },
  methods: {
    addRole () {
      this.roleModal = {
        isAdd: true,
        submitLoading: false,
        show: true,
        title: '添加角色',
        role: {
          id: null,
          name: '',
          description: '',
          state: 1,
          remark: ''
        }
      };
    },
    editRole (index) {
      this.roleModal = {
        isAdd: false,
        submitLoading: false,
        show: true,
        title: '编辑角色',
        role: { ...this.roleList[index] }
      };
    },
    getRoleList () {
      this.$axios.get('/api/role')
        .then(res => {
          // 获取响应体中统一接口交互对象 result
          let result = res.data;
          // 获取角色列表
          this.roleList = result.data;
        });
    },
    editPermission (index) {

    },
    handleSubmit () {
      // 新增角色
      this.roleModal.submitLoading = true;
      if (this.roleModal.isAdd) {
        this.$axios.post('/api/role', this.roleModal.role)
          .then(res => {
            let result = res.data;
            if (result.success) {
              this.getRoleList();
              this.roleModal.show = false;
            } else {
              this.$Message.error(result.msg);
            }
            this.roleModal.submitLoading = false;
          });
      } else {
        // 更新角色
        this.$axios.put(`/api/role/${this.roleModal.role.id}`, this.roleModal.role)
          .then(res => {
            let result = res.data;
            if (result.success) {
              this.getRoleList();
              this.roleModal.show = false;
            } else {
              this.$Message.error(result.msg);
            }
            this.roleModal.submitLoading = false;
          });
      }
    }
  },
  mounted () {
    this.getRoleList();
  }
};
</script>

<style scoped>
  .row-action {
    margin-bottom: 10px;
  }

  .btn {
    margin-right: 5px;
  }
</style>
