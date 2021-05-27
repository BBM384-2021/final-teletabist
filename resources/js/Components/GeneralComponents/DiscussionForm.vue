<template>
  <div>
      <b-row class="m-0 p-0">
        <b-form-textarea id="discussion" name="discussion" rows="3" max-rows="5" class="mb-3"></b-form-textarea>
      </b-row>
      
        <b-row class="m-0 p-0">
        <b-button type="submit" class="ml-4" variant="outline-primary">Post Discussion</b-button>
        </b-row>
  </div>
</template>

<script>
export default {
    name: "DiscussionForm",
    data() {
      return {
        selected: '1',
        options: [
          { text: 'Like', value: '1' },
          { text: 'Dislike', value: '0' }
        ]
        }
    }

}
</script>

<style>

</style>