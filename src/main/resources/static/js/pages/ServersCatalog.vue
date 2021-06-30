<template>
    <div class="ServersCatalog">
        <h1>Список серверов</h1>
        <game-selector @changer='changers'></game-selector>
        <div class="catalog_list">
            <v-item-group>
                <v-container>
                    <v-row>
                        <v-col
                                v-for="product in SERVERS_REQ"
                                key="product.id"
                                cols="12"
                                md="4"
                        >
                            <current-servers-request v-bind:product_data="product"/>
                        </v-col>
                    </v-row>
                </v-container>
            </v-item-group>
        </div>
        <v-container v-if="id != 0">
            <v-container>
                <add-servers-request v-if="authenticated"></add-servers-request>
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
  import currentServersRequest from "../components/server/currentServersRequest.vue";
  import addServersRequest from "../components/server/addServersRequest.vue"
  import {mapActions, mapGetters, mapMutations} from 'vuex'

  export default {
    name: "ServersCatalog.vue",
    components: {
      pagination,
      currentServersRequest,
      GameSelector,
      addServersRequest
    },
    props: {},
    data() {
      return {
        id: 0
      }
    },
    computed: {
      ...mapGetters([
        'SERVERS_REQ'
      ]),
      ...mapGetters([
        'GET_CUR_GAME',
        'FAVORITE_SERVERS',
        'authenticated',
        'user'
      ])
    },
    methods: {
      ...mapActions([
        'getServersReq',
        'getServersReqq',
        'getFavoriteServer'
      ]),
      ...mapMutations([
        "SET_CUR_GAME"
      ]),
      changers(id) {
        console.log('sas' + id);
        this.id = id
        this.SET_CUR_GAME(id)
        this.getServersReq(id)
        this.getFavoriteServer(this.user);
      },
      nextPg(numPage) {
        console.log("lol " + numPage);
        var kek = {id: this.id, numPage: numPage}
        this.getServersReqq(kek)
      }
    },
    mounted() {
      this.changers(this.GET_CUR_GAME);
      this.getFavoriteServer(this.user);
      // получение всех игр
    },
    created() {
      this.getFavoriteServer(this.user);
    }

  }
</script>

<style scoped>

</style>