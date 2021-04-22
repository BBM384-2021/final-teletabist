<template>
  <div class="showProfileEdit overflow-hidden " >
    <b-row class="m-0 h-100" >

        <b-col cols="8">
        <b-row  class="h-100 mt-4" >
          <b-col cols="12" >
                  <a class="text-dark text-decoration-none"  v-if="profileUrl != ''" :href="profileUrl" >
                    <span style="font-size: 28px;">{{name}}</span>
                  </a>
                  <span v-else style="font-size: 28px;">{{name}}</span>
                
                
                <hr class="profileHr">
                <b-row>
                    <b-col cols="3">

                    </b-col>
                        <b-col cols="6">
                        <b-form-input type="text"  size="sm"  style="height:37px;" placeholder="Name" id="name" name="name" v-bind:value="name"></b-form-input>
                        <br>
                        <b-form-input type="text" size="sm" style="height:37px;" placeholder="username" id="username" name="username" v-bind:value="username"></b-form-input>
                        <br>
                        <b-form-input type="text" size="sm" style="height:37px;" placeholder="Location" id="location" name="location" v-bind:value="location"></b-form-input>
                        <br>
                        <b-form-input type="date" size="sm" style="height:37px;" placeholder="Birthday" id="birthday" name="birthday" v-bind:value="birthday"></b-form-input>
                        <br>
                        <b-form-textarea size="sm" style="height:60px;" placeholder="Biography" class="mb-2"  id="biography" name="biography" v-bind:value="biography"></b-form-textarea>
                        <b-button type="submit" class="float-right" style="color: black;font-size: 16px;">SAVE</b-button>

                    </b-col>
                    <b-col cols="3">
                    </b-col>
                </b-row>                              
               
            </b-col> 
        </b-row>
        
        </b-col>

      <b-col cols="4" class="pl-2">
          <b-row>
            <b-col cols="12" >
                 <div  class="text-center">
                    <b-avatar class="mt-3 mb-2" src="https://placekitten.com/300/300" size="6rem"></b-avatar>
                    <br>
                    <b-link href="#foo" style="color: black;font-size: 16px;">CHANGE YOUR PHOTO</b-link> 
                    
                    
                </div>
            </b-col>
          </b-row>
          
      </b-col>


    </b-row>

  </div>
</template>

<script>

export default {
  name: 'ProfileEdit',
  props : {
    name : {type : String , default : ""},
    username: {type: String, default: ""},
    location : {type : String , default : ""},
    birthday : {type : String , default : ""},
    biography : {type : String , default : ""},
    profileUrl : {type : String , default : ""},
  }
}
</script>

<style>
.showProfileEdit{
    height: auto;
    width: 100%;
    border: 1px inset black;
    border-radius: 15px;
    position: relative;
    padding: 4px;
}
.profileHr{
    color: black;
}

</style>
