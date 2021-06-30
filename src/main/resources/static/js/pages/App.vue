<template>
    <v-app>
        <v-container fixed>
            <v-app-bar
                    fixed
                    color="#6A76AB"
                    dark
                    shrink-on-scroll
                    prominent
                    src="../../gamewall.jpg"
                    fade-img-on-scroll>
                <template v-slot:img="{ props }">
                    <v-img
                            v-bind="props"
                            gradient="to top right, rgba(100,115,201,.7), rgba(25,32,72,.7)"
                    ></v-img>
                </template>
                <v-app-bar-title>Найти игрока</v-app-bar-title>
                <v-spacer></v-spacer>
                <v-spacer></v-spacer>
                <v-btn icon v-if="authenticated" to="/favorite">
                    <v-icon> mdi-heart</v-icon>
                </v-btn>
                <v-tab class="ma-2 pa-2" v-if="!authenticated" to="api/auth/login">Авторизация</v-tab>
                <v-tab class="ma-2 pa-2" v-else :to="{name: 'Profile1', params: {id: this.user.id}}">{{user.firstName}}</v-tab>
                <!--<v-tab class="ma-2 pa-2" v-else to="{name: 'Profile', params: {id: user.id}">{{user.firstName}}</v-tab>-->
                <template v-slot:extension>
                    <v-tabs align-with-title>
                        <v-tab to="/">Главная</v-tab>
                        <v-tab to="/blog">Поиск игроков</v-tab>
                        <v-tab to="/servers">Поис серверов</v-tab>
                        <v-btn v-if="authenticated && user.role == 'ADMIN'" to="/feedback"> Админка</v-btn>

                    </v-tabs>
                </template>
            </v-app-bar>
        </v-container>
        <v-main class="blue lighten-5" style="margin-top: 200px;">

            <v-row class="pa-4 text-center">
                <v-container>
                    <router-view></router-view>
                </v-container>
            </v-row>
        </v-main>
    </v-app>
</template>

<script>
  import {mapGetters, mapActions} from 'vuex'
  import axios from 'axios';

  export default {
    computed: {
      ...mapGetters([
        'authenticated',
        'user'
      ]),
    },
    data() {
      return {
        likes: 0,
      }
    },
    methods: {
      ...mapActions([
        'getFavoriteRequest',
        'getFavoriteServer'
      ])
    },
    created() {
      this.getFavoriteRequest(this.user)
      this.getFavoriteServer(this.user)
    },
    mounted() {
      axios.get(`api/profile/1/likes`).then(response => (this.likes = response.data))
    }

  }

</script>
<style>

</style>