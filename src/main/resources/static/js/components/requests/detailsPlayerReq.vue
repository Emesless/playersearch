<template>
    <v-dialog v-model="dialog" max-width="600px">
        <v-card
                class="mx-auto"
                max-width="600"
        >
            <v-card-text>

                <div>{{this.cur_req.game.title}}</div>
                <div class="d-flex flex-row">
                    <p class="text-h6 text--primary">
                        {{this.cur_req.title}}
                    </p>
                    <v-btn @click="add_to_favorite"
                           icon v-if="authenticated">
                        <v-icon v-bind:color="color"> mdi-star</v-icon>
                    </v-btn>
                </div>
                <p>Прайм тайм</p>
                <p>{{this.cur_req.timeStart}} - {{this.cur_req.timeEnd}} </p>
                <div class="text--primary">
                    {{this.cur_req.info}}
                </div>
            </v-card-text>
            <v-card-actions>
                <v-btn @click="close"
                       text
                       color="deep-purple accent-4"
                >
                    Закрыть
                </v-btn>
                <v-btn v-if="authenticated && this.user.id != this.cur_req.user.id " :to="{name: 'Profile1', params: {id: this.cur_req.user.id}}"
                       color="orange"
                       text
                >
                    Открыть профиль
                </v-btn>


                <v-btn v-if="authenticated && this.user.id == this.cur_req.user.id || this.user.role == 'ADMIN' " @click="del" text
                       color="deep-purple accent-4">Удалить заявку
                </v-btn>
                <update-request v-if="authenticated && this.user.id == this.cur_req.user.id " v-bind:request_info="cur_req"></update-request>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
  import {mapActions, mapGetters, mapMutations} from 'vuex'
  import axios from 'axios'
  import updateRequest from "./updateRequest.vue";

  export default {
    components: {
      updateRequest
    },
    name: "detailsPlayerReq",

    data() {
      return {
        color: 'grey',
        cur_req: {},
        dialog: true
      }
    },
    computed: {
      ...mapGetters([
        'GET_CUR_GAME',
        'authenticated',
        'FAVORITE_REQ',
        'user'
      ])
    },
    mounted() {
      this.take_info()
    },
    methods: {
      close() {
        this.dialog = false
        this.$router.go(-1)
      },
      del() {
        axios.post(`api/profile/${this.user.id}/requests/${this.cur_req.id}/del`)
        this.$router.go(-1)
      },
      async take_info() {
        await axios.get(`api/game/${this.$route.params.id}/PlayersListt/${this.$route.params.cur}`).then(response => this.cur_req = response.data)
        console.log(JSON.stringify(this.cur_req.id))
        if (this.FAVORITE_REQ.find(x => x.id === this.cur_req.id)) {
          console.log(this.product_data.id)
          this.color = 'yellow'
        } else {
          this.color = 'grey'
        }
      },
      async add_to_favorite() {
        await axios.post(`/api/game/${this.cur_req.id}/fav`)
        if (this.color == 'grey') {
          this.color = 'yellow'
        } else {
          this.color = 'grey'
        }
      },
    },
  }
</script>

<style scoped>

</style>