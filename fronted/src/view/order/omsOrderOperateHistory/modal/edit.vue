<template>
  <a-modal
    :visible="visible"
    title="新增订单操作历史记录"
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
             <a-input v-model:value ="formState.id" />
          </a-form-item>
                                                                                                           <a-form-item label="订单id" name="orderId">
             <a-input v-model:value ="formState.orderId" />
          </a-form-item>
                                                                                                           <a-form-item label="操作人：用户；系统；后台管理员" name="operateMan">
             <a-input v-model:value ="formState.operateMan" />
          </a-form-item>
                                                                                                                       <a-form-item label="订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单" name="orderStatus">
             <a-input v-model:value ="formState.orderStatus" />
          </a-form-item>
                                                                                                           <a-form-item label="备注" name="note">
             <a-input v-model:value ="formState.note" />
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
import { edit } from "@/api/module/omsOrderOperateHistory";
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
                                                                                formState.orderId = props.record.orderId
                                                                                formState.operateMan = props.record.operateMan
                                                                                            formState.orderStatus = props.record.orderStatus
                                                                                formState.note = props.record.note
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