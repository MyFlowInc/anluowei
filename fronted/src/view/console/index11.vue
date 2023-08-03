<template>
  <div id="home">
    <page-layout>
      <a-row :gutter="[10, 10]">
        <a-col :span="24">
          <a-card>
            <a-tabs>
              <a-tab-pane key="1" tab="销量">
                <a-row>
                  <a-col :xs="24" :sm="24" :md="24" :lg="16" :xl="16">
                    <div id="container" style="width: 100%"></div>
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
                  :data-source="datas"
                  style="margin-top: 10px"
                />
              </a-col>
                  </a-col>
                </a-row>
              </a-tab-pane>
              <a-tab-pane key="2" tab="排行" force-render> </a-tab-pane>
            </a-tabs>
          </a-card>
        </a-col>
        <a-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
          <a-card title="线上热门搜索">
            <a-row :gutter="10">
              <a-col :span="12">
                <a-card>
                  <a-statistic
                    title="销量"
                    :value="11.28"
                    :precision="2"
                    suffix="%"
                    :value-style="{ color: '#3f8600' }"
                    style="margin-right: 50px"
                  >
                    <template v-slot:prefix>
                      <arrow-up-outlined />
                    </template>
                  </a-statistic>
                </a-card>
              </a-col>
              <a-col :span="12">
                <a-card>
                  <a-statistic
                    title="访客"
                    :value="9.3"
                    :precision="2"
                    suffix="%"
                    class="demo-class"
                    :value-style="{ color: '#cf1322' }"
                  >
                    <template v-slot:prefix>
                      <arrow-down-outlined />
                    </template>
                  </a-statistic>
                </a-card>
              </a-col>
              <a-col span="24">
                <a-table
                  :columns="columns"
                  :data-source="datas"
                  style="margin-top: 10px"
                />
              </a-col>
            </a-row>
          </a-card>
        </a-col>
        <a-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
          <a-card title="销售类别占比">
            <a-row :gutter="10">
              <a-col :span="12">
                <a-card>
                  <a-statistic
                    title="Feedback"
                    :value="20.18"
                    :precision="2"
                    suffix="%"
                    :value-style="{ color: '#3f8600' }"
                    style="margin-right: 50px"
                  >
                    <template v-slot:prefix>
                      <arrow-up-outlined />
                    </template>
                  </a-statistic>
                </a-card>
              </a-col>
              <a-col :span="12">
                <a-card>
                  <a-statistic
                    title="Idle"
                    :value="12.3"
                    :precision="2"
                    suffix="%"
                    class="demo-class"
                    :value-style="{ color: '#cf1322' }"
                  >
                    <template v-slot:prefix>
                      <arrow-down-outlined />
                    </template>
                  </a-statistic>
                </a-card>
              </a-col>
              <a-col span="24">
                <a-table
                  :columns="columns"
                  :data-source="datas"
                  style="margin-top: 10px"
                />
              </a-col>
            </a-row>
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
    <page-footer></page-footer>
  </div>
</template>
<script>
import { Chart } from "@antv/g2";
import { ArrowUpOutlined, ArrowDownOutlined } from "@ant-design/icons-vue";
import { onMounted, ref, onUnmounted, computed } from "vue";
import { useStore } from 'vuex';
export default {
  components: {
    ArrowUpOutlined,
    ArrowDownOutlined
  },
  setup() {
    const chartRef = ref(null);

    const store = useStore()
    const color = computed(() => store.getters.color);

    onMounted(() => {
      const datass = [
        { year: "1951 年", sales: 38 },
        { year: "1952 年", sales: 52 },
        { year: "1956 年", sales: 80 },
        { year: "1957 年", sales: 135 },
        { year: "1958 年", sales: 80 },
        { year: "1959 年", sales: 70 },
        { year: "1960 年", sales: 60 },
        { year: "1961 年", sales: 55 },
        { year: "1962 年", sales: 38 }
      ];

      const chart = new Chart({
        container: "container",
        autoFit: true,
        forceFit: true,
        height: 320
      });

      chartRef.value = chart;

      chart.data(datass);
      chart.scale("sales", {
        nice: true
      });

      chart.axis("sales", false);

      chart
        .interval()
        .position("year*sales")
        .color("year*sales", function(year, sales) {
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

    const data = [
      {
        title: "Ant Design Title 1"
      },
      {
        title: "Ant Design Title 2"
      },
      {
        title: "Ant Design Title 3"
      },
      {
        title: "Ant Design Title 4"
      }
    ];

    const columns = [
      { title: "排名", dataIndex: "key", key: "key" },
      { title: "门店", dataIndex: "name", key: "name" },
      { title: "销量", dataIndex: "age", key: "age" },
      { title: "地址", dataIndex: "address", key: "address" }
    ];

    const datas = [
      {
        key: 1,
        name: "1 号店",
        age: 13323.34,
        address: "北京市朝阳区凤鸣路 112 号."
      },
      {
        key: 2,
        name: "2 号店",
        age: 35432.41,
        address: "北京市朝阳区凤鸣路 112 号."
      },
      {
        key: 3,
        name: "3 号店",
        age: 47452.44,
        address: "北京市朝阳区凤鸣路 112 号."
      },
      {
        key: 4,
        name: "4 号店",
        age: 32352.44,
        address: "北京市朝阳区凤鸣路 112 号."
      }
    ];

    return {
      data,
      columns,
      datas,
      color
    };
  }
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
