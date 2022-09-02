<template>
  <div>
    <Dropdown
      v-model="selectedFilter"
      :options="patientFieldFilter"
      optionLabel="name"
      placeholder="Select a filter"
    />
  </div>
</template>

<script lang="ts">
  import Dropdown from 'primevue/dropdown';
  import { defineComponent } from 'vue';

  export default defineComponent({
    components: {
      Dropdown,
    },
    data() {
      return {
        selectedFilter: null,
        patientFieldFilter: [
          { name: 'Name', value: 'name' },
          { name: 'Email', value: 'email' },
          { name: 'CPF', value: 'cpf' },
        ],
      };
    },
  });
</script>

<style scoped></style>
