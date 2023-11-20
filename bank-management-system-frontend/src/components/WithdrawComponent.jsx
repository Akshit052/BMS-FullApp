// components/WithdrawComponent.js

import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import WithdrawService from '../services/WithdrawService';

const WithdrawComponent = () => {
  const [amount, setAmount] = useState('');
  const [description, setDescription] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [withdrawSuccess, setWithdrawSuccess] = useState(false);

  const handleWithdraw = async () => {
    try {
      const parsedAmount = parseFloat(amount);

      if (isNaN(parsedAmount) || parsedAmount <= 0) {
        throw new Error('Amount must be a valid positive number.');
      }

      await WithdrawService.withdrawFunds(parsedAmount, description);
      // Reset the form after a successful withdrawal
      setAmount('');
      setDescription('');
      setErrorMessage('');
      setWithdrawSuccess(true); // Set withdraw success state
    } catch (error) {
      setErrorMessage(error.message || 'An error occurred during the withdrawal.');
      setWithdrawSuccess(false); // Reset withdraw success state
    }
  };

  return (
    <div>
      <h2>Withdraw</h2>
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
      <button onClick={handleWithdraw}>Withdraw</button>
      
      {withdrawSuccess && (
        <div>
          <div style={{ color: 'green' }}>Withdrawal successful!</div>
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

export default WithdrawComponent;

