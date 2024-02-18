import { ref } from "vue";

export default function () {
  const sum = ref(0);
  const addSum = () => {
    sum.value++;
  };
  return { sum, addSum };
}