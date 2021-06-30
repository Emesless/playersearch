<template>

    <v-card class="mx-auto justify-center " max-width="1000" tile>
        <v-img height="250" src="https://images-na.ssl-images-amazon.com/images/I/716ISRXpkYL._AC_SL1500_.jpg"></v-img>
        <v-row style="margin:3.5%;position:absolute; top: 200px; width: 900px" class="justify-center " align="center"
               justify="center">
            <template>
                <v-dialog v-model="bool" max-width="600px">
                    <v-card>
                        <v-card-text>
                            <v-container grid-list-md>
                                <v-layout wrap>
                                    <v-flex xs12 sm6 md4>
                                        <v-text-field v-model="profile_data.firstName" label="Имя*" required></v-text-field>
                                    </v-flex>
                                    <v-flex xs12 sm6 md4>
                                        <v-text-field v-model="profile_data.nickname" label="Никнейм"
                                                      hint="example of helper text only on focus"></v-text-field>
                                    </v-flex>
                                    <v-flex xs12>
                                        <v-text-field v-model="profile_data.email" label="Email*" required></v-text-field>
                                    </v-flex>
                                    <v-flex xs12 sm6>
                                        <v-menu
                                                ref="menu"
                                                v-model="menu"
                                                :close-on-content-click="false"
                                                :return-value.sync="profile_data.birthdate"
                                                transition="scale-transition"
                                                offset-y
                                                min-width="auto"
                                        >
                                            <template v-slot:activator="{ on, attrs }">
                                                <v-text-field
                                                        v-model="profile_data.birthdate"
                                                        label="Дата рождения"
                                                        readonly
                                                        v-bind="attrs"
                                                        v-on="on"
                                                ></v-text-field>
                                            </template>
                                            <v-date-picker
                                                    v-model="date"
                                                    no-title
                                                    scrollable
                                            >
                                                <v-spacer></v-spacer>
                                                <v-btn
                                                        text
                                                        color="primary"
                                                        @click="menu = false"
                                                >
                                                    Cancel
                                                </v-btn>
                                                <v-btn
                                                        text
                                                        color="primary"
                                                        @click="$refs.menu.save(date)"
                                                >
                                                    OK
                                                </v-btn>
                                            </v-date-picker>
                                        </v-menu>
                                    </v-flex>

                                    <div class="list-item" v-for="(item, index) in profile_contacts">
                                        <v-layout>
                                            <v-flex xs12 sm6>
                                                <v-select
                                                        v-model="profile_contacts[index].title"
                                                        :items="title"
                                                        value="title"
                                                        item-text="title"
                                                        label="Select"
                                                        persistent-hint
                                                        return-object
                                                        single-line
                                                ></v-select>
                                            </v-flex>
                                            <v-flex xs12 sm6>
                                                <v-text-field v-model="profile_contacts[index].info" label="контакт"
                                                              hint="example of helper text only on focus"></v-text-field>
                                            </v-flex>
                                        </v-layout>
                                    </div>
                                    <v-layout>
                                        <v-btn @click='addNewItem'>Добавить контакт</v-btn>
                                        <v-btn @click='deleteItem'>Удалить</v-btn>
                                    </v-layout>
                                </v-layout>
                            </v-container>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="close">Закрыть</v-btn>
                            <v-btn color="blue darken-1" text @click="updateUser">Сохранить</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </template>
            <v-list-item class="grey lighten-4">
                <v-list-item-avatar size="100">
                    <img
                            src="https://www.w3schools.com/howto/img_avatar.png"
                            alt="John"
                    >
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title class="title" style="margin-top:20px;">{{profile_data.nickname}}</v-list-item-title>
                    <v-list-item-subtitle></v-list-item-subtitle>
                </v-list-item-content>
                <v-list-item-content style="margin-top:20px;">
                    <div class="card-footer">
                        <div class="row">
                            <div class="col-sm-4 border-right">
                                <div class="description-block">
                                    <v-tab v-if="swither== true" @click="getProfileReq">
                                        Заявки игрока
                                    </v-tab>
                                    <v-tab v-else @click="getProfileReq">Отзывы</v-tab>
                                </div>
                                <!-- /.description-block -->
                            </div>
                            <!-- /.col -->
                            <div class="col-sm-4 border-right">
                                <div class="description-block">
                                    <v-tab @click="add_like">
                                        {{likes}} Like
                                    </v-tab>
                                </div>
                                <!-- /.description-block -->
                            </div>
                            <!-- /.col -->
                            <div class="col-sm-4">
                                <div class="description-block">
                                    <h5 class="description-header">{{profile_comments.length}}</h5>
                                    <span class="description-text">Комментариев</span>
                                </div>
                                <!-- /.description-block -->
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->
                    </div>
                </v-list-item-content>
            </v-list-item>
            <v-container fluid class="grey lighten-3 mx-auto justify-center">
                <v-list-item>
                    <v-row>
                        <v-col>
                            <v-item>Имя</v-item>
                        </v-col>
                        <v-col>
                            <v-item>{{profile_data.firstName}}</v-item>
                        </v-col>
                    </v-row>
                </v-list-item>

                <v-list-item>
                    <v-row>
                        <v-col>
                            <v-item>Дата рождения</v-item>
                        </v-col>
                        <v-col>
                            <v-item>{{profile_data.birthdate}}</v-item>
                        </v-col>
                    </v-row>
                </v-list-item>

                <v-list-item>
                    <v-row>
                        <v-col>
                            <v-item>Контакты</v-item>
                        </v-col>
                        <v-col>
                            <v-container v-for="item in profile_contacts"
                                         :key="item.id">{{item.title}}: {{item.info}}
                            </v-container>
                        </v-col>
                    </v-row>

                </v-list-item>
                <v-list-item>
                    <v-list-item-content></v-list-item-content>
                </v-list-item>
                <v-btn v-if="this.user.id === profile_data.id" @click="change">Изименить информацию о себе</v-btn>
                </br></br>
                <v-btn v-if="this.user.id === profile_data.id" @click="exit">Выйти</v-btn>
                <div v-if="swither === false">
                    <h2>Текущие заявки</h2>
                    <div class="catalog_list">
                        <v-item-group>
                            <v-container>
                                <v-row>
                                    <v-col
                                            v-for="product in profile_players_req"
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
                </div>

                <div v-if="swither === false">
                    <h2>Сервера</h2>
                    <div class="catalog_list">
                        <v-item-group>
                            <v-container>
                                <v-row>
                                    <v-col
                                            v-for="product in profile_servers_req"
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
                <div v-if="swither">
                    <v-list-item two-line>
                        <v-list-item-content>
                            <v-list-item-title>Комментарии пользователей</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-simple-table class="mx-auto" style="max-width: 800px">
                        <tbody>
                        <tr
                                v-for="item in profile_comments"
                                :key="item.id"
                        >
                            <td id="keks" class="text-sm-left">
                                <v-list-item two-line>
                                    <v-list-item-content>
                                        <v-list-item-title class="text-wrap">{{ item.comments}}</v-list-item-title>
                                        <v-list-item-subtitle>
                                            <router-link :to="{name: 'Profile1', params: {id: item.senderUser.id}}"> {{ item.senderUser.firstName }}
                                            </router-link>
                                            <v-icon v-if="user.role == 'ADMIN'" @click="deleteComment(item.id)">mdi-close</v-icon>
                                        </v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                            </td>
                        </tr>
                        </tbody>
                    </v-simple-table>
                    <v-container v-if="swither" fluid>
                        <v-textarea
                                v-model="comment"
                                name="input-7-1"
                                filled
                                label="Ваш комментарий"
                                background-color="grey lighten-2"
                                auto-grow
                                value=""
                        ></v-textarea>
                        <v-btn @click="add_comments" type="submit">Добавить комментарий</v-btn>
                    </v-container>
                </div>
            </v-container>

        </v-row>
    </v-card>

