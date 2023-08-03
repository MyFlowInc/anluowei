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
    <div id="home" :visible="visible">
      <page-layout>
        <a-row :gutter="[10, 10]">
          <a-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
            <a-card title="基本信息">
              <a-row :gutter="10">
                <a-col span="24">
                  <a-form
                    ref="formRef"
                    :model="formState"
                    :label-col="labelCol"
                    :wrapper-col="wrapperCol"
                  >
                    <a-form-item ref="tableName" label="*名称" name="tableName">
                      <a-input v-model:value="formState.tableName" />
                    </a-form-item>
                    <a-form-item
                      ref="tableComment"
                      label="*描述"
                      name="tableComment"
                    >
                      <a-input v-model:value="formState.tableComment" />
                    </a-form-item>
                    <a-form-item ref="className" label="*实体" name="className">
                      <a-input v-model:value="formState.className" />
                    </a-form-item>
                    <a-form-item
                      ref="functionName"
                      label="*功能"
                      name="functionName"
                    >
                      <a-input v-model:value="formState.functionName" />
                    </a-form-item>
                    <a-form-item
                      ref="functionAuthor"
                      label="*作者"
                      name="functionAuthor"
                    >
                      <a-input v-model:value="formState.functionAuthor" />
                    </a-form-item>
                    <a-form-item ref="remark" label="*备注" name="remark">
                      <a-textarea v-model:value="formState.remark" />
                    </a-form-item>
                  </a-form>
                </a-col>
              </a-row>
            </a-card>
          </a-col>
          <a-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
            <a-card title="高级配置">
              <a-row :gutter="10">
                <a-col span="24">
                  <a-form
                    ref="formRef"
                    :model="formState"
                    :label-col="labelCol"
                    :wrapper-col="wrapperCol"
                  >
                    <a-form-item
                      ref="tplCategory"
                      label="*生成模板："
                      name="tplCategory"
                    >
                      <a-input v-model:value="formState.tplCategory" />
                    </a-form-item>
                    <a-form-item
                      ref="packageName"
                      label="*基本路径："
                      name="packageName"
                    >
                      <a-input v-model:value="formState.packageName" />
                    </a-form-item>
                    <a-form-item
                      ref="moduleName"
                      label="*模块名称："
                      name="moduleName"
                    >
                      <a-input v-model:value="formState.moduleName" />
                    </a-form-item>
                    <a-form-item
                      ref="businessName"
                      label="*业务名称："
                      name="businessName"
                    >
                      <a-input v-model:value="formState.businessName" />
                    </a-form-item>
                    <a-form-item
                      ref="parentMenuName"
                      label="*上级菜单："
                      name="parentMenuName"
                    >
                      <a-input v-model:value="formState.parentMenuName" />
                    </a-form-item>
                    <a-form-item
                      ref="genType"
                      label="*生成方式："
                      name="genType"
                    >
                      <a-input v-model:value="formState.genType" />
                    </a-form-item>
                    <a-form-item
                      ref="genPath"
                      label="*生成路径："
                      name="genPath"
                    >
                      <a-input v-model:value="formState.genPath" />
                    </a-form-item>
                  </a-form>
                </a-col>
              </a-row>
            </a-card>
          </a-col>
          <a-col :span="24">
            <a-card>
              <a-tabs>
                <a-tab-pane key="1" tab="属性配置">
                  <a-row>
                    <a-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                      <a-card>
                        <pro-table
                          ref="tableRef"
                          :fetch="fetch"
                          :columns="columns"
                        >
                        </pro-table>
                      </a-card>
                    </a-col>
                  </a-row>
                </a-tab-pane>
              </a-tabs>
            </a-card>
          </a-col>
        </a-row>
      </page-layout>
    </div>
  </a-modal>
</template>
<script>
import { reactive, ref, watch, onMounted } from "vue";
import { useStore } from "vuex";
import { columnList } from "@/api/module/generate";
export default {
  components: {},
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    record: {
      type: Object,
    },
  },
  emit: ["close"],
  setup(props, context) {
    const formRef = ref();

    const formState = reactive({
      columns2: [],
      dataSource: [],
    });
    watch(props, (props) => {
      formState.tableName = props.record.tableName;
      formState.className = props.record.className;
      formState.functionAuthor = props.record.functionAuthor;
      formState.functionName = props.record.functionName;
      formState.tableComment = props.record.tableComment;
      formState.remark = props.record.remark;

      formState.tplCategory = props.record.tplCategory;
      formState.packageName = props.record.packageName;
      formState.moduleName = props.record.moduleName;
      formState.businessName = props.record.businessName;
      formState.parentMenuId = props.record.parentMenuId;
      formState.genType = props.record.genType;
      formState.genPath = props.record.genPath;
    });
    /// 数据来源 [模拟]
    const fetch = async (param) => {
      var response = await columnList({ tableId: props.record.tableId });
      console.log(1111, response);
      return {
        data: response.data,
      };
    };
    /// 列配置
    const columns = [
      { dataIndex: "capJavaField", key: "capJavaField", title: "字段列名" },
      { dataIndex: "columnComment", key: "columnComment", title: "描述" },
      { dataIndex: "columnType", key: "columnType", title: "物理类型" },
      { dataIndex: "javaField", key: "javaField", title: "Java属性" },
      { dataIndex: "javaType", key: "javaType", title: "Java类型" },
      { dataIndex: "insert", key: "insert", title: "插入" },
      { dataIndex: "edit", key: "edit", title: "编辑" },
      { dataIndex: "query", key: "query", title: "查询" },
      { dataIndex: "list", key: "list", title: "列表" },
      { dataIndex: "required", key: "required", title: "必填" },
      { dataIndex: "queryType", key: "queryType", title: "查询方式" },
      { dataIndex: "htmlType", key: "htmlType", title: "显示类型" },
      { dataIndex: "dictType", key: "dictType", title: "字典类型" },
    ];
    const editKey = "edit";
    const submit = (e) => {
      // formRef.value.resetFields();
      context.emit("close", false);
    };

    const cancel = (e) => {
      //  formRef.value.resetFields();
      context.emit("close", false);
    };
    const chartRef = ref(null);

    const store = useStore();
    return {
      fetch,
      formRef,
      formState,
      columns,
      labelCol: { span: 6 },
      wrapperCol: { span: 18 },
      submit,
      cancel,
    };
  },
};
</script>
<style lang="less" scoped>
.ant-divider-horizontal {
  margin-bottom: 10px !important;
}
.card-left {
  display: inline;
}
.card-right {
  float: right;
}
</style>
