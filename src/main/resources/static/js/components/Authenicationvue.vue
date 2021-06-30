<template>
    <v-dialog v-model="dialog" max-width="600px">
        <v-card>
            <v-card-title>
                <v-tab class="cyan lighten-5">Авторизация</v-tab>
                <v-tab v-if="!authenticated" to="/registration">Регистрация</v-tab>
            </v-card-title>
            <v-card-text>

                <v-container>
                    <v-row>
                        <v-col cols="12">
                            <v-text-field v-model="user.email" label="Email*" required></v-text-field>
                        </v-col>
                        <v-col cols="12">
                            <v-text-field v-model="user.password" label="Password*" type="password" required></v-text-field>
                        </v-col>

                    </v-row>
                </v-container>
                <small>*indicates required field</small>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">Закрыть</v-btn>
                <v-btn color="blue darken-1" text @click="auth">Авторизироваться</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>

</template>
<script>
  //добавить кнопку регистрации  в виде таба рядом с авторизацией
  import {mapActions, mapGetters} from 'vuex'

  export default {
    props: ['users'],
    data() {
      return {
        user: {
          email: '',
          password: ''
        },
        dialog: true
      }
    },
    computed: {
      ...mapGetters([
        'authenticated'
      ])
    },
    methods: {
      ...mapActions([
          'signIn'
        ]
      ),
      auth() {
        this.signIn(this.user).then(() => {
          this.dialog = false
          this.$router.replace({
            name: 'home'
          })
        })
        //.catch можно организовать
        // axios.post('http://localhost:8080/api/auth/login', this.user).then(response => {console.log(response)});
        /*this.$resource('api/registration').save({}, this.user).then(result => result.json());
        this.user.email = '';
        this.user.password = '';*/

        /*                console.log(user);
                        this.$http.post('http://localhost:8080/api/registration', user)
                            .then(response => { user = response.data
                            }).
                        catch(error=>
                        {console.log(error)})*/
      }
    }
  }
</script>