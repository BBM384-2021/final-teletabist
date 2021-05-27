<template>
<div>
    <input type="checkbox" name="club_ids" :value="id" v-bind:checked="check" hidden>
    <club-thumbnail 
    :image_url="image_url"
    :name="name"
    :borders="check"
    v-on:click.native="clickon"
    ></club-thumbnail>
</div>

</template>

<script>
export default {
    props: {
        image_url: {
            type: String,
            default: ""
        },
        id: {
            type: String,
            default: null
        },
        name: {
            type: String,
            default: ""
        },
        link: {
            type: String,
            default: ""
        }
    },
    data: ()=>{
        return {
            check: false
        }
    },
    methods: {
        clickon: function (event) {
            this.check = !this.check;
        }
    }
}
</script>

<style>

</style>