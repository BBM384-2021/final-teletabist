<template>
  <div class="question">
    <b-col cols="12"><hr></b-col>
    <b-col cols="12">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="questiondesc" ><span class="text-danger">*</span>Question</span>
            </div>
            <input type="hidden" class="form-control" placeholder="Name" aria-label="questiondesc" aria-describedby="questiondesc"
              :id="'question'+index+'.id'" 
              :name="'questions['+index+'].id'" 
              :value="Number.parseInt(qid)" required>
            <input type="text" class="form-control" placeholder="Question" aria-label="questiondesc" aria-describedby="questiondesc"
              :id="'questions'+index+'.question'" 
              :name="'questions['+index+'].question'" 
              :value="question" required>
            <input type="number" class="form-control small" placeholder="Weight" aria-label="questiondesc" aria-describedby="questiondesc"
              :id="'question'+index+'.weight'" 
              :name="'questions['+index+'].weight'" 
              :value="weight" required>
            <input type="number" class="form-control small" placeholder="Weight" aria-label="questiondesc" aria-describedby="questiondesc"
              :id="'question'+index+'.type'" 
              :name="'questions['+index+'].type'" 
              :value="type" required>
              
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
              :name="'questions['+index+'].delete'" 
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
    type:{
      type: String,
      default: "0"
    },
    index: {
      type: String,
      default: "0"
    },
    question: {
      type: String,
      default: ""
    },
    weight:{
      type: String,
      default: "1"
    },
    qid: {
      type: String,
      default: "-1"
    },
    answers: {
      type: String,
      default: ""
    }
  },
  mounted(){
    if(this.answers.length>2)
      this.answerso = JSON.parse(this.answers);
    else
      this.answerso = [];
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