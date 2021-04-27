<template>
  <div>
      <b-row class="m-0 p-0">
        <b-form-textarea id="comment" name="comment" rows="3" max-rows="5" class="mb-3"></b-form-textarea>
      </b-row>
        <input type="hidden" id="liked" name="liked" v-bind:value="selected">
        <b-row class="m-0 p-0">
        <b-form-radio-group
            id="btn-radios-2"
            v-model="selected"
            :options="options"
            button-variant="outline-primary"
            size="md"
            name="radio-btn-outline"
            buttons
        ></b-form-radio-group>

        <b-button type="submit" class="ml-4" variant="outline-primary">Post Comment</b-button>
        </b-row>
  </div>
</template>

<script>
export default {
    name: "CommentForm",
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