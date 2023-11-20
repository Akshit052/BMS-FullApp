// services/RegistrationService.js

import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

const registerCustomer = async (customerDetails) => {
  try {
    const response = await axios.post(`${BASE_URL}/customer/register`, customerDetails);
    return response; // Assuming the response contains a success message or relevant data
  } catch (error) {
    throw error.response.data; // Throw the error response for handling in the component
  }
};

export default {
  registerCustomer,
};
