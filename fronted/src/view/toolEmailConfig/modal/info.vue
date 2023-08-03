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
                      <a-form-item label="ID" name="configId">
            <a-input v-model:value="formState.configId" />
     </a-form-item>


                                                                  <a-form-item label="收件人" name="fromUser">
            <a-input v-model:value="formState.fromUser" />
     </a-form-item>


                                                                  <a-form-item label="邮件服务器SMTP地址" name="host">
            <a-input v-model:value="formState.host" />
     </a-form-item>


                                                                  <a-form-item label="密码" name="pass">
            <a-input v-model:value="formState.pass" />
     </a-form-item>


                                                                  <a-form-item label="端口" name="port">
            <a-input v-model:value="formState.port" />
     </a-form-item>


                                                                  <a-form-item label="发件者用户名" name="user">
            <a-input v-model:value="formState.user" />
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
                        
            formState.configId = props.record.configId
                                                                            
            formState.fromUser = props.record.fromUser
                                                                            
            formState.host = props.record.host
                                                                            
            formState.pass = props.record.pass
                                                                            
            formState.port = props.record.port
                                                                            
            formState.user = props.record.user
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