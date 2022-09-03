<template>
  <div>
    <div class="grid flui grid-cols-2">
      <div>
        <div>
          <span class="text-base font-bold">Name: </span>
          {{ patient.name }}
        </div>
        <div>
          <span class="text-base font-bold">CPF: </span>
          {{ patient.cpf }}
        </div>
        <div>
          <span class="text-base font-bold">Email: </span>
          {{ patient.email }}
        </div>
        <div>
          <span class="text-base font-bold">Cell Phone: </span>
          {{ patient.cellPhone }}
        </div>
        <div>
          <span class="text-base font-bold">Gender: </span>
          {{ patient.gender }}
        </div>
        <div>
          <span class="text-base font-bold">Birthday: </span>
          {{ patient.birthday }}
        </div>
      </div>

      <div v-if="patient.address.length > 0">
        <span class="text-lg font-bold">Addresses</span>
        <div
          class="mb-4"
          v-for="(address, index) in patient.address"
          :key="index"
        >
          <h3 class="font-bold italic">{{ address.street }}</h3>
          <div class="grid flui">
            <div class="col-6">
              <span class="text-base font-bold">Number: </span>
              {{ address.number }}
              <span class="text-base font-bold">District: </span>
              {{ address.district }}
            </div>
            <div class="col-6">
              <span class="text-base font-bold">City: </span>
              {{ address.city + '-' + address.state }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import Patient from '@/model/Patient';

  import { defineComponent, PropType } from 'vue';

  export default defineComponent({
    components: {},
    data() {
      return {};
    },
    props: {
      patient: {
        required: true,
        type: Object as PropType<Patient>,
      },
    },
  });
</script>

<style scoped></style>
