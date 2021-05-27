<template>
<div >
  <b-navbar toggleable="lg" type="light" variant="light">
    <b-navbar-brand href="#" id="logo"> <img src="http://placehold.it/120x40" class="d-inline-block align-top" alt="Logo"></b-navbar-brand>
    <b-nav-form>
        <b-form-input size="sm" class="mr-sm-2" id = "inputSearch" placeholder="Search"></b-form-input>
    </b-nav-form>
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <b-nav-item href="#" class="item">HOME</b-nav-item>
        <b-nav-item href="#" class="item">CLUBS</b-nav-item>
        <b-nav-item href="#" class="item">YOUR CLUBS</b-nav-item>
        <b-nav-item href="#" class="item">YOUR EVENTS</b-nav-item>
      </b-navbar-nav>

      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto" >
        <b-nav-item-dropdown right toggle-class="p-0" v-if="username">
          <!-- Using 'button-content' slot -->
          <template #button-content > 
            <b-avatar src="https://placekitten.com/300/300" size="2.5rem"></b-avatar>
          </template>
          <b-dropdown-item :href="'/users/'+username">Profile</b-dropdown-item>
          <b-dropdown-item href="/logout">Sign Out</b-dropdown-item>
        </b-nav-item-dropdown>

        <b-nav-item right href="/login" class="item" v-if="!username">Sign In</b-nav-item>
        <b-nav-item right href="/account/register" class="item" v-if="!username">Register</b-nav-item>

      </b-navbar-nav>
    </b-collapse>

  </b-navbar>
</div>
</template>

<script>

export default {
  name: 'NavBar',
  props: {
    username: {type: String, default: ""}
  }
}
</script>

<style>
#inputSearch{
  background-color:#f8f9fa;
  border-radius: 4px;
  width: 260px;
  border-block-color: black;
  border-left-color: #000000;
  border-right-color: #000000;
  padding-right: 25px;
  /* font-family: 'Roboto'; */
}
.item{
  /* font-family: Roboto; */
  font-size: 12px;
  line-height: 14px;
  text-align: center;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  color: black;
  padding-left: 8px;
}
.userNavBar{
  border: 1px solid #000000;
  box-sizing: border-box;
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover; 
}
</style>
