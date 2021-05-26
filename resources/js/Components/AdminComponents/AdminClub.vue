<template>
  <div class="club rounded-lg">
      
  </div>
</template>

<script>
export default {
    name: "AdminClub",
    props: {
        name: {
            default: "Club",
            type: String
        },
        slug: {
            default: "club",
            type: String
        },
        subclubs: {
            default: [],
        },
        
    }
}
</script>

<style lang="scss" scoped>
    
    .club{
        background-color: $bar-color;
    }
</style>