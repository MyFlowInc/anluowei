<template>
  <a-modal
    :visible="visible"
    title="新增订单退货申请"
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
                  <a-input v-model:value = "formState.id" />
           </a-form-item>



          <a-form-item label="订单id" name="orderId">
                  <a-input v-model:value = "formState.orderId" />
           </a-form-item>



          <a-form-item label="收货地址表id" name="companyAddressId">
                  <a-input v-model:value = "formState.companyAddressId" />
           </a-form-item>



          <a-form-item label="退货商品id" name="productId">
                  <a-input v-model:value = "formState.productId" />
           </a-form-item>



          <a-form-item label="订单编号" name="orderSn">
                  <a-input v-model:value = "formState.orderSn" />
           </a-form-item>



          <a-form-item label="会员用户名" name="memberUsername">
                  <a-input v-model:value = "formState.memberUsername" />
           </a-form-item>



          <a-form-item label="退款金额" name="returnAmount">
                  <a-input v-model:value = "formState.returnAmount" />
           </a-form-item>



          <a-form-item label="退货人姓名" name="returnName">
                  <a-input v-model:value = "formState.returnName" />
           </a-form-item>



          <a-form-item label="退货人电话" name="returnPhone">
                  <a-input v-model:value = "formState.returnPhone" />
           </a-form-item>



          <a-form-item label="申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝" name="status">
                  <a-input v-model:value = "formState.status" />
           </a-form-item>



          <a-form-item label="处理时间" name="handleTime">
                  <a-input v-model:value = "formState.handleTime" />
           </a-form-item>



          <a-form-item label="商品图片" name="productPic">
                  <a-input v-model:value = "formState.productPic" />
           </a-form-item>



          <a-form-item label="商品名称" name="productName">
                  <a-input v-model:value = "formState.productName" />
           </a-form-item>



          <a-form-item label="商品品牌" name="productBrand">
                  <a-input v-model:value = "formState.productBrand" />
           </a-form-item>



          <a-form-item label="商品销售属性：颜色：红色；尺码：xl;" name="productAttr">
                  <a-input v-model:value = "formState.productAttr" />
           </a-form-item>



          <a-form-item label="退货数量" name="productCount">
                  <a-input v-model:value = "formState.productCount" />
           </a-form-item>



          <a-form-item label="商品单价" name="productPrice">
                  <a-input v-model:value = "formState.productPrice" />
           </a-form-item>



          <a-form-item label="商品实际支付单价" name="productRealPrice">
                  <a-input v-model:value = "formState.productRealPrice" />
           </a-form-item>



          <a-form-item label="原因" name="reason">
                  <a-input v-model:value = "formState.reason" />
           </a-form-item>



          <a-form-item label="描述" name="description">
                  <a-input v-model:value = "formState.description" />
           </a-form-item>



          <a-form-item label="凭证图片，以逗号隔开" name="proofPics">
                  <a-input v-model:value = "formState.proofPics" />
           </a-form-item>



          <a-form-item label="处理备注" name="handleNote">
                  <a-input v-model:value = "formState.handleNote" />
           </a-form-item>



          <a-form-item label="处理人员" name="handleMan">
                  <a-input v-model:value = "formState.handleMan" />
           </a-form-item>



          <a-form-item label="收货人" name="receiveMan">
                  <a-input v-model:value = "formState.receiveMan" />
           </a-form-item>



          <a-form-item label="收货时间" name="receiveTime">
                  <a-input v-model:value = "formState.receiveTime" />
           </a-form-item>



          <a-form-item label="收货备注" name="receiveNote">
                  <a-input v-model:value = "formState.receiveNote" />
           </a-form-item>



          <a-form-item label="删除表示(0:No,1:Yes)" name="deleted">
                  <a-input v-model:value = "formState.deleted" />
           </a-form-item>



          <a-form-item label="租户编号" name="tenantId">
                  <a-input v-model:value = "formState.tenantId" />
           </a-form-item>




    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { save } from "@/api/order/omsOrderReturnApply";
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
