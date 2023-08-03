<template>
  <a-space direction="vertical" style="width: 100%" size="large">
    <a-upload :before-upload="beforeUpload" @change="handleChange" :remove="handleRemove" :headers="headers"
      v-model:file-list="fileList" list-type="picture" :action="action">
      <a-button>
        <upload-outlined></upload-outlined>
        Upload (Max: {{ limitNum }})
      </a-button>
    </a-upload>
  </a-space>
</template>
<script>
import { defineComponent } from "vue"
export default defineComponent({
  name: "Base-Upload",
})
</script>
<script setup>
import { UploadOutlined } from '@ant-design/icons-vue';
import { ref, nextTick, defineEmits, defineProps } from 'vue';
import { message } from 'ant-design-vue';
import _ from 'lodash'
import { func } from 'vue-types';


const props = defineProps(['modelValue', 'limit'])
const emit = defineEmits(['update:modelValue',])

function init() {
  let imgList = []
  if (props.limit && props.limit == 1) {
    imgList = [{
      uid: `-1`,
      status: "done",
      url: props.modelValue,
      thumbUrl:
        props.modelValue,
    }]
  }
  if (props.limit && props.limit != 1) {
    imgList = JSON.parse(props.modelValue);
    imgList = imgList.map((v, index) => {
      return {
        uid: `-${index + 1}`,
        status: "done",
        url: v,
        thumbUrl:
          v,
      }
    });
  }
  return imgList;
}

const fileList = ref(props.modelValue && init() || []);
const tokenKey = localStorage.getItem("token_key");
const token = localStorage.getItem("token")
const action = process.env.VUE_APP_API_BASE_URL + "/api/sys/oss/upload"
const headers = {
  "Authorization": token,
  "Authorization-key": tokenKey
};

let limitNum = ref(props.limit ?? 1)
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

  const limit = fileList.value.length < limitNum.value
  if (!limit) {
    message.error(`文件个数不能大于${limitNum.value}`);
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
    console.log('uploading')
    return;
  }
  function getBase64(img, callback) {
    const reader = new FileReader();
    reader.addEventListener('load', () => callback(reader.result));
    reader.readAsDataURL(img);
  }
  const imageUrl = ref('');
  if (props.limit && props.limit == 1) {
    if (info.file.status === 'done') {
      const url = _.get(info, 'file.response.data.path')
      emit('update:modelValue', url)
    }
  } else {
    console.log(fileList.value,'fileList.value')
    fileList.value.length && emit('update:modelValue', JSON.stringify(fileList.value.map(v => {
      if (!v.url) {
        v.url = _.get(v, 'response.data.path');
      }
      return v.url
    })));
  }

  if (info.file.status === 'error') {
    message.error('upload error');
    console.log('error')
  }

};

function handleRemove(file) {
  if (Array.isArray(fileList.value)) {
    fileList.value = fileList.value.filter(v => {
      return v.url != file.url
    });
    if (props.limit && props.limit == 1) {
      emit('update:modelValue', fileList.value.map(v => {
        return v.url
      })[0]?.url ?? '');
    } else {
      fileList.value.length && emit('update:modelValue', JSON.stringify(fileList.value.map(v => {
        if (!v.url) {
          v.url = _.get(v, 'response.data.path');
        }
        return v.url
      })));
    }
  }
}



</script>

