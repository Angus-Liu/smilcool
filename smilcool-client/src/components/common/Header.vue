<template>
  <div class="header">
    <div class="nav">
      <div class="nav-logo">
        <img class="nav-logo-img" src="../../assets/logo.png">
      </div>
      <div class="nav-avatar">
        <Dropdown>
          <sui-image class="nav-avatar-img" circular src="http://img.angus-liu.cn/avatar.png"/>
          <DropdownMenu slot="list">
            <DropdownItem>驴打滚</DropdownItem>
            <DropdownItem>炸酱面</DropdownItem>
            <DropdownItem disabled>豆汁儿</DropdownItem>
            <DropdownItem>冰糖葫芦</DropdownItem>
            <DropdownItem divided>北京烤鸭</DropdownItem>
          </DropdownMenu>
        </Dropdown>
      </div>
      <div class="nav-search">
        <Input search placeholder="Enter something..."/>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Header',
  data() {
    return {
      input: ''
    };
  },
  mounted() {

  }
};
</script>

<style lang="less" scoped>
.header {
  position: fixed;
  right: 0;
  left: 0;
  height: 56px;
  background: #eee;
  border-bottom: 1px #eee solid;

  .nav {
    max-width: 1440px; // 1440px
    height: 100%;
    margin: 0 auto;
    padding: 0 3px;
    background: red;

    .nav-search {
      float: right;
      display: inline-block;
      width: 300px;
      height: 56px;

      .nav-search-input {
        margin-top: 12px;
      }
    }

    .nav-logo {
      float: left;
      height: 100%;

      .nav-logo-img {
        height: 100%;
        padding-top: 7px;
      }
    }

    .nav-avatar {
      float: right;
      height: 50px;
      padding: 3px;

      .nav-avatar-img {
        height: 50px;
        margin-bottom: 5px;
      }
    }
  }
}

</style>
