<template>
  <a-modal
    :visible="visible"
    title="新增变更集收集"
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
             <a-input v-model:value ="formState.id" />
          </a-form-item>
                                                                                                           <a-form-item label="消息id" name="messageId">
             <a-input v-model:value ="formState.messageId" />
          </a-form-item>
                                                                                                           <a-form-item label="表格id" name="dstId">
             <a-input v-model:value ="formState.dstId" />
          </a-form-item>
                                                                                                           <a-form-item label="成员id" name="memberId">
             <a-input v-model:value ="formState.memberId" />
          </a-form-item>
                                                                                                           <a-form-item label="操作动作集合" name="operations">
             <a-input v-model:value ="formState.operations" />
          </a-form-item>
                                                                                                           <a-form-item label="版本号" name="revision">
             <a-input v-model:value ="formState.revision" />
          </a-form-item>
                                                                                                           <a-form-item label="删除表示(0:No,1:Yes)" name="deleted">
             <a-input v-model:value ="formState.deleted" />
          </a-form-item>
                                                                                                           <a-form-item label="排序" name="sort">
             <a-input v-model:value ="formState.sort" />
          </a-form-item>
                                                                                                                                                                       <a-form-item label="租户编号" name="tenantId">
             <a-input v-model:value ="formState.tenantId" />
          </a-form-item>
                       
    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { edit } from "@/api/module/apitableDatasheetChangeset";
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
                                                                                formState.messageId = props.record.messageId
                                                                                formState.dstId = props.record.dstId
                                                                                formState.memberId = props.record.memberId
                                                                                formState.operations = props.record.operations
                                                                                formState.revision = props.record.revision
                                                                                formState.deleted = props.record.deleted
                                                                                formState.sort = props.record.sort
                                                                                                                                            formState.tenantId = props.record.tenantId
                    })

    const formRules = {
      name: [ { required: true, message: '请输入名称', trigger: 'blur'} ],
      code: [ { required: true, message: '请输入编号', trigger: 'blur'} ]
    };

    const editKey = "add";

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
      formRules,
      
      labelCol: { span: 6 },
      wrapperCol: { span: 18 },
    };
  },
});
</script>