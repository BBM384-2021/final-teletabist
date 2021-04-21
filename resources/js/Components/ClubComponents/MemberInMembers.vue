<template>
  <div class="overflow-hidden " >
    <div style="height: 80px;  border-bottom: 1px inset grey; position: relative;background-color: #EEEEEE; margin-left:10px; margin-right:10px;">
      <b-row>
        <b-col col="2" class="h-100">
            <b-row class="h-100 align-items-center">
                <b-col>
                    <b-avatar class="memberAvatarInClub" src="https://placekitten.com/300/300" size="4rem"></b-avatar>
                </b-col>
            </b-row>
        </b-col>

        <b-col col="10">
            <span>{{NameSurname}}</span>
            <span>Date of join: {{DateofSharing}}</span>
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
    DateofSharing : {type : Date , default : "April 1 2021"}
  },
}
</script>

<style>


</style>






















