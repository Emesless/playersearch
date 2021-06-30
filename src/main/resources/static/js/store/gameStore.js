import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios';
import response from "vue-resource/src/http/response";
import {error} from "vue-resource/src/util";

Vue.use(Vuex);

export default {
  state: {
    allGames: [],
    cur_game: null
  },
  mutations: {
    SET_GAME(state, games) {
      state.allGames = games
    },
    SET_CUR_GAME(state, game) {
      state.cur_game = game
    }
  },
  actions: {
    getAllGames({commit}) {
      axios.get('http://localhost:8080/api/game/').then(response => {
          return commit('SET_GAME', response.data)
        })
        .catch(error => {
          return console.log(error)
        })
    }
  },
  getters: {
    GET_GAMES(state) {
      return state.allGames
    },
    GET_CUR_GAME(state) {
      return state.cur_game
    }
  }
}