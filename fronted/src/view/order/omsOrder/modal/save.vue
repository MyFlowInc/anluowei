<template>
  <a-modal :visible="visible" title="新增订单" cancelText="取消" okText="提交" @ok="submit" @cancel="cancel">
    <a-form ref="formRef" :model="formState" :rules="formRules" :label-col="labelCol" :wrapper-col="wrapperCol">

      <a-form-item label="订单id" name="id">
        <a-input v-model:value="formState.id" />
      </a-form-item>

      <a-form-item label="订单编号" name="orderSn">
        <a-input v-model:value="formState.orderSn" />
      </a-form-item>

      <a-form-item label="订单总金额" name="totalAmount">
        <a-input v-model:value="formState.totalAmount" />
      </a-form-item>

      <a-form-item label="支付方式：0->未支付；1->支付宝；2->微信" name="payType">
        <a-input v-model:value="formState.payType" />
      </a-form-item>

      <a-form-item label="订单来源：0->PC订单；1->app订单" name="sourceType">
        <a-input v-model:value="formState.sourceType" />
      </a-form-item>

      <a-form-item label="订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单" name="status">
        <a-input v-model:value="formState.status" />
      </a-form-item>

      <a-form-item label="订单备注" name="note">
        <a-input v-model:value="formState.note" />
      </a-form-item>

      <a-form-item label="支付时间" name="paymentTime">
        <a-input v-model:value="formState.paymentTime" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { save } from "@/api/order/omsOrder";
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
      name: [{ required: true, message: '请输入名称', trigger: 'blur' }],
      code: [{ required: true, message: '请输入编号', trigger: 'blur' }]
    };

    const saveKey = "save";

    const submit = (e) => {
      message.loading({ content: '提交中...', key: saveKey });
      formRef.value
        .validate()
        .then(() => {
          save(toRaw(formState)).then((response) => {
            if (response.success) {
              message.success({ content: '保存成功', key: saveKey, duration: 1 }).then(() => {
                cancel();
              });
            } else {
              message.success({ content: '保存失败', key: saveKey, duration: 1 }).then(() => {
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
