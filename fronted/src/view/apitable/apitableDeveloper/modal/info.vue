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
                      <a-form-item label="Primary Key" name="id">
            <a-input v-model:value="formState.id" />
     </a-form-item>


                                                                  <a-form-item label="表格id" name="dstId">
            <a-input v-model:value="formState.dstId" />
     </a-form-item>


                                                                  <a-form-item label="用户id" name="userId">
            <a-input v-model:value="formState.userId" />
     </a-form-item>


                                                                  <a-form-item label="管理" name="allowManage">
            <a-input v-model:value="formState.allowManage" />
     </a-form-item>


                                                                  <a-form-item label="查看" name="allowWatch">
            <a-input v-model:value="formState.allowWatch" />
     </a-form-item>


                                                                  <a-form-item label="编辑" name="allowEdit">
            <a-input v-model:value="formState.allowEdit" />
     </a-form-item>


                                                                  <a-form-item label="保存记录" name="allowSave">
            <a-input v-model:value="formState.allowSave" />
     </a-form-item>


                                                                  <a-form-item label="开发者平台的唯一令牌" name="apiKey">
            <a-input v-model:value="formState.apiKey" />
     </a-form-item>


                                                                  <a-form-item label="是否开启" name="enable">
            <a-input v-model:value="formState.enable" />
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
                                                                            
            formState.dstId = props.record.dstId
                                                                            
            formState.userId = props.record.userId
                                                                            
            formState.allowManage = props.record.allowManage
                                                                            
            formState.allowWatch = props.record.allowWatch
                                                                            
            formState.allowEdit = props.record.allowEdit
                                                                            
            formState.allowSave = props.record.allowSave
                                                                            
            formState.apiKey = props.record.apiKey
                                                                            
            formState.enable = props.record.enable
                                                                            
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