<template>
  <div>
    <page-header title="消息发送" describe="通过 企业微信 向用户或其他终端直接发送文字或数字信息."></page-header>
    <page-layout>
      <a-card>
        <a-row type="flex" justify="center">
          <a-col :xs="24" :sm="24" :md="24" :lg="24" :xl="22" :xxl="19">
            <a-form ref="ruleForm" :model="form" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">
              <!-- <a-form-item name="agentId" placeholder="100002" label="接收应用id">
                <a-input v-model:value="form.agentId" />
              </a-form-item> -->

              <a-form-item name="agentId" label="接收应用">
                <a-select v-model:value="form.agentId">
                  <a-select-option :value="act.agentId" v-bind:key="index" v-for="(act, index) in state.apps">{{ act.name }}
                  </a-select-option>
                </a-select>
              </a-form-item>

              <a-form-item name="user" placeholder="@all" label="user">
                <a-input v-model:value="form.user" />
              </a-form-item>
              <a-form-item name="content" label="发送内容">
                <a-textarea v-model:value="form.content" :rows="12" />
              </a-form-item>
              <a-form-item name="active_id" label="发送活动">
                <a-select v-model:value="form.active_id">
                  <a-select-option :value="act.id" v-bind:key="index" v-for="(act, index) in state.data">{{ act.name }}
                  </a-select-option>
                </a-select>
              </a-form-item>
              <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                <a-button type="primary" @click="onSubmit"> 发送 </a-button>
                <a-button style="margin-left: 10px" @click="resetForm">
                  重置
                </a-button>
              </a-form-item>
            </a-form>
          </a-col>
        </a-row>
      </a-card>
    </page-layout>
    <page-footer></page-footer>
  </div>
</template>
<script>
import { message } from 'ant-design-vue';
import { sendMessage, sendImageMessage, sendNewsMessage } from '@/api/module/qywx'
import { toRaw, reactive } from 'vue';
import { page } from "@/api/module/cms-act";
import { list } from "@/api/module/qywx-app";
const sendKey = "sendMessage";
const state = reactive({
  data: [],
  apps: []
});
const param = {
  pageNum: 1,
  pageSize: 30,
};
const loadApps = () => {
  list(param).then((response) => {
    state.apps = response.data.record;
  });
};
loadApps();

const loadAct = () => {
  page(param).then((response) => {
    state.data = response.data.record;
  });

};

loadAct();
export default {
  data() {
    return {
      state,
      labelCol: { xs: 4, sm: 3, md: 3, lg: 3, xl: 2, xxl: 3 },
      wrapperCol: { xs: 20, sm: 21, md: 21, lg: 21, xl: 20, xxl: 17 },
      form: {
        agentId: "",
        content: undefined,
        user: "@all",
        active_id: undefined
      },
      rules: {
        // phone: [{required: true,message: "请输入对应的应用id",trigger: "blur"}],
        // message: [{required: true, message: "请输入内容", trigger: "blur"}]
      }
    };
  },

  methods: {
    onSubmit() {
      this.$refs.ruleForm
        .validate()
        .then(() => {
          message.loading({
            content: "提交中...",
            key: sendKey,
          });

          sendMessage(toRaw(this.form)).then((response) => {
            if (response.success) {
              message.success({ content: '发送成功', key: sendKey, duration: 1 }).then(() => {
                this.resetForm();
              });
            } else {
              message.error({ content: '发送失败', key: sendKey, duration: 1 }).then(() => {
                this.resetForm();
              });
            }
          })
          console.log("values", this.form);
        })
        .catch(error => {
          console.log("error", error);
        });
    },
    resetForm() {
      this.$refs.ruleForm.resetFields();
    }
  }
};
</script>
