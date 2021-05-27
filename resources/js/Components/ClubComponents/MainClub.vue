<template>
  <div class="showInfo overflow-hidden " >
    <b-row class="m-0 h-75">

      <b-col :cols="parentClubUrl ? 4 : 5">
        <b-row  class="h-100 align-items-center">
          <b-col cols="12" >
              <b-img center v-bind:src=picurl alt="Center image"></b-img>
             </b-col> 
        </b-row>
        
      </b-col>

      <b-col cols="2" v-if="parentClubUrl">
        <b-row class="text-center" v-if="admin">
            <b-col cols="12" >
              <div  class="text-center" style="border: 1px inset black; border-radius: 20px; width: 125px; margin-top:42px;">
                <a :href="adminprofile">
                    <b-avatar class="mt-3" v-bind:src=adminpp size="4rem"></b-avatar>
                    <p class="mt-3" style="font-size: 14px;">{{admin}}</p>
                    <p style="color: #515151;font-size: 12px;">SubClub Admin</p>  
                </a>
                                                 
              </div>
            </b-col>
        </b-row>
      </b-col>

      <b-col :cols="parentClubUrl ? 6 : 7">
          <b-row class="h-25">
            <!-- <b-col cols="12" >
              <img style="float: right; margin-right: 1% border;border: 1px outset black; margin-top:0.5%;" src="http://placehold.it/25x25" class="d-inline-block align-top">
              <img style="float: right; border: 1px outset black;margin-top:0.5%;" src="http://placehold.it/25x25" class="d-inline-block align-top">
                <p style="float: right; margin-right: 10px;margin-top:0.5%;">Do you like this club?</p>
            </b-col> -->
          </b-row>

          <b-row class="h-50 align-items-center">
            <b-col cols="12" class="text-center"  >
                 <h2 style="color: darkred;">{{clubname}}</h2>
                 <p v-if="description" class="mb-1">{{description}}</p>
                 <a :href="webisteURL" v-if="webisteURL" >Website</a>
                 <h4 class="mt-2">RATE: {{rate}}%</h4>
                 <div v-if="parentClubUrl">
                   <span style="font-size:16px;">Sub-club of</span>
                   <br>
                   <a :href="parentClubUrl">{{parentClub}}</a>
                 </div>
            </b-col>
          </b-row>
      </b-col>

    </b-row>

    <b-row class="m-0 h-25 align-items-end">
      <b-col cols="12">
        <b-nav  align="left">
          <b-nav-item v-if="!parentClubUrl" active class="club-nav-link" :href="clubUrl+'/'">SUBCLUBS</b-nav-item>
          <b-nav-item v-if="!parentClubUrl"  disabled>|</b-nav-item>
          <b-nav-item class="club-nav-link" v-bind:href=membersurl >MEMBERS</b-nav-item>
           <b-nav-item disabled>|</b-nav-item>
          <b-nav-item class="club-nav-link" :href="clubUrl+'/comments'" >COMMENTS</b-nav-item>
        </b-nav>
      </b-col>
      
    </b-row>
  </div>
</template>


<script>
export default {
  name: 'MainClub',
  props : {
    clubname : {type : String , default : ""},
    clubUrl : {type : String , default : ""},
    rate : {type : Number , default : 0},
    description: {type: String, default: ""},
    website: {type: String, default: ""},
    picurl: {type : String , default : "http://placehold.it/280x170"},
    subcluburl : {type : String , default : "#"},
    membersurl : {type : String , default : "#"},
    commentsurl : {type : String , default : "#"},
    parentClub: {type: String, default: null},
    parentClubUrl: {type: String, default: null},
    admin: {type: String, default: null},
    adminprofile: {type: String, default: null},
    adminpp: {type: String, default: null}
  },
  computed: {
    webisteURL: function(){
      try {
        let url = new URL(this.website);
      } catch (error) {
        return null;
      }
      
      return url;
    }
  }
}
</script>

<style>
.showInfo{
    height: 300px;
    width: 100%;
    /* border: 1px inset black; */
    position: relative;
    background: rgb(255,179,186);
    background: linear-gradient(0deg, rgba(255,179,186,1) 0%, rgba(255,223,186,1) 50%, rgba(166,198,193,1) 100%);
}
.club-nav-link .nav-link{
  color: black;
}
.club-nav-link::after {
  background-color: black;
}
.club-nav-link .nav-link.active{
  color: darkred;
}

</style>
