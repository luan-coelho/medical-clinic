<template>
  <div
    class="flex items-center flex-col justify-center pt-10 h-full bg-gray-100"
  >
    <div class="flex justify-end items-center w-full mr-96"></div>

    <DataTable
      class="shadow-2xl"
      :value="patients"
      responsiveLayout="stack"
      breakpoint="960px"
      stripedRows
    >
      <template #header>
        <div class="flex justify-around">
          <div>
            <Button
              @click="resetFilter()"
              type="button"
              icon="pi pi-filter-slash"
              class="p-button-outlined mr-2"
            />
            <Dropdown
              v-model="selectedFilter"
              :options="patientFieldFilter"
              optionLabel="name"
              placeholder="Select a filter"
              class="mr-1"
            />
            <span class="p-input-icon-left">
              <InputText v-model="inputValue" placeholder="Keyword Search" />
            </span>
            <Button
              @click="getAllPatient()"
              type="button"
              icon="pi pi-search"
              label="Search"
              class="p-button-outlined"
            />
          </div>
          <div>
            <Button
              @click="$router.push('/save')"
              label="New Patient"
              class="p-button-success"
            />
          </div>
        </div>
      </template>
      <template #empty> No customers found. </template>
      <template #loading> Loading customers data. Please wait. </template>
      <Column field="id" header="id"></Column>
      <Column field="name" header="Name"></Column>
      <Column field="cpf" header="CPF"></Column>
      <Column field="email" header="Email"></Column>
      <Column field="gender" header="Gender"></Column>
      <Column field="birthday" header="Birthday"></Column>
      <Column>
        <template #body="{ data }">
          <Button
            @click="$router.push('/edit/' + data.id)"
            icon="pi pi-pencil"
            class="p-button-info mr-2"
          /><Button
            @click="deleteDialog(data.id)"
            icon="pi pi-trash"
            class="p-button-danger"
          />
        </template>
      </Column>
    </DataTable>

    <ConfirmDialog></ConfirmDialog>

    <n-pagination
      v-show="patients.length > 0"
      @click="getAllPatient()"
      class="mt-6 mb-10"
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
  import Column from 'primevue/column';
  import ConfirmDialog from 'primevue/confirmdialog';
  import DataTable from 'primevue/datatable';
  import Dropdown from 'primevue/dropdown';
  import { defineComponent } from 'vue';

  interface patientFieldFilter {
    name: string;
    value: string;
  }

  export default defineComponent({
    name: 'PatientView',
    components: {
      DataTable,
      Column,
      Dropdown,
      ConfirmDialog,
    },
    data() {
      return {
        patients: [] as Patient[],
        inputValue: '',
        toast,
        pagination: {
          size: 5,
          pageNumber: 0,
          totalPages: 100,
        },
        selectedFilter: {} as patientFieldFilter,
        patientFieldFilter: [
          { name: 'Name', value: 'name' },
          { name: 'Email', value: 'email' },
          { name: 'CPF', value: 'cpf' },
        ] as patientFieldFilter[],
      };
    },
    methods: {
      async getAllPatient(): Promise<void> {
        return await axios
          .get(
            `http://localhost:8080/api/patient?page=${
              this.pagination.pageNumber - 1
            }&size=${this.pagination.size}&${this.selectedFilter.value}=${
              this.inputValue
            }`
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
            this.toast(ToastType.SUCESS, 'Patient successfully deleted');
          })
          .catch((error) => {
            console.log(error);
            this.toast(ToastType.DANGER, error.response.data.message);
          });
      },
      resetFilter() {
        this.selectedFilter = {} as patientFieldFilter;
        this.getAllPatient();
      },
      deleteDialog(index: number) {
        this.$confirm.require({
          message: 'Are you sure you want to proceed?',
          header: 'Confirmation',
          icon: 'pi pi-exclamation-triangle',
          acceptClass: 'p-button-info',
          accept: () => {
            this.deletePatient(index);
          },
        });
      },
    },
    beforeMount() {
      this.getAllPatient();
    },
  });
</script>
