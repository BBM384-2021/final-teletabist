<template>
  <div class="subclubsinclub overflow-hidden" >
    <b-row class="m-0 h-100">
      <b-col cols="3">
        <b-row  class="h-100 align-items-center">
          <b-col cols="12" >
              <b-img center style="border-radius: 8px;" src="http://placehold.it/240x125" alt="Center image"></b-img>
             </b-col> 
        </b-row>
        
      </b-col>

      <!-- YİĞİDE NOT
      TARİH VE CLUB NAME ARASINA DÜZGÜN BİR ARALIK EKLEYEMEDİM 
      SUBCLUB ADMIN VE NAME SURNAME İ SOLA KAYDIRAMADIM-->


      <b-col cols="5">
        <b-row  class="h-100 align-items-center">
          <b-col cols="12" >
              <span style="margin: 0 ;">{{DateofSharing}}</span>
              <p style="margin : 0; font-size: x-large;">{{SubClubName}}</p>
             </b-col> 
        </b-row>
        
      </b-col>

      <b-col cols="4">
          <b-row class="h-100 align-items-center">
            <b-col cols="5" >
              <b-avatar class="subClubAdminInClub" src="https://placekitten.com/300/300" size="6rem"></b-avatar>
            </b-col>
             <b-col cols="7" >
              <p style="margin : 0; font-size: medium;">SubClub Admin</p>
              <b-link style="margin : 0; font-size: larger; color: black;" href="#foo">{{NameSurname}}</b-link>
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
    NameSurname : {type : String , default : "Name Surname"},
    DateofSharing : {type : Date , default : "April 1 2021"},
    SubClubName : {type : String , default : "SubClub Name"}
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
