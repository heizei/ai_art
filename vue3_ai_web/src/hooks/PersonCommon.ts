import { reactive } from "vue";
import axios from "axios";

export default function () {
  let dogList = reactive([
    "https://images.dog.ceo/breeds/terrier-russell/jack2.jpg",
  ]);

  async function getDog() {
    try {
      let result = await axios.get("https://dog.ceo/api/breeds/image/random");
      console.log(result.data.message);
      // 响应式数据
      dogList.push(result.data.message);
    } catch (error) {
      // 异常处理
      alert(error);
    }
  }

  return { dogList, getDog };
}
