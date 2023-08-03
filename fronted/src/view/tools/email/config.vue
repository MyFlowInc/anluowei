<template>
  <page-layout>
    <a-card>
      <a-row type="flex" justify="center">
        <a-col :xs="24" :sm="24" :md="24" :lg="24" :xl="22" :xxl="19">
          <a-form ref="ruleForm" :model="form" :rules="rules">
            <a-form-item label="发件人邮箱" name="fromUser">
              <a-input v-model:value="form.fromUser" style="width: 40%" />
              <span style="color: #C0C0C0;margin-left: 10px;">Sender mailbox</span>
            </a-form-item>
            <a-form-item label="发件用户名" name="user">
              <a-input v-model:value="form.user" style="width: 40%;" />
              <span style="color: #C0C0C0;margin-left: 10px;">Sender usernamex</span>
            </a-form-item>
            <a-form-item label="邮箱密码" name="pass">
              <a-input v-model:value="form.pass" type="password" style="width: 40%;" />
              <span style="color: #C0C0C0;margin-left: 10px;">email Password</span>
            </a-form-item>
            <a-form-item label="SMTP地址" name="host">
              <a-input v-model:value="form.host" style="width: 40%;" />
              <span style="color: #C0C0C0;margin-left: 10px;">SMTP address</span>
            </a-form-item>
            <a-form-item label="SMTP端口" name="port">
              <a-input v-model:value="form.port" style="width: 40%;" />
              <span style="color: #C0C0C0;margin-left: 10px;">SMTP port</span>
            </a-form-item>
            <a-form-item label="">
              <a-button :loading="loading" size="medium" type="primary" @click="onSubmit">保存配置</a-button>
            </a-form-item>
          </a-form>
        </a-col></a-row></a-card></page-layout>
</template>

<script>
import { get, update } from '@/api/tools/email'
import { message } from 'ant-design-vue';
const sendKey = "sendKey";
export default {
  name: 'Config',
  data() {
    return {
      loading: false, form: { id: 1, fromUser: '', user: '', pass: '', host: '', port: '', sslEnable: '' },
      rules: {
        fromUser: [
          { required: true, message: '请输入发件人邮箱', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
        ],
        user: [
          { required: true, message: '请输入发件用户名', trigger: 'blur' }
        ],
        pass: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
        host: [
          { required: true, message: 'SMTP地址不能为空', trigger: 'blur' }
        ],
        port: [
          { required: true, message: 'SMTP端口不能为空', trigger: 'blur' }
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
