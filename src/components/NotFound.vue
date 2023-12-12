<template>
  <div class="pl-5">
    <h3>Oops, an error has occurred...</h3>
    <h6>There is no component available at route {{ getUrl() }}</h6>
  </div>
</template>

<script>
export default {
  name: "NotFound",
  data() {
  },
  methods: {
    getUrl() {
      const link = document.URL;
      return link.substring(23);
    }
  }
}
</script>

<style scoped>

</style>