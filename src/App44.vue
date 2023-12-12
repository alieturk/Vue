<template>
  <HeaderSb/>
  <NavBarSb/>
  <router-view></router-view>
</template>

<script>
import HeaderSb from './components/HeaderSb.vue';
import NavBarSb from "@/components/NavBarSb.vue";
import {SessionSbService} from "@/services/SessionSbService";
import CONFIG from '@/app-config'
import {shallowReactive} from "vue";
import {FetchInterceptor} from "@/services/FetchInterceptor";
import {OffersAdaptor} from "@/services/offers-adaptor";
// import {Offer} from "@/models/Offers";
// import {RestAdaptorInterface} from "@/services/RestAdaptorInterface";

export default {
  name: "App44",
  components: {
    HeaderSb,
    NavBarSb
  },
  provide() {
    // Create a singleton reactive service tracking the authorisation data of the session
    this.theSessionService = shallowReactive (
        new SessionSbService(CONFIG.BACKEND_URL + "authentication", "Authorization"));

    this.theFetchInterceptor = shallowReactive (
        new FetchInterceptor(this.theSessionService, this.$router));

    return {
      sessionService: this.theSessionService,
      offersService: new OffersAdaptor(CONFIG.BACKEND_URL + 'offers')
      // restService: new RestAdaptorInterface(CONFIG.BACKEND_URL + "/offers", Offer.copyConstructor)
    }
  },
  unmounted() {
    console.log("App.unmounted() has been called")
    this.theFetchInterceptor.unregister();
  }
}
</script>