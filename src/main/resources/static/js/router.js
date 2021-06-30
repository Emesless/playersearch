import VueRouter from 'vue-router'
import home from "pages/home.vue";
import blog from "components/blog.vue";
import registration from "components/registration.vue";
import Authenicationvue from "components/Authenicationvue.vue";
import PlayersCatalog from "pages/PlayersCatalog.vue";
import Profile from "pages/Profile.vue";
import detailsPlayerReq from "components/requests/detailsPlayerReq.vue";
import ServersCatalog from "./pages/ServersCatalog.vue";
import detailsServerReq from "components/server/detailsServerReq.vue";
import Favorite from "./pages/Favorite.vue";
import getFeedback from "./components/getFeedback.vue";

export default new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: home
    },
    {
      path: '/blog',
      name: 'blog',
      component: PlayersCatalog
    },
    {
      path: '/servers',
      name: 'servers',
      component: ServersCatalog
    },
    {
      path: '/registration',
      name: 'registration',
      component: registration
    }
    ,
    {
      path: '/api/auth/login',
      name: 'Authenicationvue',
      component: Authenicationvue
    },
    {
      path: '/api/auth/profile/:id',
      name: 'Profile',
      component: Profile
    },
    {
      path: '/profile/:id',
      name: 'Profile1',
      component: Profile
    },
    {
      path: '/game/:id/playerReq/:cur',
      name: 'PlayerReq',
      component: detailsPlayerReq
    },
    {
      path: '/servers/:id/serversReq/:cur',
      name: 'ServersReq',
      component: detailsServerReq
    },
    {
      path: '/favorite',
      name: 'Favorite',
      component: Favorite
    },
    {
      path: '/feedback',
      name: 'getFeedback',
      component: getFeedback
    }

  ]
})