<template>
  <Card>
    <!-- 操作栏 -->
    <Row class="row-action">
      <Button class="btn" @click="addRuleMap" type="primary" icon="md-add">添加规则映射</Button>
      <Button class="btn" @click="getRuleMapList" icon="md-refresh">刷新</Button>
    </Row>
    <!-- 操作栏 END -->
    <!-- 表格 -->
    <Table :columns="columns" :data="ruleMapList" border stripe>
      <template slot-scope="{ row }" slot="type">
        <Select v-model="row.type">
          <Option v-for="item in type" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
      </template>
      <template slot-scope="{ row }" slot="authc">
        <iSwitch v-model="row.authc"/>
      </template>
      <template slot-scope="{ row }" slot="roles">
        <iCol span="6">
          <iSwitch v-model="row.useRoles"/>
        </iCol>
        <iCol span="16" offset="2">
          <Input v-model="row.roles" placeholder="无" size="small"/>
        </iCol>
      </template>
      <template slot-scope="{ row }" slot="perms">
        <iCol span="6">
          <iSwitch v-model="row.usePerms"/>
        </iCol>
        <iCol span="16" offset="2">
          <Input v-model="row.perms" placeholder="无" size="small"/>
        </iCol>
      </template>
      <template slot-scope="{ row }" slot="rest">
        <iCol span="6">
          <iSwitch v-model="row.useRest"/>
        </iCol>
        <iCol span="16" offset="2">
          <Input v-model="row.rest" placeholder="无" size="small"/>
        </iCol>
      </template>
      <template slot-scope="{ row }" slot="rule">
        {{ buildRule(row) }}
      </template>
      <template slot-scope="{ row }" slot="state">
        <Tag :color="state[row.state].color">{{state[row.state].label}}</Tag>
      </template>
      <template slot-scope="{ row }" slot="seq">
        <InputNumber :max="100" :min="1" v-model="row.seq" style="width:60px"></InputNumber>
      </template>
      <template slot-scope="{ row, index }" slot="action">
        <Button class="btn" type="primary" size="small" @click="updateRuleMap(row)">更新</Button>
      </template>
    </Table>
    <!-- 表格 END -->
    <!-- 规则映射模态框 -->
  </Card>
</template>

<script>
export default {
  name: 'RuleMapManage',
  data() {
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
        { title: '规则映射', slot: 'rule', align: 'center', tooltip: true },
        { title: '排序值', slot: 'seq', align: 'center', width: '100' },
        { title: '状态', slot: 'state', align: 'center', width: '100' },
        { title: '操作', slot: 'action', align: 'center', width: '100' }
      ],
      type: [
        { value: 0, label: '菜单' },
        { value: 1, label: '按钮' },
        { value: 2, label: '其他' }
      ],
      state: [
        { value: 0, label: '停用', color: 'red' },
        { value: 1, label: '正常', color: 'green' }
      ],
      ruleMapList: []
    };
  },
  methods: {
    addRuleMap() {

    },
    getRuleMapList() {
      this.$axios.get('/api/rule-map')
        .then(res => {
          let result = res.data;
          this.ruleMapList = result.data;
        });
    },
    buildRule(ruleMap) {
      let rule = '';
      rule += ruleMap.authc ? 'authc' : 'anon';
      rule += ruleMap.useRoles ? `,roles[${ruleMap.roles}]` : '';
      rule += ruleMap.usePerms ? `,perms[${ruleMap.perms}]` : '';
      rule += ruleMap.useRest ? `,rest[${ruleMap.rest}]` : '';
      return rule;
    },
    updateRuleMap(ruleMap) {
      this.$axios.put(`/api/rule-map`, ruleMap)
        .then(res => {
          this.getRuleMapList();
          this.$Message.success('更新成功');
        });
    }
  },
  mounted() {
    this.getRuleMapList();
  }
};
</script>

<style lang="less" scoped>
  .row-action {
    margin-bottom: 10px;

    .row-action-alert {
      display: inline-block;
    }
  }

  .btn {
    margin-right: 5px;
  }
</style>
