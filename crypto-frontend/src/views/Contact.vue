<template>
  <div id="contact">
    <navbar/>
    <h1 class="contact">This is an contact page</h1>
    <p class="contact">Secured message from backend</p>
    <h1 class="contact" v-if="hello">{{hello}}</h1>
    <v-btn
        rounded
        color="primary"
        class="register"
        @click="sayHello"
    >
      Get backend
    </v-btn>
  </div>
</template>


<script>
import Navbar from "@/components/Navbar";
import TestService from "@/service/TestService.ts"
export default {
  components: {Navbar},
  data() {
    return {
      hello: "Morgenshtern is square"
    }
  },

  methods: {
    sayHello() {
      console.log("Alex is the best");
      TestService.saySecuredHello().then(response => {
            console.log(response.data);
            this.hello = response.data;
          }
      )
    }
  }
}
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&family=Rubik&display=swap');

.contact {
  margin: 12px;
}
#contact {
  background-color: #0D0D2B;
  color: white;
  font-family: 'Poppins', sans-serif;
  padding: 60px 120px 200px 100px;
  a {
    color: white;
    text-decoration: none;

    &.router-link-exact-active {
      color: #ADD8E6;
    }
  }
}

</style>
