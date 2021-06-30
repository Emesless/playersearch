<template>
    <v-dialog v-model="dialog" max-width="600px">
        <v-card
                class="mx-auto"
                max-width="600"
        >
            <v-card-text>
                <div>{{this.cur_req.game.title}}</div>
                <p class="text-h6 text--primary">
                    {{this.cur_req.title}}
                </p>
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
                <v-btn v-if="this.user.id != this.cur_req.user.id " :to="{name: 'Profile1', params: {id: this.cur_req.user.id}}"
                       color="orange"
                       text
                >
                    Открыть профиль
                </v-btn>
                <v-btn v-if="this.user.id == this.cur_req.user.id || this.user.role == 'ADMIN' " @click="del" text
                       color="deep-purple accent-4">Удалить заявку
                </v-btn>
                <update-servers v-if="this.user.id == this.cur_req.user.id" v-bind:request_info="cur_req"></update-servers>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
  import {mapActions, mapGetters, mapMutations} from 'vuex'
  import updateServers from "./updateServers.vue";
  import axios from 'axios'

  export default {
    name: "detailsServerReq",
    components: {
      updateServers
    },

    data() {
      return {
        cur_req: {},
        dialog: true
      }
    },
    computed: {
      ...mapGetters([
        'GET_CUR_GAME',
        'user'
      ])
    },
    mounted() {
      axios.get(`api/servers/${this.GET_CUR_GAME}/ServersList/${this.$route.params.cur}`).then(response => this.cur_req = response.data)
    },
    methods: {
      close() {
        this.dialog = false
        this.$router.go(-1)
      },
      del() {
        axios.post(`api/profile/${this.user.id}/servers/${this.cur_req.id}/del`)
        this.$router.go(-1)
      }
    }
  }
</script>

<style scoped>

</style>