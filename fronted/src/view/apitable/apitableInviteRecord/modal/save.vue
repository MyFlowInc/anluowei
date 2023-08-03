<template>
  <a-modal
    :visible="visible"
    title="新增邀请同意记录"
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


                        
                          
          <a-form-item label="Primary Key" name="id">
                  <a-input v-model:value = "formState.id" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="空间id" name="spaceId">
                  <a-input v-model:value = "formState.spaceId" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="表格id" name="dstId">
                  <a-input v-model:value = "formState.dstId" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="邀请id" name="inviter">
                  <a-input v-model:value = "formState.inviter" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="成员id" name="accepter">
                  <a-input v-model:value = "formState.accepter" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="0: 邀请; 1: 文件导出; 2: 邀请链接" name="type">
                  <a-input v-model:value = "formState.type" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="内容" name="content">
                  <a-input v-model:value = "formState.content" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="排序" name="sort">
                  <a-input v-model:value = "formState.sort" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="忽略消息" name="ignoreMsg">
                  <a-input v-model:value = "formState.ignoreMsg" />
           </a-form-item>

                                                                                                                
                          
          <a-form-item label="$column.columnComment" name="deleted">
                  <a-input v-model:value = "formState.deleted" />
           </a-form-item>

                                                                                                    
                          
          <a-form-item label="租户编号" name="tenantId">
                  <a-input v-model:value = "formState.tenantId" />
           </a-form-item>

            


    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { save } from "@/api/module/apitableInviteRecord";
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