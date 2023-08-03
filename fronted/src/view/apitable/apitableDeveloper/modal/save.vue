<template>
  <a-modal
    :visible="visible"
    title="新增格协作者"
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

                                                                            
                          
          <a-form-item label="表格id" name="dstId">
                  <a-input v-model:value = "formState.dstId" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="用户id" name="userId">
                  <a-input v-model:value = "formState.userId" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="管理" name="allowManage">
                  <a-input v-model:value = "formState.allowManage" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="查看" name="allowWatch">
                  <a-input v-model:value = "formState.allowWatch" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="编辑" name="allowEdit">
                  <a-input v-model:value = "formState.allowEdit" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="保存记录" name="allowSave">
                  <a-input v-model:value = "formState.allowSave" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="开发者平台的唯一令牌" name="apiKey">
                  <a-input v-model:value = "formState.apiKey" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="是否开启" name="enable">
                  <a-input v-model:value = "formState.enable" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="排序" name="sort">
                  <a-input v-model:value = "formState.sort" />
           </a-form-item>

                                                                                                                                        
                          
          <a-form-item label="租户编号" name="tenantId">
                  <a-input v-model:value = "formState.tenantId" />
           </a-form-item>

            


    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { save } from "@/api/module/apitableDeveloper";
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