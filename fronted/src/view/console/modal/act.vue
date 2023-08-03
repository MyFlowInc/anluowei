<template>
    <a-row>
      <a-col :xs="24" :sm="24" :md="24" :lg="16" :xl="16">
        <div id="containerAct" style="width: 100%"></div>
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
        contentDetail: [],
        act: [],
        total: "",
      });
  
      /// 数据来源 [模拟]
  
     
    onMounted(async () => {
      const response = await totalCount();
      var actDetail = response.data.act.actDetail;

      const datass = JSON.parse(JSON.stringify(actDetail));
      const chart = new Chart({
        container: "containerAct",
        autoFit: true,
        height: 500,
      });

      chart.data(datass);

      chart.coordinate("theta", {
        radius: 0.85,
      });

      chart.scale("usersNu", {
        formatter: (val) => {
          //  val = val * 100 + "%";
          return val;
        },
      });
      chart.tooltip({
        showTitle: false,
        showMarkers: false,
      });
      chart.axis(false); // 关闭坐标轴
      const interval = chart
        .interval()
        .adjust("stack")
        .position("usersNu") //name对应的id颜色
        .color("name") //颜色横向选择
        .label("name", {
          offset: -40,
          style: {
            textAlign: "center",
            shadowBlur: 2,
            shadowColor: "rgba(0, 0, 0, .45)",
            fill: "#fff",
          },
        })
        .tooltip("usersNu*name", (name, usersNu) => {
          // contentHit = contentHit * 100 + "%";
          //contentHit = contentHit  + "%";
          //饼图浮窗的点击效果
          return {
            name: usersNu,
            value: name,
          };
        })
        .style({
          lineWidth: 1,
          stroke: "#fff",
        });
      chart.interaction("element-single-selected");
      chart.render();

      // 默认选择
      interval.elements[0].setState("selected", true);
    });

      return {
        fetch,
        state,
        color,
      };
    },
  };
  </script>