<template>
  <div class="container">
    <Row>
      <iCol span="6">
        <sui-card class="fluid">
          <sui-image style="width: 100%;" :src="user.avatar"/>
          <sui-card-content>
            <sui-card-header>{{user.nickname}}</sui-card-header>
            <sui-card-meta>{{user.username}}</sui-card-meta>
            <sui-card-description>{{user.sign}}</sui-card-description>
          </sui-card-content>
          <sui-card-content extra>
            <sui-icon name="user"/>
            用户
            <span slot="right">{{user.createTime}} 加入</span>
          </sui-card-content>
        </sui-card>
        <div class="actions-buttons" v-if="id !== $store.state.user.id.toString()">
          <sui-button-group>
            <sui-button basic color="red" icon="heart" content="关注"/>
            <sui-button basic color="blue" icon="envelope" content="私信"/>
          </sui-button-group>
        </div>
      </iCol>
      <iCol span="18">
        <!-- 用户详细信息 -->
        <sui-card class="fluid">
          <sui-message attached="top" content="详细信息"/>
          <sui-card-content>
            <Row>
              <iCol span="8">
                <span class="emoji">🧑</span>性别：{{user.sex}}
              </iCol>
              <iCol span="8">
                <span class="emoji">🎂</span>生日：{{user.birthday}}
              </iCol>
              <iCol span="8">
                <span class="emoji">📧</span>邮箱：{{user.email}}
              </iCol>
            </Row>
            <Row>
              <iCol span="8">
                <span class="emoji">🏫</span>学院：{{user.college}}
              </iCol>
              <iCol span="8">
                <span class="emoji">👩‍🎓</span>年级：{{user.grade}}
              </iCol>
              <iCol span="8">
                <span class="emoji">📝</span>专业：{{user.major}}
              </iCol>
            </Row>
            <Row>
              <iCol span="8">
                <span class="emoji">📱</span>手机： {{user.phone}}
              </iCol>
              <iCol span="16">
                <span class="emoji">📷</span>简介：{{user.intro}}
              </iCol>
            </Row>
          </sui-card-content>
          <sui-button basic attached="bottom" icon="pencil alternate" content="修改个人信息"
                      v-if="id === $store.state.user.id.toString()"/>
        </sui-card>
        <!-- 用户详细信息 END -->
        <sui-tab>
          <sui-tab-pane title="发表文章">
            <h3>HTML</h3>
            <p>
              HTML (HyperText Markup Language) is the most basic building block of the Web.
              It describes and defines the content of a webpage along with the basic layout of
              the webpage. Other technologies besides HTML are generally used to describe a web
              page's appearance/presentation (CSS) or functionality/ behavior (JavaScript).
            </p>
            <a href="https://developer.mozilla.org/en-US/docs/Web/HTML">developer.mozilla.org</a>
          </sui-tab-pane>
          <sui-tab-pane title="上传文件">
            <h3>CSS</h3>
            <p>
              Cascading Style Sheets (CSS) is a stylesheet language used to describe the
              presentation of a document written in HTML or XML (including XML dialects such as SVG
              or XHTML). CSS describes how elements should be rendered on screen, on paper, in
              speech, or on other media.
            </p>
            <a href="https://developer.mozilla.org/en-US/docs/Web/CSS">developer.mozilla.org</a>
          </sui-tab-pane>
          <sui-tab-pane title="发布二手">
            <h3>JavaScript</h3>
            <p>
              JavaScript (JS) is a lightweight interpreted or JIT-compiled programming language
              with first-class functions. While it is most well-known as the scripting language for
              Web pages, many non-browser environments also use it, such as Node.js, Apache CouchDB
              and Adobe Acrobat. JavaScript is a prototype-based, multi-paradigm, dynamic
              language, supporting object-oriented, imperative, and declarative (e.g.
              functional programming) styles.
            </p>
            <a href="https://developer.mozilla.org/en-US/docs/Web/javascript">developer.mozilla.org</a>
          </sui-tab-pane>
          <sui-tab-pane title="发布失物寻物">
            <h3>JavaScript</h3>
            <p>
              JavaScript (JS) is a lightweight interpreted or JIT-compiled programming language
              with first-class functions. While it is most well-known as the scripting language for
              Web pages, many non-browser environments also use it, such as Node.js, Apache CouchDB
              and Adobe Acrobat. JavaScript is a prototype-based, multi-paradigm, dynamic
              language, supporting object-oriented, imperative, and declarative (e.g.
              functional programming) styles.
            </p>
            <a href="https://developer.mozilla.org/en-US/docs/Web/javascript">developer.mozilla.org</a>
          </sui-tab-pane>
          <sui-tab-pane title="发布帖子">
            <h3>JavaScript</h3>
            <p>
              JavaScript (JS) is a lightweight interpreted or JIT-compiled programming language
              with first-class functions. While it is most well-known as the scripting language for
              Web pages, many non-browser environments also use it, such as Node.js, Apache CouchDB
              and Adobe Acrobat. JavaScript is a prototype-based, multi-paradigm, dynamic
              language, supporting object-oriented, imperative, and declarative (e.g.
              functional programming) styles.
            </p>
            <a href="https://developer.mozilla.org/en-US/docs/Web/javascript">developer.mozilla.org</a>
          </sui-tab-pane>
        </sui-tab>
      </iCol>
    </Row>

  </div>
</template>

<script>
export default {
  name: 'User',
  props: ['id'],
  data() {
    return {
      user: {
        'id': -1,
        'username': 'anonymous',
        'nickname': '游客',
        'avatar': require('../../assets/anonymous-avatar.png'),
        'sex': '保密',
        'birthday': '1970-01-01',
        'sign': '一句话介绍自己',
        'intro': '这个人很神秘，什么也没写',
        'grade': '未填写',
        'college': '未填写',
        'major': '未填写',
        'phone': '未填写',
        'email': 'admin@admin.com',
        'createTime': '2019-03-28'
      }
    }
  },
  methods: {
    getUser() {
      this.$axios.get(`/api/user/${this.id}`)
        .then(res => {
          let result = res.data;
          this.user = result.data;
        });
    }
  },
  mounted() {
    this.getUser();
  }
}
</script>

<style lang="less" scoped>
.container {
  width: 1140px;
  margin: 0 auto;
  padding: 5px;

  .ivu-col {
    padding: 7px;

    .actions-buttons {
      text-align: center;

      .button {
        width: 134.5px;
      }
    }

    .emoji {
      display: inline-block;
      width: 30px;
      text-align: center;
      font-size: 1.2em;
    }
  }
}
</style>
