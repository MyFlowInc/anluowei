<template>
  <a-modal
    :visible="visible"
    title="新增支付宝配置类"
    cancelText="取消"
    okText="提交"
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
                  <a-input v-model:value = "formState.configId" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="应用ID" name="appId">
                  <a-input v-model:value = "formState.appId" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="编码" name="charset">
                  <a-input v-model:value = "formState.charset" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="类型 固定格式json" name="format">
                  <a-input v-model:value = "formState.format" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="网关地址" name="gatewayUrl">
                  <a-input v-model:value = "formState.gatewayUrl" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="异步回调" name="notifyUrl">
                  <a-input v-model:value = "formState.notifyUrl" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="私钥" name="privateKey">
                  <a-input v-model:value = "formState.privateKey" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="公钥" name="publicKey">
                  <a-input v-model:value = "formState.publicKey" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="回调地址" name="returnUrl">
                  <a-input v-model:value = "formState.returnUrl" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="签名方式" name="signType">
                  <a-input v-model:value = "formState.signType" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="商户号" name="sysServiceProviderId">
                  <a-input v-model:value = "formState.sysServiceProviderId" />
           </a-form-item>

            


    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { save } from "@/api/module/toolAlipayConfig";
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
      name: [ { required: true, message: '请输入名称', trigger: 'blur'} ],
      code: [ { required: true, message: '请输入编号', trigger: 'blur'} ]
    };

    const saveKey = "save";

    const submit = (e) => {
      message.loading({ content: '提交中...', key: saveKey });
      formRef.value
        .validate()
        .then(() => {
          save(toRaw(formState)).then((response)=>{
              if(response.success){
                message.success({ content: '保存成功', key: saveKey, duration: 1 }).then(()=>{
                  cancel();
                });
              }else{
                message.success({ content: '保存失败', key: saveKey, duration: 1 }).then(()=>{
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