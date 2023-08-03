<template>
  <a-modal
    :visible="visible"
    title="新增"
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
    <a-form-item ref="name" label="应用名称" name="name">
        <a-input v-model:value="formState.name" />
      </a-form-item>
      <a-form-item ref="secret" label="密钥" name="secret">
        <a-input v-model:value="formState.secret" />
      </a-form-item>
      <a-form-item ref="agentId" label="agentId" name="agentId">
        <a-textarea v-model:value="formState.agentId" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { save } from "@/api/module/qywx-app";
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
    
    const formState = reactive({});

    const formRules = {
      name: [ { required: true, message: '请输入', trigger: 'blur'} ],
      agentId: [ { required: true, message: '请输入', trigger: 'blur'} ],
      secret: [ { required: true, message: '请输入', trigger: 'blur'} ]
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