// components/CustomerRegistrationComponent.jsx

import { Link } from 'react-router-dom';
import React, { useState } from 'react';
import RegistrationService from '../services/RegistrationService';

const CustomerRegistrationComponent = () => {
  const [errorMessage, setErrorMessage] = useState('');
  const [successMessage, setSuccessMessage] = useState('');
  const [customerDetails, setCustomerDetails] = useState({
    firstName: '',
    lastName: '',
    phoneNumber: '',
    aadharNumber: '',
    address: '',
    emailAddress: '',
    password: '',
  });

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await RegistrationService.registerCustomer(customerDetails);
      console.log(response); // Success message or handle accordingly

      setSuccessMessage('Registration successful!');
      setErrorMessage('');
      setCustomerDetails({
        firstName: '',
        lastName: '',
        phoneNumber: '',
        aadharNumber: '',
        address: '',
        emailAddress: '',
        password: '',
      });
    } catch (error) {
      console.error('Error during registration:', error);
      setSuccessMessage('');
      setErrorMessage(error.message || 'An error occurred during the deposit.');
      // Handle specific error messages and display them to the user as needed
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCustomerDetails({ ...customerDetails, [name]: value });
  };

  return (
    <div>
      <h2>Customer Registration</h2>
      {successMessage && <p style={{ color: 'green' }}>{successMessage}</p>}
      {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
      <form onSubmit={handleSubmit}>
        <label>
          First Name:
          <input type="text" name="firstName" value={customerDetails.firstName} onChange={handleInputChange} />
        </label>
        <br />
        <label>
          Last Name:
          <input type="text" name="lastName" value={customerDetails.lastName} onChange={handleInputChange} />
        </label>
        <br />
        <label>
          Phone Number:
          <input type="text" name="phoneNumber" value={customerDetails.phoneNumber} onChange={handleInputChange} />
        </label>
        <br />
        <label>
          Aadhar Number:
          <input type="text" name="aadharNumber" value={customerDetails.aadharNumber} onChange={handleInputChange} />
        </label>
        <br />
        <label>
          Address:
          <input type="text" name="address" value={customerDetails.address} onChange={handleInputChange} />
        </label>
        <br />
        <label>
          Email Address:
          <input type="text" name="emailAddress" value={customerDetails.emailAddress} onChange={handleInputChange} />
        </label>
        <br />
        <label>
          Password:
          <input type="password" name="password" value={customerDetails.password} onChange={handleInputChange} />
        </label>
        <br />
        <button type="submit">Register</button>
      </form>
      <div>
        <Link to="/dashboard">Back to Dashboard</Link>
      </div>
    </div>
  );
};

export default CustomerRegistrationComponent;
