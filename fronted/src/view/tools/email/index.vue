<template>
  <page-layout>
    <a-card>
      <a-tabs>
        <a-tab-pane key="1" tab="邮箱配置">
       <Config />
        </a-tab-pane>
        <a-tab-pane key="2" tab="发送邮件">
          <Send/>
        </a-tab-pane>
        <a-tab-pane key="3" tab="使用说明">
          <div>
            <blockquote class="my-blockquote">邮件服务器配置</blockquote>
            <pre class="my-code">
 # 邮件服务器的SMTP地址，可选，默认为smtp
 # 邮件服务器的SMTP端口，可选，默认465或者25
 # 发件人（必须正确，否则发送失败）
 # 用户名，默认为发件人邮箱前缀
 # 密码（注意，某些邮箱需要为SMTP服务单独设置密码，如QQ和163等等）
 # 是否开启ssl，默认开启</pre>
            <blockquote class="my-blockquote">更多帮助</blockquote>
            <pre
              class="my-code">更多帮助请查看文档：<a style="color:#009688" href="http://hutool.mydoc.io/#text_319499" target="_black">hutool工具包</a></pre>
          </div>
        </a-tab-pane>
      </a-tabs></a-card></page-layout>
</template>

<script>
import Config from "./config";
import Send from './send'
export default {
  name: "Email",
  components: { Config,Send },
  data() {
    return {
      activeName: "second",
    };
  },
};
</script>

