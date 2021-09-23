import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import DocumentView from '@/view/document/DocumentView.vue'
import DocumentEdit from '@/view/document/DocumentEdit.vue'

const routes: Array<RouteRecordRaw> = [
    { path: '/:documentName', component: DocumentView, props: true },
    { path: '/:documentName/edit', component: DocumentEdit, props: true }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
