<template>
  <a-modal :visible="visible" title="新增【请填写功能名称】" cancelText="取消" okText="提交" @ok="submit" @cancel="cancel">
    <a-form ref="formRef" :model="formState" :rules="formRules" :label-col="labelCol" :wrapper-col="wrapperCol">

      <a-form-item label="邮箱" name="email">
        <a-input v-model:value="formState.email" />
      </a-form-item>

      <a-form-item label="手机号" name="phone">
        <a-input v-model:value="formState.phone" />
      </a-form-item>

      <a-form-item label="验证码" name="captcha">
        <a-input v-model:value="formState.captcha" />
      </a-form-item>



      <a-form-item label="验证类型 0:验证码验证 1:sign验证" name="type">
        <a-input v-model:value="formState.type" />
      </a-form-item>



      <a-form-item label="验证结果 0:未验证 1:验证成功 2:验证失败" name="validated">
        <a-input v-model:value="formState.validated" />
      </a-form-item>



      <a-form-item label="过期时长" name="expired">
        <a-input v-model:value="formState.expired" />
      </a-form-item>



      <a-form-item label="1:Delete，0:Not Deleted" name="deleted">
        <a-input v-model:value="formState.deleted" />
      </a-form-item>



      <a-form-item label="排序" name="sort">
        <a-input v-model:value="formState.sort" />
      </a-form-item>



      <a-form-item label="租户编号" name="tenantId">
        <a-input v-model:value="formState.tenantId" />
      </a-form-item>




    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { save } from "@/api/module/sysCaptcha";
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
      name: [{ required: true, message: '请输入名称', trigger: 'blur' }],
      code: [{ required: true, message: '请输入编号', trigger: 'blur' }]
    };

    const saveKey = "save";

    const submit = (e) => {
      message.loading({ content: '提交中...', key: saveKey });
      formRef.value
        .validate()
        .then(() => {
          save(toRaw(formState)).then((response) => {
            if (response.success) {
              message.success({ content: '保存成功', key: saveKey, duration: 1 }).then(() => {
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