</template>

<script>
  import axios from 'axios';
  import {mapGetters, mapActions} from 'vuex'
  import currentPlayerRequest from "../components/requests/currentPlayerRequest.vue";
  import currentServersRequest from "../components/server/currentServersRequest.vue";

  export default {
    name: "Profile",
    components: {
      currentServersRequest,
      currentPlayerRequest
    },
    props: {},
    data() {
      return {
        menu: false,
        date: '',
        title: [
          'Discord',
          'Steam',
          'Skype',
          'TeamSpeak',
          'VK',
        ],
        comment: '',
        bool: false,
        swither: true,
        profile_data: {},
        profile_comments: [],
        profile_contacts: [],
        profile_players_req: [],
        profile_servers_req: [],
        likes: 0
      }
    },
    methods: {
      ...mapActions([
        'signOut'
      ]),
      change() {
        this.bool = !this.bool;
      },
      another_proile() {
        axios.get(`api/profile/${this.$route.params.id}`)
          .then(response => (this.profile_data = response.data));
        axios.get(`api/profile/${this.$route.params.id}/comments`).then(response => (this.profile_comments = response.data))
        axios.get(`api/profile/${this.$route.params.id}/contacts`).then(response => (this.profile_contacts = response.data))
        axios.get(`api/profile/${this.$route.params.id}/likes`).then(response => (this.likes = response.data))
      },
      async add_comments() {
        await axios.post(`api/profile/${this.$route.params.id}/comments`, {
          comments: this.comment,
          addressesUser: this.$route.params.id, senderUser: this.user.id
        })
        axios.get(`api/profile/${this.$route.params.id}/comments`).then(response => (this.profile_comments = response.data))
        this.comment = ''
      },
      async add_like() {
        await axios.post(`api/profile/${this.$route.params.id}/likes`)
        axios.get(`api/profile/${this.$route.params.id}/likes`).then(response => (this.likes = response.data))
      },
      addNewItem() {
        this.profile_contacts.push({
          title: null,
          info: null
        });
      },
      deleteItem() {
        this.profile_contacts.pop()
      },
      updateUser() {
        axios.post(`api/profile/${this.$route.params.id}/upd`, this.profile_data).then(response => {
          console.log(response)
        }).catch(e => console.log(e));
        for (let i = 0; i < this.profile_contacts.length; i++) {
          if (this.profile_contacts[i].info == null || this.profile_contacts[i].title == null) {
            this.profile_contacts.splice(i, 1)
            i--
          }
        }
        axios.post(`api/profile/${this.$route.params.id}/upd/contacts`, this.profile_contacts).then(response => {
          console.log(response)
        }).catch(e => console.log(e));
        this.change()
      },
      close() {
        this.change()
        this.$router.go()
      },
      getProfileReq() {
        axios.get(`api/profile/${this.$route.params.id}/requests`).then(response => (this.profile_players_req = response.data))
        axios.get(`api/profile/${this.$route.params.id}/servers`).then(response => (this.profile_servers_req = response.data))
        this.swither = !this.swither
      },
      exit() {
        this.signOut().then(() => this.$router.replace({name: 'home'}))
      },
      async deleteComment(id) {
        await axios.post(`/api/admin/profile/${this.$route.params.id}/comments/${id}`)
        axios.get(`api/profile/${this.$route.params.id}/comments`).then(response => (this.profile_comments = response.data))
      }
    },
    mounted() {
      this.another_proile()
    },
    computed: {
      ...mapGetters([
        'user'
      ]),
    },
    watch: {
      $route(to, from) {
        this.another_proile();
      }
    }
  }
</script>

<style scoped>
    #keks {
        width: 50px;
    }
</style>