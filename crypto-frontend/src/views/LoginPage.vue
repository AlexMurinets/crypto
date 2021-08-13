<template>
  <div>
    <v-card width="400" class="mx-auto mt-5">
      <v-card-title>
        <h1 class="display-1">Login</h1>
      </v-card-title>
      <v-card-text>
        <v-form
            @submit.prevent="handleLogin"
            id="login-form">
          <v-text-field
              v-model="user.username"
              type="text"
              label="username"
              prepend-icon="mdi-account-circle"
              name="username"
          />
          <v-text-field
              v-model="user.password"
              label="password"
              :type="showPassword ? 'text' : 'password'"
              prepend-icon="mdi-lock"
              :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
              @click:append="showPassword = !showPassword"
              name="password"
          />
        </v-form>
      </v-card-text>
      <v-divider></v-divider>
      <v-card-actions>
        <v-btn color="success" @click="$router.push({ path: '/register' })">Register</v-btn>
        <v-spacer></v-spacer>
        <v-btn color="info" type="submit" form="login-form">Login</v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
const Auth = namespace("Auth");


@Component
export default class Login extends Vue {
  private user: any = { username: "", password: "" };
  private showPassword = false;

  @Auth.Getter
  private isLoggedIn!: boolean;

  @Auth.Action
  private login!: (data: any) => Promise<any>;

  created() {
    if (this.isLoggedIn) {
      this.$router.push("/profile");
    }
  }

  handleLogin() {
      if (this.user.username && this.user.password) {
        this.login(this.user).then(
            (data) => {
              this.$router.push("/contacts");
            },
            (error) => {
              console.log("Not good boy")
            }
        );
      }
  }
}

</script>

<style scoped>

</style>