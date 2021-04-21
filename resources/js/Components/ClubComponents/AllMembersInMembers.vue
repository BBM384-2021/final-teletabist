<template>
  <div class="overflow-hidden " >
    <b-row>
        <b-col class="h-100 align-items-center" >
            <div style=" border-radius: 10px; border: 1px inset black; height : 65px; " class="text-center">
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
    Count : {type : Number , default : "0"},
  },
}
</script>

<style>
</style>





