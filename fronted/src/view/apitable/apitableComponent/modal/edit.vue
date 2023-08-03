<template>
  <a-modal
    :visible="visible"
    title="新增格组件"
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
                                                                                                           <a-form-item label="名称" name="name">
             <a-input v-model:value ="formState.name" />
          </a-form-item>
                                                                                                           <a-form-item label="英文名" name="nameEn">
             <a-input v-model:value ="formState.nameEn" />
          </a-form-item>
                                                                                                           <a-form-item label="组件内容" name="content">
             <a-input v-model:value ="formState.content" />
          </a-form-item>
                                                                                                           <a-form-item label="工作空间" name="spaceId">
             <a-input v-model:value ="formState.spaceId" />
          </a-form-item>
                                                                                                           <a-form-item label="版本号" name="revision">
             <a-input v-model:value ="formState.revision" />
          </a-form-item>
                                                                                                           <a-form-item label="排序" name="sort">
             <a-input v-model:value ="formState.sort" />
          </a-form-item>
                                                                                                           <a-form-item label="删除表示(0:No,1:Yes)" name="deleted">
             <a-input v-model:value ="formState.deleted" />
          </a-form-item>
                                                                                                                                                                       <a-form-item label="租户编号" name="tenantId">
             <a-input v-model:value ="formState.tenantId" />
          </a-form-item>
                       
    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { edit } from "@/api/module/apitableComponent";
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
                                                                                formState.name = props.record.name
                                                                                formState.nameEn = props.record.nameEn
                                                                                formState.content = props.record.content
                                                                                formState.spaceId = props.record.spaceId
                                                                                formState.revision = props.record.revision
                                                                                formState.sort = props.record.sort
                                                                                formState.deleted = props.record.deleted
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