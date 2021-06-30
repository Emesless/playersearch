import Vue from 'vue'
import App from 'pages/App.vue'
import VueResource from 'vue-resource'
import Router from 'vue-router'
import router from "./router";
import store from "./store";
import Vuex from 'vuex'
import '@babel/polyfill'
import Vuetify from "vuetify";
import 'vuetify/dist/vuetify.min.css'
import {connect} from "./util/ws";

require('./store/defaultSubcrib')
connect()

const vuetifyOptions = {}
Vue.use(Vuetify)
Vue.use(Router);

Vue.use(VueResource);
Vue.use(Vuex);
store.dispatch('attempt', {token: localStorage.getItem('token')});

new Vue({
  el: '#app',
  vuetify: new Vuetify(vuetifyOptions),
  render: a => a(App),
  router,
  store
})