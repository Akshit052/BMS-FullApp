// services/WithdrawService.js

import axios from 'axios';
import LoginService from './LoginService';
const BASE_URL = 'http://localhost:8080';

const withdrawFunds = async (amount, description) => {
  try {
    const accountNumber = LoginService.getAccountNumber();
    console.log('Request Payload:', { accountNumber, amount, description });
    const response = await axios.post(
      `${BASE_URL}/customer/login/withdraw`,
      null, // Set the data parameter to null when making a POST request with parameters in the URL
      {
        params: {
          accountNumber,
          amount,
          description,
        },
      }
    );
    return response.data; // Assuming the response contains a success message or relevant data
  } catch (error) {
    throw error.response.data; // Throw the error response for handling in the component
  }
};

export default {
  withdrawFunds,
};
