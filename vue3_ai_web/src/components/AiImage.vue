<template>
  <div class="person">
    <input v-model="imageInfo.Prompt"><br><br>
    <button @click="generateImage">生成图片</button><br>
    <img v-if="imageUrl" :src="'data:image/png;base64,' + imageUrl">
  </div>
</template>
  
  <!-- 组件名 -->
<script lang="ts">
export default {
  name: "AiImage"
}
</script>
  
  // 在这里可以使用setup语法糖
<script lang="ts" setup>
import axios from 'axios';
import { ref } from 'vue';

const config = {
  headers: { 'Content-Type': 'application/json' }
}

let imageUrl = ref('');

let imageInfo = {
  "Prompt": "奔跑的猫",
  "NegativePrompt": null,
  "Styles": null,
  "ResultConfig": null,
  "LogoAdd": 0,
  "LogoParam": null,
  "RspImgType": null
}

async function generateImage() {
  try {
    // 发送POST请求
    const response = await axios.post('http://localhost:8085/ai/textToImag', JSON.stringify(imageInfo), config);
    // 获取生成的图像URL
    console.log(response.data.msg);
    imageUrl.value = response.data.msg;
  }
  catch (error) {
    alert(error);
  }
}
</script>
  
  
<style scoped></style>
  