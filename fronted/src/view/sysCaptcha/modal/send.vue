<template>
  <a-modal :visible="visible" title="新增【请填写功能名称】" cancelText="取消" okText="提交" @ok="submit" @cancel="cancel">
    <a-form ref="formRef" :model="formState" :rules="formRules" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-item label="邮箱" name="email">
        <a-input v-model:value="formState.email" />
      </a-form-item>
      <a-form-item label="手机号(暂无)" name="phone">
        <a-input v-model:value="formState.phone" />
      </a-form-item>
      <a-form-item label="过期时长" name="expired">
        <a-input v-model:value="formState.expired" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import {  send } from "@/api/module/sysCaptcha";
import { defineComponent, reactive, ref, toRaw } from "vue";
export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
  },
  emit: ["close"],
  setup(props, context) {
    const formRef = ref();
    const formState = reactive({
      //  sort: 0,
      //   enable: 1,
    });


    const formRules = {
      email: [{ required: true, message: '请输入名称', trigger: 'blur' }],
    };

    const saveKey = "send";

    const submit = (e) => {
      message.loading({ content: '提交中...', key: saveKey });
      formRef.value
        .validate()
        .then(() => {
          send(toRaw(formState)).then((response) => {
            if (response.success) {
              message.success({ content: '成功', key: saveKey, duration: 1 }).then(() => {
                cancel();
              });
            } else {
              message.success({ content: '保存失败', key: saveKey, duration: 1 }).then(() => {
                cancel();
              });
            }
          });
        })
        .catch(error => {
          console.log('error', error);
        });
    };

    const cancel = (e) => {
      formRef.value.resetFields();
      context.emit("close", false);
    };

    return {
      submit,
      cancel,
      formRef,
      formState,
      formRules,

      labelCol: { span: 6 },
      wrapperCol: { span: 18 },
    };
  },
});
</script>
