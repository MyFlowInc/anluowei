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


                                                                  <a-form-item label="应用ID" name="appId">
            <a-input v-model:value="formState.appId" />
     </a-form-item>


                                                                  <a-form-item label="编码" name="charset">
            <a-input v-model:value="formState.charset" />
     </a-form-item>


                                                                  <a-form-item label="类型 固定格式json" name="format">
            <a-input v-model:value="formState.format" />
     </a-form-item>


                                                                  <a-form-item label="网关地址" name="gatewayUrl">
            <a-input v-model:value="formState.gatewayUrl" />
     </a-form-item>


                                                                  <a-form-item label="异步回调" name="notifyUrl">
            <a-input v-model:value="formState.notifyUrl" />
     </a-form-item>


                                                                  <a-form-item label="私钥" name="privateKey">
            <a-input v-model:value="formState.privateKey" />
     </a-form-item>


                                                                  <a-form-item label="公钥" name="publicKey">
            <a-input v-model:value="formState.publicKey" />
     </a-form-item>


                                                                  <a-form-item label="回调地址" name="returnUrl">
            <a-input v-model:value="formState.returnUrl" />
     </a-form-item>


                                                                  <a-form-item label="签名方式" name="signType">
            <a-input v-model:value="formState.signType" />
     </a-form-item>


                                                                  <a-form-item label="商户号" name="sysServiceProviderId">
            <a-input v-model:value="formState.sysServiceProviderId" />
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
                                                                            
            formState.appId = props.record.appId
                                                                            
            formState.charset = props.record.charset
                                                                            
            formState.format = props.record.format
                                                                            
            formState.gatewayUrl = props.record.gatewayUrl
                                                                            
            formState.notifyUrl = props.record.notifyUrl
                                                                            
            formState.privateKey = props.record.privateKey
                                                                            
            formState.publicKey = props.record.publicKey
                                                                            
            formState.returnUrl = props.record.returnUrl
                                                                            
            formState.signType = props.record.signType
                                                                            
            formState.sysServiceProviderId = props.record.sysServiceProviderId
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