import { createToast } from 'mosha-vue-toastify';
import 'mosha-vue-toastify/dist/style.css';
import { ToastType } from './ToastType';

const toast = (
  messageType: ToastType | ToastType.SUCESS,
  ...messages: string[]
) => {
  messages.forEach((message) => {
    if (message) {
      createToast(message, {
        type: messageType,
        showIcon: true,
      });
    }
  });
};

export default toast;
