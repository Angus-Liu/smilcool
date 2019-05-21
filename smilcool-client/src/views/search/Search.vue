<template>
  <div class="container">
    <Row>
      <iCol>
        <sui-menu secondary pointing>
          <a
            v-for="item in items"
            :key="item"
            is="sui-menu-item"
            :content="item"
            :active="isActive(item)"
            @click="select(item)"
          />
        </sui-menu>

      </iCol>
    </Row>
  </div>
</template>

<script>
export default {
  name: 'Search',
  data() {
    return {
      items: ['文章资讯', '文件仓库', '二手交易', '失物寻物'],
      active: '文章资讯',
    };
  },
  methods: {
    isActive(name) {
      return this.active === name;
    },
    select(name) {
      this.active = name;
    },
  },
}
</script>

<style lang="less" scoped>
.container {
  width: 1140px;
  margin: 0 auto;
  padding: 5px;

  .ivu-col {
    padding: 7px;
  }
}
</style>
