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


                                                                  <a-form-item label="$column.columnComment" name="memberId">
            <a-input v-model:value="formState.memberId" />
     </a-form-item>


                                                                  <a-form-item label="$column.columnComment" name="couponId">
            <a-input v-model:value="formState.couponId" />
     </a-form-item>


                                                                  <a-form-item label="订单编号" name="orderSn">
            <a-input v-model:value="formState.orderSn" />
     </a-form-item>


                                                                          <a-form-item label="用户帐号" name="memberUsername">
            <a-input v-model:value="formState.memberUsername" />
     </a-form-item>


                                                                  <a-form-item label="订单总金额" name="totalAmount">
            <a-input v-model:value="formState.totalAmount" />
     </a-form-item>


                                                                  <a-form-item label="应付金额（实际支付金额）" name="payAmount">
            <a-input v-model:value="formState.payAmount" />
     </a-form-item>


                                                                  <a-form-item label="运费金额" name="freightAmount">
            <a-input v-model:value="formState.freightAmount" />
     </a-form-item>


                                                                  <a-form-item label="促销优化金额（促销价、满减、阶梯价）" name="promotionAmount">
            <a-input v-model:value="formState.promotionAmount" />
     </a-form-item>


                                                                  <a-form-item label="积分抵扣金额" name="integrationAmount">
            <a-input v-model:value="formState.integrationAmount" />
     </a-form-item>


                                                                  <a-form-item label="优惠券抵扣金额" name="couponAmount">
            <a-input v-model:value="formState.couponAmount" />
     </a-form-item>


                                                                  <a-form-item label="管理员后台调整订单使用的折扣金额" name="discountAmount">
            <a-input v-model:value="formState.discountAmount" />
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


                                                                  <a-form-item label="订单类型：0->正常订单；1->秒杀订单" name="orderType">
            <a-input v-model:value="formState.orderType" />
     </a-form-item>


                                                                  <a-form-item label="物流公司(配送方式)" name="deliveryCompany">
            <a-input v-model:value="formState.deliveryCompany" />
     </a-form-item>


                                                                  <a-form-item label="物流单号" name="deliverySn">
            <a-input v-model:value="formState.deliverySn" />
     </a-form-item>


                                                                  <a-form-item label="自动确认时间（天）" name="autoConfirmDay">
            <a-input v-model:value="formState.autoConfirmDay" />
     </a-form-item>


                                                                  <a-form-item label="可以获得的积分" name="integration">
            <a-input v-model:value="formState.integration" />
     </a-form-item>


                                                                  <a-form-item label="可以活动的成长值" name="growth">
            <a-input v-model:value="formState.growth" />
     </a-form-item>


                                                                  <a-form-item label="活动信息" name="promotionInfo">
            <a-input v-model:value="formState.promotionInfo" />
     </a-form-item>


                                                                  <a-form-item label="发票类型：0->不开发票；1->电子发票；2->纸质发票" name="billType">
            <a-input v-model:value="formState.billType" />
     </a-form-item>


                                                                  <a-form-item label="发票抬头" name="billHeader">
            <a-input v-model:value="formState.billHeader" />
     </a-form-item>


                                                                  <a-form-item label="发票内容" name="billContent">
            <a-input v-model:value="formState.billContent" />
     </a-form-item>


                                                                  <a-form-item label="收票人电话" name="billReceiverPhone">
            <a-input v-model:value="formState.billReceiverPhone" />
     </a-form-item>


                                                                  <a-form-item label="收票人邮箱" name="billReceiverEmail">
            <a-input v-model:value="formState.billReceiverEmail" />
     </a-form-item>


                                                                  <a-form-item label="收货人姓名" name="receiverName">
            <a-input v-model:value="formState.receiverName" />
     </a-form-item>


                                                                  <a-form-item label="收货人电话" name="receiverPhone">
            <a-input v-model:value="formState.receiverPhone" />
     </a-form-item>


                                                                  <a-form-item label="收货人邮编" name="receiverPostCode">
            <a-input v-model:value="formState.receiverPostCode" />
     </a-form-item>


                                                                  <a-form-item label="省份/直辖市" name="receiverProvince">
            <a-input v-model:value="formState.receiverProvince" />
     </a-form-item>


                                                                  <a-form-item label="城市" name="receiverCity">
            <a-input v-model:value="formState.receiverCity" />
     </a-form-item>


                                                                  <a-form-item label="区" name="receiverRegion">
            <a-input v-model:value="formState.receiverRegion" />
     </a-form-item>


                                                                  <a-form-item label="详细地址" name="receiverDetailAddress">
            <a-input v-model:value="formState.receiverDetailAddress" />
     </a-form-item>


                                                                  <a-form-item label="订单备注" name="note">
            <a-input v-model:value="formState.note" />
     </a-form-item>


                                                                  <a-form-item label="确认收货状态：0->未确认；1->已确认" name="confirmStatus">
            <a-input v-model:value="formState.confirmStatus" />
     </a-form-item>


                                                                  <a-form-item label="删除状态：0->未删除；1->已删除" name="deleteStatus">
            <a-input v-model:value="formState.deleteStatus" />
     </a-form-item>


                                                                  <a-form-item label="下单时使用的积分" name="useIntegration">
            <a-input v-model:value="formState.useIntegration" />
     </a-form-item>


                                                                  <a-form-item label="支付时间" name="paymentTime">
            <a-input v-model:value="formState.paymentTime" />
     </a-form-item>


                                                                  <a-form-item label="发货时间" name="deliveryTime">
            <a-input v-model:value="formState.deliveryTime" />
     </a-form-item>


                                                                  <a-form-item label="确认收货时间" name="receiveTime">
            <a-input v-model:value="formState.receiveTime" />
     </a-form-item>


                                                                  <a-form-item label="评价时间" name="commentTime">
            <a-input v-model:value="formState.commentTime" />
     </a-form-item>


                                                                  <a-form-item label="修改时间" name="modifyTime">
            <a-input v-model:value="formState.modifyTime" />
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
                                                                            
            formState.memberId = props.record.memberId
                                                                            
            formState.couponId = props.record.couponId
                                                                            
            formState.orderSn = props.record.orderSn
                                                                                        
            formState.memberUsername = props.record.memberUsername
                                                                            
            formState.totalAmount = props.record.totalAmount
                                                                            
            formState.payAmount = props.record.payAmount
                                                                            
            formState.freightAmount = props.record.freightAmount
                                                                            
            formState.promotionAmount = props.record.promotionAmount
                                                                            
            formState.integrationAmount = props.record.integrationAmount
                                                                            
            formState.couponAmount = props.record.couponAmount
                                                                            
            formState.discountAmount = props.record.discountAmount
                                                                            
            formState.payType = props.record.payType
                                                                            
            formState.sourceType = props.record.sourceType
                                                                            
            formState.status = props.record.status
                                                                            
            formState.orderType = props.record.orderType
                                                                            
            formState.deliveryCompany = props.record.deliveryCompany
                                                                            
            formState.deliverySn = props.record.deliverySn
                                                                            
            formState.autoConfirmDay = props.record.autoConfirmDay
                                                                            
            formState.integration = props.record.integration
                                                                            
            formState.growth = props.record.growth
                                                                            
            formState.promotionInfo = props.record.promotionInfo
                                                                            
            formState.billType = props.record.billType
                                                                            
            formState.billHeader = props.record.billHeader
                                                                            
            formState.billContent = props.record.billContent
                                                                            
            formState.billReceiverPhone = props.record.billReceiverPhone
                                                                            
            formState.billReceiverEmail = props.record.billReceiverEmail
                                                                            
            formState.receiverName = props.record.receiverName
                                                                            
            formState.receiverPhone = props.record.receiverPhone
                                                                            
            formState.receiverPostCode = props.record.receiverPostCode
                                                                            
            formState.receiverProvince = props.record.receiverProvince
                                                                            
            formState.receiverCity = props.record.receiverCity
                                                                            
            formState.receiverRegion = props.record.receiverRegion
                                                                            
            formState.receiverDetailAddress = props.record.receiverDetailAddress
                                                                            
            formState.note = props.record.note
                                                                            
            formState.confirmStatus = props.record.confirmStatus
                                                                            
            formState.deleteStatus = props.record.deleteStatus
                                                                            
            formState.useIntegration = props.record.useIntegration
                                                                            
            formState.paymentTime = props.record.paymentTime
                                                                            
            formState.deliveryTime = props.record.deliveryTime
                                                                            
            formState.receiveTime = props.record.receiveTime
                                                                            
            formState.commentTime = props.record.commentTime
                                                                            
            formState.modifyTime = props.record.modifyTime
                                                                            
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