<template>
  <Card class="card-panel">
    <Row>
      <iCol span="4">
        <div class="row-action">
          <Button class="btn" @click="addDicType" type="primary" icon="md-add">添加字典类型</Button>
          <Button class="btn" @click="getDicTypeList" icon="md-refresh">刷新</Button>
        </div>
        <Alert show-icon>
          <span v-if="selectedDicType != null">
            {{ selectedDicType.title }}
            <a @click="cancelSelect">取消</a>
          </span>
          <span v-else>未选择字典类型</span>
        </Alert>
        <div class="tree-bar">
          <Tree ref="tree" :data="dicTypeList" @on-select-change="selectDicType"></Tree>
        </div>
      </iCol>
      <iCol span="19" offset="1">
        <div class="table">
          <Table :columns="columns" :data="dicItemList" border stripe>
            <template slot-scope="{ row }" slot="fixed">
              <iSwitch v-model="row.fixed"/>
            </template>
            <template slot-scope="{ row }" slot="state">
              <Tag :color="state[row.state].color">{{state[row.state].label}}</Tag>
            </template>
            <template slot-scope="{ row, index }" slot="action">
              <Button class="btn" type="primary" size="small">编辑</Button>
            </template>
          </Table>
        </div>
      </iCol>
    </Row>
    <!-- 添加资源类型模态框 -->
    <Modal :title="resourceTypeAddModel.title" v-model="resourceTypeAddModel.show" :mask-closable="false" :width="450">
      <Form :model="resourceTypeAddModel.resourceType" :label-width="50">
        <FormItem label="父类型" prop="parentId">
          <Select class="form-item" v-model="resourceTypeAddModel.resourceType.parentId"
                  placeholder="此项不填则添加为父类型" clearable>
            <Option v-for="item in dicTypeList" :value="item.id" :key="item.id">{{ item.title }}</Option>
          </Select>
        </FormItem>
        <FormItem label="名称" prop="name">
          <Input class="form-item" v-model="resourceTypeAddModel.resourceType.name"
                 placeholder="Enter something..."></Input>
        </FormItem>
        <FormItem label="标签" prop="tag">
          <Input class="form-item" v-model="resourceTypeAddModel.resourceType.tag"
                 placeholder="Enter something..."></Input>
        </FormItem>
        <FormItem label="描述" prop="description">
          <Input class="form-item" v-model="resourceTypeAddModel.resourceType.description"
                 placeholder="Enter something..."></Input>
        </FormItem>
        <FormItem label="状态" prop="state">
          <Select class="form-item" v-model="resourceTypeAddModel.resourceType.state">
            <Option v-for="item in state" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </FormItem>
        <FormItem label="备注" prop="remark">
          <Input class="form-item" v-model="resourceTypeAddModel.resourceType.remark"
                 placeholder="Enter something..."></Input>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="resourceTypeAddModel.show = false">关闭</Button>
        <Button type="primary" @click="handleSubmit" :loading="resourceTypeAddModel.submitLoading">提交</Button>
      </div>
    </Modal>
  </Card>
</template>

<script>
export default {
  name: 'DicManage',
  data() {
    return {
      // 表格设置
      columns: [
        { type: 'index', width: 60, align: 'center' },
        { title: '类型', key: 'dicTypeCode', width: '150' },
        { title: '项目名', key: 'name', width: '150' },
        { title: '项目码', key: 'code', width: '100' },
        { title: '排序值', key: 'seq', align: 'center', width: '100' },
        { title: '固定', slot: 'fixed', align: 'center', width: '100' },
        { title: '状态', slot: 'state', align: 'center', width: '100' },
        { title: '备注', key: 'remark', tooltip: true },
        { title: '创建时间', key: 'createTime', align: 'center', width: '150' },
        { title: '更新时间', key: 'updateTime', align: 'center', width: '150' },
        { title: '操作', slot: 'action', align: 'center', width: '100' }
      ],
      state: [
        { value: 0, label: '停用', color: 'red' },
        { value: 1, label: '正常', color: 'green' }
      ],
      // 表格数据（字典项目）
      dicItemList: [{
        'id': 6,
        'dicTypeCode': 'article-category',
        'name': '校园新闻',
        'code': '2',
        'seq': 0,
        'fixed': false,
        'state': 1,
        'createTime': '2019-05-12 17:21:29',
        'updateTime': '2019-05-21 10:39:42',
        'deleted': false
      }],
      // 树数据（字典类型）
      dicTypeList: [{
        'id': 1,
        'name': '文章类别',
        'code': 'article-category',
        'seq': 0,
        'state': 1,
        'createTime': '2019-05-12T17:17:25.000+0000',
        'updateTime': '2019-05-13T08:54:10.000+0000',
        'deleted': false
      }],
      selectedDicType: null,
      // 添加资源类型模态框
      resourceTypeAddModel: {
        show: false,
        title: '添加资源类型',
        submitLoading: false,
        resourceType: {
          parentId: null,
          name: '',
          tag: '',
          description: '',
          seq: 0,
          state: 1,
          remark: ''
        }
      }
    }
  },
  methods: {
    getDicTypeList() {
      this.$axios.get(`/api/dic/type`)
        .then(res => {
          let result = res.data;
          // 更新父类型树
          this.dicTypeList = result.data;
          this.dicTypeList.forEach(dicType => {
            dicType.title = dicType.name + ' / ' + dicType.code;
          });
        });
    },
    getDicItemList(dicTypeCode) {
      this.$axios.get(`/api/dic/item`, { dicTypeCode })
        .then(res => {
          let result = res.data;
          // 更新父类型树
          this.dicItemList = result.data;
        });
    },
    addDicType() {
      this.resourceTypeAddModel = {
        show: true,
        title: '添加资源类型',
        submitLoading: false,
        resourceType: {
          name: '',
          tag: '',
          description: '',
          seq: 0,
          state: 1,
          remark: ''
        }
      }
    },
    selectDicType(arr, item) {
      this.selectedDicType = item;
      this.getDicItemList(item.code);
    },
    handleSubmit() {
      this.resourceTypeAddModel.submitLoading = true;
      this.$axios.post('/api/resource-type', this.resourceTypeAddModel.resourceType)
        .then(res => {
          this.resourceTypeAddModel.submitLoading = false;
          this.resourceTypeAddModel.show = false;
          this.getDicTypeList(this.resourceTypeAddModel.resourceType.parentId);
        })
        .catch(error => {
          this.resourceTypeAddModel.submitLoading = false;
        });
    },
    cancelSelect() {
      let node = this.$refs.tree.getSelectedNodes()[0];
      if (node) {
        node.selected = false;
      }
      this.selectedDicType = null;
      this.getDicItemList();
    }
  },
  mounted() {
    this.getDicTypeList();
    this.getDicItemList();
  }
}
</script>

<style lang="less" scoped>

  .card-panel {
    /*height: 100%;*/
  }

  .row-action {
    margin-bottom: 10px;
  }

  .btn {
    margin-right: 5px;
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
