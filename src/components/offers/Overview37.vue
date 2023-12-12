<template>
  <div class="list-group">

    <div class="container-fluid">
      <div class="row">
        <div class="col">
          <button type="button" class="list-group-item list-group-item-action active">
            Offer Title:
          </button>
          <table class="table table-hover">
            <tbody class="align-middle">
            <tr v-for="(offer) in offers" :key="offer">
              <td @click="offerClicked(offer)"> {{ offer.title + "-" + offer.id }}</td>
            </tr>
            </tbody>
          </table>
          <div class="button-add float-right">
            <button type="button" class="list-group-item list-group-item-action active" @click="addOffer">Add
              offer
            </button>
          </div>
        </div>
        <div class="col pt-5 mt-3">
          <h5 v-if="!selected" class="header-form">Select an offer</h5>
          <router-view v-else :currentOffer="currentOffer" @refresh="onReload"></router-view>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Offer } from '@/models/Offers'

export default {
  inject: ["offersService"],
  name: "Overview37",
  components: {
  },
  data() {
    return {
      currentOffer: 0,
      offers: [],
      selected: false,
    }
  },
  async created() {
    await this.onReload();
    if (this.$route?.params?.id != null) {
      this.currentOffer = this.findSelectedFromRouteParams(this.$route?.params?.id);
      this.selected = true;
    } else {
      this.selected = false;
    }
  },
  watch: {
    '$route'() {
      let id = this.$route.params.id;
      for (let i = 0; i < this.offers.length; i++) {
        if (this.offers[i].id.toString() === id) {
          this.currentOffer = this.offers[i];
          this.selected = true;
          return
        }
      }
      this.selected = false;
    },
  },
  methods: {
    findSelectedFromRouteParams(id) {
      if (id > 0) {
        for (let i = 0; i < this.offers.length; i++) {
          if (this.offers[i].id === id) {
            return this.offers[i]
          }
        }
      }
      return null;
    },
    async addOffer() {
      const sampleOffer = await this.offersService.asyncSave(Offer.createSampleOffer(), null);
      this.nextId = this.nextId++;

      this.offers.push(sampleOffer)
      console.log(sampleOffer)
      this.$router.push(this.$route.matched[0].path + "/" + sampleOffer.id)
    },
    offerClicked(offer) {
      let id = this.$route.params.id;
      this.$router.push(this.$route.matched[0].path +"/" + offer.id)

      if (id === undefined) {return;}
      this.currentOffer = offer;
    },
    async onReload() {
      this.offers = [];
      this.offers = await this.offersService.asyncFindAll();
    }
  }
}
</script>
<style scoped>

</style>