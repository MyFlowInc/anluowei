<template>
  <a-row>
    <a-col :xs="24" :sm="24" :md="24" :lg="16" :xl="16">
      <div id="containerOss" style="width: 100%"></div>
    </a-col>
    <a-col
      :xs="24"
      :sm="24"
      :md="24"
      :lg="{ span: 7, offset: 1 }"
      :xl="{ span: 7, offset: 1 }"
    >
    </a-col>
  </a-row>
</template>
<script>
import { Chart } from "@antv/g2";
import { onMounted, reactive, ref, onUnmounted, computed } from "vue";
import { useStore } from "vuex";

import { totalCount, tree } from "@/api/module/cms-category";

export default {
  components: {},
  setup() {
    const chartRef = ref(null);
    const store = useStore();
    const color = computed(() => store.getters.color);
    const state = reactive({
      ossDetail: [],
      act: [],
      total: "",
    });

    /// 数据来源 [模拟]

    onMounted(async () => {
      const response = await totalCount();
      state.ossDetail = response.data.oss.ossDetail;

      const datass = JSON.parse(JSON.stringify(state.ossDetail));
      console.log(111,datass);

      

      const chart = new Chart({
        container: "containerOss",
        autoFit: true,
        height: 500,
        padding: [20, 120, 95],
      });
      chart.data(datass);
      chart.axis(false);
      chart.coordinate("rect").transpose().scale(1, -1);
      chart.tooltip({
        showMarkers: false,
      });
      chart
        .interval()
        .adjust("symmetric")
        .position("suffix*count")
        .shape("pyramid")
        .color("suffix", [
          "#0050B3",
          "#1890FF",
          "#40A9FF",
          "#69C0FF",
          "#BAE7FF",
        ])
        .label(
          "suffix*count",
          (suffix, count) => {
            return {
              content: `${suffix} ${count}`,
            };
          },
          {
            offset: 35,
            labelLine: {
              style: {
                lineWidth: 1,
                stroke: "rgba(0, 0, 0, 0.15)",
              },
            },
          }
        )
        .animate({
          appear: {
            animation: "fade-in",
          },
          update: {
            annotation: "fade-in",
          },
        });

      chart.interaction("element-active");

      chart.render();
    });


    return {
      fetch,
      state,
      color,
    };
  },
};
</script>