<template>
  <div class="showProfileEdit overflow-hidden " >
    <b-row class="m-0 h-100" >

        <b-col cols="8">
        <b-row  class="h-100 mt-4" >
          <b-col cols="12" >
                <span style="font-size: 28px;">{{NameSurname}}</span>
                <hr class="profileHr">
                <b-row>
                    <b-col cols="3">

                    </b-col>
                        <b-col cols="6">
                        <b-form-textarea size="sm"  style="height:37px;" id="textarea-default" placeholder="Name"></b-form-textarea>
                        <br>
                        <b-form-textarea size="sm" style="height:37px;" id="textarea-default" placeholder="Surname"></b-form-textarea>
                        <br>
                        <b-form-textarea size="sm" style="height:37px;" id="textarea-default" placeholder="Location"></b-form-textarea>
                        <br>
                        <b-form-textarea size="sm" style="height:37px;" id="textarea-default" placeholder="Birth Date"></b-form-textarea>
                        <br>
                        <b-form-textarea size="sm" style="height:60px;" id="textarea-default" placeholder="Biography" class="mb-2"></b-form-textarea>
                        <b-link href="#foo" class="float-right" style="color: black;font-size: 16px;">SAVE</b-link>

                    </b-col>
                    <b-col cols="3">
                    </b-col>
                </b-row>                              
               
            </b-col> 
        </b-row>
        
        </b-col>

     


      <b-col cols="4" class="pl-2">
          <b-row>
            <b-col cols="12" >
                 <div  class="text-center">
                    <b-avatar class="mt-3 mb-2" src="https://placekitten.com/300/300" size="6rem"></b-avatar>
                    <br>
                    <b-link href="#foo" style="color: black;font-size: 16px;">CHANGE YOUR PHOTO</b-link> 
                    <br>
                    <br>
                    <b-link href="#foo" style="color: black;font-size: 12px;">DELETE MY ACCOUNT</b-link> 
                </div>
            </b-col>
          </b-row>
          
      </b-col>


    </b-row>

  </div>
</template>

<script>

export default {
  name: 'ProfileEdit',
  props : {
    NameSurname : {type : String , default : "NAME SURNAME"},
  }
}
</script>

<style>
.showProfileEdit{
    height: auto;
    width: 100%;
    border: 1px inset black;
    border-radius: 15px;
    position: relative;
    padding: 4px;
}
.profileHr{
    color: black;
}

</style>
