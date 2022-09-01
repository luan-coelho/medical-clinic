<template>
  <div class="flex items-center flex-col justify-center mt-10">
    <button
      @click="
        {
          $router.push('/save');
        }
      "
      type="button"
      class="focus:outline-none relative right-50 text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
    >
      New Patient
    </button>

    <table class="w-3/4 table-auto text-left shadow-2xl rounded-2xl">
      <thead class="text-sm bg-gray-50 text-white dark:bg-root-color-dark">
        <tr>
          <th class="p-2">ID</th>
          <th>Name</th>
          <th>CPF</th>
          <th>Email</th>
          <th>Birthday</th>
          <th>Gender</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="patient in patients" :key="patient.id">
          <td class="p-3">{{ patient.id }}</td>
          <td>{{ patient.name }}</td>
          <td>{{ patient.cpf }}</td>
          <td>{{ patient.email }}</td>
          <td>{{ patient.birthday }}</td>
          <td>{{ patient.gender }}</td>
          <td class="p-3 flex justify-center items-center">
            <font-awesome-icon
              @click="
                {
                  $router.push('/edit/' + patient.id);
                }
              "
              class="mr-2 cursor-pointer"
              icon="fa-solid fa-user-pen"
              size="lg"
            />
            <font-awesome-icon
              @click="deletePatient(patient.id)"
              class="cursor-pointer"
              icon="fa-sharp fa-solid fa-trash"
              size="lg"
            />
          </td>
        </tr>
      </tbody>
    </table>

    <n-pagination
      @click="getAllPatient()"
      class="mt-5"
      v-model:page="pagination.pageNumber"
      :page-count="pagination.totalPages"
    />
  </div>
</template>

<script lang="ts">
  import Patient from '@/model/Patient';
  import toast from '@/utils/Toast';
  import { ToastType } from '@/utils/ToastType';
  import axios from 'axios';
  import { defineComponent } from 'vue';

  export default defineComponent({
    name: 'PatientView',
    components: {},
    data() {
      return {
        patients: [] as Patient[],
        toast,
        pagination: {
          size: 5,
          pageNumber: 0,
          totalPages: 100,
        },
      };
    },
    methods: {
      async getAllPatient(): Promise<void> {
        return await axios
          .get(
            `http://localhost:8080/api/patient?page=${
              this.pagination.pageNumber - 1
            }&size=${this.pagination.size}`
          )
          .then((response) => {
            this.patients = response.data.content;
            this.pagination.totalPages = response.data.totalPages;
            console.log(response.data);
          })
          .catch((error) => {
            console.warn(error);
          });
      },

      async deletePatient(id: number) {
        return await axios
          .delete('http://localhost:8080/api/patient/' + id)
          .then(() => {
            this.getAllPatient();
          })
          .catch((error) => {
            console.log(error);
            this.toast(ToastType.DANGER, error.response.data.message);
          });
      },
    },
    beforeMount() {
      this.getAllPatient();
    },
  });
</script>
