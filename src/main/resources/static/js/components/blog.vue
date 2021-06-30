<template>
    <div class="blog">
        <h1>ЫФ</h1>
        <game-selector></game-selector>
    </div>
</template>
<script>
  import GameSelector from "./GameSelector.vue";

  export default {
    name: 'blog',
    components: {GameSelector},
    data() {
      return {
        title: 'Blog'
      }
    }
  }
</script>
<style scoped>

</style>