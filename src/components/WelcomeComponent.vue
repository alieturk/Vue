<template>
  <div class="contentComp">
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm float-left">
          <div class="menu">
            <h4 class="pb-2">Pick a date</h4>
            <input id="dateinput" type="date">
            <h4 class="pb-1 pt-4">Pick a category</h4>
            <ul>
              <a href="./">
                <li>Antiques</li>
              </a>
              <a href="./">
                <li>Cars</li>
              </a>
              <a href="./">
                <li>Event tickets</li>
              </a>
              <a href="./">
                <li>Hollidays</li>
              </a>
              <a href="./">
                <li>Collectables</li>
              </a>
            </ul>
          </div>
        </div>
        <div class="col-sm gallery float-left">
          <div>
            <h4 class="gallery-title pb-2">Auctions that expire today:</h4>
            <a class="gallery">
              <div class="container float-left">
                <div class="row">
                  <div class="col no-padding" href="#">
                    <a href="#img1"><img src="../assets/image1.jpg" alt="img"></a>
                  </div>
                  <div class="col no-padding">
                    <a href="#img2"><img src="../assets/image2.jpg" alt="img"></a>
                  </div>
                  <div class="col no-padding">
                    <a href="#img3"><img src="../assets/image3.jpg" alt="img"></a>
                  </div>
                  <div class="col no-padding">
                    <a href="#img4"><img src="../assets/image4.jpg" alt="img"></a>
                  </div>
                  <div class="col no-padding">
                    <a href="#img5"><img src="../assets/image5.jpg" alt="img"></a>
                  </div>
                  <div class="col no-padding">
                    <a href="#img6"><img src="../assets/image6.jpg" alt="img"></a>
                  </div>
                  <div class="col no-padding">
                    <a href="#img7"><img src="../assets/image7.jpg" alt="img"></a>
                  </div>
                  <div class="col no-padding">
                    <a href="#img8"><img src="../assets/image8.jpg" alt="img"></a>
                  </div>
                  <div class="col no-padding">
                    <a href="#img9"><img src="../assets/image9.jpg" alt="img"></a>
                  </div>
                </div>
              </div>
            </a>
          </div>
        </div>
        <div class="col-sm">
          <div class="activities pb-2">
            <h4>Activities</h4>
            <p>Design the Header component template to hold a background-image, two (logo)
              pictures left and right, a title and a sub-title. Store the pictures in src/assets.
              Make sure the header is responsive such that its logos use fixed size and the title
              space scales with the size of the window. The subtitle should be justified to the right.

              Design the Welcome component template to display its content in three columns.
              The left and right columns have a fixed width, the centre column scales with the
              window. The left-hand side of the page contains a date picker and a list of locations
              displaying how many cabins still are available per location at the given date. The
              right-hand side column has some interesting information about a special event.</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "WelcomeComponent",
}
</script>

<style scoped>
.gallery {
  min-width: 750px;
}
.gallery img {
  object-fit: cover;
  width: 240px;
  height: 240px;
  padding-right: 10px;
  padding-bottom: 10px;
}
.no-padding {
  padding: 0 !important;
  margin: 0 !important;
}
#dateinput {
  width: 250px;
  height: 42px;
}
.menu {
  min-width: 300px;
  order: 1;
}
.activities {
  min-width: 150px;
  width: 400px;
}
@media(max-width: 1100px) {
  .activities {
    order: 2;
  }
  .gallery {
    order: 3;
  }
}
@media(max-width: 750px) {
  .gallery {
    min-width: 500px;
  }
}
@media(max-width: 550px) {
  .gallery {
    min-width: 300px;
  }
}
@media(max-width: 400px) {
  .activities {
    max-width: 300px;
  }
}
</style>