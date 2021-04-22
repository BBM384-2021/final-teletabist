<template>
  <div class="mynavbar">
   <b-navbar type="light" class="w-100" id="Navbar">
   
    <b-navbar-brand href="#" id="logo"> <img src="http://placehold.it/140x60" class="d-inline-block align-top" alt="Logo"></b-navbar-brand>
    
    <b-nav-form>
          <b-form-input size="sm" class="mr-sm-2" id = "inputSearch" placeholder="SEARCH"></b-form-input>
        </b-nav-form>

    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav class="ml-4">
        <b-nav-item href="/clubs" class="item">Home</b-nav-item>
      </b-navbar-nav>

      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">
        <!-- <img  src="http://placehold.it/50x50" alt="notification" class="notification"> -->
        <div style="font-size: 2rem; line-height:64px" class="align-middle d-inline">
            <b-icon icon="bell-fill" class="border rounded p-2 align-middle"></b-icon>
        </div>
        <b-badge style="height:18px; margin-top:20px">5</b-badge>

        <b-nav-item-dropdown right v-if="username">
          <!-- Using 'button-content' slot -->
          <template #button-content>  
            
            <b-avatar class="userNavBar" src="https://placekitten.com/300/300" size="4rem"></b-avatar>
          </template>
          <b-dropdown-item :href="'/users/'+username">Profile</b-dropdown-item>
          <b-dropdown-item href="/logout">Sign Out</b-dropdown-item>
        </b-nav-item-dropdown>
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

#Navbar{
  background-color: #C4C4C4;
  top : 0;
  left: 200;
  right: 0;
  height: 80px;
}
.logo{
  left: 20%;
}
#inputSearch{
  background-color: #C4C4C4;
  border-radius: 4px;
  width: 260px;
  border-block-color: black;
  border-left-color: #000000;
  border-right-color: #000000;
  padding-right: 25px;
  font-family: 'Roboto';
}
.item{
  font-family: Roboto;
  font-size: 12px;
  line-height: 14px;
  text-align: center;
  letter-spacing: 0.2em;
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
#notification{
  /* width: 50px;
  height: 50px; */
  border: 1px solid #000000;
  box-sizing: border-box;

  background-position: center center;
  background-size: cover;
}

</style>
