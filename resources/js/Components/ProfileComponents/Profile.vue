<template>
  <div class="showProfile overflow-hidden mt-4 mb-4" >
    <b-row class="m-0 h-100" >

        <b-col cols="8">
        <b-row  class="h-100 mt-4" >
          <b-col cols="12" >
                <span style="font-size: 28px;">{{name}}</span>
                <hr class="profileHr">
                <span style="font-size: 18px;color: #800909;">Clubby Member Since: </span>
                <span style="font-size: 18px;">{{ memberSince }} </span>
                <br>
                <span class="h4"><b-icon icon="geo-alt"></b-icon></span><span style="font-size: 18px;" class="ml-2">{{location}}</span>
                <br>
                <span class="h4"><b-icon icon="gift"></b-icon></span><span style="font-size: 18px;" class="ml-2">{{birthday}}</span>
                <br>
                <br>
                <div class="Biography">
                    <p class="pl-4 pt-2 mb-2" style="font-size=12px;color: #800909;">BIOGRAPHY</p>
                    <p class="pl-4 mt-0 pt-0 pr-2" style="font-size=11px;">{{biography}}</p>
                </div>
                <br>
                <p style="font-size: 18px;color: #800909;">GROUPS JOINED: </p>
                <b-container fluid class="ml-0 pl-0 mb-1">
                    <b-row>
                        <b-col cols="11">
                            <div id="listgroup-ex" style="position:relative; overflow-y:auto; height:200px">
                              <div v-for="(item, index ) in listofGroups" v-bind:key="index">
                                 <profile-groups :ClubName=item.ClubName></profile-groups>
                              </div>  
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
                    <b-link :href="'/users/'+username+'/edit'" style="color: #800909;;font-size: 16px;">Edit Profile</b-link> 
                    <br>
                    <br>              
                    <chart :interest="interest" class="mt-5"></chart>
                </div>
            </b-col>
          </b-row>
          
      </b-col>
    </b-row>

  </div>
</template>

<script>
import ProfileGroups from './ProfileGroups.vue'
import Chart from './Chart.vue'
export default {
  name: 'Profile',
    components:{
    ProfileGroups,
    Chart,
  },
   props : {
    name : {type : String , default : ""},
    username: {type: String, default: ""},
    location : {type : String , default : ""},
    birthday : {type : String , default : ""},
    biography : {type : String , default : ""},
    memberSince: {type : String , default : ""},
  },
  data : function() {
    return {
      interest : [
            {
            name: "SPORT club",
            ratio: 29.2
            },
            {
            name: "Club Y",
            ratio: 30.4
            },
            {
            name: "Music ",
            ratio: 29.2
            },
            {
            name: "Teletubbies ",
            ratio: 29.2
            },
            {
            name: "Clubby ",
            ratio: 29.2
            },
            
            ],
      listofGroups : [
        {
          ClubName : "SPORT CLUB",
          DateofSharing : "April 1 2021"
        },
        {
          ClubName : "SPORT CLUB2",
          DateofSharing : "April 1 2021"
        },
        {
          ClubName : "SPORT CLUB3",
          DateofSharing : "April 1 2021"
        }
      ]
    }

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
     border: 1px inset #ffb3ba !important;
     border-radius: 10px;
     height: 140px;
     width: 470px;
}
</style>
