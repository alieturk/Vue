<template>
  <div class="list-group">

    <div class="container-fluid">
      <div class="row">
        <div class="col">
          <button type="button" class="list-group-item list-group-item-action active">
            All offers(on sale):
          </button>
          <table class="table table-hover">
            <thead>
            <th>Title</th>
            <th>Highest bid</th>
            <th>Made by</th>
            </thead>
            <tbody class="align-middle">
            <tr v-for="(offer) in offers" :key="offer">
              <td @click="offerClicked(offer)"> {{ offer.title + "-" + offer.id }}</td>
              <td @click="offerClicked(offer)"> {{ offer.highestBid }}</td>
              <td @click="offerClicked(offer)"> {{ offer.madeBy }}</td>
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
import {Offer} from '@/models/Offers'

export default {
  inject: ["offersService"],
  name: "Overview45",
  components: {},
  data() {
    return {
      currentOffer: 0,
      offers: [],
      selected: false,
      statusList: Offer.statusList
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
        return this.offers.find(x => x.id === id);
      }
      return null;
    },
    async addOffer() {
      const sampleOffer = await this.offersService.asyncSave(Offer.createSampleOffer(), null);
      this.nextId = this.nextId++;

      this.offers.push(sampleOffer)
      this.$router.push(this.$route.matched[0].path + "/" + sampleOffer.id)
    },
    offerClicked(offer) {
      let id = this.$route.params.id;
      this.$router.push(this.$route.matched[0].path + "/" + offer.id)

      if (id === undefined) {
        return;
      }
      this.currentOffer = offer;
    },
    async onReload() {
      this.offers = [];
      this.offers = await this.offersService.asyncFindAllForSale();

      this.offers = this.offers.map((offer) => {
        offer.bids.sort((a, b) => {
          return b.value - a.value
        })

        return {
          ...offer,
          highestBid: offer.bids[0].value,
          madeBy: offer.bids[0].user.name
        }
      })
    }
  }
}
</script>
<style scoped>

</style>