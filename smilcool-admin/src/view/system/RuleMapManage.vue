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
        <Select v-model="type[row.type].value">
          <Option v-for="item in type" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
      </template>
      <template slot-scope="{ row }" slot="authc">
        <iSwitch v-model="row.authc"/>
      </template>
      <template slot-scope="{ row }" slot="roles">
        <Col span="6">
          <iSwitch v-model="row.useRoles"/>
        </Col>
        <Col span="16" offset="2">
          <Input v-model="row.roles" placeholder="无" size="small"/>
        </Col>
      </template>
      <template slot-scope="{ row }" slot="perms">
        <Col span="6">
          <iSwitch v-model="row.usePerms"/>
        </Col>
        <Col span="16" offset="2">
          <Input v-model="row.perms" placeholder="无" size="small"/>
        </Col>
      </template>
      <template slot-scope="{ row }" slot="rest">
        <Col span="6">
          <iSwitch v-model="row.useRest"/>
        </Col>
        <Col span="16" offset="2">
          <Input v-model="row.rest" placeholder="无" size="small"/>
        </Col>
      </template>
      <template slot-scope="{ row }" slot="rule">
        {{ buildRule(row) }}
      </template>
      <template slot-scope="{ row }" slot="state">
        <Tag :color="state[row.state].color">{{state[row.state].label}}</Tag>
      </template>
      <template slot-scope="{ row, index }" slot="action">
        <Button class="btn" type="primary" size="small" @click="editRuleMap(index)">更新</Button>
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
        { title: '请求地址', key: 'url', width: '120' },
        { title: '描述', key: 'description', align: 'center', width: '120' },
        { title: '类型', slot: 'type', align: 'center', width: '120' },
        { title: '身份认证', slot: 'authc', align: 'center', width: '120' },
        { title: '角色认证', slot: 'roles', align: 'center' },
        { title: '权限认证', slot: 'perms', align: 'center' },
        { title: 'HTTP方法认证', slot: 'rest', align: 'center' },
        { title: '规则映射', slot: 'rule', align: 'center', tooltip:true },
        { title: '状态', slot: 'state', align: 'center', width: '100' },
        { title: '操作', slot: 'action', align: 'center', width: '100' }
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

    },
    buildRule (ruleMap) {
      let rule = '';
      if (ruleMap.authc) {
        rule += 'authc';
      } else {
        rule += 'anon';
      }
      if (ruleMap.useRoles) {
        rule += `,roles[${ruleMap.roles}]`;
      }
      if (ruleMap.usePerms) {
        rule += `,perms[${ruleMap.perms}]`;
      }
      if (ruleMap.useRest) {
        rule += `,rest[${ruleMap.rest}]`;
      }
      return rule;
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
