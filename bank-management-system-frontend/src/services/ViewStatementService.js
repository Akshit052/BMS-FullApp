// services/ViewStatementService.js

import axios from 'axios';
import LoginService from './LoginService';

const BASE_URL = 'http://localhost:8080';

const viewStatement = async () => {
  try {
    const accountNumber = LoginService.getAccountNumber();
    console.log(accountNumber);
    const response = await axios.post(
      `${BASE_URL}/customer/login/viewstatement`,
      null, // Set data to null for params
      {
        params: { accountNumber },
      }
    );
    return response.data; // Assuming the response contains a list of transactions
  } catch (error) {
    throw error.response.data; // Throw the error response for handling in the component
  }
};

export default {
  viewStatement,
};
