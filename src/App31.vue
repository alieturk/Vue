<template>
  <HeaderComponent/>
  <NavbarComponent/>
   <OffersOverview31/>
</template>

<script>
import HeaderComponent from './components/HeaderComponent.vue';
import NavbarComponent from "@/components/NavbarComponent";
 import OffersOverview31 from "@/components/OffersOverview31";

export default {
  name: 'App31',
  components: {
    NavbarComponent,
    HeaderComponent,
     OffersOverview31,
  }
}
</script>