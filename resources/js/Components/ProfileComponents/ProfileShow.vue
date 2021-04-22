<template>
  <div class="showProfile overflow-hidden mt-4" >
    <b-row class="m-0 h-100" >

        <b-col cols="8">
        <b-row  class="h-100 mt-4" >
          <b-col cols="12" >
                <span style="font-size: 28px;">{{NameSurname}}</span>
                <p>Member/Subclub Manager</p>
                <hr class="profileHr">
                <span style="font-size: 18px;">CLUBBY MEMBER SINCE: {{DateofSharing}} </span>
                <br>
                <span class="h4"><b-icon icon="geo-alt"></b-icon></span><span style="font-size: 18px;" class="ml-2">{{Location}}</span>
                <br>
                <span class="h4"><b-icon icon="gift"></b-icon></span><span style="font-size: 18px;" class="ml-2">{{BirthDate}}</span>
                <br>
                <br>
                <div class="Biography">
                    <p class="pl-4 pt-2 mb-2" style="font-size=12px;">BIOGRAPHY</p>
                    <p class="pl-4 mt-0 pt-0 pr-2" style="font-size=12px;">Lorem ipsum, or lipsum as it is 
                       sometimes known, is dummy text used in laying out print, graphic or web designs.</p>
                </div>
                <br>
                <p style="font-size: 18px;">REGISTERED CLUBS: </p>
                <b-container fluid class="ml-0 pl-0 mb-1">
                    <b-row>
                        <b-col cols="11">
                            <div id="listgroup-ex" style="position:relative; overflow-y:auto; height:200px">
                                <profile-groups2></profile-groups2>
                                <profile-groups2></profile-groups2>
                                <profile-groups2></profile-groups2>
                                <profile-groups2></profile-groups2>
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
                    <b-avatar class="mt-4 mb-2" src="https://placekitten.com/300/300" size="10rem"></b-avatar>
                    <br>
                    <br>
                    <!-- GRAFİK EKLENECEK -->
                    <p class="h1"><b-icon icon="graph-up"></b-icon></p>
                    <p>MEMBER’S INTEREST RATE </p>
                </div>
            </b-col>
          </b-row>
          
      </b-col>


    </b-row>

  </div>
</template>

<script>
import ProfileGroups2 from './ProfileGroups2.vue'
export default {
  name: 'ProfileShow',
    components:{
    ProfileGroups2,
  },
  props : {
    name : {type : String , default : ""},
    username: {type: String, default: ""},
    location : {type : String , default : ""},
    birthday : {type : String , default : ""},
    biography : {type : String , default : ""},
    memberSince: {type : String , default : ""},  
  }
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
