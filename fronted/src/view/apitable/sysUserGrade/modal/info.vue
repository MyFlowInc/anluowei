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
                      <a-form-item label="id" name="id">
            <a-input v-model:value="formState.id" />
     </a-form-item>


                                                                  <a-form-item label="等级" name="grade">
            <a-input v-model:value="formState.grade" />
     </a-form-item>


                                                                  <a-form-item label="等级名称" name="name">
            <a-input v-model:value="formState.name" />
     </a-form-item>


                                                                  <a-form-item label="有效期" name="validDay">
            <a-input v-model:value="formState.validDay" />
     </a-form-item>


                                                                  <a-form-item label="价格" name="price">
            <a-input v-model:value="formState.price" />
     </a-form-item>


                                                                  <a-form-item label="展示图" name="image">
            <a-input v-model:value="formState.image" />
     </a-form-item>


                                                                  <a-form-item label="促销价格" name="promotionPrice">
            <a-input v-model:value="formState.promotionPrice" />
     </a-form-item>


                                                                  <a-form-item label="绑定角色" name="bindRole">
            <a-input v-model:value="formState.bindRole" />
     </a-form-item>


                                                                  <a-form-item label="状态" name="status">
            <a-input v-model:value="formState.status" />
     </a-form-item>


                                                                  <a-form-item label="销量" name="sale">
            <a-input v-model:value="formState.sale" />
     </a-form-item>


                                                                  <a-form-item label="升级会员等级条件描述" name="catchCondition">
            <a-input v-model:value="formState.catchCondition" />
     </a-form-item>


                                                                  <a-form-item label="升级会员等级条件，init:默认获取;pay:付费升级；frequency:消费次数；amount:累积消费金额升级" name="catchType">
            <a-input v-model:value="formState.catchType" />
     </a-form-item>


                                                                  <a-form-item label="达到升级条件的值" name="catchValue">
            <a-input v-model:value="formState.catchValue" />
     </a-form-item>


                                                                  <a-form-item label="会员权益描述" name="userPrivilege">
            <a-input v-model:value="formState.userPrivilege" />
     </a-form-item>


                                                                  <a-form-item label="享受折扣" name="discount">
            <a-input v-model:value="formState.discount" />
     </a-form-item>


                                                                  <a-form-item label="积分加速" name="speed">
            <a-input v-model:value="formState.speed" />
     </a-form-item>


                                                                  <a-form-item label="排序" name="sort">
            <a-input v-model:value="formState.sort" />
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
                                                                            
            formState.grade = props.record.grade
                                                                            
            formState.name = props.record.name
                                                                            
            formState.validDay = props.record.validDay
                                                                            
            formState.price = props.record.price
                                                                            
            formState.image = props.record.image
                                                                            
            formState.promotionPrice = props.record.promotionPrice
                                                                            
            formState.bindRole = props.record.bindRole
                                                                            
            formState.status = props.record.status
                                                                            
            formState.sale = props.record.sale
                                                                            
            formState.catchCondition = props.record.catchCondition
                                                                            
            formState.catchType = props.record.catchType
                                                                            
            formState.catchValue = props.record.catchValue
                                                                            
            formState.userPrivilege = props.record.userPrivilege
                                                                            
            formState.discount = props.record.discount
                                                                            
            formState.speed = props.record.speed
                                                                            
            formState.sort = props.record.sort
                                                                            
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