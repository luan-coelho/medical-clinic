<template>
  <NavBar />
  <router-view></router-view>
</template>

<script lang="ts">
  import { defineComponent } from 'vue';
  import NavBar from './components/NavBar.vue';

  export default defineComponent({
    components: {
      NavBar,
    },
    data() {
      return {};
    },
  });
</script>

<style scoped></style>
