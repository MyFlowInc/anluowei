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
                      <a-form-item label="订单id" name="id">
            <a-input v-model:value="formState.id" />
     </a-form-item>


                                                                  <a-form-item label="订单id" name="orderId">
            <a-input v-model:value="formState.orderId" />
     </a-form-item>


                                                                  <a-form-item label="订单编号" name="orderSn">
            <a-input v-model:value="formState.orderSn" />
     </a-form-item>


                                                                  <a-form-item label="$column.columnComment" name="productId">
            <a-input v-model:value="formState.productId" />
     </a-form-item>


                                                                  <a-form-item label="$column.columnComment" name="productPic">
            <a-input v-model:value="formState.productPic" />
     </a-form-item>


                                                                  <a-form-item label="$column.columnComment" name="productName">
            <a-input v-model:value="formState.productName" />
     </a-form-item>


                                                                  <a-form-item label="$column.columnComment" name="productBrand">
            <a-input v-model:value="formState.productBrand" />
     </a-form-item>


                                                                  <a-form-item label="$column.columnComment" name="productSn">
            <a-input v-model:value="formState.productSn" />
     </a-form-item>


                                                                  <a-form-item label="销售价格" name="productPrice">
            <a-input v-model:value="formState.productPrice" />
     </a-form-item>


                                                                  <a-form-item label="购买数量" name="productQuantity">
            <a-input v-model:value="formState.productQuantity" />
     </a-form-item>


                                                                  <a-form-item label="商品sku编号" name="productSkuId">
            <a-input v-model:value="formState.productSkuId" />
     </a-form-item>


                                                                  <a-form-item label="商品sku条码" name="productSkuCode">
            <a-input v-model:value="formState.productSkuCode" />
     </a-form-item>


                                                                  <a-form-item label="商品分类id" name="productCategoryId">
            <a-input v-model:value="formState.productCategoryId" />
     </a-form-item>


                                                                  <a-form-item label="商品促销名称" name="promotionName">
            <a-input v-model:value="formState.promotionName" />
     </a-form-item>


                                                                  <a-form-item label="商品促销分解金额" name="promotionAmount">
            <a-input v-model:value="formState.promotionAmount" />
     </a-form-item>


                                                                  <a-form-item label="优惠券优惠分解金额" name="couponAmount">
            <a-input v-model:value="formState.couponAmount" />
     </a-form-item>


                                                                  <a-form-item label="积分优惠分解金额" name="integrationAmount">
            <a-input v-model:value="formState.integrationAmount" />
     </a-form-item>


                                                                  <a-form-item label="该商品经过优惠后的分解金额" name="realAmount">
            <a-input v-model:value="formState.realAmount" />
     </a-form-item>


                                                                  <a-form-item label="$column.columnComment" name="giftIntegration">
            <a-input v-model:value="formState.giftIntegration" />
     </a-form-item>


                                                                  <a-form-item label="$column.columnComment" name="giftGrowth">
            <a-input v-model:value="formState.giftGrowth" />
     </a-form-item>


                                                                  <a-form-item label="商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]" name="productAttr">
            <a-input v-model:value="formState.productAttr" />
     </a-form-item>


                                                                  <a-form-item label="删除表示(0:No,1:Yes)" name="deleted">
            <a-input v-model:value="formState.deleted" />
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
                                                                            
            formState.orderId = props.record.orderId
                                                                            
            formState.orderSn = props.record.orderSn
                                                                            
            formState.productId = props.record.productId
                                                                            
            formState.productPic = props.record.productPic
                                                                            
            formState.productName = props.record.productName
                                                                            
            formState.productBrand = props.record.productBrand
                                                                            
            formState.productSn = props.record.productSn
                                                                            
            formState.productPrice = props.record.productPrice
                                                                            
            formState.productQuantity = props.record.productQuantity
                                                                            
            formState.productSkuId = props.record.productSkuId
                                                                            
            formState.productSkuCode = props.record.productSkuCode
                                                                            
            formState.productCategoryId = props.record.productCategoryId
                                                                            
            formState.promotionName = props.record.promotionName
                                                                            
            formState.promotionAmount = props.record.promotionAmount
                                                                            
            formState.couponAmount = props.record.couponAmount
                                                                            
            formState.integrationAmount = props.record.integrationAmount
                                                                            
            formState.realAmount = props.record.realAmount
                                                                            
            formState.giftIntegration = props.record.giftIntegration
                                                                            
            formState.giftGrowth = props.record.giftGrowth
                                                                            
            formState.productAttr = props.record.productAttr
                                                                            
            formState.deleted = props.record.deleted
                                                                                                                                        
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