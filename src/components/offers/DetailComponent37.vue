<template>
  <form class="form-control" @submit.prevent=onSave @cancel = onCancel>
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
      <label class="col-2 col-form-label">Status:</label>
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
                @click="onDelete()">Delete
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
        <button type="submit" :disabled="isButtonDisabled()" @click="onSave($event)" class="btn btn-primary ml-3">Save</button>
      </div>
    </div>
  </form>
</template>

<script>
import {Offer} from "@/models/Offers.js"

export default {
  inject: ['offersService'],
  name: "DetailComponent37",
  emits: ["refresh"],
  watch: {
    currentOffer(newCurrentOffer) {
      this.copyOffer = Offer.copyConstructor(newCurrentOffer)
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
    }
  },
  computed: {
    hasChanged() {
      return JSON.stringify(Offer.copyConstructor(this.currentOffer)) === JSON.stringify(Offer.copyConstructor(this.copyOffer))
    }
  },
  methods: {
    async onDelete() {
      await this.offersService.asyncDeleteById(this.currentOffer.id);
      this.$emit('refresh')
      this.$router.push(this.$route.matched[0].path)
      this.selected = false;
    },
    async onSave() {
      console.log(this.copyOffer.sellDate)
      await this.offersService.asyncSave(this.currentOffer, this.copyOffer);
      this.$emit('refresh')
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