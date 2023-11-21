// services/__tests__/ViewBalanceService.test.js
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import axios from 'axios';
import { BrowserRouter as Router } from "react-router-dom";
import ViewBalanceService from '../ViewBalanceService';
import LoginService from '../LoginService';

jest.mock('axios');

describe('ViewBalanceService', () => {
    // Mock the LoginService.getAccountNumber method
    jest.spyOn(LoginService, 'getAccountNumber').mockReturnValue('mockedAccountNumber');
  
    afterEach(() => {
      jest.clearAllMocks();
    });
  
    it('should fetch customer details successfully', async () => {
        // Mock the LoginService.getAccountNumber method
        jest.spyOn(LoginService, 'getAccountNumber').mockReturnValue('mockedAccountNumber');
    
        // Mock the axios.get method to simulate a successful response
        axios.get.mockResolvedValue({ data: { balance: 1000, name: 'John Doe' } });
    
        const result = await ViewBalanceService.viewCustomerDetails();
    
        expect(result).toEqual({ balance: 1000, name: 'John Doe' });
    
        // Ensure that axios.get was called with the correct parameters
        expect(axios.get).toHaveBeenCalledWith(
          'http://localhost:8080/customer/login/details',
          {
            params: {
              accountNumber: 'mockedAccountNumber',
            },
          }
        );
      });
  
    it('should handle fetch failure', async () => {
      // Mock the axios.get method to simulate an error response
      axios.get.mockRejectedValue({ response: { data: 'Fetch failed' } });
  
      // Ensure that the function throws the expected error
      await expect(ViewBalanceService.viewCustomerDetails()).rejects.toEqual('Fetch failed');
    });
  });

