import Address from './Address';
import { Gender } from './Gender';

export default interface Patient {
  id: number;
  name: string;
  email: string;
  cpf: string;
  gender: Gender;
  birthday: Date;
  address: Address[];
}
