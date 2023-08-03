<template>
  <a-modal :visible="visible" title="文件上传" cancelText="取消" okText="提交" @ok="submit" @cancel="cancel">
    <a-upload :action="uploadUrl" :before-upload="beforeUpload" @change="handleChange" list-type="picture"
      v-model:file-list="fileList" :headers="headers">
      <a-button>
        <upload-outlined></upload-outlined>
        upload
      </a-button>
    </a-upload>
  </a-modal>
</template>
<script>
import { defineComponent, reactive, ref, nextTick } from "vue";
import { message } from 'ant-design-vue';
import { uploadUrl } from '../../../api/request'
export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
  },
  emit: ["close"],
  setup(props, context) {
    const formRef = ref();

    const formState = reactive({
      sort: 0,
      enable: "true",
    });

    const formRules = {
      name: [{ required: true, message: "名称", trigger: "blur" }],
      code: [{ required: true, message: "编号", trigger: "blur" }],
    };

    const saveKey = "save";

    const submit = (e) => {
    };

    const fileList = ref([
    ]);

    const cancel = (e) => {
      context.emit("close", false);
    };

    const tokenKey = localStorage.getItem("token_key");
    const token = localStorage.getItem("token")

    const headers = {
      "Authorization": token,
      "Authorization-key": tokenKey
    };

    const beforeUpload = file => {
      console.log('beforeUpload')
      // const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';

      // if (!isJpgOrPng) {
      //   message.error('You can only upload JPG file!');
      // }

      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isLt2M) {
        message.error('文件不能大于20mb');

      }
      const limit = fileList.value.length === 0
      if (!limit) {
        message.error('文件个数不能大于1');
      }

      if (!isLt2M || !limit) {
        nextTick(() => {
          fileList.value.pop()
        })
      }

      return isLt2M && limit;
    };

    const handleChange = info => {
      if (info.file.status === 'uploading') {
        console.log(111)
        return;
      }

      if (info.file.status === 'done') {
        // Get this url from response in real world.
        // getBase64(info.file.originFileObj, base64Url => {
        //   imageUrl.value = base64Url;
        //   loading.value = false;
        // });
        console.log(222)

      }

      if (info.file.status === 'error') {
        message.error('upload error');
        console.log(333)

      }
    };
    return {
      submit,
      cancel,
      headers,
      formRef,
      fileList,
      formState,
      formRules,
      uploadUrl,
      labelCol: { span: 6 },
      wrapperCol: { span: 18 },
      handleChange,
      beforeUpload,
    };
  },
});
</script>