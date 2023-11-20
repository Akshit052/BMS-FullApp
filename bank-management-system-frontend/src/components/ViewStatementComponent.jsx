// components/ViewStatementComponent.jsx

import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import ViewStatementService from '../services/ViewStatementService';

const ViewStatementComponent = () => {
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await ViewStatementService.viewStatement();
        console.log(response);
        setTransactions(response || []); // Initialize with an empty array if response.data is undefined
      } catch (error) {
        console.error('Error fetching transaction statement:', error);
        // Handle specific error messages and display them to the user as needed
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      <h2>View Transaction Statement</h2>
      <table>
        <thead>
          <tr>
            <th>Transaction ID</th>
            <th>Account Number</th>
            <th>Transaction Type</th>
            <th>Amount</th>
            <th>Beneficiary Account</th>
            <th>Narration</th>
            <th>Debit/Credit Type</th>
            <th>Transaction Date</th>
            {/* Add more headers as needed */}
          </tr>
        </thead>
        <tbody>
          {transactions.map(transaction => (
            <tr key={transaction.transactionId}>
              <td>{transaction.transactionId}</td>
              <td>{transaction.accountNumber}</td>
              <td>{transaction.transactionType}</td>
              <td>{transaction.amount}</td>
              <td>{transaction.beneficiaryAccount}</td>
              <td>{transaction.narration}</td>
              <td>{transaction.dbcrType}</td>
              <td>{transaction.transactionDate}</td>
              {/* Add more columns as needed */}
            </tr>
          ))}
        </tbody>
      </table>
      <br />
      <br />
      <Link to="/dashboard" className="btn btn-primary mb-2">
          Back to dashboard
        </Link>
    </div>
  );
};

export default ViewStatementComponent;
