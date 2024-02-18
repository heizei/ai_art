import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";

const app = createApp(App)
// 先加载路由
app.use(router)

app.mount("#app")

