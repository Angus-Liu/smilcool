<template>
  <Table border :columns="columns12" :data="data6" stripe>
    <template slot-scope="{ row }" slot="username">
      <strong>{{ row.username }}</strong>
    </template>
    <template slot-scope="{ row, index }" slot="action">
      <Button type="primary" size="small" style="margin-right: 5px" @click="show(index)">View</Button>
      <Button type="error" size="small" @click="remove(index)">Delete</Button>
    </template>
  </Table>
</template>
<script>
export default {
  data () {
    return {
      columns12: [
        { title: '用户名', key: 'username', slot: 'username' },
        { title: '昵称', key: 'nickname' },
        { title: '性别', key: 'sex' },
        { title: '生日', key: 'birthday' },
        { title: '年级', key: 'grade' },
        { title: '学院', key: 'college' },
        { title: '专业', key: 'major' },
        { title: '手机', key: 'phone' },
        { title: '邮箱', key: 'email' },
        { title: '状态', key: 'state' },
        { title: '备注', key: 'remark' },
        { title: '创建时间', key: 'createTime' },
        { title: '更新时间', key: 'updateTime' },
        { title: 'Action', slot: 'action', width: 150, align: 'center' }
      ],
      data6: [
        {
          'id': 1,
          'username': 'admin',
          'nickname': '换个好听的昵称',
          'avatar': 'default-avatar.jpg',
          'sex': '保密',
          'birthday': '1970-01-01',
          'sign': '一句话介绍自己',
          'intro': '这个人很神秘，什么也没写',
          'grade': '未填写',
          'college': '未填写',
          'major': '未填写',
          'phone': null,
          'email': 'admin@admin.com',
          'state': '未激活',
          'remark': null,
          'createTime': '2019/03/28 12:02:46',
          'updateTime': '2019/03/28 12:02:46',
          'isDel': false
        },
        {
          'id': 2,
          'username': 'angus',
          'nickname': '换个好听的昵称',
          'avatar': 'default-avatar.jpg',
          'sex': '保密',
          'birthday': '1970-01-01',
          'sign': '一句话介绍自己',
          'intro': '这个人很神秘，什么也没写',
          'grade': '未填写',
          'college': '未填写',
          'major': '未填写',
          'phone': null,
          'email': 'angus.liu96@qq.com',
          'state': '未激活',
          'remark': null,
          'createTime': '2019/03/28 12:18:53',
          'updateTime': '2019/03/28 12:18:53',
          'isDel': false
        },
        {
          'id': 3,
          'username': 'tommy',
          'nickname': '换个好听的昵称',
          'avatar': 'default-avatar.jpg',
          'sex': '保密',
          'birthday': '1970-01-01',
          'sign': '一句话介绍自己',
          'intro': '这个人很神秘，什么也没写',
          'grade': '未填写',
          'college': '未填写',
          'major': '未填写',
          'phone': null,
          'email': 'tommy@qq.com',
          'state': '未激活',
          'remark': null,
          'createTime': '2019/03/28 12:20:57',
          'updateTime': '2019/03/28 12:20:57',
          'isDel': false
        },
        {
          'id': 4,
          'username': 'any',
          'nickname': '换个好听的昵称',
          'avatar': 'default-avatar.jpg',
          'sex': '保密',
          'birthday': '1970-01-01',
          'sign': '一句话介绍自己',
          'intro': '这个人很神秘，什么也没写',
          'grade': '未填写',
          'college': '未填写',
          'major': '未填写',
          'phone': null,
          'email': 'any@qq.com',
          'state': '未激活',
          'remark': null,
          'createTime': '2019/03/28 12:58:15',
          'updateTime': '2019/03/28 12:58:15',
          'isDel': false
        }
      ]
    }
  },
  methods: {
    show (index) {
      this.$Modal.info({
        title: 'User Info',
        content: `Name：${this.data6[index].name}<br>Age：${this.data6[index].age}<br>Address：${this.data6[index].address}`
      })
    },
    remove (index) {
      this.data6.splice(index, 1)
    }
  }
}
</script>
