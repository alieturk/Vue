<template>
  <h5 class="header-form" v-if="currentOffer === 0">Select an offer</h5>
  <form v-else class="form-control " @submit.prevent=onSave @cancel = onCancel>
    <h5 class="headings" id="idForm">Offer details (id={{ copyOffer.id }})</h5>
    <div class="form-group row">
      <label for="title" class="col-2 col-form-label">Title</label>
      <div class="col-10">
        <input id="title" name="title-form" type="text" class="form-control" v-model="copyOffer.title">
      </div>
    </div>
    <div class="form-group row">
      <label for="description" class="col-2 col-form-label">Description</label>
      <div class="col-10">
        <input id="description" name="description-form" type="text" class="form-control"
               v-model="copyOffer.description">
      </div>
    </div>
    <div class="form-group row">
      <label for="status" class="col-2 col-form-label">Status:</label>
      <div class="col-10">
        <select id="status" name="status-form" class="custom-select" v-model="copyOffer.status">
          <option class="form-control" v-for="(offerStatus) in offerStatuses" :value="offerStatus" :key="offerStatus">
            {{ offerStatus }}
          </option>
        </select>
      </div>
    </div>
    <div class="form-group row">
      <label for="sellDate" class="col-2 col-form-label">Sell date:</label>
      <div class="col-10">
        <input class="form-control" id="sellDate" type="date" :value="getDate(copyOffer.sellDate)">
      </div>
    </div>
    <div class="form-group row">
      <label for="highestBid" class="col-2 col-form-label">Highest bid:</label>
      <div class="col-10">
        <input class="form-control" id="highestBid" type="number" v-model="copyOffer.highestBid">
      </div>
    </div>
    <div class="form-group row">
      <div class="offset-2 col-10">
        <button type="button" class="btn btn-primary" :disabled="!isButtonDisabled()"
                @click="[$emit('app-offers-detail', copyOffer.id), onCancel]">Delete
        </button>
        <button type="reset" class="btn btn-primary ml-3"
        >Clear
        </button>
        <button type="button" @click="onReset" :disabled="isButtonDisabled()" class="btn btn-primary ml-3"
        >Reset
        </button>
        <button type="button" @click= "onCancel" class="btn btn-primary ml-3"
        >Cancel
        </button>
        <button type="submit" :disabled="isButtonDisabled()" @click="onSave" class="btn btn-primary ml-3">Save</button>
      </div>
    </div>
  </form>
</template>

<script>
import {Offer} from "@/models/Offers.js"

export default {
  name: "DetailComponent34",
  emits: ["update-offer", "app-offers-detail"],
  props: [
    "currentOffer"
  ],
  watch: {
    currentOffer(newCurrentOffer) {
      this.copyOffer = Offer.copyConstructor(newCurrentOffer)
    }
  },
  created() {
    this.copyOffer = Offer.copyConstructor(this.currentOffer)
  },
  data() {
    return {
      offerStatuses: Offer.statusList,
      copyOffer: '',
    }
  },
  computed: {
    mutableOffer() {
      return this.currentOffer
    },
    hasChanged() {
      return JSON.stringify(Offer.copyConstructor(this.currentOffer)) === JSON.stringify(Offer.copyConstructor(this.copyOffer))
    }
  },
  methods: {
    onSave() {
      this.$emit('updateOffer', this.copyOffer)
      this.$router.push(this.$route.matched[0].path)
    },
    onReset() {
      this.copyOffer = Offer.copyConstructor(this.currentOffer)
    },
    onCancel() {
      this.$router.push(this.$route.matched[0].path)
    },
    isButtonDisabled() {
      return this.hasChanged
    },
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
    }
  }
}
</script>

<style scoped>
td {
  width: 200px;
}

.headings {
  margin-bottom: 20px;
}

#title, #description, #highestBid, #sellDate, #status {
  width: 400px;
}
</style>