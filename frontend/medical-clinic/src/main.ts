import naive from 'naive-ui';
import PrimeVue from 'primevue/config';
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';

import 'animate.css';
import 'flowbite';
import 'normalize.css';
import 'primeicons/primeicons.css'; //icons
import 'primevue/resources/primevue.min.css';
import 'primevue/resources/themes/saga-blue/theme.css';
import './index.css';

import { library } from '@fortawesome/fontawesome-svg-core';
import { faGithub } from '@fortawesome/free-brands-svg-icons';
import { faTrash, faUserPen } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

library.add(faGithub, faUserPen, faTrash);

import Button from 'primevue/button';
import Column from 'primevue/column';
import ConfirmationService from 'primevue/confirmationservice';
import DataTable from 'primevue/datatable';
import Dialog from 'primevue/dialog';
import Dropdown from 'primevue/dropdown';
import InputText from 'primevue/inputtext';

createApp(App)
  .use(router)
  .use(naive)
  .use(PrimeVue)
  .use(ConfirmationService)
  .component('font-awesome-icon', FontAwesomeIcon)
  .component('Dropdown', Dropdown)
  .component('DataTable', DataTable)
  .component('Column', Column)
  .component('InputText', InputText)
  .component('Button', Button)
  .component('Dialog', Dialog)
  .mount('#app');
