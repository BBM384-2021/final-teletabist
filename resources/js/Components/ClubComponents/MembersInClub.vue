<template>
  <div class="membersInClub overflow-hidden " >
    <b-row class="m-0 h-25">

      <b-col cols="3">
        <all-members-in-members></all-members-in-members>
      </b-col>

      <b-col cols="9">
          <div style="height: 500px; border-radius: 10px; border: 1px inset black; position: relative;" class="mb-4">
            <b-row class="h-25">
                <b-col cols="12" >      
                        <b-dropdown id="dropdown-right" right text="Sort alphabetically" class="m-2" style="float: right; margin-right: 10px;margin-top:0.5%;">
                        <b-dropdown-item>Sort by date of joining the club </b-dropdown-item>
                        </b-dropdown>
                        <br>
                        <span class="ml-3 pt-4" >ALL MEMBERS</span>
                        <b-form-input size="sm" class="mr-sm-2 m-3" id = "findMembers" placeholder="FIND MEMBERS"></b-form-input>
                </b-col>
            </b-row>

            <b-row class="h-75">
                  <b-container fluid>
                    <b-row>
                        <b-col cols="12">
                            <div id="listgroup-ex" style="position:relative; overflow-y:auto; height:340px">
                                <member-in-members></member-in-members>
                                <member-in-members></member-in-members>
                               <member-in-members></member-in-members>
                            </div>
                        </b-col>
                    </b-row>
                </b-container>
            </b-row>
          </div> 
      </b-col>
    </b-row>
  </div>
</template>

<script>
import MemberInMembers from './MemberInMembers.vue'
import AllMembersInMembers from './AllMembersInMembers.vue'

export default {
  components: { MemberInMembers, AllMembersInMembers },
  name: 'MembersInClub',
}
</script>

<style>
.membersInClub{
    margin-top : 35px;
    height: auto;
    width: 100%;
    position: relative;
}
#findMembers{
  background-color: white;
  border-radius: 4px;
  width: 90%;
  border: 1px inset black;
  padding-right: 25px;
}
.memberAvatarInClub{
    border: 1px solid #000000;
    box-sizing: border-box;
    background-repeat: no-repeat;
    background-position: center center;
    background-size: cover; 
}
</style>
