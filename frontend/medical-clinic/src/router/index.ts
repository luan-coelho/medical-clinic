import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Patients',
    component: () => import('@/views/PatientView.vue'),
  },
  {
    path: '/save',
    name: 'Save Patient',
    component: () => import('@/views/CreatePatientView.vue'),
  },
  {
    path: '/edit/:id',
    name: 'Edit Patient',
    component: () => import('@/views/EditPatientView.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  document.title = to.name ? to.name.toString() : 'Cliniconect';
  next();
});

export default router;
