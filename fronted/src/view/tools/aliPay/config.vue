<template>
  <page-layout>
    <a-card>
      <a-row type="flex" justify="center">
        <a-col :xs="24" :sm="24" :md="24" :lg="24" :xl="22" :xxl="19">
          <a-form ref="ruleForm" :model="form" :rules="rules">
            <a-form-item label="appID" name="appId">
              <a-input v-model:value="form.appId" style="width: 40%" />
              <span style="color: #C0C0C0;margin-left: 10px;">应用APPID,收款账号既是APPID对应支付宝账号</span>
            </a-form-item>
            <a-form-item label="商家账号" name="sysServiceProviderId">
              <a-input v-model:value="form.sysServiceProviderId" style="width: 40%;" />
              <span style="color: #C0C0C0;margin-left: 10px;">商家账号</span>
            </a-form-item>
            <a-form-item label="商户私钥" name="privateKey">
              <a-textarea v-model:value="form.privateKey" type="password" style="width: 40%;" />
              <span style="color: #C0C0C0;margin-left: 10px;">商户私钥，你的PKCS8格式RSA2私钥</span>
            </a-form-item>
            <a-form-item label="支付宝公钥" name="publicKey">
              <a-textarea v-model:value="form.publicKey" type="password" style="width: 40%;" />
              <span style="color: #C0C0C0;margin-left: 10px;">支付宝公钥</span>
            </a-form-item>
            <a-form-item label="回调地址" name="returnUrl">
              <a-input v-model:value="form.returnUrl" style="width: 40%;" />
              <span style="color: #C0C0C0;margin-left: 10px;">订单完成后返回的地址</span>
            </a-form-item>
            <a-form-item label="异步通知" name="notifyUrl">
              <a-input v-model:value="form.notifyUrl" style="width: 40%;" />
              <span style="color: #C0C0C0;margin-left: 10px;">支付结果异步通知地址</span>
            </a-form-item>
            <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
              <a-button :loading="loading" size="medium" type="primary" @click="onSubmit">保存配置</a-button>
            </a-form-item>
          </a-form>
        </a-col>
      </a-row>
    </a-card>
  </page-layout>
</template>

<script>
import { get, update } from '@/api/tools/alipay'
import { message } from 'ant-design-vue';
const sendKey = "sendKey";
export default {
  name: 'Config',
  data() {
    return {
      loading: false,
      form: { appId: '', sysServiceProviderId: '', privateKey: '', publicKey: '', returnUrl: '', notifyUrl: '' },
      rules: {
        appId: [
          { required: true, message: '请输入appID', trigger: 'blur' }
        ],
        sysServiceProviderId: [
          { required: true, message: '请输入商家账号', trigger: 'blur' }
        ],
        privateKey: [
          { required: true, message: '商户私钥不能为空', trigger: 'blur' }
        ],
        publicKey: [
          { required: true, message: '支付宝公钥不能为空', trigger: 'blur' }
        ],
        returnUrl: [
          { required: true, message: '回调地址不能为空', trigger: 'blur' }
        ],
        notifyUrl: [
          { required: true, message: '回调地址不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      get().then(res => {
        this.form = res
      })
    },
      onSubmit() {
      this.$refs.ruleForm
        .validate()
        .then(() => {
          message.loading({
            content: "提交中...",
            key: sendKey,
          });
          update(this.form).then((response) => {
            if (response.success) {
              message.success({ content: '保存成功', key: sendKey, duration: 1 }).then(() => {
              });
            } else {
              message.error({ content: '失败', key: sendKey, duration: 1 }).then(() => {
              });
            }
          })
          console.log("values", this.form);
        })
        .catch(error => {
          console.log("error", error);
        });
    },
    doSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.loading = true
          update(this.form).then(res => {
            this.$notify({
              title: '修改成功',
              type: 'success',
              duration: 2500
            })
            this.loading = false
          }).catch(err => {
            this.loading = false
            console.log(err.response.data.message)
          })
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style scoped></style>
