import { createRouter, createWebHistory } from "vue-router";
import Person from '@/components/Person.vue'
import AiImage from '@/components/AiImage.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/person",
            component: Person
        },
        {
            path: "/aiImage",
            component: AiImage
        }
    ]
})

export default router