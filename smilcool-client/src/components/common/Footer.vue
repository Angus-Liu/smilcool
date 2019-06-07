<template>
  <footer>
    <div class="container">
      <div class="row">
        <span class="copyright">Copyright © Smilcool - Angus Liu 2019</span>
        <span class="links">
          <a href="#">帮助中心</a>
          <a href="#">加入我们</a>
          <a href="https://github.com/Angus-Liu" target="_blank">关于作者</a>
          <a href="https://github.com/Angus-Liu/smilcool" target="_blank">项目地址</a>
        </span>
      </div>
      <div class="row">
        <a class="icp" href="http://www.miitbeian.gov.cn/" target="_blank">蜀ICP备18021444号</a>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'Footer'
};
</script>

<style lang="less" scoped>
footer {
  height: 100px;
  margin-top: 10px;
  background: #ff8364;
  color: #fff;

  .container {
    height: 100%;
    width: 1200px;
    margin: 0 auto;
    padding-top: 15px;
    font-weight: 500;

    .row {
      margin-top: 10px;
      overflow: auto;

      .copyright,
      .links {
        font-size: 14px;
      }

      .links {
        float: right;

        a {
          color: #fff;
          margin-left: 10px;
          padding-right: 10px;
          border-right: 1px solid #fff;
        }

        a:last-child {
          padding-right: 0;
          border-right: none;
        }

        a:hover {
          color: #ccc;
        }
      }

      .icp {
        float: right;
        font-size: 12px;
        color: #fff;
      }
    }
  }
}

</style>
