<template>
<h4 class="pl-5">Please provide your login credentials: </h4>
  <div class="ml-5">
    <table class="ml-5 mt-4 w-50 sm-w-100 table table-bordered">
      <tr>
        <th class="bg-color text-right">User e-mail:</th>
        <td><input v-model="emailInput" class="w-100" placeholder="Enter your email"></td>
      </tr>
      <tr>
        <th class="bg-color text-right">Password:</th>
        <td><input v-model="passwordInput" class="w-100" placeholder="Enter your password"></td>
      </tr>
    </table>
    <div class="ml-5 w-50 text-right">
      <button @click="signIn" class="px-3 py-2">Sign In</button>
    </div>

    <div v-if="showSignInError" class="ml-5 w-50">
      <h6 class="text-danger">{{ errorText }}</h6>
    </div>
  </div>
</template>

<script>
export default {
  inject: ["sessionService"],
  name: "SignIn",
  data() {
    return {
      showSignInError: false,
      errorText: "",
      emailInput: null,
      passwordInput: null,
      user: "userName"
    }
  },
  methods: {
    async signIn() {
      const service = this.sessionService;
      if (service.isAuthenticated()) {
        this.errorText = "You already signed in"
        this.showSignInError = true;
        return;
      }
      const user = await service.asyncSignIn(this.emailInput, this.passwordInput);
      if (user == null) {
        this.errorText = "Could not authenticate with given credentials";
        this.showSignInError = true;
      } else {
        this.showSignInError = false;
        this.name = user.name;
        this.sessionService.authenticated = this.sessionService.isAuthenticated();
      }
    }
  }
}
</script>
<style scoped>
.bg-color {
  background: #e8be91;
}
</style>