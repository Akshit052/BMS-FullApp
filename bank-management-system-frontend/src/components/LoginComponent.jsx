// // components/LoginComponent.jsx

// import React, { useState } from 'react';
// import { withRouter } from 'react-router-dom';
// import LoginService from '../services/LoginService';

// const LoginComponent = ({ history }) => {
//   const [loginDetails, setLoginDetails] = useState({
//     customerId: '',
//     password: '',
//   });

//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     try {
//       const response = await LoginService.login(loginDetails.customerId, loginDetails.password);
//       console.log(response); // Success message or handle accordingly

//       // Redirect to the dashboard on successful login
//       history.push('/dashboard');
//     } catch (error) {
//       console.error('Error during login:', error);
//       // Handle specific error messages and display them to the user as needed
//     }
//   };

//   const handleInputChange = (e) => {
//     const { name, value } = e.target;
//     setLoginDetails({ ...loginDetails, [name]: value });
//   };

//   return (
//     <div>
//       <h2>Customer Login</h2>
//       <form onSubmit={handleSubmit}>
//         <label>
//           Customer ID:
//           <input type="text" name="customerId" value={loginDetails.customerId} onChange={handleInputChange} />
//         </label>
//         <br />
//         <label>
//           Password:
//           <input type="password" name="password" value={loginDetails.password} onChange={handleInputChange} />
//         </label>
//         <br />
//         <button type="submit">Login</button>
//       </form>
//     </div>
//   );
// };

// export default withRouter(LoginComponent);

import React, { useState } from 'react';
import { withRouter } from 'react-router-dom';
import LoginService from '../services/LoginService';
import './LoginComponent.css';
import { Link } from 'react-router-dom';
 
const LoginComponent = ({ history }) => {
  const [loginDetails, setLoginDetails] = useState({
    customerId: '',
    password: '',
  });
 
  const handleSubmit = async (e) => {
    e.preventDefault();
 
    try {
      const response = await LoginService.login(loginDetails.customerId, loginDetails.password);
      console.log(response); // Success message or handle accordingly
 
      // Redirect to the dashboard on successful login
      history.push('/dashboard');
      // <Link to="/dashboard"></Link>
 
      // Clear input fields after successful submission
      setLoginDetails({
        customerId: '',
        password: '',
      });
    } catch (error) {
      console.error('Error during login:', error);
      // Handle specific error messages and display them to the user as needed
    }
  };
 
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setLoginDetails({ ...loginDetails, [name]: value });
  };
 
  return (
    <div>
      <h2>Customer Login</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Customer ID:
          <input type="text" name="customerId" value={loginDetails.customerId} onChange={handleInputChange} />
        </label>
        <br />
        <label>
          Password:
          <input type="password" name="password" value={loginDetails.password} onChange={handleInputChange} />
        </label>
        <br />
        <button type="submit">Login</button>
        <br />
        <Link to="/register" className="btn btn-primary mb-2">
          New Registration
        </Link>
 
      </form>
 
     
    </div>
  );
};
 
export default LoginComponent;
