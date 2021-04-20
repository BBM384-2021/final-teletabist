<template>
  <div class="subClubshowInfo overflow-hidden " >
    <b-row class="m-0" style="height: 85%">

      <b-col cols="4">
        <b-row  class="h-100 align-items-center">
          <b-col cols="12 mt-" >
              <b-img center src="http://placehold.it/280x180" alt="Center image"></b-img>
             </b-col> 
        </b-row>
        
      </b-col>

      <b-col cols="2" >
        <b-row class="text-center">
            <b-col cols="12" >
              <div  class="text-center" style="border: 1px inset black; border-radius: 20px; width: 125px; margin-top:42px;">
                    <b-avatar class="mt-3" src="https://placekitten.com/300/300" size="4rem"></b-avatar>
                    <p class="mt-3" style="font-size: 14px;">{{NameSurname}}</p>
                    <p style="color: #515151;font-size: 12px;">SubClub Admin</p>                               
              </div>
            </b-col>
        </b-row>
      </b-col>


      <b-col cols="6">
          <b-row>
            <b-col cols="12" >
              <img style="float: right; margin-right: 1% border;border: 1px outset black; margin-top:0.5%;" src="http://placehold.it/25x25" class="d-inline-block align-top">
              <img style="float: right; border: 1px outset black;margin-top:0.5%;" src="http://placehold.it/25x25" class="d-inline-block align-top">
              <p style="float: right; margin-right: 10px;margin-top:0.5%;">Do you like this club?</p>
            </b-col>
          </b-row>

          <b-row class="h-100" style="padding-bottom:42px">
            <b-col cols="12" class="text-center"  >
                 <h2 class="mt-3">{{ClubName}}</h2>
                 <h4>RATE 50%</h4>

                 <h4 style="margin-top:35px;">
                   {{ShortText}}
                 </h4>
            </b-col>
                         
          </b-row>
      </b-col>


    </b-row>

    <b-row style="height: 15%;" class="m-0 align-items-end" >
      <b-col cols="7">
        <b-nav  align="left">
          <b-nav-item active class="club-nav-link">CLUB EVENTS</b-nav-item>
          <b-nav-item disabled>|</b-nav-item>
          <b-nav-item class="club-nav-link">MEMBERS</b-nav-item>
          <b-nav-item disabled>|</b-nav-item>
          <b-nav-item class="club-nav-link">DISCUSSIONS</b-nav-item>
           <b-nav-item disabled>|</b-nav-item>
          <b-nav-item class="club-nav-link">COMMENTS</b-nav-item>
        </b-nav>
      </b-col>

      <b-col cols="2">
       <b-button href="#" block pill variant="outline-secondary" size="sm mb-1">Join/Leave</b-button>
      </b-col>

      <b-col cols="3">
          <b-link href="#foo" class="float-right mb-2" style="color: black;">Send a message</b-link>
      </b-col>
    </b-row>

  </div>
</template>

<script>
export default {
  name: 'SubClub',
   props : {
    NameSurname : {type : String , default : "Name Surname"},
    ShortText : {type : String , default : "A short text about the club"},
    ClubName : {type : String , default : "CLUB NAME"}
  },
}
</script>

<style>
.subClubshowInfo{
    height: 300px;
    width: 100%;
    border: 1px inset black;
    position: relative;
    
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
