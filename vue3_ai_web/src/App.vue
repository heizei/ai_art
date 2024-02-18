<template>
  <div class="app">
    <h1 class="title">欢迎来到小龙的星球</h1>

    <div>
      <RouterLink to="/aiImage" active-class="">ai画图</RouterLink>
      <RouterLink to="/person" active-class="">随机小狗图片</RouterLink>
    </div>

    <div class="content">
      <RouterView></RouterView>
    </div>

  </div>
</template>

<script lang="ts">
export default {
  name: 'App'
}
</script>
<script lang="ts" setup>
import { RouterView, RouterLink } from 'vue-router'
</script>

<style>
.app {
  box-shadow: inset;
  border-radius: 10px;
  padding: 20px;
}

.title {
  text-align: center;
}

.content {
  background-color: rgb(0, 127, 201);

  height: auto;
  width: auto;
  border-radius: 10px;
  padding: 20px;
  /* margin-top: 20px;
  margin-bottom: 20px; */
}
</style>
