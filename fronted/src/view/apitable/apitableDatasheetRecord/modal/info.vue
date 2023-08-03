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
                      <a-form-item label="Primary key" name="id">
            <a-input v-model:value="formState.id" />
     </a-form-item>


                                                                  <a-form-item label="操作id" name="recordId">
            <a-input v-model:value="formState.recordId" />
     </a-form-item>


                                                                  <a-form-item label="表格id" name="dstId">
            <a-input v-model:value="formState.dstId" />
     </a-form-item>


                                                                  <a-form-item label="一行记录数据(对应于每个字段)" name="data">
            <a-input v-model:value="formState.data" />
     </a-form-item>


                                                                  <a-form-item label="版本历史" name="revisionHistory">
            <a-input v-model:value="formState.revisionHistory" />
     </a-form-item>


                                                                  <a-form-item label="版本号" name="revision">
            <a-input v-model:value="formState.revision" />
     </a-form-item>


                                                                  <a-form-item label="字段更新信息" name="fieldUpdatedInfo">
            <a-input v-model:value="formState.fieldUpdatedInfo" />
     </a-form-item>


                                                                  <a-form-item label="删除标识" name="deleted">
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
                                                                            
            formState.recordId = props.record.recordId
                                                                            
            formState.dstId = props.record.dstId
                                                                            
            formState.data = props.record.data
                                                                            
            formState.revisionHistory = props.record.revisionHistory
                                                                            
            formState.revision = props.record.revision
                                                                            
            formState.fieldUpdatedInfo = props.record.fieldUpdatedInfo
                                                                            
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