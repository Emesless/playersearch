<template>
    <v-row justify="center">
        <v-dialog
                v-model="dialog"
                persistent
                max-width="600px"
        >
            <template v-slot:activator="{ on, attrs }">
                <v-btn
                        color="deep-purple accent-4"
                        text
                        v-bind="attrs"
                        v-on="on"
                >
                    Изменить заявку
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
                                        v-model="request_info.title"
                                        label="Краткий заголовок"
                                        required
                                ></v-text-field>
                            </v-col>
                            <v-col cols="12">
                                <span class="text-subtitle-2">Прайм тайм</span></br>
                                <input v-model="request_info.timeStart" type="time"> <span class="text-h5">  -  </span> <input v-model="request_info.timeEnd"
                                                                                                                               type="time">
                            </v-col>
                            <v-col cols="12">
                                <v-textarea
                                        v-model="request_info.info"
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
                            @click="updateReq"
                    >
                        Сохранить
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
    name: "updateRequest",
    props: {
      request_info: {
        type: Object,
        default() {
          return {}
        }
      }
    },
    data() {
      return {
        request: {},
        dialog: false
      }
    },
    computed: {
      ...mapGetters([
        'GET_CUR_GAME',
        'user'
      ])
    },
    methods: {
      updateReq() {
        if (this.request_info.timeStart.length < 6) {
          this.request_info.timeStart += ":00"
        }
        if (this.request_info.timeEnd.length < 6) {
          this.request_info.timeEnd += ":00"
        }
        axios.post(`/api/profile/${this.user.id}/requests/${this.$route.params.cur}/upd`, this.request_info).then(this.dialog = false)
      },
      close() {
        this.dialog = false
        console.log(this.sas)
      }
    },
  }
</script>

<style scoped>

</style>