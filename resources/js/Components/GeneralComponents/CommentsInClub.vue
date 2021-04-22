<template>
  <div class="commentsInClub overflow-hidden " >

    <b-row class="m-0">

      <b-col cols="9">
        <div class="clubcomments">
          <b-row   style="height:30%;">
             <b-col cols="12" class="align-items-center" >
                   <b-avatar class="memberAvatarInClub" v-bind:src=memberpicurl size="4rem"></b-avatar>
                   <span class="ml-4" style="font-size:18px;">{{Name}}</span>
                   
                   <div class="align-middle d-inline">
                       <b-icon icon="record-fill" font-scale="0.5"></b-icon>
                   </div>

                   <span class="ml-2" style="font-size:12px;">{{DateofSharing}}</span>
                
                   <img class="ml-4" src="http://placehold.it/25x25" alt="">
             </b-col> 
           </b-row>

         <b-row  class="mr-2 ml-2 mt-1 align-items-center" style="height:70%;">
            <b-col cols="12" >
               <p style="font-size: 12px;">{{ text }}</p>
            </b-col> 
         </b-row>
         </div>

      </b-col>

      <b-col cols="3">
          
      </b-col>

    </b-row>

  </div>
</template>

<script>
export default {
  name: 'CommnetsInClub',
  props : {
    Name : {type : String , default : "Name"},
    DateofSharing : {type : Date , default : "April 1"},
    memberpicurl : {type : String , default : "#"},
    text : {type : String , default : " Quis magna Lorem anim amet ipsum do mollit sit cillum voluptate ex nulla tempor.Laborum consequat non elit enim exercitation cillum aliqua consequat id aliqua. Esse ex consectetur mollit voluptate est in duis."},
  }
}
</script>

<style>
.commentsInClub{
    margin-top: 35px;
    height: 300px;
    width: 100%;
    position: relative;
}
.clubcomments{
    height: 190px;
    width: 100%;
    border-radius: 15px;
    background-color: #C4C4C4;
    position: relative;
}

</style>
