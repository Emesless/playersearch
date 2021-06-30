import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios';
import response from "vue-resource/src/http/response";

Vue.use(Vuex);

export default {
  state: {
    token: null,
    //   token: localStorage.getItem('user-token') || '',
    user: null
  },

  getters: {
    authenticated(state) {
      return state.token && state.user
    },
    user(state) {
      return state.user
    }
  },

  mutations: {
    SET_TOKEN(state, token) {
      state.token = token
    },
    SET_USER(state, user) {
      state.user = user
    }
  },
  actions: {
    async signIn({dispatch}, credentials) {
      // пробрасывать только токен в диспач response.data.token
      axios.post('http://localhost:8080/api/auth/login', credentials).then(response => {
        return dispatch('attempt', response.data);
      });
      /// dispatch('attempt', response.data.token)
    },
    //обрабатывать токен и получать посредсвом юзера
    async attempt({commit, state}, data) {
      if (data.token) {
        commit('SET_TOKEN', data.token);
      }
      if (!state.token) {
        return

      }

      // в этом месте проверку на наличие такого юзера
      // + сохранение его профиля
      // изменить название name и отредачить
      try {

        var name;
        await axios.get('api/auth/iam').then(response => {
          name = response.data
        });
        console.log(name);
        commit('SET_USER', name)
      } catch (e) {
        console.log('error')
        commit('SET_TOKEN', null);
        commit('SET_USER', null);
      }
    },
    signOut({commit}) {
      return axios.post("api/auth/logout").then(() => {
        commit('SET_TOKEN', null);
        commit('SET_USER', null);
      })
    }
  }
}