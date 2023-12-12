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
              <td @click="created(offer)"> {{ offer.title + "-" + offer.id }}</td>
            </tr>
            </tbody>
          </table>
          <div class="button-add float-right">
            <button type="button" class="list-group-item list-group-item-action active" @click="onNewOffer">Add
              offer
            </button>
          </div>
        </div>
        <div class="col pt-5 mt-3">
          <router-view :currentOffer="currentOffer" v-on:updateOffer="update($event)"
                       @app-offers-detail="deleteOffer"></router-view>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Offer } from '@/models/Offers'

export default {
  name: "Overview34",
  components: {
  },
  watch: {
    '$route'() {
      let id = this.$route.params.id;
      for (let i = 0; i < this.offers.length; i++) {
        if (this.offers[i].id.toString() === id) {
          this.currentOffer = this.offers[i];
        }
      }
    },
  },
  data() {
    return {
      currentOffer: 0,
      nextId: 30000,
      offers: [],
      deep: true
    }
  },
  created() {
    for (let index = 0; index < 8; index++) {
      this.offers.push(Offer.createSampleOffer(this.nextId))
      this.nextId = this.nextId + 3;
    }
  },
  methods: {
    update(eventData) {
      Object.keys(eventData).forEach((key) => {
        this.currentOffer[key] = eventData[key];
      })
    },
    onNewOffer() {
      this.offers.push(Offer.createSampleOffer(this.nextId))
      this.nextId = this.nextId + 3;
    },
    created(offer) {
      let id = this.$route.params.id;
      this.$router.push(this.$route.matched[0].path +"/" + offer.id)

      if (id === undefined) {
        return;
      }
      this.currentOffer = offer;
    },
    deleteOffer(selectedId) {
      this.offers = this.offers.filter(offer => offer.id !== selectedId);
      this.$router.push(this.$route.matched[0].path)
      this.currentOffer = 0;
    },

    }
}
</script>
<style scoped>

</style>