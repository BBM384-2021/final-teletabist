<template>
  <div class="overflow-hidden " >
    <b-row class="m-0" align-v="center" style=" border-radius: 10px; border: 1px inset black;height:65px;">
        <b-col class="text-center" >
            <div  class="text-center">
                <span align="center">ALL MEMBERS</span>
                <span style="margin-left:20px">{{Count}}</span>
            </div>
        </b-col> 
    </b-row>
  </div>
</template>

<script>
export default {
  name: 'AllMembersInMembers',
  props : {
    Count : {type : Number , default : 0},
  },
}
</script>

<style>
</style>





