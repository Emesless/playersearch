import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios';
import response from "vue-resource/src/http/response";

Vue.use(Vuex);

export default {
  state: {
    playerRequest: [],
    currentPage: 0,
    totalPage: 0
  },
  mutations: {
    SET_PLAYERS_REQ(state, playersReq) {
      console.log(playersReq)
      state.playerRequest = playersReq
    },
    SET_TOTAL_PAGE(state, totalPages) {
      state.totalPage = totalPages
    }
  },
  actions: {
    getPayersReq({commit}, id) {
      axios.get(`http://localhost:8080/api/game/${id}/PlayersListt`,
        {
          params: {}
        })
        .then(response => {
          commit('SET_TOTAL_PAGE', response.data.totalPages)
          return commit('SET_PLAYERS_REQ', response.data.playerSearchList)
        })
        .catch(error => {
          return console.log(error)
        })
    },

    getPayersReqq({commit}, data) {
      console.log("sas" + data.numPage)
      console.log("kek" + data.id)
      axios.get(`http://localhost:8080/api/game/${data.id}/PlayersListt`,
        {
          params: {
            page: data.numPage
          }
        })
        .then(response => {
          commit('SET_TOTAL_PAGE', response.data.totalPages)
          return commit('SET_PLAYERS_REQ', response.data.playerSearchList)
        })
        .catch(error => {
          return console.log(error)
        })
    }
  },
  getters: {
    PLAYERSREQ(state) {
      return state.playerRequest
    },
    GETPAGES(state) {
      return state.totalPage
    }
  }
}