import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios';
import auth from "./auth";
import response from "vue-resource/src/http/response";
import {mapGetters} from 'vuex'

Vue.use(Vuex);

export default {
  computed: {
    ...mapGetters([
      'user'
      // ...
    ])
  },
  state: {
    favorite_request: [],
    favorite_servers: [],
  },
  mutations: {
    SET_FAVORITE_REQ(state, playersReq) {
      state.favorite_request = []
      for (let i = 0; i < playersReq.length; i++) {
        state.favorite_request.push(playersReq[i].playerSearchList)
      }
      console.log()
    },
    SET_FAVORITE_SERVERS(state, servers) {
      state.favorite_servers = [];
      for (let i = 0; i < servers.length; i++) {
        state.favorite_servers.push(servers[i].serverSearchList)
      }
    }
  },
  actions: {
    getFavoriteRequest({commit}, data) {
      axios.get(`api/profile/${data.id}/fav/players`).then(response =>
        commit('SET_FAVORITE_REQ', response.data))
    },
    getFavoriteServer({commit}, data) {
      axios.get(`api/profile/${data.id}/fav/servers`).then(response =>
        commit('SET_FAVORITE_SERVERS', response.data))
    }
  },
  getters: {
    FAVORITE_REQ(state) {
      return state.favorite_request
    },
    FAVORITE_SERVERS(state) {
      return state.favorite_servers
    }
  }
}