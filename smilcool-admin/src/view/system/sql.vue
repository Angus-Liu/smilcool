<template>
  <Card class="card">
    <div class="goto">
      <Form inline @submit.native.prevent>
        <Form-item label="网址" prop="url" :label-width="40">
          <Input :value="url" placeholder="请输入网址……" search enter-button="转到"
                 @on-search="handleGoto" style="width: 300px"/>
        </Form-item>
        <Form-item>
          <Button @click="handleOpen" icon="md-open">新窗口中打开</Button>
        </Form-item>
      </Form>
    </div>
    <div class="page">
      <iframe :src="url" frameborder="0" width="100%" height="90%" scrolling="auto" @load="spinShow = false"></iframe>
      <Spin size="large" fix v-if="spinShow"></Spin>
    </div>
  </Card>
</template>

<script>
export default {
  name: 'sql',
  data () {
    return {
      spinShow: true,
      url: 'http://localhost:8100/druid'
    };
  },
  methods: {
    handleGoto (value) {
      if (value !== this.url) {
        this.spinShow = true;
        this.url = value;
      }
    },
    handleOpen () {
      window.open(this.url);
    }
  }
};
</script>

<style scoped>
  .card, .card >>> .ivu-card-body {
    height: 100%;
    overflow: hidden;
  }

  .page {
    height: 100%;
    position: relative;
  }
</style>
