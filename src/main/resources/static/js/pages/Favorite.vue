<template>
    <div class="PlayersCatalog">
        <h1>Заявки игровоков</h1>
        <div class="catalog_list">
            <v-item-group>
                <v-container>
                    <v-row>
                        <v-col
                                v-for="product in FAVORITE_REQ"
                                key="product.id"
                                cols="12"
                                md="4"
                        >
                            <current-player-request v-bind:product_data="product"/>
                        </v-col>
                    </v-row>
                </v-container>
            </v-item-group>
        </div>
        <h1>Список серверов</h1>
        <div class="catalog_list">
            <v-item-group>
                <v-container>
                    <v-row>
                        <v-col
                                v-for="product in FAVORITE_SERVERS"
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

    </div>
</template>
<script>
  import axios from 'axios';
  import GameSelector from "../components/GameSelector.vue";
  import currentServersRequest from "../components/server/currentServersRequest.vue";
  import currentPlayerRequest from "../components/requests/currentPlayerRequest.vue";
  import {mapActions, mapGetters, mapMutations} from 'vuex'

  export default {
    name: 'Favorite',
    components: {
      currentPlayerRequest,
      currentServersRequest,
      GameSelector,
    },
    props: {},
    data() {
      return {}
    },
    computed: {
      ...mapGetters([
        'FAVORITE_REQ',
        'FAVORITE_SERVERS',
        'user'
      ]),

    },
    methods: {
      ...mapActions([
        'getFavoriteRequest',
        'getFavoriteServer'
      ])
    },
    mounted() {
      console.log('1')
      this.getFavoriteRequest(this.user)
      this.getFavoriteServer(this.user)
    },

    created() {
      console.log('3')
      this.getFavoriteRequest(this.user)
      this.getFavoriteServer(this.user)
    }
  }
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>