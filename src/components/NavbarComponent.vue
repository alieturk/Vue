<template>
  <div class="navbar1">
    <router-link to="/home">
      Home
    </router-link>
    <router-link to="/offers/overview31" class="dropdown1">
      <div>
        <button class="dropbtn">My Offers
          <i class="fa fa-caret-down"></i>
        </button>
        <div class="dropdown1-content">
          <router-link to="/offers/overview31">
            All offers overview
          </router-link>
          <router-link to="/offers/overview32">
            Offers Details (comp)
          </router-link>
          <router-link to="/offers/overview33">
            Offers edit (router)
          </router-link>
          <router-link to="/offers/overview34">
            Offers edit (managed)
          </router-link>
          <router-link to="/offers/overview37">
            Offers edit (3.7)
          </router-link>
        </div>
      </div>
    </router-link>
    <router-link to="/bids/overview45" class="dropdown1">
      <div>
        <button class="dropbtn">My Bids
          <i class="fa fa-caret-down"></i>
        </button>
        <div class="dropdown1-content">
          <router-link to="/bids/overview45">
            My bids
          </router-link>
        </div>
      </div>
    </router-link>
    <router-link to="/my-account">
      My Account
    </router-link>
    <router-link to="/log-in" class="float-right" >
      Log in
    </router-link>
    <router-link to="/sign-up" class="float-right">
      Sign up
    </router-link>
  </div>
</template>

<script>
export default {
  name: "NavbarComponent",
}
</script>

<style scoped>
a.router-link-active {
  background: #994C0F;
  color: white;
}
.dropbtn.router-link-active {
  background: #994C0F;
  color: white;
}
.navbar1 {
  overflow: hidden;
  background-color: var(--font_color);
  font-family: Arial;
  margin-bottom: 30px;
  font-size: 13px;
}
.navbar1 a {
  float: left;
  font-size: 16px;
  color: white;
  text-align: center;
  padding: 10px 16px;
  text-decoration: none;
}
.dropdown1 {
  float: left;
  overflow: hidden;
}
.dropdown1 .dropbtn {
  font-size: 16px;
  border: none;
  outline: none;
  color: white;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}
.navbar1 a:hover, .dropdown1:hover .dropbtn {
  background-color: #994C0F !important;
  text-decoration: none;
  color: white;
}
.dropdown1-content {
  margin-left: -16px;
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
  margin-top: 10px;
}
.dropdown1-content a {
  float: none;
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}
.dropdown1-content a:hover {
  background-color: #ddd;
}
.dropdown1:hover .dropdown1-content {
  display: block;
}
</style>