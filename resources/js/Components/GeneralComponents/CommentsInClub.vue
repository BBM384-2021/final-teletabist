<template>
  <div class="commentsInClub overflow-hidden " >

    <b-row class="m-0">

      <b-col cols="12">
        <div class="clubcomments">
          <b-row   style="height:30%;">
             <b-col cols="12" class="align-items-center" >
                   <b-avatar class="memberAvatarInClub mt-3 ml-3" v-bind:src=memberpicurl size="4rem"></b-avatar>
                   <span class="ml-4" style="font-size:18px;">{{usersname}}</span>                               
                   <span class="ml-2" style="font-size:12px;">Date : {{date}}</span>
                
                   <b-icon variant="success" icon="hand-thumbs-up" v-if="liked"></b-icon>
                   <b-icon variant="danger" icon="hand-thumbs-down" v-if="!liked"></b-icon>
             </b-col> 
           </b-row>

         <b-row  class="mr-2 ml-2 mt-1 align-items-center" style="height:70%;">
            <b-col cols="12" >
               <p style="font-size: 12px; word-wrap: break-word;">{{ comment }}</p>
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
    usersname : {type : String , default : "Name"},
    date : {type : String , default : ""},
    memberpicurl : {type : String , default : "#"},
    comment : {type : String , default : ""},
    liked : {type : Boolean , default : null},
  }
}
</script>

<style>
.commentsInClub{
    margin-top: 35px;
    min-height: 200px;
    width: 100%;
    position: relative;
}
.clubcomments{
    height: 190px;
    width: 100%;
    border-radius: 15px;
    background: rgb(255,179,186);
    background: radial-gradient(circle, rgba(255,179,186,1) 0%, rgba(186,225,255,1) 100%);
    position: relative;
}

</style>
