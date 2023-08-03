<template>
  <div id="home">
    <page-layout>
      <a-row :gutter="[10, 10]">
        <a-col :span="24">
          <a-card>
            <a-tabs>
              <a-tab-pane key="1" tab="文章点击量">
                <a-row>
                  <a-col :xs="24" :sm="24" :md="24" :lg="16" :xl="16">
                    <div id="containerDoc" style="width: 100%"></div>
                  </a-col>
                  <a-col
                    :xs="24"
                    :sm="24"
                    :md="24"
                    :lg="{ span: 7, offset: 1 }"
                    :xl="{ span: 7, offset: 1 }"
                  >
                    <a-col span="24">
                      <a-table
                        :columns="columns"
                        :data-source="state.contentDetail"
                        style="margin-top: 10px"
                      />
                    </a-col>
                  </a-col>
                </a-row>
              </a-tab-pane>
        
              <a-tab-pane key="2" tab="活动参与量" force-render>
                <act/>
              </a-tab-pane>
              <a-tab-pane key="3" tab="分类统计" force-render>
                <cate/>
              </a-tab-pane>
              <a-tab-pane key="4" tab="物料统计" force-render>
                <oss/>
              </a-tab-pane> 
            </a-tabs>
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
    <page-footer></page-footer>
  </div>
</template>
<script>

import cate from "./modal/cate";
import act from "./modal/act";
import oss from "./modal/oss";
import { Chart } from "@antv/g2";
//import { ArrowUpOutlined, ArrowDownOutlined } from "@ant-design/icons-vue";
import { onMounted, reactive, ref, onUnmounted, computed } from "vue";
import { useStore } from "vuex";

import { totalCount, tree } from "@/api/module/cms-category";

export default {
  components: {
    // ArrowUpOutlined,
    // ArrowDownOutlined,
   cate,
   act,
   oss
  },
  setup() {
    const chartRef = ref(null);
    const store = useStore();
    const color = computed(() => store.getters.color);
    const state = reactive({
      contentDetail: [],
      act: [],
      total: "",
    });

    /// 数据来源 [模拟]

    onMounted(async () => {
      const response = await totalCount();
      state.contentDetail = response.data.content.contentDetail;

      const datass = JSON.parse(JSON.stringify(state.contentDetail));
      const chart = new Chart({
        container: "containerDoc",
        autoFit: true,
        //  forceFit: true,
        height: 540,
      });

      chartRef.value = chart;

      chart.data(datass);

      chart.scale("contentHit", {
        nice: true,
      });

      chart.axis("contentHit", false);

      chart
        .interval()
        .position("contentTitle*contentHit")
        .color("contentTitle*contentHit", function (contentTitle, contentHit) {
          return color.value;
        });

      chart.interaction("active-region");
      chart.render();

      const e = document.createEvent("Event");
      e.initEvent("resize", true, true);
      window.dispatchEvent(e);
    });
    onUnmounted(() => {
      chartRef.value && chartRef.value.destroy();
    });

    const columns = [
      { title: "排名", align: "center",width: 60,   customRender: ({ index }) => {return `${index + 1}`;},},
      { title: "文章名", dataIndex: "contentTitle", key: "contentTitle" },
      // { title: "预览", dataIndex: "contentImg", key: "contentImg",image: { width: 34 } },
      { title: "阅读量", dataIndex: "contentHit", key: "contentHit",
        sorter: (a, b) => {
          return a.contentHit - b.contentHit;
        },
        defaultSortOrder: "descend",
      },
    ];

    const columnscate = [
      { title: "排名", align: "center",width: 60,   customRender: ({ index }) => {return `${index + 1}`;},},
      { title: "分类名", dataIndex: "contentTitle", key: "contentTitle" },
      { title: "分类图", dataIndex: "contentHit", key: "contentHit",image: { width: 34 } },
    ];

    return {
      fetch,
      state,
      columnscate,
      columns,
      color,
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
