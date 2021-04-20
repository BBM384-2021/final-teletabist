<template>
<div style="width:100%; overflow:hidden;" class="adminpanel h-100" >
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion " id="accordionSidebar">
        <li >
            <div>
                <a href="#collapse-2" v-b-toggle class="m-1" @click.prevent >Manage Clubs</a>
                <b-collapse id="collapse-2">                                 
                    <div class="pl-4">
                        <a href="#collapse-3" v-b-toggle class="m-1" @click.prevent >{{Club}}</a>
                        <b-collapse id="collapse-3">
                            <div class="pl-4">
                                <a href="#collapse-4" v-b-toggle class="m-1" @click.prevent>{{SubClub}}</a>
                                <br>
                                <a href="#collapse-4" v-b-toggle class="m-1" @click.prevent >{{SubClub}}</a>
                            </div>                                                                                 
                        </b-collapse>
                    </div>             
                </b-collapse>
            </div>
        </li>
        <br>
        
        <li >
            <div>
                <a href="#" class="m-1" @click.prevent >Create Clubs</a>
            </div>
        </li>

    </ul>
</div>
</template>

<script>
export default {
  name: 'AdminPanel',
  props : {
    Club : {type : String , default : "Club1"},
    SubClub : {type : String , default : "SubClub"}
  },
}
</script>

<style>
.adminpanel{
    padding-top : 35px;
    width: 100%;
    background-color: #C4C4C4;
    position: relative;
}

</style>
