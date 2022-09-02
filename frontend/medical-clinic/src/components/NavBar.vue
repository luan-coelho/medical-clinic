<template>
  <nav
    class="uppercase dark:bg-root-color-dark h-14 shadow-lg dark:border-gray-600 p-2"
  >
    <div class="h-full flex justify-around items-center">
      <img
        class="ml-5 self-center whitespace-nowrap w-56"
        src="@/assets/cliniconect.png"
        alt="Cliniconect Logo"
      />

      <div
        class="items-center justify-center w-full flex md:w-auto order-1"
        id="navbar-sticky"
      >
        <ul
          class="flex flex-row items-center justify-center bg-gray-50 rounded-lg border border-gray-100 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0 dark:bg-root-color-dark dark:border-gray-700"
        >
          <li>
            <router-link
              to="/"
              class="block py-2 pr-4 pl-3 rounded md:bg-transparen md:p-0 md:hover:text-blue-700 dark:text-white"
              >Patients</router-link
            >
          </li>
          <li>
            <Button
              @click="activeDarkMode()"
              class="text-black dark:text-white"
              icon="pi pi-moon"
              iconPos="right"
            />
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script lang="ts">
  import Button from 'primevue/button';
  import { defineComponent } from 'vue';

  export default defineComponent({
    components: {
      Button,
    },
    data() {
      return {};
    },
    methods: {
      activeDarkMode() {
        if (
          (document.querySelector('html') as HTMLElement).classList.contains(
            'dark'
          )
        ) {
          (document.querySelector('html') as HTMLElement).classList.remove(
            'dark'
          );
        } else {
          (document.querySelector('html') as HTMLElement).classList.add('dark');
        }
      },
    },
  });
</script>
