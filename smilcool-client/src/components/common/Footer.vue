<template>
  <footer>
    <div class="container">
      <hr>
      <div class="row">
        <span class="copyright">Copyright © Smilcool - Angus Liu 2019</span>
        <span class="links">
          <a href="#">帮助中心</a>
          <a href="#">加入我们</a>
          <a href="#">使用条例</a>
          <a href="#">隐私政策</a>
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
  margin-top: 5px;

  .container {
    height: 100%;
    width: 1200px;
    margin: 0 auto;
    padding-top: 10px;
    font-weight: 300;

    hr {
      margin-top: 20px;
      margin-bottom: 20px;
      border: 0;
      border-top: 1px solid #dedede;
    }

    .row {
      margin-bottom: 10px;
      overflow: auto;

      .copyright,
      .links {
        font-size: 14px;
        color: #373737;
      }

      .links {
        float: right;

        a {
          color: #373737;
          margin-left: 10px;
          padding-right: 10px;
          border-right: 1px solid #7a7a7a;
        }

        a:last-child {
          padding-right: 0;
          border-right: none;
        }

        a:hover {
          color: #ff8364;
        }
      }

      .icp {
        float: right;
        font-size: 12px;
        color: #808080;
      }
    }
  }
}

</style>
