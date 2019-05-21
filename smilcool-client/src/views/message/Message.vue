<template>
  <div class="container">
    <Row>
      <iCol>
        <sui-card class="fluid">
          <sui-message attached="top">消息中心</sui-message>
          <sui-card-content>
            <Row>
              <iCol span="7">
                <!-- 好友列表 -->
                <div class="friend-list-container">
                  <sui-item-group>
                    <!-- 好友列表 -->
                    <sui-item v-for="friend in friendList"
                              :class="{active: currentFriend === friend}"
                              @click.native="selectFriend(friend)">
                      <sui-image circular :src="friend.avatar"/>
                      <sui-item-content>
                        <sui-item-description>{{friend.nickname}}
                          <Badge :count="1"/>
                        </sui-item-description>
                        <!-- 显示当前好友最新信息 -->
                        <sui-item-meta>{{lastMessage(friend)}}</sui-item-meta>
                      </sui-item-content>
                    </sui-item>
                  </sui-item-group>
                </div>
                <!-- 好友列表 END -->
              </iCol>
              <iCol span="17">
                <!-- 聊天框 -->
                <div v-if="currentFriend" class="message-list-container">
                  <!-- 聊天用户 -->
                  <div class="current-friend">
                    <p>和 {{currentFriend.nickname}} 聊天中...</p>
                  </div>
                  <!-- 聊天用户 END -->
                  <!-- 消息列表 -->
                  <div class="message-list">
                    <div class="time">7分钟前</div>
                    <template v-for="message in currentMessageList">
                      <!-- 发送的消息 -->
                      <div class="bubble-wrapper" v-if="message.sendUserId === currentUser.id">
                        <div class="bubble right-bubble" v-html="message.content"/>
                      </div>
                      <!-- 接收的消息 -->
                      <div class="bubble-wrapper" v-if="message.receiveUserId === currentUser.id">
                        <div class="bubble left-bubble" v-html="message.content"/>
                      </div>
                    </template>
                  </div>
                  <!-- 消息列表 END -->
                  <!-- 输入框 -->
                  <sui-form class="message-input">
                    <sui-form-field>
                      <textarea placeholder="输入消息" v-model="message" rows="3" @keydown.ctrl.enter="sendMessage"/>
                    </sui-form-field>
                    <sui-form-field>
                      <sui-button basic floated="right" content="发送(Ctrl+Enter)" @click.prevent="sendMessage"/>
                    </sui-form-field>
                  </sui-form>
                  <!-- 输入框 END -->
                </div>
                <!-- 提示框 -->
                <div v-else>
                  <sui-message>
                    <sui-message-header>
                      <sui-icon name="bell outline"/>
                      这里是消息中心
                    </sui-message-header>
                    <p>在这里，你可以接收到系统发送的消息。你也可以发送消息联系你的好友或是校友，快来开启愉快的聊天之旅吧 🤗</p>
                  </sui-message>
                </div>
                <!-- 提示框 END -->
                <!-- 聊天框 END -->
              </iCol>
            </Row>
          </sui-card-content>
        </sui-card>
      </iCol>
    </Row>
  </div>
</template>

<script>
import storage from '@/utils/storage'

