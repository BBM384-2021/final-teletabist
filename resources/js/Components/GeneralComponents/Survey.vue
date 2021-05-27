<template>
 <div id="survey">
    <b-container>
        <b-card class="mt-3">
            <b-card-title class=" text-uppercase mb-2">{{ quiz.title }}</b-card-title>
            <hr>
            <p v-if="errors[questionIndex]" class="alert alert-danger">
                {{ error }}
            </p>

            <div v-show="questionIndex === 0">
            
            <div v-for="(name) in allClubs" v-bind:key="name">
                <survey-clubs :clubname=name></survey-clubs>
                <br>
            </div>

            <button class="btn btn-secondary" @click="next">
                Next
            </button>  

            </div>

            <div v-for="(question, index) in quiz.questions" v-bind:key="index">
            <div v-show="index+1 === questionIndex">
            
                <b-container v-for="(item, index) in question.quiz1" v-bind:key="item">
                    <p class="body-1 mt-3">
                        <strong>{{index + 1}}. </strong>
                        {{item.question}}
                    </p>
                    <b-form-radio-group v-model="item.answer" stacked>
                        
                        <b-form-radio
                        v-for="(choice, val) in item.choices"
                        v-bind:key="val"
                        v-bind:value="val + 1"
                        readonly>

                        <span >
                            {{choice}}
                        </span>
                    
                        </b-form-radio> 

                    </b-form-radio-group>
                    <b-divider></b-divider>
                </b-container>

                <b-container v-for="(item, index) in question.quiz2" v-bind:key="item">
                    <p class="body-1 mt-3">
                        <strong>{{index + question.quiz1.length + 1}}. </strong>
                        {{item.question}}
                    </p>
                    <b-form-checkbox-group v-model="item.answer" stacked>
                        
                        <b-form-checkbox
                        v-for="(choice, val) in item.choices"
                        v-bind:key="val"
                        v-bind:value="val + 1"
                        readonly>

                        <span >
                            {{choice}}
                        </span>
                    
                        </b-form-checkbox> 

                    </b-form-checkbox-group>
                    <b-divider></b-divider>
                </b-container>


                
                <div class="mt-5" v-show="questionIndex < quiz.questions.length+1 ">
                <button 
                    class="btn btn-primary" 
                    v-if="questionIndex > 0" 
                    @click="prev">
                    Prev
                </button>
                <button class="btn btn-secondary" @click="next">
                    Next
                </button>  
                </div>
            </div>
            </div>
            
            <div v-show="questionIndex === quiz.questions.length+1">
            <h4>Your Clubs</h4>
            
            <div v-for="(name) in selectableClubNames" v-bind:key="name">
                <survey-clubs :clubname=name></survey-clubs>
                <br>
            </div> 

            </div>
        </b-card>
    </b-container>
  </div>
</template>

<script>
import SurveyClubs from './SurveyClubs.vue';

export default {
  components: { SurveyClubs },
  name: 'Survey',
  
  data: () => { 
    return{
    allClubs : ['Club1' , 'Club2' , 'Club3' , 'Club4'],
    selectableClubNames : ['Club1' , 'Club2'] ,
    quiz: {
        title: "SURVEY",
        questions: [
        {
        text: 'QUESTIONS OF CLUB A',
        quiz1: [
                    {
                        question: 'Q 1',
                        choices: [
                        'option a',
                        'option b',
                        'option c'
                        ],
                        answer: null
                    },
                    {
                        question: 'Q 2',
                        choices: [
                        'option a',
                        'option b',
                        'option c'
                        ],
                        answer: null
                    },
                    {
                        question: 'Q 3',
                        choices: [
                        'option a',
                        'option b'
                        ],
                        answer: null
                    },
                ],
        quiz2: [
            {
                question: 'Q 4',
                choices: [
                'option 1',
                'option 2',
                'option 3'
                ],
                answer: []
            },
            {
                question: 'Q 5',
                choices: [
                'option 1',
                'option 2',
                'option 3'
                ],
                answer: []
            },
            ],  
        },
        {
        text: 'QUESTIONS OF CLUB B',
        quiz1: [
                    {
                        question: 'Q B1',
                        choices: [
                        'option a',
                        'option b',
                        'option c'
                        ],
                        answer: null
                    },
                    {
                        question: 'Q B2',
                        choices: [
                        'option a',
                        'option b',
                        'option c'
                        ],
                        answer: null
                    },
                    {
                        question: 'Q B3',
                        choices: [
                        'option a',
                        'option b'
                        ],
                        answer: null
                    },
                ],
        quiz2: [
            {
                question: 'Q B4',
                choices: [
                'option 1',
                'option 2',
                'option 3'
                ],
                answer: []
            },
            {
                question: 'Q B5',
                choices: [
                'option 1',
                'option 2',
                'option 3'
                ],
                answer: []
            },
            ],  
        },
    ]
},
        questionIndex: 0,
        responses: [],
        errors: [],
        error: ''
    }
  }, 
  methods: {
    prev: function() {
      this.questionIndex--;
    },
    
    next: function() {
    //   if (this.responses[this.questionIndex] === undefined) {
    //     this.errors[this.questionIndex] = 1;
    //     this.error = 'Please select your answer';
    //   } 
    //   else {
    //     this.errors[this.questionIndex] = 0;
        this.questionIndex++;
     // } 
    },
  }
}
</script>

<style>

</style>
