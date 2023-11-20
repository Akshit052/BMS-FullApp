// services/TransferService.js

import axios from 'axios';
import LoginService from './LoginService';

const BASE_URL = 'http://localhost:8080';

const transferFunds = async (beneficiaryAccount, amount, description) => {
  try {
    const accountNumber = LoginService.getAccountNumber();
    if (!accountNumber) {
      throw new Error('Account number not available. Please log in first.');
    }

    const response = await axios.post(
      `${BASE_URL}/customer/login/transfer`,
      null,  // Set data to null for params
      {
        params: {
          accountNumber,
          beneficiaryAccount,
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
  transferFunds,
};
