<template>
    <div class="text-center">
        <v-pagination
                v-model="currentPage"
                :length=GETPAGES
                circle
                @input="changePage"
        ></v-pagination>
    </div>
</template>

<script>
  import {mapActions, mapGetters} from 'vuex'

  export default {
    name: "pagination",
    data() {
      return {
        currentPage: 0
      }
    },
    computed: {
      ...mapGetters([
        'GETPAGES'
      ])
    },
    methods: {
      changePage(pageNumber) {
        this.currentPage = pageNumber
        this.$emit('nextPage', this.currentPage - 1)
        console.log(this.currentPage)
      }
    }
  }
</script>

<style scoped>

</style>