// RegistrationService.test.js
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import axios from "axios";
import { BrowserRouter as Router } from "react-router-dom";
import RegistrationService from "../RegistrationService";

jest.mock("axios");

describe('RegistrationService', () => {
    afterEach(() => {
      jest.clearAllMocks();
    });
  
    it('should register a customer successfully', async () => {
      // Mock the axios.post method to simulate a successful response
      axios.post.mockResolvedValue({ data: 'Registration successful' });
  
      const customerDetails = {
        // Provide necessary customer details for the test
        // ...
      };
  
      const result = await RegistrationService.registerCustomer(customerDetails);
  
      expect(result.data).toEqual('Registration successful');
      // Ensure that axios.post was called with the correct parameters
      expect(axios.post).toHaveBeenCalledWith(
        'http://localhost:8080/customer/register',
        customerDetails
      );
    });
  
    it('should handle registration failure', async () => {
      // Mock the axios.post method to simulate an error response
      axios.post.mockRejectedValue({ response: { data: 'Registration failed' } });
  
      const customerDetails = {
        // Provide necessary customer details for the test
        // ...
      };
  
      // Ensure that the function throws the expected error
      await expect(RegistrationService.registerCustomer(customerDetails)).rejects.toEqual(
        'Registration failed'
      );
    });
  });