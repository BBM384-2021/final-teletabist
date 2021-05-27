<template>
  <div class="club_thumbnail">
        <a :href="link" v-if="link">
            <div class="profile_image rounded-lg" v-bind:class="{borderedClub: borders}">
                <div class="image" :style="imgstyle"></div>
            </div>
            <h2>{{name}}</h2>
        </a>
        <span v-if="!link">
            <div class="profile_image rounded-lg" v-bind:class="{borderedClub: borders}">
                <div class="image" :style="imgstyle"></div>
            </div>
            <h2>{{name}}</h2>
        </span>

  </div>
</template>

temp

<script>
export default {
    props: {
        image_url: {
            type: String,
            default: ""
        },
        name: {
            type: String,
            default: ""
        },
        link: {
            type: String,
            default: ""
        },
        borders: {
            type: Boolean,
            default: false
        }
    },
    computed: {
        imgstyle: function() {

            return "background-image: url('"+(this.image_url.length > 0 ? this.image_url : '/public/img/default_club.jpg')+"');";
        }
    }
}
</script>

<style lang="scss" scoped>
.club_thumbnail{
    width: 100%;
    height: 180px;
    .profile_image{
        width: 100%;
        height: 140px;
        overflow: hidden;
        background-color: $color-3;
        border-width: 0px;
        border-style: solid;
        border-color: $color-3;
        transition: border-width 0.1s ease-in-out;
        .image{
            width: 110%;
            height: 110%;
            background-position: center center;
            background-repeat: no-repeat;
            background-size: cover;
        }

        &.borderedClub{
            border-width: 8px;
        }
    }
    
    h2{
        font-weight: 500;
        font-size: 1.2em;
        color: $bar-color;
    }
}
</style>