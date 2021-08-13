<template>
  <div id="header">
    <navbar />
    <v-container>
      <v-row>
        <v-col>
          <h1 class="slogan">The best platform to <br> learn and invest in <br> crypto</h1>
          <p class="smalltext">Buy and sell cryptocurrencies, earn money and gain <br>experience.</p>
          <br>
          <br>
          <v-btn
              rounded
              color="primary"
              class="try-button"
          >
            <router-link to="/register">Try it now</router-link>
          </v-btn>
        </v-col>
        <v-col>
          <v-img
              :src="require('../assets/Bitcoin.svg')"
              class="bitcoin"
              contain
              height="650"
          />
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script lang="ts">
  import Vue from 'vue'
  import Navbar from "@/components/Navbar.vue";

  export default Vue.extend({
    name: 'Home',

    components: {
      Navbar,
    },
  })
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&family=Rubik&display=swap');

//font-family: 'Poppins', sans-serif;
//font-family: 'Rubik', sans-serif;

#header {
  background-color: #0D0D2B;
  color: white;
  font-family: 'Poppins', sans-serif;
  padding: 60px 120px 200px 120px;
  a {
    color: white;
    text-decoration: none;

    &.router-link-exact-active {
      color: #ADD8E6;
    }
  }
}

.smalltext {
  color: #E0E0E0;
  font-size: 1vw;;
}

.slogan {
  padding-top: 110px;
  padding-bottom: 60px;
  font-size: 3.3vw;
  line-height: 82px;
  font-weight: 600;
}

.try-button {
  width: 135px !important;
  height: 50px !important;
  border-radius: 32px !important;
  font-weight: bold !important;
  font-family: 'Rubik', sans-serif !important;
  text-transform: none !important;
}

.bitcoin {
  margin-top: 40px;
}

.btn {
  color: white;

  a {
    color: white;
  }
  border-radius: 32px;
  //background: #3671E9;
  background: white;
  display: block;
  width: 150px;
  height: 50px;
  text-align: center;
  align-items: center;
}

.main-part {
  margin: 0;
  padding: 0;
}
</style>