export default {
  name: 'Message',
  data() {
    return {
      message: '',
      currentUser: this.$store.state.user,
      currentFriend: null,
      currentMessageList: [],
      webSocket: null,
      friendList: [{
        id: -1,
        username: 'angus',
        nickname: '文刀',
        avatar: 'http://img.angus-liu.cn/avatar/avatar07.png',
      }]
    }
  },
  methods: {
    // 切换好友
    selectFriend(friend) {
      this.currentFriend = friend;
      this.currentMessageList = friend.messageList;
    },
    // 获取好友列表
    getFriendList() {
      this.$axios.get('/api/friend')
        .then(res => {
          let result = res.data;
          this.friendList = result.data;
          // 初始化消息
          this.initFriendList();
          // 建立连接
          this.connectWebSocketServer();
        })
    },
    // 初始化好友列表
    initFriendList() {
      let oldFriendList = storage.get(this.currentUser.id);
      this.friendList.forEach(friend => {
        friend.messageList = [];
        if (oldFriendList != null) {
          oldFriendList.forEach(oldFriend => {
            if (friend.id === oldFriend.id) {
              friend.messageList = oldFriend.messageList;
            }
          })
        }
      });
    },
    connectWebSocketServer() {
      // 获取 WebSocket Server URL
      this.$axios.get('/api/sys/ws')
        .then(res => {
          let webSocketServerUrl = res.data;
          // 初始化 WebSocket 并连接
          this.initWebSocket(webSocketServerUrl);
        });
    },
    initWebSocket(webSocketServer) {
      if (this.webSocket != null) {
        console.log('复用连接');
        this.webSocket.onconnect();
      }
      this.webSocket = new WebSocket(webSocketServer);
      // 建立连接
      this.webSocket.onopen = event => {
        console.log('ws.onopen: ', event);
        this.webSocket.onconnect();
      };
      // 接收消息
      this.webSocket.onmessage = event => {
        console.log('ws.onmessage: ', event);
        let message = JSON.parse(event.data);
        if (this.currentFriend &&
          (message.sendUserId === this.currentFriend.id
            || message.receiveUserId === this.currentFriend.id)) {
          this.currentMessageList.push(message);
          storage.set(this.currentUser.id, this.friendList);
          return;
        }
        // 根据 sendUserId 和 receiveUserId，将消息放入对应 friend 下的 messageList 中
        this.friendList.forEach(friend => {
          if (message.sendUserId === friend.id
            || message.receiveUserId === friend.id) {
            friend.messageList.push(message);
            return;
          }
        });
      };
      // 关闭连接
      this.webSocket.onclose = event => console.log('ws.onclose: ', event);
      // 发生异常
      this.webSocket.onerror = event => console.log('ws.onerror: ', event);
      // 发送消息
      this.webSocket.onsend = data => {
        console.log('ws.send', data);
        this.webSocket.send(JSON.stringify(data));
      };
      // 连接
      this.webSocket.onconnect = () => {
        console.log('ws.connect');
        this.webSocket.onsend({ type: 0, sendUserId: this.currentUser.id });
      }
    },
    // 发送消息
    sendMessage() {
      let data = {
        type: 1,
        sendUserId: this.currentUser.id,
        receiveUserId: this.currentFriend.id,
        content: this.message
      };
      this.webSocket.onsend(data);
      this.currentMessageList.push(data);
      storage.set(this.currentUser.id, this.friendList);
      this.message = '';
    },
    lastMessage(friend) {
      if (friend.messageList && friend.messageList.length > 0) {
        return friend.messageList[friend.messageList.length - 1].content;
      }
      return '暂无消息';
    }
  },
  mounted() {
    // 获取好友列表
    this.getFriendList();
  }
}

</script>

<style lang="less" scoped>
.container {
  width: 1140px;
  margin: 0 auto;
  padding: 5px;

  .ivu-col {
    padding: 5px;
  }

  .card {
    height: 720px;
  }

  .friend-list-container {
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

        .meta {
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
        }
      }

      .item:hover,
      .item.active {
        background: #eeeeee;
      }
    }
  }

  .message-list-container {
    .current-friend {
      height: 20px;
      padding: 0 10px;

      p {
        position: relative;
        top: -5px;
        color: #666;
      }
    }

    .message-list {
      height: 480px;
      margin-bottom: 15px;
      padding: 10px;
      overflow: auto;

      .time {
        text-align: center;
        font-size: 12px;
        color: #666666;
        margin-bottom: 10px;
      }

      .bubble-wrapper {
        clear: right;
        margin-bottom: 20px;
        overflow: auto;

        .bubble {
          display: inline-block;
          max-width: 65%;
          padding: 9px 14px;
          border-radius: 20px;
          font-size: 14px;
          line-height: 24px;
        }

        .left-bubble {
          float: left;
          background: #fdfdfd;
          border: 1px solid rgba(0, 0, 0, 0.05);
          border-top-left-radius: 0;
        }

        .right-bubble {
          float: right;
          color: #fff;
          background-color: #3FD1E1;
          border-bottom-right-radius: 0;
        }
      }
    }

    .message-input {
      textarea {
        resize: none;
        height: 100%;
      }
    }
  }
}
</style>
