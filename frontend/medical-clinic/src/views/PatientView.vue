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
              class="p-button-success p-button-rounded"
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
      <Column>
        <template #body="{ data }">
          <Button
            @click="
              display = true;
              patient = data;
            "
            icon="pi pi-arrow-up-right"
            class="p-button-help mr-2 p-button-rounded"
          />
          <Button
            @click="$router.push('/edit/' + data.id)"
            icon="pi pi-pencil"
            class="p-button-info mr-2 p-button-rounded"
          />
          <Button
            @click="deleteDialog(data.id)"
            icon="pi pi-trash"
            class="p-button-danger p-button-rounded"
          />
        </template>
      </Column>
    </DataTable>

    <Dialog header="Patient Data" v-model:visible="display" :modal="true">
      <ShowPaTientData :patient="patient" />
    </Dialog>

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
  import ShowPaTientData from '@/components/ShowPatientData.vue';
  import Patient from '@/model/Patient';
  import axios from '@/utils/axios';
  import toast from '@/utils/Toast';
  import { ToastType } from '@/utils/ToastType';
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
      ShowPaTientData,
    },
    data() {
      return {
        patients: [] as Patient[],
        patient: {} as Patient,
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
        display: false,
      };
    },
    methods: {
      async getAllPatient(): Promise<void> {
        return await axios
          .get(
            `/patient?page=${this.pagination.pageNumber - 1}&size=${
              this.pagination.size
            }&${this.selectedFilter.value}=${this.inputValue}`
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
          .delete('/patient/' + id)
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
