<template>
    <v-col
            class="mx-auto"
            cols="12"
            sm="3"

    >
        <v-select
                v-model="selected"
                :items="GET_GAMES"
                item-text="title"
                item-value="id"
                filled
                label="Выберите игру"
                dense
                @change="changeCallStatus"
        ></v-select>
    </v-col>

</template>

<script>
  import {mapActions, mapGetters} from 'vuex'

  export default {
    name: "GameSelector",
    props: {
      options: {
        type: Object,
        default() {
          return {}
        }
      }
    },
    data() {
      return {
        selected: 0
      }
    },
    computed: {
      ...mapGetters([
        'GET_GAMES',
        'GET_CUR_GAME'
      ])

    },
    methods: {
      ...mapActions([
        'getAllGames'
      ]),
      changeCallStatus(event) {
        console.log('sas' + event + 'sas')
        this.$emit('changer', event)
      }
    },
    mounted() {
      this.getAllGames()
      this.selected = this.GET_CUR_GAME
    }
  }
</script>

<style scoped>

</style>