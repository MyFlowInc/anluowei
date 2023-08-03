<template>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <!-- 查询表单 -->
        <a-col :span="24">
          <a-card>
            <pro-query
              :searchParam="searchParam"
              @on-search ="search"
            ></pro-query>
          </a-card>
        </a-col>
        <!-- 岗位列表 -->
        <a-col :span="24">
          <a-card>
            <pro-table
             rowKey="tableId"
              ref="tableRef"
              :fetch="fetch"
              :columns="columns"
              :toolbar="toolbar"
              :operate="operate"
              :param="state.param"
              :pagination="pagination"
              :row-selection="{ selectedRowKeys: state.selectedRowKeys, onChange: onSelectChange }"
            >
            </pro-table>
          </a-card>
        </a-col>
      </a-row>
      <save :visible="state.visibleSave" @close="closeSave"></save>
      <edit :visible="state.visibleEdit" @close="closeEdit" :record="state.recordEdit"></edit>
      <info :visible="state.visibleInfo" @close="closeInfo" :record="state.recordInfo"></info>
      <preview :visible="state.visiblePreview" @close="closePreview" :record="state.recordPreview"></preview>
    </page-layout>
</template>

<script>
import save from './modal/save';
import edit from './modal/edit';
import info from './modal/info';
import preview from './modal/preview';
import { message , modal} from 'ant-design-vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import { page, remove, removeBatch,download,previewCode,genCode } from "@/api/module/generate";
import { reactive, createVNode, ref } from 'vue';

const removeKey = "remove";
const removeBatchKey = "removeBatch";

export default {
  components: {
    save,
    edit,
    info,
    preview,
  },
  setup() {

    const tableRef = ref();

    /// 列配置
    const columns = [
      { dataIndex: "tableName", key: "tableName", title: "表名称" },
      { dataIndex: "tableComment", key: "tableComment", title: "表描述" },
      { dataIndex: "className", key: "className", title: "实体类名称" },
      { dataIndex: "createTime", key: "createTime", title: "创建时间" },
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
        title: '您是否确定要删除?',
        icon: createVNode(ExclamationCircleOutlined),
        okText: '确定',
        cancelText: '取消',
        onOk() {
          message.loading({ content: "提交中...", key: removeKey });
          remove({"id":record.tableId}).then((response) => {
            if(response.success){
              message.success({content: "删除成功", key: removeKey, duration: 1}).then(() => 
                tableRef.value.reload()
              )
            }else{
              message.error({content: "删除失败", key: removeKey, duration: 1})
            }
          })
        }
      });
    }

    const removeBatchMethod = (ids) => {
       modal.confirm({
        title: '您是否确定要删除?',
        icon: createVNode(ExclamationCircleOutlined),
        okText: '确定',
        cancelText: '取消',
        onOk() {
          message.loading({ content: "提交中...", key: removeBatchKey });
          removeBatch({"ids":ids}).then((response) => {
            if(response.success){
              message.success({content: "删除成功", key: removeBatchKey, duration: 1}).then(() => 
                tableRef.value.reload()
              )
            }else{
              message.error({content: "删除失败", key: removeBatchKey, duration: 1})
            }
          })
        }
      });
    }

    /// 删除配置
    const downloadCode = (record) => {
      download({"tableName":record.tableName}).then((response) => {
            if(response.success){
              console.log("22",response);
              message.success({content: "成功", key: removeBatchKey, duration: 1})
            }else{
              message.error({content: "失败", key: removeBatchKey, duration: 1})
            }
          })
       }

      /// 删除配置
  const genCodeAuto = (record) => {
    genCode({"tableName":record.tableName}).then((response) => {
            if(response.success){
              console.log("22",response);
              message.success({content: "成功", key: removeBatchKey, duration: 1})
            }else{
              message.error({content: "失败", key: removeBatchKey, duration: 1})
            }
          })
       }
    /// 工具栏
    const toolbar = [
      { label: "+生成", event: function () { state.visibleSave = true }},
      { label: "+导入", event: function () { state.visibleSave = true }},
      { label: "批量删除", event: function () { removeBatchMethod(state.selectedRowKeys) }},
    ];
 
    /// 行操作
    const operate = [
      { label: "修改", event: function (record) { state.visibleEdit = true, state.recordEdit = record }},
      // { label: "查看", event: function (record) { state.visibleInfo = true, state.recordInfo = record }},
      { label: "预览", event: function (record) { state.visiblePreview= true, state.recordPreview = record }},
      { label: "下载", event: function (record) { downloadCode(record) }},
      { label: "删除", event: function (record) { removeMethod(record) }},
      { label: "自動創建", event: function (record) { genCodeAuto(record) }},

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
      visiblePreview: false,
      visibleInfo: false,
      recordEdit: {},
      recordInfo: {},
      recordPreview: {},
    })
    
    const searchParam = [
        { key: "tableName", type: "input", label: "名称"},
        { key: "tableComment", type: "input", label: "描述"},
    ]

    const search = function(value) {
      state.param = value
    }

    const closeSave = function(){
      state.visibleSave = false
    }

    const closeEdit = function(){
      state.visibleEdit = false
    }
    const closePreview = function(){
      state.visiblePreview = false
    }
    const closeInfo = function(){
      state.visibleInfo = false
    }

    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

    return {
      state, // 状态共享
      fetch, // 数据回调
      toolbar, // 工具栏
      columns, // 列配置
      operate, // 行操作
      pagination, // 分页配置

      search,
      searchParam, // 查询参数
    
      closeSave,
      closeEdit,
      closePreview,
      closeInfo,
      
      onSelectChange,
      tableRef
    };
  },
};
</script>