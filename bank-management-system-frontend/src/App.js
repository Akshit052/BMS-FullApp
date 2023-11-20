// App.js

import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import "bootstrap/dist/css/bootstrap.min.css";
import LoginComponent from './components/LoginComponent';
import DashboardComponent from './components/DashboardComponent';
import DepositComponent from './components/DepositComponent';
import WithdrawComponent from './components/WithdrawComponent';
import TransferComponent from './components/TransferComponent';
import ViewBalanceComponent from './components/ViewBalanceComponent';
import ViewStatementComponent from './components/ViewStatementComponent';
import { BrowserRouter } from 'react-router-dom/cjs/react-router-dom.min';
import CustomerRegistrationComponent from './components/CustomerRegistrationComponent';

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/login" component={LoginComponent} />
        <Route path="/dashboard" component={DashboardComponent} />
        <Route path="/deposit" component={DepositComponent} />
        <Route path="/withdraw" component={WithdrawComponent} />
        <Route path="/transfer" component={TransferComponent} />
        <Route path="/view-balance" component={ViewBalanceComponent} />
        <Route path="/view-statement" component={ViewStatementComponent} />
        <Route path="/register" component={CustomerRegistrationComponent} />
      </Switch>
    </BrowserRouter>
  );
}

export default App;
