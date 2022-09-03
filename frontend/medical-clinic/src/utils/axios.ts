import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://ec2-52-87-248-189.compute-1.amazonaws.com:8080/api',
});

export default instance;
