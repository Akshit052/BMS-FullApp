// services/__tests__/DepositService.test.js
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import axios from 'axios';
import { BrowserRouter as Router } from "react-router-dom";
import DepositService from '../DepositService';
import LoginService from '../LoginService';

jest.mock('axios');

describe('DepositService', () => {
    // Mock the LoginService.getAccountNumber method
    jest.spyOn(LoginService, 'getAccountNumber').mockReturnValue('mockedAccountNumber');
  
    afterEach(() => {
      jest.clearAllMocks();
    });
  
  
    it('should handle deposit failure', async () => {
      // Mock the axios.post method to simulate an error response
      axios.post.mockRejectedValue({ response: { data: 'Deposit failed' } });
  
      // Ensure that the function throws the expected error
      await expect(DepositService.depositFunds(50, 'Failed deposit')).rejects.toEqual('Deposit failed');
    });
  });
