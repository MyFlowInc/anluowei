<template>
  <a-modal
    :visible="visible"
    title="新增邮箱配置"
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

                                                                            
                          
          <a-form-item label="收件人" name="fromUser">
                  <a-input v-model:value = "formState.fromUser" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="邮件服务器SMTP地址" name="host">
                  <a-input v-model:value = "formState.host" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="密码" name="pass">
                  <a-input v-model:value = "formState.pass" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="端口" name="port">
                  <a-input v-model:value = "formState.port" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="发件者用户名" name="user">
                  <a-input v-model:value = "formState.user" />
           </a-form-item>

            


    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { save } from "@/api/module/toolEmailConfig";
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