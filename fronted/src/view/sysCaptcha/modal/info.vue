<template>
  <a-modal
    :visible="visible"
    title="查看详情"
    cancelText="取消"
    okText="确定"
    @ok="submit"
    @cancel="cancel"
  >
    <a-form
       ref="formRef"
      :model="formState"
      :rules="formRules"
      :label-col="labelCol"
      :wrapper-col="wrapperCol"
    >
                      <a-form-item label="$column.columnComment" name="id">
            <a-input v-model:value="formState.id" />
     </a-form-item>


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
import { defineComponent, reactive, ref, toRaw, watch } from "vue";
export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
    record: {
      type: Object,
    }
  },
  emit: ["close"],
  setup(props, context) {

    const formRef = ref();
    
    const formState = reactive({});

    watch(props, (props) => {
                        
            formState.id = props.record.id
                                                                            
            formState.email = props.record.email
                                                                            
            formState.phone = props.record.phone
                                                                            
            formState.captcha = props.record.captcha
                                                                            
            formState.type = props.record.type
                                                                            
            formState.validated = props.record.validated
                                                                            
            formState.expired = props.record.expired
                                                                            
            formState.deleted = props.record.deleted
                                                                            
            formState.sort = props.record.sort
                                                                                                                                        
            formState.tenantId = props.record.tenantId
                })

    const formRules = {};

    const submit = (e) => {
      formRef.value.resetFields();
      context.emit("close", false);
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