// components/TransferComponent.js

import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import TransferService from '../services/TransferService';

const TransferComponent = () => {
  const [beneficiaryAccount, setBeneficiaryAccount] = useState('');
  const [amount, setAmount] = useState('');
  const [description, setDescription] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [transferSuccess, setTransferSuccess] = useState(false);

  const handleTransfer = async () => {
    try {
      const parsedAmount = parseFloat(amount);

      if (isNaN(parsedAmount) || parsedAmount <= 0) {
        throw new Error('Amount must be a valid positive number.');
      }

      await TransferService.transferFunds(beneficiaryAccount, parsedAmount, description);
      // Reset the form after a successful transfer
      setBeneficiaryAccount('');
      setAmount('');
      setDescription('');
      setErrorMessage('');
      setTransferSuccess(true); // Set transfer success state
    } catch (error) {
      setErrorMessage(error.message || 'An error occurred during the transfer.');
      setTransferSuccess(false); // Reset transfer success state
    }
  };

  return (
    <div>
      <h2>Transfer Funds</h2>
      <div>
        <label>Beneficiary Account:</label>
        <input
          type="text"
          value={beneficiaryAccount}
          onChange={(e) => setBeneficiaryAccount(e.target.value)}
        />
      </div>
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
      <button onClick={handleTransfer}>Transfer Funds</button>
      
      {transferSuccess && (
        <div>
          <div style={{ color: 'green' }}>Transfer successful!</div>
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

export default TransferComponent;
