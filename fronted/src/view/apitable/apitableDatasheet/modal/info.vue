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


                                                                  <a-form-item label="Custom ID" name="dstId">
            <a-input v-model:value="formState.dstId" />
     </a-form-item>


                                                                  <a-form-item label="节点id" name="nodeId">
            <a-input v-model:value="formState.nodeId" />
     </a-form-item>


                                                                  <a-form-item label="表格名" name="dstName">
            <a-input v-model:value="formState.dstName" />
     </a-form-item>


                                                                  <a-form-item label="工作空间" name="spaceId">
            <a-input v-model:value="formState.spaceId" />
     </a-form-item>


                                                                  <a-form-item label="版本号" name="revision">
            <a-input v-model:value="formState.revision" />
     </a-form-item>


                                                                  <a-form-item label="删除表示(0:No,1:Yes)" name="deleted">
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
                                                                            
            formState.dstId = props.record.dstId
                                                                            
            formState.nodeId = props.record.nodeId
                                                                            
            formState.dstName = props.record.dstName
                                                                            
            formState.spaceId = props.record.spaceId
                                                                            
            formState.revision = props.record.revision
                                                                            
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