<template>
  <div class="discussionsPage overflow-hidden " >

    <b-modal ok-title="Share" id="modal-center" centered title="SPEAK ABOUT YOUR INTERESTS?">
        
        <b-container fluid>
            <b-row align-content="center">
                <b-col cols="12" class="text-center" >            
                    <img class="ml-2" src="http://placehold.it/50x45" alt="">
                    <img class="ml-2" src="http://placehold.it/50x45" alt="">   
                </b-col> 
            </b-row>

            <b-row class="justify-content-center">   <!--ORTALAMAK İÇİN BUNU KULLAN class="justify-content-center" -->
            
                <b-col cols="8" class="text-center"> <!-- <b-col cols="8" class="text-center" offset="2"> -->
                    <p style="font-size: 20px">Share your comments about the club with us</p>
                </b-col>                   
                
            </b-row>

            <b-row>
                <b-col cols = "12">
                  <b-form>
                     <b-form-textarea
                      id="textarea"
                      v-model="text"
                      placeholder="Placeholder..."
                      rows="3"
                      max-rows="6"
                    ></b-form-textarea>
                  </b-form>
                </b-col>
            </b-row>


        </b-container>
    </b-modal>

  </div>
</template>

<script>
export default {
  name: 'DiscussionsPage',
   data() {
      return {
        text: `Quis magna Lorem anim amet ipsum do mollit sit cillum voluptate ex nulla tempor. Laborum consequat non elit enim exercitation cillum aliqua consequat id aliqua. Esse ex consectetur mollit voluptate est in duis laboris ad sit ipsum anim Lorem.`
      }
    }
}
</script>

<style>
.discussionsPage{
    margin-top: 35px;
    height: 550px;
    width: 100%;
    position: relative;
}
.mainDivforCommend{
    height: 520px;
}

</style>
