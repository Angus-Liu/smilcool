<template>
  <div class="container">
    <sui-card class="fluid">
      <sui-message attached="top">
        消息中心
      </sui-message>
      <sui-card-content>
        <Row>
          <iCol class="column" span="7">
            <div class="friend-list">
              <sui-item-group>
                <sui-item v-for="i in 5" :class="{active: id === i}" @click.native="id = i">
                  <sui-image circular src="http://img.angus-liu.cn/avatar/avatar06.png"/>
                  <sui-item-content>
                    <sui-item-description>
                      Angus
                      <Badge v-if="i % 2 === 0" :count="3"/>
                    </sui-item-description>
                    <sui-item-meta>这是一条消息</sui-item-meta>
                  </sui-item-content>
                </sui-item>
              </sui-item-group>
            </div>
          </iCol>
          <iCol class="column" span="17">
            <div class="message-list">
              <div class="message-friend">
                <p>和 Angus 聊天中...</p>
              </div>
              <div class="message-show">
                <div class="bubble-wrapper">
                  <div class="bubble left-bubble">你好好，做气泡框</div>
                </div>
                <div class="time-wrapper">昨天</div>
                <div class="bubble-wrapper">
                  <div class="bubble right-bubble">你好好，做气泡框</div>
                </div>
                <div class="bubble-wrapper">
                  <div class="bubble right-bubble">你好好，做气泡框</div>
                </div>
                <div class="bubble-wrapper">
                  <div class="bubble left-bubble">你到底想问什么，你说话啊！😠别当哑巴！！！快说话</div>
                </div>
                <div class="time-wrapper">7分钟前</div>
                <div class="bubble-wrapper">
                  <div class="bubble right-bubble">You are not a good man! You are so rude 😭</div>
                </div>
                <div class="bubble-wrapper">
                  <div class="bubble right-bubble">如果能在意我的想法，发挥我的脑洞和技术特长，欢迎邮件至 me@zhangwenli.com 勾搭~</div>
                </div>
                <div class="bubble-wrapper">
                  <div class="bubble left-bubble">快看美女！<br/><img src="http://img.angus-liu.cn/avatar/avatar06.png">
                  </div>
                </div>
                <div class="bubble-wrapper">
                  <div class="bubble right-bubble"><b>你就这点出息！</b></div>
                </div>
                <div class="bubble-wrapper">
                  <div class="bubble right-bubble"><img src="https://tu.jiuwa.net/pic/20171105/1509886023460480.jpg">
                  </div>
                </div>
              </div>
              <div class="message-input">
                <sui-form>
                  <sui-form-field>
                    <textarea placeholder="输入消息"/>
                  </sui-form-field>
                </sui-form>
              </div>
            </div>
          </iCol>
        </Row>
      </sui-card-content>
    </sui-card>
  </div>
</template>

<script>
export default {
  name: 'Message',
  data() {
    return {
      id: 1
    }
  }
}
</script>

<style lang="less" scoped>
.container {
  width: 960px;
  margin: 20px auto;
  padding: 5px;

  .card {
    height: 720px;

    .column {
      padding: 5px;

      .friend-list {
        height: 625px;
        overflow: auto;

        .items {
          .item {
            cursor: pointer;
            margin-bottom: 5px;
            padding: 2px;
            border-radius: 30px;

            .image {
              width: 60px;
              height: 60px;
            }
          }

          .item:hover {
            background: #fbfbfb;
          }

          .item.active {
            background: #fbfbfb;
          }
        }
      }

      .message-list {
        height: 625px;

        .message-friend {
          height: 20px;
          padding: 0 10px;

          p {
            position: relative;
            top: -5px;
            color: #666;
          }
        }

        .message-show {
          height: 470px;
          margin-bottom: 15px;
          padding: 10px;
          overflow: auto;
        }

        .message-input {
          height: 110px;

          textarea {
            resize: none;
            height: 100%;
          }
        }
      }
    }
  }
}

div.bubble {
  display: inline-block;
  max-width: 65%;
  padding: 9px 14px;
  border-radius: 20px;
  font-size: 14px;
  line-height: 24px;
}

div.left-bubble {
  background: #fdfdfd;
  border: 1px solid rgba(0, 0, 0, 0.05);
  border-top-left-radius: 0;
}

div.right-bubble {
  float: right;
  color: #fff;
  background-color: #3FD1E1;
  border-bottom-right-radius: 0;
}

.bubble-wrapper {
  clear: right;
  margin-bottom: 20px;
  overflow: auto;
}

.time-wrapper {
  text-align: center;
  font-size: 12px;
  color: #666666;
  margin-bottom: 10px;
}
</style>
