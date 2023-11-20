// components/DashboardComponent.jsx

import React from 'react';
import { Link } from 'react-router-dom';

const DashboardComponent = () => {
  return (
    <div>
      <h2>Welcome to the Dashboard</h2>
      <ul>
        <li><Link to="/deposit">Deposit</Link></li>
        <li><Link to="/withdraw">Withdraw</Link></li>
        <li><Link to="/transfer">Transfer</Link></li>
        <li><Link to="/view-balance">View Balance</Link></li>
        <li><Link to="/view-statement">View Statement</Link></li>
      </ul>
    </div>
  );
};

export default DashboardComponent;
