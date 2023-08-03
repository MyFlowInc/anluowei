<template>
  <a-modal :visible="visible" title="新增会员等级" cancelText="取消" okText="提交" @ok="submit" @cancel="cancel">
    <a-form ref="formRef" :model="formState" :rules="formRules" :label-col="labelCol" :wrapper-col="wrapperCol">
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
import { message } from 'ant-design-vue';
import { save } from "@/api/module/sysUserGrade";
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
