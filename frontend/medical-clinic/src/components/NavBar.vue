<template>
  <nav
    class="px-2 py-2.5 uppercase bg-gray-50s dark:bg-root-color-dark w-full z-20 top-0 left-0 border-b border-gray-200 dark:border-gray-600"
  >
    <div class="container flex justify-around items-center">
      <img
        class="self-center whitespace-nowrap"
        src="https://lh5.googleusercontent.com/ZlV2koB7Ou5w9Z7EuKVpnQVHFaw85Nkn143iHliGMAutrl6xekZrz9smO5qazGeHd7nTHQ=w16383"
        alt=""
      />

      <div
        class="items-center justify-center w-full flex md:w-auto md:order-1"
        id="navbar-sticky"
      >
        <ul
          class="flex flex-row items-center justify-center sx:p-2 sm:p-3 md:p-4 bg-gray-50 rounded-lg border border-gray-100 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0 dark:bg-root-color-dark dark:border-gray-700"
        >
          <li>
            <router-link
              to="/"
              class="block py-2 pr-4 pl-3 rounded md:bg-transparen md:p-0 md:hover:text-blue-700 dark:text-white"
              >Patients</router-link
            >
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script lang="ts">
  import { defineComponent } from 'vue';

  export default defineComponent({
    data() {
      return {};
    },
  });
</script>
