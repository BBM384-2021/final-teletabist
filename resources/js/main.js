window.Vue = require("vue").default

import Vue from 'vue'

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import { VBScrollspy } from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'bootstrap-vue/dist/bootstrap-vue-icons.min.css'


Vue.config.productionTip = false
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.directive('b-scrollspy', VBScrollspy)

Vue.component('navbar', require('./Components/GeneralComponents/NavBar.vue').default)
Vue.component('footerbar', require('./Components/GeneralComponents/FooterBar.vue').default)
Vue.component('commendpage', require('./Components/GeneralComponents/CommendPage.vue').default)
Vue.component('commentsinclub', require('./Components/GeneralComponents/CommentsInClub.vue').default)


Vue.component('mainclub', require('./Components/ClubComponents/MainClub.vue').default)
Vue.component('membersinclub', require('./Components/ClubComponents/MembersInClub.vue').default)
Vue.component('subclubsinclub', require('./Components/ClubComponents/SubClubsInClub.vue').default)

Vue.component('subclub', require('./Components/SubClubComponents/SubClub.vue').default)

Vue.component('admin', require('./Components/AdminComponents/Admin.vue').default)



const App = new Vue({el:'#App'})