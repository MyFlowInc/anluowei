<template>
    <a-modal :visible="visible" title="设置该部门可以访问的分类" cancelText="取消" okText="提交" @ok="submit" @cancel="cancel">
        <pro-table 
            :fetch="fetch"
            :columns ="columns"
            :row-selection="{ 
            selectedRowKeys: state.selectedRowKeys, 
            onChange: onSelectChange 
            }"
            >
        </pro-table>
    </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { givePower , powerList} from "@/api/module/dept";
import { selectCateByParentId } from "@/api/module/cms-category";
import { defineComponent, reactive, ref, toRaw, watch } from "vue";
export default defineComponent({
  props: {
      visible: {
        type: Boolean
      },
      record: {
        type: Object
      }
  },
  emit: ['close'],
  setup(props, context) {
    const state = reactive({
      selectedRowKeys: []
    });

    /// 加载部门权限
    watch(props,async (value) => {

      state.selectedRowKeys = [];
      var response = await powerList({"deptId":value.record.id})
      response.data.forEach(element => {
        state.selectedRowKeys.push(element.categoryId);
      });
    })

    /// 加分类
    const fetch = async (param) => {
      var response = await selectCateByParentId(param);
      return {
        data: response.data,
      };
    };

    const columns = [
      { dataIndex: "categoryTitle", key: "categoryTitle", title: "名称" },
      { dataIndex: "categoryImg", key: "categoryImg", title: "图标", image: { width: 34 }  },
      { dataIndex: "id", key: "id", title: "唯一标识"}
    ];

    const submit = (e) => {
      givePower( {"deptId": props.record.id,"categoryId":state.selectedRowKeys} ).then((response)=>{
            if(response.success){
                message.success({ content: '保存成功', duration: 1 }).then(()=>{
                  cancel();
                });
            }else{
                message.error({ content: '保存失败', duration: 1 }).then(()=>{
                  cancel();
                });
            }
          })
    };

    const cancel = e => {
      context.emit('close', false);  
    }

    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

    return {
      state,
      fetch, 
      submit,
      cancel,
      columns,
      onSelectChange
    };
  },
});
</script>