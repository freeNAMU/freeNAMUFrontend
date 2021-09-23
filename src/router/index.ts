import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import document from "@/router/document";

const routes: Array<RouteRecordRaw> = [
    { path: '/', redirect: '/view/document/Front Page' },
    { path: '/view/document', component: document }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
