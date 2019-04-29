<template>
  <header>
    <div class="container">
      <img class="logo" src="../../assets/logo.png">
      <nav>
        <ul>
          <li><a href="#">校园资讯</a></li>
          <li><a href="#">资料分享</a></li>
          <li><a href="#">物品小站</a></li>
          <li><a href="#">校内论坛</a></li>
        </ul>
      </nav>
      <div class="avatar">
        <Dropdown>
          <sui-image class="avatar-img" circular src="http://img.angus-liu.cn/avatar.png"/>
          <DropdownMenu slot="list">
            <DropdownItem>驴打滚</DropdownItem>
            <DropdownItem>炸酱面</DropdownItem>
            <DropdownItem disabled>豆汁儿</DropdownItem>
            <DropdownItem>冰糖葫芦</DropdownItem>
            <DropdownItem divided>北京烤鸭</DropdownItem>
          </DropdownMenu>
        </Dropdown>
      </div>
      <div class="search">
        <Input search placeholder="Enter something..."/>
      </div>
    </div>
  </header>
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
header {
  /*position: fixed;*/
  width: 100%;
  height: 70px;
  background: #fff;
  box-shadow: 0 1px 3px #eee;
  z-index: 999;
  margin-bottom: 5px;
}

.container {
  width: 1440px;
  margin: 0 auto;

  .logo {
    float: left;
    height: 55px;
    margin-top: 10px;
  }

  nav {
    float: left;
    margin-left: 30px;
    line-height: 70px;

    ul {
      list-style-type: none;
      padding-top: 5px;
      font-size: 16px;

      li {
        display: inline-block;
        margin-right: 20px;

        a {
          text-decoration: none;
        }
      }
    }
  }

  .avatar {
    float: right;

    .avatar-img {
      height: 58px;
      margin-top: 6px;
    }
  }

  .search {
    width: 300px;
    float: right;
    margin-top: 20px;
    margin-right: 30px;
  }
}
</style>
