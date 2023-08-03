<template>
  <a-modal
    :visible="visible"
    title="新增订单中所包含的商品"
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


                        
                          
          <a-form-item label="订单id" name="id">
                  <a-input v-model:value = "formState.id" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="订单id" name="orderId">
                  <a-input v-model:value = "formState.orderId" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="订单编号" name="orderSn">
                  <a-input v-model:value = "formState.orderSn" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="$column.columnComment" name="productId">
                  <a-input v-model:value = "formState.productId" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="$column.columnComment" name="productPic">
                  <a-input v-model:value = "formState.productPic" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="$column.columnComment" name="productName">
                  <a-input v-model:value = "formState.productName" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="$column.columnComment" name="productBrand">
                  <a-input v-model:value = "formState.productBrand" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="$column.columnComment" name="productSn">
                  <a-input v-model:value = "formState.productSn" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="销售价格" name="productPrice">
                  <a-input v-model:value = "formState.productPrice" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="购买数量" name="productQuantity">
                  <a-input v-model:value = "formState.productQuantity" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="商品sku编号" name="productSkuId">
                  <a-input v-model:value = "formState.productSkuId" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="商品sku条码" name="productSkuCode">
                  <a-input v-model:value = "formState.productSkuCode" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="商品分类id" name="productCategoryId">
                  <a-input v-model:value = "formState.productCategoryId" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="商品促销名称" name="promotionName">
                  <a-input v-model:value = "formState.promotionName" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="商品促销分解金额" name="promotionAmount">
                  <a-input v-model:value = "formState.promotionAmount" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="优惠券优惠分解金额" name="couponAmount">
                  <a-input v-model:value = "formState.couponAmount" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="积分优惠分解金额" name="integrationAmount">
                  <a-input v-model:value = "formState.integrationAmount" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="该商品经过优惠后的分解金额" name="realAmount">
                  <a-input v-model:value = "formState.realAmount" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="$column.columnComment" name="giftIntegration">
                  <a-input v-model:value = "formState.giftIntegration" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="$column.columnComment" name="giftGrowth">
                  <a-input v-model:value = "formState.giftGrowth" />
           </a-form-item>

                                                                            
                          
          <a-form-item label="商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]" name="productAttr">
                  <a-input v-model:value = "formState.productAttr" />
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
import { save } from "@/api/module/omsOrderItem";
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