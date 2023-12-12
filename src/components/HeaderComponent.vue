<template>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <div class="container text-center">
    <div class="row">
      <div class="col-sm">
          <img id="rightLogo" src="../assets/auction.png" alt="auction">
      </div>
      <div class="col-sm pt-3">
          <h1>The Auctioneer</h1>
          <h2>HvA, Webframeworks</h2>
          <h2>Datum: {{ date.toLocaleString().substring(0, 9) }}</h2>
      </div>
      <div class="col-sm">
          <img id="leftLogo" src="../assets/money-bag.png" alt="money bag">
      </div>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      date: new Date()
    }
  },
  name: "HeaderComp",
}
</script>

<style scoped>
.headerDiv {
  background: #FFF2E1;
}
img {
  width: 60%;
  height: 60%;
  object-fit: contain;
  align-items: center;
}

h1 {
  font-size: var(--font_2);
  color: var(--font_color);
  font-weight: 600;
  grid-row: 1;
  grid-column: 2;
  padding-top: 20px;
}

h2 {
  font-size: var(--font_4);
  font-weight: 500;
  color: var(--font_color);
  grid-row: 2;
  grid-column: 2;
}

#rightLogo {
  width: 70px;
  height: auto;
  padding-top: 70px;
}

#leftLogo {
  width: 70px;
  height: auto;
  padding-top: 70px;
}
@media(max-width: 580px) {
  #rightLogo {
    display: none;
  }
  #leftLogo {
    display: none;
  }
}
</style>