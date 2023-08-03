<template>
  <a-modal
    :visible="visible"
    title="修改任务"
    cancelText="取消"
    okText="提交"
    @ok="submit"
    @cancel="cancel"
  >
    <a-form
       ref="formRef"
      :model="formState"
      :label-col="labelCol"
      :wrapper-col="wrapperCol"
    >
    <a-form-item ref="tableName" label="表名" name="tableName">
        <a-input v-model:value="formState.tableName" />
      </a-form-item>
      <a-form-item ref="packageName" label="包名" name="packageName">
        <a-input v-model:value="formState.packageName" />
      </a-form-item>
      <a-form-item ref="functionAuthor" label="作者" name="functionAuthor">
        <a-input v-model:value="formState.functionAuthor" />
      </a-form-item>
      <a-form-item ref="className" label="类名" name="className">
        <a-input v-model:value="formState.className" />
      </a-form-item>
  
      <a-form-item ref="createTime" label="创建时间" name="createTime">
        <a-textarea v-model:value="formState.createTime" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { edit } from "@/api/module/generate";
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
        formState.tableName = props.record.tableName
        formState.packageName = props.record.packageName
        formState.functionAuthor = props.record.functionAuthor
        formState.className = props.record.className
        formState.createTime = props.record.createTime
    })



    const editKey = "edit";

    const submit = (e) => {
      message.loading({ content: '提交中...', key: editKey });
      formRef.value
        .validate()
        .then(() => {
          edit(toRaw(formState)).then((response)=>{
              if(response.success){
                message.success({ content: '保存成功', key: editKey, duration: 1 }).then(()=>{
                  cancel();
                });
              }else{
                message.success({ content: '保存失败', key: editKey, duration: 1 }).then(()=>{
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
      
      labelCol: { span: 6 },
      wrapperCol: { span: 18 },
    };
  },
});
</script>