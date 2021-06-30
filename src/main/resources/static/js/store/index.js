import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios';
import auth from "./auth";
import PlayerSearchStore from "./PlayerSearchStore";
import gameStore from "./gameStore";
import chatMessages from "./chatMessages";
import ServersStore from "./ServersStore";
import favoriteStore from "./favoriteStore";

Vue.use(Vuex)
export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    auth,
    PlayerSearchStore,
    ServersStore,
    gameStore,
    favoriteStore,
    chatMessages
  },
})