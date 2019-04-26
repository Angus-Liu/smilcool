<template>
  <Card class="card-panel">
    <Row>
      <iCol span="4">
        <div class="row-action">
          <Button class="btn" @click="addResourceType" type="primary" icon="md-add">添加资源类型</Button>
          <Button class="btn" @click="getResourceTypeList()" icon="md-refresh">刷新</Button>
        </div>
        <Alert show-icon>
          <span v-if="selectResourceTypeIndex != null">
            {{ parentResourceTypeList[selectResourceTypeIndex].title }}
            <a @click="cancelSelect">取消</a>
          </span>
          <span v-else>未选择父类型</span>
        </Alert>
        <div class="tree-bar">
          <Tree ref="tree" :data="parentResourceTypeList" @on-select-change="selectParentResourceType"></Tree>
        </div>
      </iCol>
      <iCol span="19" offset="1">
        <div class="table">
          <Table :columns="columns" :data="childResourceTypeList" border stripe>
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
            <Option v-for="item in parentResourceTypeList" :value="item.id" :key="item.id">{{ item.title }}</Option>
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
  name: 'ResourceTypeManage',
  data() {
    return {
      // 表格设置
      columns: [
        { type: 'index', width: 60, align: 'center' },
        { title: '名称', key: 'name', width: '150' },
        { title: '标签', key: 'tag', width: '150' },
        { title: '描述', key: 'description', width: '150', tooltip: true },
        { title: '排序值', key: 'seq', align: 'center', width: '100' },
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
      // 表格数据
      childResourceTypeList: [],
      // 树数据
      parentResourceTypeList: [],
      selectResourceTypeId: null,
      selectResourceTypeIndex: null,
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
    getResourceTypeList(parentId) {
      this.$axios.get(`/api/resource-type/`, { parentId })
        .then(res => {
          let result = res.data;
          if (parentId === null || parentId === undefined) {
            // 更新父类型树
            this.parentResourceTypeList = this.initParentResourceTypeList(result.data);
          } else {
            // 更新子类型表格
            this.childResourceTypeList = result.data;
          }
        });
    },
    initParentResourceTypeList(parentResourceTypeList) {
      let index = 0;
      parentResourceTypeList.forEach(parentResourceType => {
        parentResourceType.index = index++;
        parentResourceType.title = parentResourceType.name + ' / ' + parentResourceType.tag;
      });
      return parentResourceTypeList;
    },
    addResourceType() {
      this.resourceTypeAddModel = {
        show: true,
        title: '添加资源类型',
        submitLoading: false,
        resourceType: {
          parentId: this.selectResourceTypeId,
          name: '',
          tag: '',
          description: '',
          seq: 0,
          state: 1,
          remark: ''
        }
      }
    },
    selectParentResourceType(arr, item) {
      this.selectResourceTypeId = item.id;
      this.selectResourceTypeIndex = item.index;
      this.getResourceTypeList(item.id);
    },
    handleSubmit() {
      this.resourceTypeAddModel.submitLoading = true;
      this.$axios.post('/api/resource-type', this.resourceTypeAddModel.resourceType)
        .then(res => {
          this.resourceTypeAddModel.submitLoading = false;
          this.resourceTypeAddModel.show = false;
          this.getResourceTypeList(this.resourceTypeAddModel.resourceType.parentId);
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
      this.selectResourceTypeId = null;
      this.selectResourceTypeIndex = null;
    }
  },
  mounted() {
    this.getResourceTypeList();
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
