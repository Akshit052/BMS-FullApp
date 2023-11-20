// services/LoginService.js

import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

const ACCOUNT_NUMBER_KEY = 'accountNumber';

let accountNumber = localStorage.getItem(ACCOUNT_NUMBER_KEY) || null;

const login = async (customerId, password) => {
  try {
    const response = await axios.get(`${BASE_URL}/customer/login/`, {
      params: { customerId, password },
    });
    accountNumber = response.data.accountNumber;

    localStorage.setItem(ACCOUNT_NUMBER_KEY, accountNumber);
    return response.data; // Assuming the response contains customer data
  } catch (error) {
    throw error.response.data; // Throw the error response for handling in the component
  }

};

const getAccountNumber = () => accountNumber;

export default {
    login,
    getAccountNumber
  };
