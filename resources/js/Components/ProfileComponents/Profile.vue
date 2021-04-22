<template>
  <div class="showProfile overflow-hidden mt-4 mb-4" >
    <b-row class="m-0 h-100" >

        <b-col cols="8">
        <b-row  class="h-100 mt-4" >
          <b-col cols="12" >
                <span style="font-size: 28px;">{{name}}</span>
                <hr class="profileHr">
                <span style="font-size: 18px;">CLUBBY MEMBER SINCE: APRIL 1 2021 </span>
                <br>
                <span class="h4"><b-icon icon="geo-alt"></b-icon></span><span style="font-size: 18px;" class="ml-2">{{location}}</span>
                <br>
                <span class="h4"><b-icon icon="gift"></b-icon></span><span style="font-size: 18px;" class="ml-2">{{birthdate}}</span>
                <br>
                <br>
                <div class="Biography">
                    <p class="pl-4 pt-2 mb-2" style="font-size=12px;">BIOGRAPHY</p>
                    <p class="pl-4 mt-0 pt-0 pr-2" style="font-size=12px;">{{biography}}</p>
                </div>
                <br>
                <p style="font-size: 18px;">GROUPS JOINED: </p>
                <b-container fluid class="ml-0 pl-0 mb-1">
                    <b-row>
                        <b-col cols="11">
                            <div id="listgroup-ex" style="position:relative; overflow-y:auto; height:200px">
                                <profile-groups></profile-groups>
                                <profile-groups></profile-groups>
                                <profile-groups></profile-groups>
                                <profile-groups></profile-groups>
                            </div>
                        </b-col>
                    </b-row>
                </b-container>
            </b-col> 
        </b-row>
        
        </b-col>

      <b-col cols="4" class="pl-2">
          <b-row>
            <b-col cols="12" >
                 <div  class="text-center">
                    <b-avatar class="mt-3 mb-2" src="https://placekitten.com/300/300" size="6rem"></b-avatar>
                    <br>
                    <b-link href="#foo" style="color: black;font-size: 16px;">EDIT YOUR PROFILE</b-link> 
                    <br>
                    <br>
                    <!-- GRAFİK EKLENECEK -->
                    <p class="h1"><b-icon icon="graph-up"></b-icon></p>
                </div>
            </b-col>
          </b-row>
          
      </b-col>
    </b-row>

  </div>
</template>

<script>
import ProfileGroups from './ProfileGroups.vue'
export default {
  name: 'Profile',
    components:{
    ProfileGroups,
  },
   props : {
    name : {type : String , default : "NAME SURNAMEEEE"},
    location : {type : String , default : "LOCATION"},
    birthdate : {type : Date , default : "April 1 1999"},
    biography : {type : String , default : "Lorem ipsum, or lipsum as it is sometimes known, is dummy text used in laying out print, graphic or web designs."},

  },

}
</script>

<style>
.showProfile{
    height: auto;
    width: 100%;
    border: 1px inset black;
    border-radius: 15px;
    position: relative;
}
.profileHr{
    color: black;
}
.Biography{
     border: 1px inset #4D4D4D;
     border-radius: 10px;
     height: 140px;
     width: 470px;
}
</style>
