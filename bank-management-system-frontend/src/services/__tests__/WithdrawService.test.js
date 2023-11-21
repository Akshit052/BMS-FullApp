// services/__tests__/WithdrawService.test.js
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import axios from 'axios';
import { BrowserRouter as Router } from "react-router-dom";
import LoginService from '../LoginService';
import WithdrawService from '../WithdrawService';

jest.mock('axios');

describe('WithdrawService', () => {
    afterEach(() => {
      jest.clearAllMocks();
    });
  
    it('should withdraw funds successfully', async () => {
      // Mock the LoginService.getAccountNumber method
      jest.spyOn(LoginService, 'getAccountNumber').mockReturnValue('mockedAccountNumber');
  
      // Mock the axios.post method to simulate a successful response
      axios.post.mockResolvedValue({ data: { message: 'Withdrawal successful' } });
  
      const result = await WithdrawService.withdrawFunds(50, 'Grocery shopping');
  
      expect(result).toEqual({ message: 'Withdrawal successful' });
  
      // Ensure that axios.post was called with the correct parameters
      expect(axios.post).toHaveBeenCalledWith(
        'http://localhost:8080/customer/login/withdraw',
        null,
        {
          params: {
            accountNumber: 'mockedAccountNumber',
            amount: 50,
            description: 'Grocery shopping',
          },
        }
      );
    });
  
    it('should handle withdrawal failure', async () => {
      // Mock the LoginService.getAccountNumber method
      jest.spyOn(LoginService, 'getAccountNumber').mockReturnValue('mockedAccountNumber');
  
      // Mock the axios.post method to simulate an error response
      axios.post.mockRejectedValue({ response: { data: 'Withdrawal failed' } });
  
      // Ensure that the function throws the expected error
      await expect(WithdrawService.withdrawFunds(30, 'Restaurant')).rejects.toEqual('Withdrawal failed');
    });
  });