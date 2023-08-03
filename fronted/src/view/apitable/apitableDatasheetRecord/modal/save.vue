<template>
  <a-modal
    :visible="visible"
    title="新增数据单记录"
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


                        
                          
          <a-form-item label="Primary key" name="id">
                  <a-input v-model:value = "formState.id" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="操作id" name="recordId">
                  <a-input v-model:value = "formState.recordId" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="表格id" name="dstId">
                  <a-input v-model:value = "formState.dstId" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="一行记录数据(对应于每个字段)" name="data">
                  <a-input v-model:value = "formState.data" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="版本历史" name="revisionHistory">
                  <a-input v-model:value = "formState.revisionHistory" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="版本号" name="revision">
                  <a-input v-model:value = "formState.revision" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="字段更新信息" name="fieldUpdatedInfo">
                  <a-input v-model:value = "formState.fieldUpdatedInfo" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="删除标识" name="deleted">
                  <a-input v-model:value = "formState.deleted" />
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
import { save } from "@/api/module/apitableDatasheetRecord";
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