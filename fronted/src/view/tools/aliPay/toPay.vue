<template>
  <page-layout>
    <a-card>
      <a-row type="flex" justify="center">
        <a-col :xs="24" :sm="24" :md="24" :lg="24" :xl="22" :xxl="19">
          <a-form ref="ruleForm" :model="form" :rules="rules" style="margin-top: 6px" size="small" label-width="90px">
            <a-form-item label="商品名称" name="subject">
              <a-input v-model:value="form.subject" style="width: 35%" />
              <span style="color: #c0c0c0; margin-left: 10px"></span>
            </a-form-item>
            <a-form-item label="商品价格" name="totalAmount">
              <a-input v-model:value="form.totalAmount" style="width: 35%" />
              <span style="color: #c0c0c0; margin-left: 10px">测试允许区间(0,5000]</span>
            </a-form-item>
            <a-form-item label="商品描述" name="body">
              <a-input v-model:value="form.body" style="width: 35%" rows="8" type="textarea" />
              <span style="color: #c0c0c0; margin-left: 10px"></span>
            </a-form-item>
            <a-form-item label="">
              <a-button :loading="loading" size="medium" type="primary" @click="doSubmit">去支付</a-button>
            </a-form-item>
          </a-form>
        </a-col></a-row></a-card></page-layout>
</template>

<script>
import { message } from 'ant-design-vue';
import { toAliPay } from "@/api/tools/alipay";
export default {
  data() {
    return {
      url: "",
      // 新窗口的引用
      newWin: null,
      loading: false,
      form: { subject: "", totalAmount: "", body: "" },
      rules: {
        subject: [
          { required: true, message: "商品名称不能为空", trigger: "blur" },
        ],
        totalAmount: [
          { required: true, message: "商品价格不能为空", trigger: "blur" },
        ],
        body: [
          { required: true, message: "商品描述不能为空", trigger: "blur" },
        ],
      },
    };
  },
  watch: {
    url(newVal, oldVal) {
      if (newVal && this.newWin) {
        this.newWin.sessionStorage.clear();
        this.newWin.location.href = newVal;
        // 重定向后把url和newWin重置
        this.url = "";
        this.newWin = null;
      }
    },
  },
  methods: {
    doSubmit() {
      this.$refs.ruleForm.validate().then(() => {
        message.loading({
          content: "提交中...",
        });
      });
      this.loading = true;
      // 先打开一个空的新窗口，再请求
      this.newWin = window.open();
      let url = "";
      if (/(Android)/i.test(navigator.userAgent)) {
        // 判断是否为Android手机
        url = "aliPay/toPayAsWeb";
      } else if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
        // 判断是否为苹果手机
        url = "aliPay/toPayAsWeb";
      } else {
        console.log(122112)
        url = "aliPay/toPayAsPC";
      }
      toAliPay(url, this.form)
        .then((res) => {
          this.loading = false;
          this.url = res;
        })
        .catch((err) => {
          this.loading = false;
          console.log(err.response.data.message);
        });
    },
  },
};

</script>

<style scoped></style>
