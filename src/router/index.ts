import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import Document from "@/view/document/Document.vue";
import DocumentView from "@/view/document/DocumentView.vue";
import DocumentHistory from "@/view/document/DocumentHistory.vue";
import DocumentEdit from "@/view/document/DocumentEdit.vue";

const routes: Array<RouteRecordRaw> = [
    {path: '/', redirect: '/view/document/Front Page/latest'},
    {
        path: '/view/document/:documentName', component: Document, props: true,
        children: [
            {path: 'history', name: "DocumentHistory", component: DocumentHistory},
            {path: ':revision', name: "DocumentView", component: DocumentView},
            {path: ':revision/edit', name: "DocumentEdit", component: DocumentEdit},
        ]
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
