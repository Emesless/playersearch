import store from "../store";
import axios from "axios";

store.subscribe((mutation => {
  axios.defaults.baseURL = 'http://localhost:8080/';
  switch (mutation.type) {
    case 'SET_TOKEN':
      if (mutation.payload) {
        axios.defaults.headers.common['Authorization'] = `${mutation.payload}`
        localStorage.setItem('token', mutation.payload)
      } else {
        axios.defaults.headers.common['Authorization'] = null
        localStorage.removeItem('token')
      }
      break;
  }
}));