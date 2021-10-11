import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import Document from "@/view/document/Document.vue";
import DocumentView from "@/view/document/DocumentView.vue";
import DocumentHistory from "@/view/document/DocumentHistory.vue";
import DocumentEdit from "@/view/document/DocumentEdit.vue";

const routes: Array<RouteRecordRaw> = [
    {path: '/', redirect: '/document/view/FrontPage'},
    {
        path: '/document', component: Document,
        children: [
            {path: 'history/:documentName(.*)', name: "DocumentHistory", component: DocumentHistory},
            {path: 'view/:documentName(.*)', name: "DocumentView", component: DocumentView},
            {path: 'edit/:documentName(.*)', name: "DocumentEdit", component: DocumentEdit},
        ]
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
