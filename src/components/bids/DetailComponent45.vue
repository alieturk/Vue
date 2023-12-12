<template>
  <form class="form-control" @submit.prevent=onSave @cancel=onCancel>
    <h5 class="headings" id="idForm">Offer details (id={{ copyOffer.id }})</h5>
    <div class="form-group row">
      <label for="title" class="col-2 col-form-label">Title</label>
      <div class="col-10">
        <input id="title" name="title-form" type="text" class="form-control" :disabled="isDisabled"
               v-model="copyOffer.title">
      </div>
    </div>
    <div class="form-group row">
      <label for="description" class="col-2 col-form-label">Description</label>
      <div class="col-10">
        <input id="description" :disabled="isDisabled" name="description-form" type="text" class="form-control"
               v-model="copyOffer.description">
      </div>
    </div>
    <div class="form-group row">
      <label class="col-2 col-form-label">Status:</label>
      <div class="col-10">
        <select id="status" name="status-form" :disabled="isDisabled" class="custom-select" v-model="copyOffer.status">
          <option class="form-control" v-for="(offerStatus) in offerStatuses" :value="offerStatus" :key="offerStatus">
            {{ offerStatus }}
          </option>
        </select>
      </div>
    </div>
    <div class="form-group row">
      <label for="sellDate" class="col-2 col-form-label">Sell date:</label>
      <div class="col-10">
        <input class="form-control" :disabled="isDisabled" id="sellDate" type="date"
               :value="getDate(copyOffer.sellDate)">
      </div>
    </div>
    <div class="form-group row">
      <label for="lastBid" class="col-2 col-form-label">New bid:</label>
      <div class="col-10">
        <input class="form-control" id="lastBid" v-model="copyOffer.highestBid" @input="checkBidDifference($event)">
      </div>
    </div>
    <div class="form-group row">
      <label for="highestBid" class="col-2 col-form-label">Latest bid:</label>
      <div class="col-10">
        <input class="form-control" id="highestBid" v-model="copyOffer.bidOwner">
      </div>
    </div>
    <div class="form-group row">
      <div class="offset-2 col-10">
        <button type="button" @click="onCancel" class="btn btn-primary ml-3">Cancel</button>
        <button type="submit" :disabled="isDisabled" @click="onSave($event)" class="btn btn-primary ml-3">
          Submit
        </button>
      </div>
    </div>
  </form>
</template>

<script>
import {Offer} from "@/models/Offers.js"

export default {
  inject: ['offersService'],
  name: "DetailComponent45",
  emits: ["refresh"],
  watch: {

    currentOffer(newCurrentOffer) {
      let incomingOffer = newCurrentOffer;

      //sort highest bid
      incomingOffer.bids.sort((a, b) => {
        return b.value - a.value;
      })

      //get highest bid user and stringfy the object
      let bidOwner = JSON.stringify(incomingOffer.bids[0].user);

      //add bidOwner as new key and pass JSON (stringfied) data
      incomingOffer = {
        ...incomingOffer,
        bidOwner,
        highestBid: incomingOffer.bids[0].value + 1
      }

      this.copyOffer = Offer.copyConstructor(incomingOffer)
    },
    async '$route'() {
      let id = this.$route.params.id;
      if (id != null) {
        this.currentOffer = await this.offersService.asyncFindById(id);
      }
    },
  },
  async created() {
    this.copyOffer = Offer.copyConstructor(this.currentOffer)
    this.currentOffer = await this.offersService.asyncFindById(this.$route?.params?.id);

  },
  data() {
    return {
      offerStatuses: Offer.statusList,
      copyOffer: '',
      currentOffer: 0,
      selected: false,
      isDisabled: true
    }
  },
  computed: {
    hasChanged() {
      return JSON.stringify(Offer.copyConstructor(this.currentOffer)) === JSON.stringify(Offer.copyConstructor(this.copyOffer))
    }
  },
  methods: {
    async onSave() {
      const user = window.localStorage.getItem("CurrentUser");
      let bid = this.copyOffer.highestBid
      bid.user = JSON.parse(user)
      console.log(bid)
      await this.offersService.asyncPostBid(this.$route?.params?.id, bid)
      this.$emit('refresh')
    },
    onCancel() {
      this.$router.push(this.$route.matched[0].path)
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
    },
    checkBidDifference(newBid) {
      if (parseInt(newBid.target.value) > this.currentOffer.highestBid) {
        console.log("New Bid is Higher", parseInt(newBid.target.value))
        this.isDisabled = false;
      } else {
        console.log("New Bid is Lower", parseInt(newBid.target.value))
        this.isDisabled = true;
      }
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