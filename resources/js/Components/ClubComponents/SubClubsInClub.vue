<template>
  <div class="subclubsinclub overflow-hidden" >
    <b-row class="m-0 h-100">
      <b-col cols="3">
        <b-row  class="h-100 align-items-center">
          <b-col cols="12" >
              <b-img center style="border-radius: 8px;" v-bind:src=imageUrl alt="Center image"></b-img>
             </b-col> 
        </b-row>
        
      </b-col>

      <b-col cols="5">
        <b-row  class="h-100 align-items-center">
          <b-col cols="12" >
              <span v-if="url">
                <a class="text-dark" :href="url">
                  <p style="margin : 0; font-size: x-large;">{{name}}</p>
                </a>
              </span>
              <span v-else>
                  <p style="margin : 0; font-size: x-large;">{{name}}</p>
              </span>
          </b-col> 
        </b-row>
        
      </b-col>

      <b-col cols="4">
          <b-row class="h-100 align-items-center" v-if="adminUrl">
            <b-col cols="5" >
              <b-avatar class="subClubAdminInClub" v-bind:src=adminUrl size="6rem"></b-avatar>
            </b-col>
             <b-col cols="7" >
              <p style="margin : 0; font-size: medium;">SubClub Admin</p>
              <b-link style="margin : 0; font-size: larger; color: black;" href="#foo">{{adminName}}</b-link>
            </b-col>
          </b-row>

      </b-col>

    </b-row>
  </div>
</template>

<script>
export default {
  name: 'SubClubsInClub',
  props : {
    name : {type : String , default : ""},
    url : {type: String, default: ""},
    adminName: {type : String , default : ""},
    adminUrl: {type : String , default : ""},
    imageUrl : {type : String , default : "http://placehold.it/240x125"},
  },
}
</script>

<style>
.subclubsinclub{
    margin-top : 35px;
    height: 160px;
    width: 100%;
    background-color: #C4C4C4;
    border-radius: 15px;
    position: relative;
}
.subClubAdminInClub{
  border: 1px solid #000000;
  box-sizing: border-box;
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover; 
}


</style>
