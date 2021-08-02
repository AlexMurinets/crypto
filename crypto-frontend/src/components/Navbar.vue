<template>
  <div id="nav">
      <div class="logo">
        <p>MURDOL CRYPTO</p>
      </div>
    <div class="right-part">
      <ul class="menu">
        <li>
      <router-link to="/">Home</router-link>
        </li>
        <li>
      <router-link to="/about">About</router-link>
        </li>
        <li>
          <router-link to="/currencies">Currencies</router-link>
        </li>
        <li>
          <router-link to="/contacts">Contact</router-link>
        </li>
        <li class="special">
          <router-link to="/login">Login</router-link>
        </li>
        <li class="certa">
          |
        </li>
      </ul>
      <v-btn
          rounded
          color="primary"
          class="register"
      >
        <router-link to="/register">Register</router-link>
      </v-btn>
    </div>
  </div>
</template>

<script>
export default {
name: "Navbar"
}
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&family=Rubik&display=swap');

//font-family: 'Poppins', sans-serif;
//font-family: 'Rubik', sans-serif;
.certa {
  opacity: 0.3;
}


.right-part {
  //padding-right: 90px;
  display: flex;
  margin-left: auto;
}

p {
  color: white;
}

.logo {
  font-size: 18px;
  align-items: center;
  color: white;
  display: flex;
}

.menu {
  align-items: center;
  display: flex;
}

.special {
  font-weight: bold;
  margin-left: 40px;
  margin-right: 3px;
}

li, ul {
  color: white;
  padding: 0;
  margin: 0;
  list-style-type: none;
}

.register {
  width: 105px !important;
  height: 50px !important;
  border-radius: 32px !important;
  font-weight: bold !important;
  font-family: 'Rubik', sans-serif !important;
  text-transform: none !important;
}

.menu li {
  margin-right: 40px;
}

.menu li a {
  font-size: 16px;
  text-decoration: none;
}

#nav {
  padding-left: 12px;
  padding-right: 12px;
  align-items: center;
  display: flex;
  font-family: 'Rubik', sans-serif;

  a {
    color: white;
    text-decoration: none;

    &.router-link-exact-active {
      color: #ADD8E6;
    }
  }
}
</style>