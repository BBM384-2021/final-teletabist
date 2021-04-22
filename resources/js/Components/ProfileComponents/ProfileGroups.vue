<template>
  <div class="overflow-hidden" >
  
    <div style="height: 80px; width: 590px; border-bottom: 1px inset grey; position: relative;background-color: #EEEEEE; margin-left:10px; margin-right:10px;">     
        <b-row class="h-100">
            <b-col cols="3" class="h-100">
                <b-row class="h-100 align-items-center">
                    <b-col class="ml-3">
                        <b-img left src="http://placehold.it/100x55" alt="Center image"></b-img>
                    </b-col>
                </b-row>
            </b-col>

            <b-col cols="9">
                <b-row class="h-100 align-items-center">
                    <b-col cols="8">
                        <span>{{ClubName}}</span>
                        <br>
                        <span>Date of join: {{DateofSharing}}</span>
                    </b-col>

                    <b-col cols="2">
                        <b-link href="#foo" class="float-right mr-3" style="color: black;">LEAVE GROUP</b-link>
                    </b-col>
                </b-row>
                
            </b-col>
        </b-row>
    </div>
 </div>
</template>

<script>

export default {
  name: 'ProfileGroups',
    props : {
    ClubName : {type : String , default : "Club Name"},
    DateofSharing : {type : Date , default : "April 1 2021"},
  }
}
</script>

<style>

</style>
