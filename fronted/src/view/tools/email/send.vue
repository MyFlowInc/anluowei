<template>
  <page-layout>
    <a-card>
      <a-row type="flex" justify="center">
        <a-col :xs="24" :sm="24" :md="24" :lg="24" :xl="22" :xxl="19">
          <a-form ref="ruleForm" :model="form" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">
            <a-form-item label="邮件标题" name="subject">
              <a-input v-model:value="form.subject" placeholder="请输入邮件标题，标题不能为空" />
            </a-form-item>
            <a-form-item label="收件地址" name="tos">
              <a-input v-model:value="form.tos" placeholder="请输入收件地址，多个地址英文逗号,隔开" />
            </a-form-item>
            <a-form-item name="content" label="邮箱内容">
              <a-textarea v-model:value="form.content" :rows="10" />
            </a-form-item>
            <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
              <a-button type="primary" @click="onSubmit"> 发送 </a-button>
              <a-button style="margin-left: 10px" @click="resetForm">
                重置
              </a-button>
            </a-form-item>
          </a-form>
        </a-col>
      </a-row>
    </a-card>
  </page-layout>
</template>

<script>
import { send } from "@/api/tools/email";
import { message } from "ant-design-vue";
import { toRaw } from "vue";

const sendKey = "sendKey";

export default {
  data() {
    return {
      labelCol: { xs: 4, sm: 3, md: 3, lg: 3, xl: 2, xxl: 3 },
      wrapperCol: { xs: 20, sm: 21, md: 21, lg: 21, xl: 20, xxl: 17 },
      other: "",
      form: { subject: undefined, tos: "", content: undefined },
      rules: {
        subject: [{ required: true, message: "标题不能为空", trigger: "blur" }],
        tos: [{ required: true, message: "收件人不能为空", trigger: "blur" }],
        content: [
          { required: true, message: "请输入邮件内容", trigger: "blur" },
        ],
      },
    };
  },

  methods: {
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },
    onSubmit() {
      this.$refs.ruleForm
        .validate()
        .then(() => {
          message.loading({
            content: "提交中...",
            key: sendKey,
          });
          this.form.tos = this.form.tos.split(',');
          send(toRaw(this.form)).then((response) => {
            if (response.success) {
              message
                .success({ content: "发送成功", key: sendKey, duration: 1 })
                .then(() => {
                  this.resetForm();
                });
            } else {
              message
                .error({ content: "发送失败", key: sendKey, duration: 1 })
                .then(() => {
                  this.resetForm();
                });
            }
          });
          console.log("values", this.form);
        })
        .catch((error) => {
          console.log("error", error);
        });
    },
  },
};
</script>

<style scoped>
.editor {
  text-align: left;
  margin: 20px;
  width: 730px;
}

::v-deep .w-e-text-container {
  height: 360px !important;
}
</style>
