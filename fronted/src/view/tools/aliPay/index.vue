<template>
  <page-layout>
    <a-card>
      <a-tabs>
        <a-tab-pane key="1" tab="支付配置">
          <Config />
        </a-tab-pane>
        <a-tab-pane key="2" tab="支付测试" force-render>
          <ToPay />
        </a-tab-pane>
        <a-tab-pane key="3" tab="使用说明" label="使用说明" name="third">
          <a-descriptions title="注意">
            <a-descriptions-item>测试所用参数都是沙箱环境，仅供测试使用，申请地址： <br />
              <a style="color: #00a0e9" href="https://openhome.alipay.com/platform/appDaily.htm?tab=info"
                target="_blank">支付宝开发平台</a>
              如需付款测试，请使用
              账号：uuxesw9745@sandbox.com
              密码与支付密码：111111
            </a-descriptions-item>
          </a-descriptions>
          <a-descriptions title="支付设置">
            <a-descriptions-item>
              // 支付提供两个接口，
              // PC端与手机端，并且在前端使用代码识别
              <br />
              if (/(Android)/i.test(navigator.userAgent)){ // 判断是否为Android手机
              url = "/aliPay/toPayAsWeb"
              }else if(/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)){ // 判断是否为苹果手机
              url = "/aliPay/toPayAsWeb"
              } else {
              url = "/aliPay/toPayAsPC"
              }
            </a-descriptions-item>
          </a-descriptions>
        </a-tab-pane>
      </a-tabs>
    </a-card></page-layout>
</template>
<script>
import Config from './config'
import ToPay from './toPay'
export default {
  name: 'AliPay',
  components: { Config, ToPay },
  data() {
    return {
      activeName: 'second'
    }
  }
}
</script>

<style scoped></style>
