<template>
  <div class="club-pagination"></div>
</template>

<script>
export default {
    props: {
        pageCount: {
            default: 0,
            type: Number
        },
        currentPage: {
            default: 0,
            type: Number
        }
    }
}
</script>

<style lang="scss" scoped>

</style>