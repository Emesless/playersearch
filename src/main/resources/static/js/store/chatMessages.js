import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios';

Vue.use(Vuex);

export default {
  state: {
    messages: []
  },
  mutations: {
    SET_MESSAGES(state, messages) {
      console.log(messages)
      state.messages = messages
    }
  },
  actions: {
    getMessages({commit}) {
      axios.get('http://localhost:8080/chat').then(response => {
          return commit('SET_MESSAGES', response.data)
        })
        .catch(error => {
          console.log("sas");
          return console.log(error)
        })
    }
  },
  getters: {
    GET_MESSAGES(state) {
      return state.messages
    }
  }
}