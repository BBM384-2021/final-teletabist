<template>
  <div class="overflow-hidden " >
    <div style="height: 80px;  border-bottom: 1px inset grey; position: relative;background-color: #EEEEEE; margin-left:10px; margin-right:10px;">
      <b-row>
        <b-col cols="2" class="h-100">
            <b-row class="h-100 align-items-center">
                <b-col class="text-center pt-2">
                    <b-avatar class="memberAvatarInClub d-inline-block " v-bind:src=pic size="4rem"></b-avatar>
                </b-col>
            </b-row>
        </b-col>

        <b-col cols="10" class="pt-3">
          <b-row>
            <b-col cols="12">
              <span>{{NameSurname}}</span>
            </b-col>
          </b-row>
          <b-row>
            <b-col cols="12">
              <span>Date of join: {{DateofSharing}}</span>
            </b-col>
          </b-row>        
        </b-col>
      </b-row>   
    </div>
  </div>
</template>

<script>
export default {
  name: 'MemberInMembers',
  props : {
    NameSurname : {type : String , default : "Name Surname"},
    DateofSharing : {type : String , default : "April 1 2021"},
    pic : {type : String , default : "https://placekitten.com/300/300"}
  },
}
</script>

<style>


</style>






















