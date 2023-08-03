<template>
  <page-layout>
    <a-row :gutter="[10, 10]">
      <!-- 查询表单 -->
      <a-col :span="24">
        <a-card>
          <pro-query :searchParam="searchParam" @on-search="search"></pro-query>
        </a-card>
      </a-col>
      <!-- 订单列表 -->
      <a-col :span="24">
        <a-card>
          <pro-table ref="tableRef" :fetch="fetch" :columns="columns" :toolbar="toolbar" :operate="operate"
            :param="state.param" :pagination="pagination"
            :row-selection="{ selectedRowKeys: state.selectedRowKeys, onChange: onSelectChange }">
          </pro-table>
        </a-card>
      </a-col>
    </a-row>
    <save :visible="state.visibleSave" @close="closeSave"></save>
    <edit :visible="state.visibleEdit" @close="closeEdit" :record="state.recordEdit"></edit>
    <info :visible="state.visibleInfo" @close="closeInfo" :record="state.recordInfo"></info>
  </page-layout>
</template>

<script>
import save from './modal/save';
import edit from './modal/edit';
import info from './modal/info';
import { message, modal } from 'ant-design-vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import { page, remove, removeBatch } from "@/api/order/omsOrder";
import { reactive, createVNode, ref } from 'vue';

const removeKey = "remove";
const removeBatchKey = "removeBatch";

export default {
  components: {
    save,
    edit,
    info,
  },
  setup() {

    const tableRef = ref();

    /// 预览
    const imageFormat = { width: 34 }
    /// 开关
    const switchFormat = { yes: true, no: false };

    /// 列配置
    const columns = [

      { dataIndex: "id", key: "id", title: "订单id" },
      { dataIndex: "orderSn", key: "orderSn", title: "订单编号" },
      { dataIndex: "memberUsername", key: "memberUsername", title: "用户帐号" },
      { dataIndex: "totalAmount", key: "totalAmount", title: "订单总金额" },
      // { dataIndex: "payAmount", key: "payAmount", title: "应付金额（实际支付金额）" },
      // { dataIndex: "payType", key: "payType", title: "支付方式：0->未支付；1->支付宝；2->微信" },
      // { dataIndex: "sourceType", key: "sourceType", title: "订单来源：0->PC订单；1->app订单" },
      { dataIndex: "status", key: "status", title: "状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单"},
    ];

    /// 数据来源 [模拟]
    const fetch = async (param) => {
      var response = await page(param);
      return {
        total: response.data.total,
        data: response.data.record,
      };
    };

    /// 删除配置
    const removeMethod = (record) => {
      modal.confirm({
        title: '您是否确定要删除此配置?',
        icon: createVNode(ExclamationCircleOutlined),
        okText: '确定',
        cancelText: '取消',
        onOk() {
          message.loading({ content: "提交中...", key: removeKey });
          remove({ "id": record.id }).then((response) => {
            if (response.success) {
              message.success({ content: "删除成功", key: removeKey, duration: 1 }).then(() => {
                tableRef.value.reload()
              })
            } else {
              message.error({ content: "删除失败", key: removeKey, duration: 1 })
            }
          })
        }
      });
    }

    const removeBatchMethod = (ids) => {
      modal.confirm({
        title: '您是否确定要删除选择配置?',
        icon: createVNode(ExclamationCircleOutlined),
        okText: '确定',
        cancelText: '取消',
        onOk() {
          message.loading({ content: "提交中...", key: removeBatchKey });
          removeBatch({ "ids": ids }).then((response) => {
            if (response.success) {
              message.success({ content: "删除成功", key: removeBatchKey, duration: 1 }).then(() => {
                tableRef.value.reload()
              })
            } else {
              message.error({ content: "删除失败", key: removeBatchKey, duration: 1 })
            }
          })
        }
      });
    }

    /// 工具栏
    const toolbar = [
      { label: "新增", event: function () { state.visibleSave = true } },
      { label: "删除", event: function () { removeBatchMethod(state.selectedRowKeys) } },
    ];

    /// 行操作
    const operate = [
      { label: "查看", event: function (record) { state.visibleInfo = true, state.recordInfo = record } },
      { label: "修改", event: function (record) { state.visibleEdit = true, state.recordEdit = record } },
      { label: "删除", event: function (record) { removeMethod(record) } },
    ];

    /// 分页参数
    const pagination = {
      pageNum: 1,
      pageSize: 10,
    }

    /// 外置参数 - 当参数改变时, 重新触发 fetch 函数
    const state = reactive({
      selectedRowKeys: [],
      param: {},
      visibleSave: false,
      visibleEdit: false,
      visibleInfo: false,
      recordEdit: {},
      recordInfo: {},
    })

    const searchParam = [
      { key: "name", type: "input", label: "名称" },
      { key: "code", type: "input", label: "编码" },
    ]

    const search = function (value) {
      state.param = value
      tableRef.value.reload()
    }

    const closeSave = function () {
      state.visibleSave = false
      tableRef.value.reload()
    }

    const closeEdit = function () {
      state.visibleEdit = false
      tableRef.value.reload()
    }

    const closeInfo = function () {
      state.visibleInfo = false
    }

    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

    return {
      state,
      fetch,
      search,
      toolbar,
      columns,
      operate,
      pagination,
      searchParam,
      onSelectChange,

      closeSave,
      closeEdit,
      closeInfo,

      tableRef
    };
  },
};
</script>
