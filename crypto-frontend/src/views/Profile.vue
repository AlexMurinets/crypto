<template>
  <div id="profile">
    <h1>Nice to meet you</h1>
    <h1>{{ user.id }}</h1>
    <h1>{{ user.username }}</h1>
    <h1>{{ user.email }}</h1>
    <h1> {{user.status}}</h1>
  </div>
</template>

<script lang="ts">
import {Component, Vue} from "vue-property-decorator";
import UserService from "@/service/UserService";
import User from "@/models/User";
import {namespace} from "vuex-class";

const Auth = namespace("Auth");

@Component
export default class UserProfile extends Vue {
  private user: User = {
    id: null,
    email: "",
    username: "",
    status: "",
  };

  @Auth.Getter
  private isLoggedIn!: boolean;

  created() {
    if (!this.isLoggedIn) {
      this.$router.push("/login");
    }
    else {
        const profile = localStorage.getItem('user');
        const user = JSON.parse(profile ? profile : "");
        const userId: bigint = user.id;
        console.log("Nice to meet you");
        UserService.getUserInfo(userId).then(response => {
              console.log(response.data);
              this.user.id = response.data.id;
              this.user.email = response.data.email;
              this.user.username = response.data.username
              this.user.status = response.data.status
            }
        )
    }
  }
}
</script>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&family=Rubik&display=swap');

#profile {
  background-color: #0D0D2B;
  color: white;
  font-family: 'Poppins', sans-serif;
  padding: 60px 120px 200px 120px;
}
</style>