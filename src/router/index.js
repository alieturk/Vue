import { createRouter, createWebHashHistory } from "vue-router";

import WelcomeComponent from "@/components/WelcomeComponent";
import SignIn from "@/components/SignIn";
import Overview32 from "@/components/offers/Overview32";
import Overview33 from "@/components/offers/Overview33";
import Overview34 from "@/components/offers/Overview34";
import Overview37 from "@/components/offers/Overview37";
import OffersOverview31 from "@/components/OffersOverview31";
import DetailComponent32 from "@/components/offers/DetailComponent32";
import DetailComponent34 from "@/components/offers/DetailComponent34";
import DetailComponent37 from "@/components/offers/DetailComponent37";
import NotFound from "@/components/NotFound";
import Overview45 from "@/components/bids/Overview45.vue";
import DetailComponent45 from "@/components/bids/DetailComponent45.vue";

export const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {path: '/', redirect: "/home"},
        {path: '/home', component: WelcomeComponent},
        {path: '/sign-in', component: SignIn},
        {path: '/offers/overview31', component: Overview32},
        {path: '/offers/overview32', component: OffersOverview31},
        {path: '/offers/overview33', component: Overview33, children: [
                {path: ':id', component: DetailComponent32}
            ]},
        {path: '/offers/overview34', component: Overview34, children: [
                {path: ':id', component: DetailComponent34}
            ]},
        {path: '/offers/overview37', component: Overview37, children: [
                {path: ':id', component: DetailComponent37}
            ]},
        {path: '/bids/overview45', component: Overview45, children: [
                {path: ':id', component: DetailComponent45}
            ]},
        {path: '/:notFound(.*)', component: NotFound}
    ]
});