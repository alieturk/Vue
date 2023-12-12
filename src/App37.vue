<template>
  <HeaderComponent/>
  <NavbarComponent/>
  <router-view></router-view>
</template>

<script>
import HeaderComponent from './components/HeaderComponent.vue';
import NavbarComponent from "@/components/NavbarComponent";
import {OffersAdaptor} from "@/services/offers-adaptor";
import CONFIG from '@/app-config'

export default {
  name: "App37",
  components: {
    HeaderComponent,
    NavbarComponent
  },
  provide() {
    return {
      //stateless data services adaptor singleton
       offersService: new OffersAdaptor(CONFIG.BACKEND_URL + 'offers'),
    }
  }
}
</script>