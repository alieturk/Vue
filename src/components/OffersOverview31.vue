<template>
  <div class="container-fluid px-1 py-5 mx-auto">
    <div class="row d-flex justify-content-center">
      <div class="col-xl-7 col-lg-8 col-md-9 col-11 text-left">
        <h3 class="display-8">List of all offers:</h3>
        <div class="card" >
          <table class="table table-hover" >
            <thead>
            <tr>
              <th scope="col" class="text-white">Id</th>
              <th scope="col" class="text-white">Title</th>
              <th scope="col" class="text-white">Selling date</th>
              <th scope="col" class="text-white">Status</th>
              <th scope="col" class="text-white">Value Highest Bid</th>
            </tr>
            </thead>
            <tbody class="align-middle">
            <tr v-for="offer in offers" :key = "offer">
              <td> {{ offer.id}} </td>
              <td> {{ offer.title}} </td>
              <td> {{ offer.sellDate.toLocaleString()}} </td>
              <td> {{ offer.status}} </td>
              <td v-show="offer.highestBid !== 0"> ${{ offer.highestBid}} </td>
            </tr>
            </tbody>
          </table>
          <button class="col-md-12 bg-light text-right" @click ="onNewOffer">New Offer</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {Offer} from "@/models/Offers";

export default {
  data() {
    return {
      nextId : 30000,
      offers : [],
    }
  },
  name: "OffersOverview31",

  created() {
    for (let index = 0; index < 8; index++){
      this.offers.push(Offer.createSampleOffer(this.nextId))
      this.nextId = this.nextId +3;
    }

  },
  methods: {
    onNewOffer() {
      this.offers.push(Offer.createSampleOffer(this.nextId))
      this.nextId = this.nextId +3;
    }
  }
}

</script>

<style scoped>

thead {
  background-color: #542A09;
}

</style>