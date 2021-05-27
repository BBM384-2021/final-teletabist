<template>
  <div class="question">
    <b-col cols="12"><hr></b-col>
    <b-col cols="12">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="questiondesc" ><span class="text-danger">*</span>Question</span>
            </div>
            
            <input type="text" class="form-control" placeholder="Name" aria-label="questiondesc" aria-describedby="questiondesc"
              :id="'question'+index+'.question'" 
              :name="'question['+index+'].question'" 
              :value="question" required>
              
        </div>
    </b-col>
    <b-col cols="12">
        
        <qa
        :questioni="index"
        v-for="(ans, ai) in answerso"
        v-bind:key="ai"
        :index="ai"
        :value="ans.value"
        :text="ans.text"
        ></qa>
    </b-col>
    <b-col cols="12">
      <button @click="addQuestion" class="btn btn-primary">Add Answer</button>
    </b-col>
    <b-col cols="12">
        <input type="checkbox" aria-label="markfordelete" aria-describedby="markfordelete"
              :id="'question'+index+'.delete'" 
              :name="'question['+index+'].delete'" 
              > Mark for delete
    </b-col>
  </div>
</template>

<script>
import qa from "./QuestionA.vue";
export default {
  components:{
    qa
  },
  props:{
    index: {
      type: String,
      default: null
    },
    question: {
      type: String,
      default: ""
    },
    answers: {
      type: String,
      default: ""
    }
  },
  mounted(){
    this.answerso = JSON.parse(this.answers);
  },
  data: ()=>{
    return {
      answerso: []
    }
  },
  methods: {
    addQuestion: function(e){
      e.preventDefault();
      let answer = {
        value: this.answerso.size,
        text: "" 
      }
      this.answerso.push(answer);
    }
  }
}
</script>

<style>
.question{
    width: 100%;
    height: 100%;
}
</style>