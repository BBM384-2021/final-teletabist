<template>
<div style="overflow:hidden;" class="admin" >
    <b-container fluid class="h-100">
        <b-row class="p-0 m-0 h-100">
            <b-col cols="2" class="p-0 m-0 h-100">
               <admin-panel></admin-panel>
            </b-col>
            <b-col cols="10">

            </b-col>
        </b-row>

    </b-container>
   
</div>
</template>

<script>
import AdminPanel from './AdminPanel.vue'
export default {
  name: 'Admin',
  components:{
    AdminPanel,
  }
}
</script>

<style>
.admin{
    height: 160px;
    width: 100%;
    position: relative;
    min-height: 100vh;
}

</style>
