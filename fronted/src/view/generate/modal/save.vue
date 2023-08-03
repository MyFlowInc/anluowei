<template>
  <a-modal
    :visible="visible"
    title="导入配置"
    cancelText="取消"
    okText="导入"
    width="800px"
    height="500px"
    @ok="submit"
    @cancel="cancel"
  >
    <pro-table
      rowKey="tableName"
      ref="tableRef"
      :fetch="fetch"
      :columns="columns"
      :operate="operate"
      :param="state.param"
      :pagination="pagination"
      :row-selection="{
        selectedRowKeys: state.selectedRowKeys,
        onChange: onSelectChange,
      }"
    >
    </pro-table>
  </a-modal>
</template>
<script>
import { DBPage, add } from "@/api/module/generate";
import { defineComponent, reactive, ref, createVNode } from "vue";
import { message, modal } from "ant-design-vue";
import { ExclamationCircleOutlined } from "@ant-design/icons-vue";

export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
  },
  emit: ["close"],
  setup(props, context) {
    const saveKey = "save";
    /// 外置参数 - 当参数改变时, 重新触发 fetch 函数
    const state = reactive({
      selectedRowKeys: [],
      visibleInfo: false,
      tableData : [],
      recordInfo: {},
    });
    /// 数据来源 [模拟]
    const fetch = async (param) => {
      var response = await DBPage(param);
      state.tableData = response.data.record;
      return {
        total: response.data.total,
        data: response.data.record,
      };
    };

    /// 列配置
    const columns = [
      { dataIndex: "tableName", key: "content", title: "名称" },
      { dataIndex: "tableComment", key: "createName", title: "描述" },
      { dataIndex: "createTime", key: "createTime", title: "创建时间" },
    ];
    /// 行操作
    const operate = [
      { label: "导入", event: function (record) { saveInfo(record) }},
    ];
    const saveInfo=(record)=>{
          message.loading({ content: "提交中...", key: saveKey });
          add({ tableNames: [record.tableName] }).then((response) => {
            if (response.success) {
              message.success({
                content: "导入成功",
                key: saveKey,
                duration: 1,
              });
            } else {
              message.error({ content: "导入失败", key: saveKey, duration: 1 });
            }
          });
    }
    /// 分页参数
    const pagination = {
      pageNum: 1,
      pageSize: 5,
    };

    const submit = (e) => {
      modal.confirm({
        title: "您是否确定要导入选择任务?",
        icon: createVNode(ExclamationCircleOutlined),
        okText: "确定",
        cancelText: "取消",
        onOk() {
          message.loading({ content: "提交中...", key: saveKey });
          add({ tableNames: state.selectedRowKeys }).then((response) => {
            if (response.success) {
              message.success({
                content: "导入成功",
                key: saveKey,
                duration: 1,
              });
            } else {
              message.error({ content: "导入失败", key: saveKey, duration: 1 });
            }
          });
        },
      });
    };

    const cancel = (e) => {
      context.emit("close", false);
    };


    const onSelectChange = (selectedRowKeys) => {
      state.selectedRowKeys = selectedRowKeys;
    };

    return {
      state,
      submit,
      cancel,
      fetch,
      columns,
      operate,
      pagination,
      onSelectChange,
    };
  },
});
</script>