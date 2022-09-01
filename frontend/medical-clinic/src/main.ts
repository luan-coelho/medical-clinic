import naive from 'naive-ui';
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';

import 'animate.css';
import 'flowbite';
import 'normalize.css';
import './index.css';

import { library } from '@fortawesome/fontawesome-svg-core';
import { faGithub } from '@fortawesome/free-brands-svg-icons';
import { faTrash, faUserPen } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

library.add(faGithub, faUserPen, faTrash);

createApp(App)
  .use(router)
  .use(naive)
  .component('font-awesome-icon', FontAwesomeIcon)
  .mount('#app');
