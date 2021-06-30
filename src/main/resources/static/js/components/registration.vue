<template>
    <v-dialog v-model="dialog" max-width="600px">

        <v-card>
            <v-card-title>
                <v-tab v-if="!authenticated" to="api/auth/login">Авторизация</v-tab>
                <v-tab class="cyan lighten-5">Регистрация</v-tab>

            </v-card-title>
            <v-card-text>
                <v-container grid-list-md>
                    <v-layout wrap>
                        <v-flex xs12 sm6 md4>
                            <v-text-field v-model="user.firstName" label="Имя*" required></v-text-field>
                        </v-flex>
                        <v-flex xs12 sm6 md4>
                            <v-text-field v-model="user.nickname" label="Никнейм" hint="example of helper text only on focus"></v-text-field>
                        </v-flex>
                        <v-flex xs12>
                            <v-text-field v-model="user.email" label="Email*" required></v-text-field>
                        </v-flex>
                        <v-flex xs12>
                            <v-text-field v-model="user.password" label="Password*" type="password" required></v-text-field>
                        </v-flex>
                        <v-flex xs12 sm6>
                            <v-menu
                                    ref="menu"
                                    v-model="menu"
                                    :close-on-content-click="false"
                                    :return-value.sync="user.birthdate"
                                    transition="scale-transition"
                                    offset-y
                                    min-width="auto"
                            >
                                <template v-slot:activator="{ on, attrs }">
                                    <v-text-field
                                            v-model="user.birthdate"
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
                        <v-flex xs12 sm6>

                        </v-flex>

                        <div class="list-item" v-for="(item, index) in items">
                            <v-layout>
                                <v-flex xs12 sm6>
                                    <v-select
                                            v-model="selected[index]"
                                            :items="difContact"
                                            item-text="state"
                                            item-value="abbr"
                                            label="Select"
                                            persistent-hint
                                            return-object
                                            single-line
                                    ></v-select>
                                </v-flex>

                                <v-flex xs12 sm6>
                                    <v-text-field v-model="contacts[index]" label="контакт" hint="example of helper text only on focus"></v-text-field>
                                </v-flex>
                            </v-layout>
                        </div>
                    </v-layout>
                    <v-layout>
                        <v-btn @click='addNewItem'>Добавить контакт</v-btn>
                        <v-btn @click='deleteItem'>Удалить</v-btn>
                    </v-layout>

                </v-container>
                <small>*indicates required field</small>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">Закрыть</v-btn>
                <v-btn color="blue darken-1" text @click="addUser">Регистрация</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>
<script>
  import axios from 'axios'
  import {mapGetters} from 'vuex'

  export default {
    props: ['users'],
    data() {
      return {
        difContact: [
          {state: 'Discord', abbr: 'Discord'},
          {state: 'Steam', abbr: 'Steam'},
          {state: 'Skype', abbr: 'Skype'},
          {state: 'TeamSpeak', abbr: 'TeamSpeak'},
          {state: 'VK', abbr: 'VK'},
        ],
        date: new Date().toISOString().substr(0, 10),
        menu: false,
        modal: false,
        menu2: false,
        user: {
          firstName: '',
          nickname: '',
          email: '',
          password: '',
          birthdate: this.date
        },
        dialog: true,
        items: [
          {
            title: '',
            text: ''
          }
        ],
        selected: [],
        contacts: []
      }
    },
    methods: {
      addUser() {
        console.log(JSON.stringify(this.user))
        axios.post('api/registration', this.user).then(response => {
          console.log(response)
        }).catch(e => console.log(e));
        for (var i = 0; i < this.selected.length; i++) {
          console.log(this.selected[i] + " " + this.contacts[i])
          axios.post('api/registration/contacts', {title: this.selected[i].abbr, info: this.contacts[i]})
        }
        this.dialog = false
        this.$router.go(-1)
        /* this.$resource('api/registration').save({}, this.user).then(result => result.json());
         this.user.email = '';
         this.user.password = '';*/

        /*                console.log(user);
                        this.$http.post('http://localhost:8080/api/registration', user)
                            .then(response => { user = response.data
                            }).
                        catch(error=>
                        {console.log(error)})*/
      },
      addNewItem() {
        this.items.push({
          title: 'Your title here',
          text: 'Your text here'
        });
        var sas = JSON.stringify(this.selected);
        var keke = JSON.parse(sas)

        console.log(this.selected[0].abbr)
        console.log(this.contacts[0])
      },
      deleteItem() {
        console.log(this.selected[0])
        this.items.pop()
      }
    },
    computed: {
      ...mapGetters([
        'authenticated'
      ])
    }
  }
</script>