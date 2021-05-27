<template>
  <div class="mt-2 ">
        <input type="hidden" 
        :value="value" 
        :id="'question'+questioni+'.answers'+index+'.value'" 
        :name="'question['+questioni+'].answers['+index+'].value'">
      <div class="input-group mb-2">

        <div class="input-group-prepend">
            <p class="input-group-text">
                <input type="checkbox" :id="'question'+questioni+'.answers'+index+'.markDelete'"  :name="'question['+questioni+'].answers['+index+'].markDelete'" :value="del"><span style="color:red">Delete</span>
            </p>
           
        </div>
        <input type="text" 
        class="form-control" 
        :value="text" 
        :id="'question'+questioni+'.answers'+index+'.text'" 
        :name="'question['+questioni+'].answers['+index+'].text'">
      </div>
  </div>
</template>

<script>
export default {
    name: 'qa',
    props: {
        questioni: {
            type: String,
            default: ""
        },
        index: {
            type: Number,
            default: 0
        },
        value: {
            type: Number,
            default: 0
        },
        text: {
            type: String,
            default: ""
        },
        del: {
            type: Boolean,
            default: false
        }
    },

}
</script>

<style>

</style>