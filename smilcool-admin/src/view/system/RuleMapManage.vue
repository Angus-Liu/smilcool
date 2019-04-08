<template>
  <Card>
    <!-- 操作栏 -->
    <Row class="row-action">
      <Button class="btn" @click="addRuleMap" type="primary" icon="md-add">添加规则映射</Button>
      <Button class="btn" @click="getRuleMapList" icon="md-refresh">刷新</Button>
    </Row>
    <!-- 表格 -->
    <Table :columns="columns" :data="ruleMapList" border stripe>
      <template slot-scope="{ row }" slot="type">
        <Tag type="dot" :color="type[row.type].color">{{type[row.type].label}}</Tag>
      </template>
      <template slot-scope="{ row }" slot="authc">
        <iSwitch :value="row.authc"/>
      </template>
      <template slot-scope="{ row }" slot="useRoles">
        <iSwitch :value="row.useRoles"/>
      </template>
      <template slot-scope="{ row }" slot="usePerms">
        <iSwitch :value="row.usePerms"/>
      </template>
      <template slot-scope="{ row }" slot="useRest">
        <iSwitch :value="row.useRest"/>
      </template>
      <template slot-scope="{ row }" slot="state">
        <Tag :color="state[row.state].color">{{state[row.state].label}}</Tag>
      </template>
      <template slot-scope="{ row, index }" slot="action">
        <Button class="btn" type="primary" size="small" @click="editRuleMap(index)">详情</Button>
        <Button class="btn" type="primary" size="small" @click="editRuleMap(index)">重置</Button>
      </template>
    </Table>
    <!-- 规则映射模态框 -->
  </Card>
</template>

<script>
export default {
  name: 'RuleMapManage',
  data () {
    return {
      columns: [
        { type: 'index', width: 60, align: 'center' },
        { title: 'URL', key: 'url', width: '120' },
        { title: '描述', key: 'description', align: 'center', width: '120' },
        { title: '类型', slot: 'type', align: 'center', width: '120' },
        { title: '开启身份认证', slot: 'authc', align: 'center', width: '120' },
        { title: '开启角色认证', slot: 'useRoles', align: 'center', width: '120' },
        { title: '角色认证规则', key: 'roles', align: 'center' },
        { title: '开启权限认证', slot: 'usePerms', align: 'center', width: '120' },
        { title: '权限认证规则', key: 'perms', align: 'center' },
        { title: '开启REST认证', slot: 'useRest', align: 'center', width: '120' },
        { title: 'REST认证规则', key: 'rest', align: 'center' },
        { title: '状态', slot: 'state', align: 'center', width: '100' },
        { title: '操作', slot: 'action', align: 'center', width: '150' }
      ],
      type: [
        { value: 0, label: '菜单', color: 'primary' },
        { value: 1, label: '按钮', color: 'success' },
        { value: 2, label: '其他', color: 'warning' }
      ],
      state: [
        { value: 0, label: '停用', color: 'red' },
        { value: 1, label: '正常', color: 'green' }
      ],
      ruleMapList: []
    };
  },
  methods: {
    addRuleMap () {

    },
    getRuleMapList () {
      this.$axios.get('/api/rule-map')
        .then(res => {
          let result = res.data;
          this.ruleMapList = result.data;
        });
    },
    editRuleMap (index) {

    }
  },
  mounted () {
    this.getRuleMapList();
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
