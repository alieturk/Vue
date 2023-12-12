<template>
  <HeaderComponent/>
  <NavbarComponent/>
  <router-view></router-view>
</template>

<script>
import HeaderComponent from './components/HeaderComponent.vue';
import NavbarComponent from "@/components/NavbarComponent";

export default {
  name: "App33",
  components: {
    HeaderComponent,
    NavbarComponent
  }
}
</script>