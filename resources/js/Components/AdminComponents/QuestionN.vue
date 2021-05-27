<template>
  
</template>

<script>
export default {
    name: "QuestionN",
    props: {
        outI: {
            type: String,
            default: 0
        }
    },
    data: {
        lastQ: 0,
        qNumber: 0
    },
    mounted(){
        this.lastQ = Number.parseInt(this.outI);
    },
    methods: {
        addQ: function(e){
            e.preventDefault();
            this.lastQ++;
            this.qNumber++;
        }
    }
}
</script>

<style>

</style>