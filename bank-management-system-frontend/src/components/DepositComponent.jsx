// components/DepositComponent.js

import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import DepositService from '../services/DepositService';

const DepositComponent = () => {
  const [amount, setAmount] = useState('');
  const [description, setDescription] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [depositSuccess, setDepositSuccess] = useState(false);

  const handleDeposit = async () => {
    try {
      const parsedAmount = parseFloat(amount);

      if (isNaN(parsedAmount) || parsedAmount <= 0) {
        throw new Error('Amount must be a valid positive number.');
      }

      await DepositService.depositFunds(parsedAmount, description);
      // Reset the form after a successful deposit
      setAmount('');
      setDescription('');
      setErrorMessage('');
      setDepositSuccess(true); // Set deposit success state
    } catch (error) {
      setErrorMessage(error.message || 'An error occurred during the deposit.');
      setDepositSuccess(false); // Reset deposit success state
    }
  };

  return (
    <div>
      <h2>Deposit</h2>
      <div>
        <label>Amount:</label>
        <input
          type="text"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />
      </div>
      <div>
        <label>Description:</label>
        <input
          type="text"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
      </div>
      <button onClick={handleDeposit}>Deposit</button>
      <br/>
    
      {depositSuccess && (
        <div>
          <div style={{ color: 'green' }}>Deposit successful!</div>
          <div>
            {/* Optionally, you can provide a link or redirect the user */}
            <Link to="/dashboard">Back to Dashboard</Link>
          </div>
        </div>
      )}
      
      {errorMessage && <div style={{ color: 'red' }}>{errorMessage}</div>}
      <br />
      <Link to="/dashboard" className="btn btn-primary mb-2">
          Back to dashboard
      </Link>
    </div>
  );
};

export default DepositComponent;
