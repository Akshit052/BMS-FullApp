// LoginService.test.js
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import axios from "axios";
import { BrowserRouter as Router } from "react-router-dom";
import LoginService from "../LoginService";

jest.mock("axios");

describe('LoginService', () => {
    const mockCustomerData = {
      accountNumber: '123456789',
      // ...other customer data
    };
  
    beforeEach(() => {
      // Reset localStorage and mocked axios before each test
      localStorage.clear();
      axios.get.mockReset();
    });
  
    test('login should set accountNumber in localStorage on successful login', async () => {
      // Mock successful response from the API
      axios.get.mockResolvedValue({ data: mockCustomerData });
  
      // Call the login function
      await LoginService.login('testCustomerId', 'testPassword');
  
      // Check if localStorage is updated with the correct accountNumber
      expect(localStorage.getItem('accountNumber')).toBe(mockCustomerData.accountNumber);
    });
  
    test('login should throw an error on unsuccessful login', async () => {
      // Mock an error response from the API
      const errorMessage = 'Invalid credentials';
      axios.get.mockRejectedValue({ response: { data: errorMessage } });
  
      // Use async/await to handle the promise returned by login
      await expect(LoginService.login('invalidCustomerId', 'invalidPassword')).rejects.toEqual(errorMessage);
  
      // Ensure localStorage is not updated in case of an error
      expect(localStorage.getItem('accountNumber')).toBeNull();
    });
  });