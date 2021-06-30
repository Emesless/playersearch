<template>
    <div id="app">
        <v-app id="inspire">
            <v-card>
                <v-card-title>
                    <v-text-field
                            v-model="search"
                            append-icon="mdi-magnify"
                            label="Search"
                            single-line
                            hide-details
                    ></v-text-field>
                </v-card-title>
                <v-data-table
                        :headers="headers"
                        :items="feedbacks"
                        :search="search"
                        hide-default-footer
                >
                </v-data-table>
                <v-pagination
                        v-model="current_page"
                        :length="pageCount"
                        @input="get_data()"
                ></v-pagination>
            </v-card>
        </v-app>
    </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: "getFeedback",
    data() {
      return {
        feedbacks: [],
        pageCount: 2,
        current_page: 0,
        search: '',
        selected: [],
        headers: [
          {text: "ID", value: "user.id"},
          {
            text: 'Никнейм',
            align: 'start',
            filterable: false,
            value: 'user.nickname',
          },
          {text: 'Short', value: 'title'},
          {text: 'Full', value: 'info'},

        ],
      }
    },
    methods: {
      async get_data() {
        await axios.get(`api/admin/feedback`, {
          params: {
            page: this.current_page - 1
          }
        }).then(response => {
          this.pageCount = response.data.totalPages
          this.feedbacks = response.data.feedBacks
        })
      }
    },
    created() {
      this.get_data(this.currentPage)
    }
  }
</script>

<style scoped>

</style>