import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios';

Vue.use(Vuex);
export default {
  state: {
    serversRequest: [],
    currentPageS: 0,
    totalPageS: 0
  },
  mutations: {
    SET_SERVERS_REQ(state, serversReq) {
      state.serversRequest = serversReq
    },
    SET_TOTAL_PAGE_S(state, totalPages) {
      state.totalPageS = totalPages
    }
  },

  actions: {
    getServersReq({commit}, id) {
      axios.get(`/api/servers/${id}/ServersList`,
        {
          params: {}
        })
        .then(response => {
          console.log(response.data);
          commit('SET_TOTAL_PAGE_S', response.data.totalPages)
          return commit('SET_SERVERS_REQ', response.data.serverSearchList)
        })
        .catch(error => {
          return console.log(error)
        })
    },

    getServersReqq({commit}, data) {
      console.log("sas" + data.numPage)
      console.log("kek" + data.id)
      axios.get(`http://localhost:8080/api/servers/${data.id}/ServersList`,
        {
          params: {
            page: data.numPage
          }
        })
        .then(response => {
          commit('SET_TOTAL_PAGE_S', response.data.totalPages)
          return commit('SET_SERVERS_REQ', response.data.serverSearchList)
        })
        .catch(error => {
          return console.log(error)
        })
    }
  },
  getters: {
    SERVERS_REQ(state) {
      return state.serversRequest
    },
    GET_SERVERS_PAGES(state) {
      return state.totalPageS
    }
  }
}