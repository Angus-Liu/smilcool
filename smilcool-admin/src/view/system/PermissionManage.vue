<template>
  <Card>
    <!-- 操作栏 -->
    <Row class="row-action">
      <Button class="btn" @click="addPermission" type="primary" icon="md-add">添加角色</Button>
      <Button class="btn" @click="getPermissionList" icon="md-refresh">刷新</Button>
    </Row>
    <Row>
      <!-- 权限列表 -->
      <Col span="5">
        <div class="tree-bar">
          <Tree :data="permissionList" @on-select-change="handleSelectPermission" show-checkbox></Tree>
        </div>
      </Col>
      <Col span="7" offset="2">
        <!-- 权限详情 -->
        <Form ref="permissionForm" :model="permission" :label-width="85">
          <FormItem label="类型" prop="type">
            <RadioGroup :value="types[permission.type].label">
              <Radio v-for="item in types" :label="item.label"></Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="ID" prop="id">
            <Input class="form-item" v-model="permission.id" placeholder="Enter something..."></Input>
          </FormItem>
          <FormItem label="名称" prop="name">
            <Input class="form-item" v-model="permission.name" placeholder="Enter something..."></Input>
          </FormItem>
          <FormItem label="描述" prop="description">
            <Input class="form-item" v-model="permission.description" placeholder="Enter something..."></Input>
          </FormItem>
          <FormItem label="URL" prop="url">
            <Input class="form-item" v-model="permission.url" placeholder="Enter something..."></Input>
          </FormItem>
          <FormItem label="创建时间" prop="createTime">
            <Input class="form-item" v-model="permission.createTime" placeholder="Enter something..."></Input>
          </FormItem>
          <FormItem label="更新时间" prop="updateTime">
            <Input class="form-item" v-model="permission.updateTime" placeholder="Enter something..."></Input>
          </FormItem>
          <FormItem>
            <Button class="btn" type="primary" icon="ios-search" @click="handleUpdate">更新</Button>
            <Button class="btn" @click="handleReset">重置</Button>
          </FormItem>
        </Form>
      </Col>
    </Row>
    <!-- 表格 -->
  </Card>
</template>

<script>

export default {
  name: 'PermissionManage',
  data () {
    return {
      permissionList: [],
      permission: {
        id: 1,
        parentId: null,
        name: null,
        description: null,
        url: null,
        type: 0,
        seq: 0,
        state: '正常',
        remark: '',
        createTime: null,
        updateTime: null,
        deleted: false
      },
      types: [
        { type: 0, label: '菜单' },
        { type: 1, label: '按钮' },
        { type: 2, label: '其他' }
      ]
    };
  },
  watch: {},
  methods: {
    getPermissionList () {
      this.$axios.get('/api/permission')
        .then(res => {
          let result = res.data;
          this.permissionList = this.initPermissionList(result.data);
        });
    },
    initPermissionList (permissionList) {
      permissionList.forEach(permission => {
        permission.title = permission.description;
        permission.expand = true;
        this.initPermissionList(permission.children);
      });
      return permissionList;
    },
    addPermission () {

    },
    handleSelectPermission (arr, item) {
      this.permission = item;
    },
    handleUpdate () {

    },
    handleReset () {
      this.$refs.permissionForm.resetFields();
    }
  },
  mounted () {
    this.getPermissionList();
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

  .form-item {
    width: 100%;
  }

  .tree-bar {
    overflow: auto;
    margin-top: 5px;
    max-height: 500px;
  }

  .tree-bar::-webkit-scrollbar {
    width: 6px;
    height: 6px;
  }

  .tree-bar::-webkit-scrollbar-thumb {
    border-radius: 4px;
    -webkit-box-shadow: inset 0 0 2px #d1d1d1;
    background: #e4e4e4;
  }
</style>
