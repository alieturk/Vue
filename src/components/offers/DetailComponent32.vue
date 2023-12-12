<template>
  <h5 v-if="currentOffer === 0">Select an offer</h5>
  <table v-else class="table">
    <h5>Offer details (id={{ currentOffer.id }})</h5>
    <tbody class="align-middle">
    <tr>
      <td class="table-background">Title:</td>
      <td>
        <input id="title" type="text" v-model="mutableOffer.title">
      </td>
    </tr>
    <tr>
      <td class="table-background">Description:</td>
      <td>
        <input id="description" type="text" v-model="mutableOffer.description">
      </td>
    </tr>
    <tr>
      <td class="table-background">Status:</td>
      <td>
        <select id="status" v-model="mutableOffer.status">
          <option v-for="(offerStatus) in offerStatuses" :value="offerStatus" :key="offerStatus">{{ offerStatus }}
          </option>
        </select>
      </td>
    </tr>
    <tr>
      <td class="table-background">Sell date:</td>
      <td>
        <input id="sellDate" type="date" :value="getDate(mutableOffer.sellDate)" v-on:change="updateParent">
      </td>
    </tr>
    <tr>
      <td class="table-background">Highest bid:</td>
      <td>
        <input id="highestBid" type="text" v-model="mutableOffer.highestBid">
      </td>
    </tr>
    </tbody>
  </table>
  <div class="button-add float-right">
    <button type="button" class="btn btn-primary ml-3 btn-lg"
            @click="$emit('app-offers-detail', currentOffer.id)">Delete
    </button>
    <button type="button" class="btn btn-primary ml-3 btn-lg "
    >Clear
    </button>
    <button type="button" class="btn btn-primary ml-3 btn-lg "
    >Reset
    </button>
    <button type="button" class="btn btn-primary ml-3 btn-lg"
    >Cancel
    </button>
    <button type="button" class="btn btn-primary ml-3 btn-lg "
    >Save
    </button>
  </div>
</template>

<script>
import {Offer} from "@/models/Offers.js"

export default {
  name: "DetailComponent32",
  emits: ["app-offers-detail"],
  props: [
    "currentOffer",
  ],
  data() {
    return {
      offerStatuses: Offer.statusList
    }
  },
  computed: {
    mutableOffer() {
      return this.currentOffer
    }
  },
  methods: {
    getDate(offerDate) {
      if (offerDate !== null) {
        let year = offerDate.getYear() + 1900;
        let month = offerDate.getMonth() + 1;
        let day = offerDate.getDate();
        if (day < 10) {
          day = "0" + day;
        }
        if (month < 10) {
          month = "0" + month;
        }
        return year + "-" + month + "-" + day;
      }
      return null;
    },
    updateParent() {
      const emitData = {
        title: this.mutableOffer.title,
        description: this.mutableOffer.description,
        status: this.mutableOffer.status,
        sellDate: this.currentOffer.sellDate,
        highestBid: this.mutableOffer.highestBid,
      }
      this.$emit('updateOffer', emitData)
    }
  }
}
</script>

<style scoped>
td {
  width: 200px;
}
.table-background {
  background: #f3c797;
}
</style>