<template>
  <a-modal
    :visible="visible"
    width="90%"
    title="查看详情"
    cancelText="取消"
    okText="确定"
    @ok="submit"
    @cancel="cancel"
  >
  <div class="card-container">
    <a-tabs 
      v-model:activeKey="activeKey"
      :style="{ height: '100%' }"
    >
      <a-tab-pane v-for="i in formState.codeData" :key="i.title" :tab="`${i.title}`">
        <pre v-highlightA> <code class="json">{{ i.content}}</code>
    
     </pre>
      </a-tab-pane>
    </a-tabs>
  </div>
  </a-modal>
</template>
<script>
import { defineComponent, reactive, ref, watch } from "vue";
import { message } from "ant-design-vue";
import { previewCode } from "@/api/module/generate";
export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
    record: {
      type: Object,
    },
  },
  emit: ["close"],
  setup(props, context) {
    const preview = "preview";
    //  const formRef = ref();

    const formState = reactive({
      codeData: [],
    });

    let value;
    let itemData = [];
    let items = [];
    watch(props, (props) => {
      previewCode({ tableId: props.record.tableId }).then((response) => {
        if (response.success) {
          items = [];
          itemData = response.data;
          for (const index in itemData) {
            value = itemData[index];
            // value = value.replace(/</g, "&lt;");
            // value = value.replace(/>/g, "&gt;");
            let templateName = index
              .substring(index.lastIndexOf("/") + 1, index.length)
              .replace(/\.vm/g, "");
            items.push({
              title: templateName,
              content: value,
            });
          }
          formState.codeData = items;
          console.log(1, items);
          console.log(2, formState.codeData);
          message.success({ content: "成功", key: preview, duration: 1 });
        } else {
          message.success({ content: "成功", key: preview, duration: 1 });
        }
      });
    });

    const formRules = {};

    const submit = (e) => {
      // formRef.value.resetFields();
      context.emit("close", false);
    };

    const cancel = (e) => {
      //  formRef.value.resetFields();
      context.emit("close", false);
    };
    const activeKey = ref("sql");
    return {
      activeKey,
      items,
      submit,
      cancel,
      //  formRef,
      formState,
      formRules,

      labelCol: { span: 6 },
      wrapperCol: { span: 18 },
    };
  },
});
</script>
<style lang="less">
.full-modal {
  .ant-modal {
    max-width: 100%;
    top: 0;
    padding-bottom: 0;
    margin: 0;
  }
  .ant-modal-content {
    display: flex;
    flex-direction: column;
    height: calc(100vh);
  }
  .ant-modal-body {
    flex: 1;
  }
}



</style>