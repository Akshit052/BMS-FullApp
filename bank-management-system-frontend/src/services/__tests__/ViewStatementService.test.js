// services/__tests__/ViewStatementService.test.js
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import axios from 'axios';
import { BrowserRouter as Router } from "react-router-dom";
import ViewStatementService from '../ViewStatementService';
import LoginService from '../LoginService';

jest.mock('axios');

describe('ViewStatementService', () => {
    afterEach(() => {
      jest.clearAllMocks();
    });
  
    it('should fetch statement successfully', async () => {
      // Mock the LoginService.getAccountNumber method
      jest.spyOn(LoginService, 'getAccountNumber').mockReturnValue('mockedAccountNumber');
  
      // Mock the axios.post method to simulate a successful response
      axios.post.mockResolvedValue({ data: { transactions: [{ id: 1, amount: 100, description: 'Purchase' }] } });
  
      const result = await ViewStatementService.viewStatement();
  
      expect(result).toEqual({ transactions: [{ id: 1, amount: 100, description: 'Purchase' }] });
  
      // Ensure that axios.post was called with the correct parameters
      expect(axios.post).toHaveBeenCalledWith(
        'http://localhost:8080/customer/login/viewstatement',
        null,
        {
          params: {
            accountNumber: 'mockedAccountNumber',
          },
        }
      );
    });
  
    it('should handle fetch failure', async () => {
      // Mock the LoginService.getAccountNumber method
      jest.spyOn(LoginService, 'getAccountNumber').mockReturnValue('mockedAccountNumber');
  
      // Mock the axios.post method to simulate an error response
      axios.post.mockRejectedValue({ response: { data: 'Fetch failed' } });
  
      // Ensure that the function throws the expected error
      await expect(ViewStatementService.viewStatement()).rejects.toEqual('Fetch failed');
    });
  });