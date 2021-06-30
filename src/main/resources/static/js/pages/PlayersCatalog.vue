<template>
    <div class="PlayersCatalog">
        <h1>Заявки игровоков</h1>
        <game-selector @changer='changers'></game-selector>
        <div class="catalog_list">
            <v-item-group>
                <v-container>
                    <v-row>
                        <v-col
                                v-for="product in PLAYERSREQ"
                                v-bind:key="PLAYERSREQ.id"
                                cols="12"
                                md="4"
                        >
                            <current-player-request v-bind:product_data="product"/>
                        </v-col>
                    </v-row>
                </v-container>
            </v-item-group>
        </div>
        <v-container v-if="id!= null">
            <v-container>
                <add-player-request v-if="authenticated"></add-player-request>
            </v-container>
            <v-container>
                <pagination v-if="authenticated" @nextPage="nextPg"></pagination>
            </v-container>
        </v-container>
    </div>
</template>
<script>
  import GameSelector from "../components/GameSelector.vue";
  import pagination from "../components/pagination.vue";
  import currentPlayerRequest from "../components/requests/currentPlayerRequest.vue";
  import addPlayerRequest from "../components/requests/addPlayerRequest.vue";
  import {mapActions, mapGetters, mapMutations} from 'vuex'

  export default {
    name: 'PlayersCatalog',
    components: {
      pagination,
      currentPlayerRequest,
      GameSelector,
      addPlayerRequest
    },
    props: {},
    data() {
      return {
        id: 0,
        bool: false
      }
    },
    computed: {
      ...mapGetters([
        'PLAYERSREQ'
      ]),
      ...mapGetters([
        'GET_CUR_GAME',
        'FAVORITE_REQ',
        'authenticated',
        'user'
      ])
    },
    methods: {
      ...mapActions([
        'getPayersReq',
        'getPayersReqq',
        'getFavoriteRequest',

      ]),
      ...mapMutations([
        "SET_CUR_GAME"
      ]),
      changers(id) {
        console.log('sas' + id);
        this.id = id
        this.SET_CUR_GAME(id)
        this.getPayersReqq({id: this.id, numPage: 0})
        this.getFavoriteRequest(this.user)
      },
      nextPg(numPage) {
        this.getPayersReqq({id: this.id, numPage: numPage})
      }
    },
    updated() {
      this.getFavoriteRequest(this.user)
    },
    mounted() {
      this.changers(this.GET_CUR_GAME);
      // получение всех игр
    },
    created() {
      this.getFavoriteRequest(this.user)
    }

  }
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>