<template>
  <div id="about">
    <navbar/>
    <h1>This is an about page</h1>
  </div>
</template>


<script>
import Navbar from "@/components/Navbar";
export default {
  components: {Navbar}
}
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&family=Rubik&display=swap');

#about {
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
  .main-text {

  }
}

</style>


