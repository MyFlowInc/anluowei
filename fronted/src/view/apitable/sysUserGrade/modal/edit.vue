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
          <a-select v-model:value="formState.bindRole">
            <a-select-option :value="post.id" v-bind:key="index"
              v-for="(post, index) in state.grades">{{ post.name }}</a-select-option>
          </a-select>
        </a-form-item>
    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { list } from "@/api/module/role";
import { edit } from "@/api/module/sysUserGrade";
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

    const state = reactive({
      grades: [],
    })
    const formRef = ref();

    const formState = reactive({});

    const loadGrades = () => {

      list({}).then((response) => {
        state.grades = response.data;
      })
     }
    loadGrades();
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

    const formRules = {
      name: [{ required: true, message: '请输入名称', trigger: 'blur' }],
      code: [{ required: true, message: '请输入编号', trigger: 'blur' }]
    };

    const editKey = "add";

    const submit = (e) => {
      message.loading({ content: '提交中...', key: editKey });
      formRef.value
        .validate()
        .then(() => {
          edit(toRaw(formState)).then((response) => {
            if (response.success) {
              message.success({ content: '保存成功', key: editKey, duration: 1 }).then(() => {
                cancel();
              });
            } else {
              message.success({ content: '保存失败', key: editKey, duration: 1 }).then(() => {
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
      state,
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
