<template>
    <v-main>
        <h1>О сайте</h1>
        <v-container><h2> Из-за обилия многопользовательских игр, бывает сложно найти
            себе партнера или целую компанию для совместного времяпрепровождения в
            конкретной игре. Чтобы облегчить и автоматизировать этот процесс была разработана
            данная информационная система подбора участников
            многопользовательских игр.</h2></v-container>
        <v-container fluid fill-height v-if="authenticated">
            <v-layout align-center justify-center>
                <v-flex xs12 sm8 md4 lg6>
                    <v-card class="elevation-12 rounded-xl" color="primary lighten-4">
                        <v-toolbar dark color="blue-grey">
                            <v-toolbar-title>Chat</v-toolbar-title>
                        </v-toolbar>
                        <v-card-text>
                            <v-list class="rounded-xl" ref="chat" id="logs">
                                <template v-for="(item, index) in logs">
                                    <v-subheader v-if="item" :key="index">{{ item }}</v-subheader>
                                </template>
                            </v-list>
                        </v-card-text>

                        <v-form @submit.prevent="submit">
                            <v-textarea class="rounded-xl" id="Chat" v-model="msg" background-color="cyan lighten-4"
                                        color="cyan"
                                        autofocus
                                        label="Label" single-line solo-inverted
                                        rows="3"></v-textarea>
                            <v-btn fab dark small color="primary" type="submit">
                                <v-icon dark>send</v-icon>
                            </v-btn>
                        </v-form>

                    </v-card>
                </v-flex>
            </v-layout>
        </v-container>
        <feedback v-if="authenticated"></feedback>
    </v-main>
</template>
<script>
  import feedback from "../components/AddFeedback.vue";
  import {sendMessage} from "../util/ws";
  import {mapGetters} from 'vuex'
  import {addHandler} from "../util/ws";
  import axios from "axios";

  export default {
    components: {
      feedback
    },
    name: 'home',
    data: () => ({
      logs: [],
      msg: null,
    }),
    methods: {
      submit() {
        sendMessage({message: this.msg, user: this.user})
        console.log(JSON.stringify(this.user) + 'sas')
        //axios.post('http://localhost:8080/chat', {message: this.msg, user: this.user})
        /*this.logs.push( this.user.firstName + " " + this.user.lastName + ": " + this.msg);*/
        this.msg = "";

      },

      get_message() {
        axios.get('http://localhost:8080/chat').then(response => console.log(response))
      }
    },

    computed: {
      ...mapGetters([
        'user',
        'authenticated'
      ])
    },

    watch: {
      logs() {
        setTimeout(() => {
          this.$refs.chat.$el.scrollTop = this.$refs.chat.$el.scrollHeight;
        }, 0);
      }
    },
    created() {
      addHandler(data => {
        this.logs.push(data.user.nickname + ": " + data.message)
      })
    }
  }
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    #logs {
        height: 200px;
        overflow: auto;
    }


</style>