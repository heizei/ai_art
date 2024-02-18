<template>
  <div class="person">
    <h2>求和：{{ sum }}</h2>
    <button @click="addSum">增加1</button>

    <hr>
    <img class="dogImg" v-for="dog in dogList" :src="dog" :key="dog">
    <button @click="getDog">添加一直小狗</button>
  </div>
</template>

<!-- 组件名 -->
<script lang="ts">
export default {
  name: "Person"
}
</script>

// 在这里可以使用setup语法糖
<script lang="ts" setup name="Person">
import PersonCommon from '@/hooks/PersonCommon'
import SumCommon from '@/hooks/SumCommon'

let {dogList, getDog} = PersonCommon()
let {sum, addSum} = SumCommon()

</script>


<style scoped>
.person {
  background-color: rgb(205, 255, 212);
  box-shadow: 0 0 10px;
  border-radius: 10px;
  padding: 20px;
}
.dogImg 
{
  width: 100px;
  height: 100px;
  border-radius: 50px;
}
</style>
