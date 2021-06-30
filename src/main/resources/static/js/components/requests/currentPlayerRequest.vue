<template>
    <div class="currentPlayerRequest">

        <v-item v-slot="{ active, toggle }">
            <v-card
                    class="mx-auto "
                    max-width="400"
                    outlined
                    max-height="200"
                    elevation="12"
                    rounded="lg">
                <!-- <img v-bind:src="'../../image/' + product_data.image" alt="img"> -->
                <div class="d-flex flex-row">
                    <v-card-text @click="lul" class="headline text-truncate font-weight-bold, text-center, currentPlayerRequest_name">
                        {{product_data.title}}
                    </v-card-text>
                    <v-btn @click="add_to_favorite"
                           icon v-if="authenticated">
                        <v-icon v-bind:color="color"> mdi-star</v-icon>
                    </v-btn>
                </div>
                <p @click="lul" class="currentPlayerRequest_price text-truncate ">
                    {{product_data.info}}
                </p>
            </v-card>
        </v-item>
    </div>
</template>

<script>
  import router from "../../router";
  import {mapGetters, mapActions} from 'vuex'
  import axios from 'axios'

  export default {
    name: "currentPlayerRequest",
    props: {
      product_data: {
        type: Object,
        default() {
          return {}
        }
      }
    },
    data() {
      return {
        color: 'grey'
      }
    },
    computed: {
      ...mapActions([
        'getFavoriteRequest'
      ]),
      ...mapGetters([
        'GET_CUR_GAME',
        "authenticated",
        'FAVORITE_REQ',
        'user'
      ])
    },
    methods: {
      lul() {
        router.push({path: `/game/${this.GET_CUR_GAME}/playerReq/${this.product_data.id}`})
        console.log(this.product_data.id)
      },
      async add_to_favorite() {
        await axios.post(`/api/game/${this.product_data.id}/fav`)
        if (this.color == 'grey') {
          this.color = 'yellow'
        } else {
          this.color = 'grey'
        }
        this.getFavoriteRequest(this.user)
      },

    },
    mounted() {

    },
    created() {
      this.color = 'grey'
      if (this.FAVORITE_REQ.find(x => x.id == this.product_data.id)) {
        console.log(this.product_data.id)
        this.color = 'yellow'
        console.log('sasasa')
      } else {
        this.color = 'grey'
      }
    },

  }
</script>

<style scoped>

</style>