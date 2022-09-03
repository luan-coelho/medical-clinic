<template>
  <div class="flex items-center justify-center md:mt-0">
    <form
      @submit.prevent="savePatient()"
      class="mt-5 mb-20 md:w-2/4 sm:9/12 sx:w-11/12"
    >
      <div class="shadow-2xl sm:rounded-md">
        <n-tabs type="line" animated>
          <n-tab-pane name="Basic Data" tab="Basic Data">
            <div class="bg-white px-4 py-5 sm:p-6">
              <div class="grid col-span-6 grid-cols-6 gap-6">
                <div class="col-span-6 sm:col-span-6">
                  <label
                    for="name"
                    class="block text-sm font-medium text-gray-700"
                    >Full Name</label
                  >
                  <input
                    v-model="patient.name"
                    type="text"
                    name="name"
                    autocomplete="given-name"
                    class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                  />
                </div>

                <div class="col-span-6 sm:col-span-2">
                  <label
                    for="cpf"
                    class="block text-sm font-medium text-gray-700"
                    >CPF</label
                  >
                  <input
                    @keyup="validateCpf()"
                    v-model="patient.cpf"
                    type="text"
                    name="cpf"
                    placeholder="000.000.000-00"
                    id="cpf"
                    maxlength="14"
                    autocomplete="family-name"
                    class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                  />
                </div>

                <div class="col-span-6 sm:col-span-4">
                  <label
                    for="email-address"
                    class="block text-sm font-medium text-gray-700"
                    >Email</label
                  >
                  <input
                    @blur="validateEmail()"
                    v-model="patient.email"
                    type="text"
                    name="email"
                    id="email"
                    placeholder="cliniconect@exemplo.com"
                    class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                  />
                </div>

                <div class="col-span-3 sm:col-span-2">
                  <label
                    for="phone"
                    class="block text-sm font-medium text-gray-700"
                    >Cell Phone</label
                  >
                  <input
                    v-model="patient.cellPhone"
                    type="text"
                    name="phone"
                    id="phone"
                    maxlength="11"
                    class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                  />
                </div>

                <div class="col-span-3 sm:col-span-2">
                  <label
                    for="country"
                    class="block text-sm font-medium text-gray-700"
                    >Gender</label
                  >

                  <div class="flex mt-2">
                    <div class="flex items-center mr-4">
                      <input
                        id="inline-radio"
                        type="radio"
                        value="MALE"
                        v-model="patient.gender"
                        name="inline-radio-group"
                        class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:border-gray-600"
                      />
                      <label for="inline-radio" class="ml-2 text-sm font-medium"
                        >MALE</label
                      >
                    </div>
                    <div class="flex items-center mr-4">
                      <input
                        id="inline-radio"
                        type="radio"
                        value="FEMALE"
                        v-model="patient.gender"
                        name="inline-radio-group"
                        class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:border-gray-600"
                      />
                      <label for="inline-radio" class="ml-2 text-sm font-medium"
                        >FEMALE</label
                      >
                    </div>
                  </div>
                </div>

                <div class="col-span-2">
                  <label
                    for="country"
                    class="block text-sm font-medium text-gray-700 focus:ring-blue-500 dark:focus:ring-blue-600"
                    >Birthday</label
                  >

                  <input
                    v-model="patient.birthday"
                    type="date"
                    placeholder="dd/MM/yyyy"
                  />
                </div>
              </div>
            </div>
            <div class="bg-gray-50 px-4 py-3 text-right sm:px-6">
              <button
                type="submit"
                class="inline-flex justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
              >
                Save
              </button>
            </div>
          </n-tab-pane>
          <n-tab-pane name="Address" tab="Address">
            <span v-if="patient.address.length == 0" class="ml-10"
              >No address linked to this patient</span
            >

            <div>
              <n-card
                class="m-1"
                v-for="(addressCard, index) in patient.address"
                :key="index"
              >
                <div class="flex flex-col">
                  <h3 class="text-lg font-bold">
                    {{ addressCard.street }}
                  </h3>
                  <span> Number: {{ addressCard.number }} </span>
                  <div>
                    <span class="mr-3"> {{ addressCard.zipCode }} </span
                    ><span class="italic"
                      >{{ addressCard.city + '-' + addressCard.state }}
                    </span>
                  </div>
                </div>

                <div class="mt-2">
                  <n-button
                    class="mr-2"
                    strong
                    secondary
                    type="info"
                    @click="
                      showModal = true;
                      cardAddressIndex = index;
                      editEnabled = true;
                      address = addressCard;
                    "
                  >
                    Edit
                  </n-button>
                  <n-button
                    @click="deleteAddress(index)"
                    strong
                    secondary
                    type="error"
                  >
                    Delete
                  </n-button>
                </div>
              </n-card>

              <n-button
                strong
                secondary
                type="success"
                class="m-4 focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
                @click="
                  showModal = true;
                  editEnabled = false;
                  cleanFormAddress();
                "
                >Add address</n-button
              >
              <n-modal
                id="modal"
                v-model:show="showModal"
                :mask-closable="false"
                preset="dialog"
                title="Address"
              >
                <div class="grid grid-cols-6 gap-6 mt-4">
                  <div class="col-span-6 sm:col-span-2 lg:col-span-2">
                    <label
                      for="postal-code"
                      class="block text-sm font-medium text-gray-700"
                      >ZIP code *</label
                    >
                    <input
                      @blur="findAddressByCep()"
                      type="text"
                      v-model="address.zipCode"
                      maxlength="8"
                      name="postal-code"
                      id="postal-code"
                      autocomplete="postal-code"
                      class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>

                  <div class="col-span-6">
                    <label
                      for="street-address"
                      class="block text-sm font-medium text-gray-700"
                      >Street *</label
                    >
                    <input
                      type="text"
                      v-model="address.street"
                      name="street-address"
                      id="street-address"
                      autocomplete="street-address"
                      class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>

                  <div class="col-span-4">
                    <label
                      for="street-district"
                      class="block text-sm font-medium text-gray-700"
                      >District *</label
                    >
                    <input
                      type="text"
                      v-model="address.district"
                      name="street-district"
                      id="street-district"
                      class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>

                  <div class="col-span-2 sm:col-span-3 lg:col-span-2">
                    <label
                      for="number"
                      class="block text-sm font-medium text-gray-700"
                      >Number *</label
                    >
                    <input
                      type="text"
                      v-model="address.number"
                      name="number"
                      id="number"
                      autocomplete="address-level1"
                      class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>

                  <div class="col-span-4 lg:col-span-4">
                    <label
                      for="complement"
                      class="block text-sm font-medium text-gray-700"
                      >Complement</label
                    >
                    <input
                      type="text"
                      v-model="address.complement"
                      name="complement"
                      id="complement"
                      autocomplete="address-level1"
                      class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>
                  <div class="col-span-3 sm:col-span-3 lg:col-span-3">
                    <label
                      for="city"
                      class="block text-sm font-medium text-gray-700"
                      >City *</label
                    >
                    <input
                      type="text"
                      v-model="address.city"
                      name="city"
                      id="city"
                      autocomplete="address-level2"
                      class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>

                  <div class="col-span-3 sm:col-span-3 lg:col-span-3">
                    <label
                      for="region"
                      class="block text-sm font-medium text-gray-700"
                      >State *</label
                    >
                    <input
                      type="text"
                      v-model="address.state"
                      name="region"
                      id="region"
                      autocomplete="address-level1"
                      class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>
                </div>

                <div class="mt-5 flex justify-end">
                  <n-button
                    class="text-white bg-red-700 hover:bg-red-800 focus:outline-none focus:ring-4 focus:ring-red-300 font-medium text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900"
                    strong
                    secondary
                    type="error"
                    @click="showModal = false"
                    >Cancel</n-button
                  >

                  <n-button
                    class="focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
                    strong
                    secondary
                    type="success"
                    @click="addAddress()"
                    >Save</n-button
                  >
                </div>
              </n-modal>
            </div>
          </n-tab-pane>
        </n-tabs>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
  import Address from '@/model/Address';
  import Patient from '@/model/Patient';
  import axios from '@/utils/axios';
  import CpfValidator from '@/utils/CpfValidator';
  import EmailValidator from '@/utils/EmailValidator';
  import Toast from '@/utils/Toast';
  import { ToastType } from '@/utils/ToastType';
  import { defineComponent, ref } from 'vue';

  export default defineComponent({
    data() {
      return {
        patient: {
          address: [] as Address[],
        } as Patient,
        address: {} as Address,
        cardAddressIndex: 0,
        editEnabled: false,
        showModal: ref(false),
        Toast,
        CpfValidator,
        EmailValidator,
      };
    },
    methods: {
      async savePatient() {
        return await axios
          .post('/patient', this.patient)
          .then(() => {
            this.Toast(ToastType.SUCESS, 'Patient saved successfully');
            this.cleanForm();
          })
          .catch((error) => {
            console.warn(error);
            this.Toast(ToastType.DANGER, ...error.response.data.message);
          });
      },
      addAddress() {
        if (!this.validateAddressForm()) {
          return;
        }
        if (this.editEnabled) {
          this.patient.address[this.cardAddressIndex] = this.address;
          this.Toast(ToastType.SUCESS, 'Address updated successfully');
        } else {
          this.patient.address.push(this.address);
          this.Toast(ToastType.SUCESS, 'Address added successfully');
        }

        console.log(this.address);

        this.cardAddressIndex;
        this.address = {} as Address;
        this.showModal = false;
      },
      deleteAddress(index: number) {
        this.patient.address.splice(index, 1);
      },
      cleanForm() {
        this.patient = {} as Patient;
      },
      cleanFormAddress() {
        this.address = {} as Address;
      },
      validateCpf() {
        if (!this.CpfValidator()) {
          if (this.patient.cpf.length == 14) {
            this.Toast(ToastType.DANGER, 'Please provide a valid CPF');
            (document.getElementById('cpf') as HTMLInputElement).value = '';
          }
        }
      },
      validateEmail() {
        if (!EmailValidator()) {
          this.Toast(ToastType.DANGER, 'Provide a valid email');
        }
      },
      validateAddressForm(): boolean {
        if (
          !this.address.zipCode ||
          !this.address.street ||
          !this.address.district ||
          !this.address.number ||
          !this.address.city ||
          !this.address.state
        ) {
          this.Toast(ToastType.DANGER, 'Provide all mandatory address data');
          return false;
        }
        return true;
      },
      editAddress() {
        this.showModal = true;
      },
      async findAddressByCep() {
        if (this.address.zipCode.length == 8) {
          return await axios
            .get(`https://viacep.com.br/ws/${this.address.zipCode}/json/`)
            .then((response) => {
              console.log(response);
              this.address.street = response.data.logradouro;
              this.address.district = response.data.bairro;
              this.address.city = response.data.localidade;
              this.address.state = response.data.uf;
            })
            .catch((error) => {
              console.warn(error);
            });
        }
      },
    },
  });
</script>
