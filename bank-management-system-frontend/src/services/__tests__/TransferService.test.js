// Transferservice.test.js
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import axios from "axios";
import { BrowserRouter as Router } from "react-router-dom";
import TransferService from "../TransferService";
import LoginService from '../LoginService';

jest.mock("axios");

describe('TransferService', () => {
    afterEach(() => {
      jest.clearAllMocks();
    });
  
    it('should transfer funds successfully', async () => {
      // Mock the LoginService.getAccountNumber method
      jest.spyOn(LoginService, 'getAccountNumber').mockReturnValue('mockedAccountNumber');
  
      // Mock the axios.post method to simulate a successful response
      axios.post.mockResolvedValue({ data: { message: 'Transfer successful' } });
  
      const result = await TransferService.transferFunds('beneficiaryAccount123', 50, 'Family support');
  
      expect(result).toEqual({ message: 'Transfer successful' });
  
      // Ensure that axios.post was called with the correct parameters
      expect(axios.post).toHaveBeenCalledWith(
        'http://localhost:8080/customer/login/transfer',
        null,
        {
          params: {
            accountNumber: 'mockedAccountNumber',
            beneficiaryAccount: 'beneficiaryAccount123',
            amount: 50,
            description: 'Family support',
          },
        }
      );
    });
  
    it('should handle missing account number', async () => {
        // Mock the LoginService.getAccountNumber method to return null (simulate missing account number)
        jest.spyOn(LoginService, 'getAccountNumber').mockReturnValue(null);
      
        // Ensure that the function throws the expected error
        await expect(TransferService.transferFunds('beneficiaryAccount456', 30, 'Friend loan'))
          .rejects.toThrow('Account number not available. Please log in first.')
          .catch((error) => {
            // Ensure that the error does not contain 'data' property
            expect(error.response).toBeUndefined();
          });
      
        // Ensure that axios.post was not called
        expect(axios.post).not.toHaveBeenCalled();
      });
  
    it('should handle transfer failure', async () => {
      // Mock the LoginService.getAccountNumber method
      jest.spyOn(LoginService, 'getAccountNumber').mockReturnValue('mockedAccountNumber');
  
      // Mock the axios.post method to simulate an error response
      axios.post.mockRejectedValue({ response: { data: 'Transfer failed' } });
  
      // Ensure that the function throws the expected error
      await expect(TransferService.transferFunds('beneficiaryAccount789', 20, 'Charity donation')).rejects.toEqual(
        'Transfer failed'
      );
    });
  });

  

