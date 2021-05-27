<template>
  <div>
      <b-row>
      <admin-question
      v-for="(q, i) in questions"
      v-bind:key="i"
      :index="(Number.parseInt(outeri)+i)+''"
      :question="q.question"
      :answer="JSON.stringify(q.answer)"
      ></admin-question>
      </b-row>
      <b-row>
      <b-col cols="12" class="justify-content-center">
          <button @click="addQ" class="btn btn-primary">
              Add Question
          </button>
      </b-col>
      </b-row>
  </div>
</template>

<script>
export default {
    name: "QuestionN",
    props: {
        outi: {
            type: String,
            default: 0
        }
    },
    data: ()=>{
        return{
        outeri: 0,
        qNumber: 0,
        questions: []
        }
    },
    mounted(){
        this.outeri = Number.parseInt(this.outi);

    },
    methods: {
        addQ: function(e){
            e.preventDefault();
            this.qNumber++;
            let q = {
                index: (this.qNumber+this.outeri)+"",
                question: "",
                answer:[]
            };
            this.questions.push(q);
        }
    }
}
</script>

<style>

</style>