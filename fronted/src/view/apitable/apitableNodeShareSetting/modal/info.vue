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


                                                                  <a-form-item label="数据id" name="dstId">
            <a-input v-model:value="formState.dstId" />
     </a-form-item>


                                                                  <a-form-item label="节点id" name="nodeId">
            <a-input v-model:value="formState.nodeId" />
     </a-form-item>


                                                                  <a-form-item label="视图id" name="viewId">
            <a-input v-model:value="formState.viewId" />
     </a-form-item>


                                                                  <a-form-item label="分享标识" name="shareId">
            <a-input v-model:value="formState.shareId" />
     </a-form-item>


                                                                  <a-form-item label="分享状态 (0: off, 1: on)" name="isEnabled">
            <a-input v-model:value="formState.isEnabled" />
     </a-form-item>


                                                                  <a-form-item label="允许保存 (0: No, 1: Yes)" name="allowSave">
            <a-input v-model:value="formState.allowSave" />
     </a-form-item>


                                                                  <a-form-item label="允许编辑(0: No, 1: Yes)" name="allowEdit">
            <a-input v-model:value="formState.allowEdit" />
     </a-form-item>


                                                                  <a-form-item label="分享属性" name="props">
            <a-input v-model:value="formState.props" />
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
                                                                            
            formState.viewId = props.record.viewId
                                                                            
            formState.shareId = props.record.shareId
                                                                            
            formState.isEnabled = props.record.isEnabled
                                                                            
            formState.allowSave = props.record.allowSave
                                                                            
            formState.allowEdit = props.record.allowEdit
                                                                            
            formState.props = props.record.props
                                                                            
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