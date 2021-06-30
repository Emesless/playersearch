<template>
    <v-row justify="center">
        <v-dialog
                v-model="dialog"
                persistent
                max-width="600px"
        >
            <template v-slot:activator="{ on, attrs }">
                <v-btn
                        color="teal lighten-2"
                        dark
                        v-bind="attrs"
                        v-on="on"
                >
                    Добавить заявку
                </v-btn>
            </template>
            <v-card>
                <v-card-title>
                    <span class="text-h5">Новая заявка</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>

                            <v-col cols="12">
                                <v-text-field
                                        v-model="request.title"
                                        label="Краткий заголовок"
                                        required
                                ></v-text-field>
                            </v-col>
                            <v-col cols="12">
                                <span class="text-subtitle-2">Прайм тайм</span></br>
                                <input v-model="request.timeStart" type="time"> <span class="text-h5">  -  </span> <input v-model="request.timeEnd" type="time">
                            </v-col>
                            <v-col cols="12">
                                <v-textarea
                                        v-model="request.info"
                                        outlined
                                        name="input-7-4"
                                        label="Подробная информация"
                                ></v-textarea>
                            </v-col>
                        </v-row>
                    </v-container>
                    <small>*indicates required field</small>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                            color="blue darken-1"
                            text
                            @click="close"
                    >
                        Закрыть
                    </v-btn>
                    <v-btn
                            color="blue darken-1"
                            text
                            @click="addNewReq"
                    >
                        Добавить
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-row>
</template>
<script>
  import axios from 'axios';
  import {mapActions, mapGetters, mapMutations} from 'vuex'

  export default {
    name: "addPlayerRequest",
    data() {
      return {
        request: {
          title: '',
          info: '',
          timeStart: null,
          timeEnd: null
        },
        dialog: false
      }
    },
    computed: {
      ...mapGetters([
        'GET_CUR_GAME'
      ])
    },
    methods: {
      ...mapActions([
        'getPayersReqq'
      ]),
      addNewReq() {
        if (this.request.timeStart != null && this.request.timeStart.length < 6) {
          this.request.timeStart += ":00"
        }
        if (this.request.timeEnd != null && this.request.timeEnd.length < 6) {
          this.request.timeEnd += ":00"
        }
        axios.post(`http://localhost:8080/api/game/${this.GET_CUR_GAME}/PlayersList`, this.request).then(this.dialog = false)
        this.getPayersReqq({id: this.GET_CUR_GAME, numPage: 0})
      },
      close() {
        this.dialog = false
        console.log(this.sas)
      }
    },
  }
</script>