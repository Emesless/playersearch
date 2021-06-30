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
                    Отправить отзыв
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
                                        label="Тема обращения"
                                        required
                                ></v-text-field>
                            </v-col>
                            <v-col cols="12">
                                <v-textarea
                                        v-model="request.info"
                                        outlined
                                        name="input-7-4"
                                        label="Полное описание"
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
                        Отмена
                    </v-btn>
                    <v-btn
                            color="blue darken-1"
                            text
                            @click="addNewReq"
                    >
                        Отправить
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-row>
</template>

<script>
  import axios from 'axios';
  import {mapGetters} from 'vuex'

  export default {
    name: "feedback",
    data() {
      return {
        request: {
          title: '',
          info: '',
        },
        dialog: false
      }
    },
    computed: {
      ...mapGetters([
        'user'
      ])
    },
    methods: {
      addNewReq() {
        axios.post(`api/profile/${this.user.id}/feedback`, this.request).then(this.dialog = false)
        this.$router.go()
      },
      close() {
        this.dialog = false
      }
    },
  }
</script>

<style scoped>

</style>