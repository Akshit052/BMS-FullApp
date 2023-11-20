// services/ViewBalanceService.js

import axios from 'axios';
import LoginService from './LoginService';
const BASE_URL = 'http://localhost:8080';

const viewCustomerDetails = async () => {
  try {
    const accountNumber = LoginService.getAccountNumber();
    const response = await axios.get(`${BASE_URL}/customer/login/details`, {
      params: { accountNumber }
    });
    return response.data; // Assuming the response contains customer view data
  } catch (error) {
    throw error.response.data; // Throw the error response for handling in the component
  }
};

export default {
  viewCustomerDetails,
};
