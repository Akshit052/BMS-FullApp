//package com.teamb.bankmanagementsystem.TDD.service;
//import com.teamb.bankmanagementsystem.model.Customer;
//import com.teamb.bankmanagementsystem.model.CustomerDetails;
//import com.teamb.bankmanagementsystem.service.RegistrationService;
//import com.teamb.bankmanagementsystem.repository.CustomerRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//
//@SpringBootTest
//public class RegistrationServiceTest {
//
//    @InjectMocks
//    private RegistrationService registrationService;
//
//    @Mock
//    private CustomerRepository customerRepository;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testCreateCustomerWithValidDetails() {
//        CustomerDetails customerDetails = new CustomerDetails();
//        customerDetails.setPhoneNumber("12345678909");
//        customerDetails.setAadharNumber("123451234512");
//        customerDetails.setEmailAddress("nj12@gmail.com");
//        // Set valid customer details here
//
//        // Mock the save method to do nothing (void method)
////        Mockito.doNothing().when(customerRepository).save(any(Customer.class));
//
//        boolean result = registrationService.createCustomer(customerDetails);
//
//        assertFalse(result);
//    }
//
//    @Test
//    public void testCreateCustomerWithInvalidPhoneNumber() {
//        CustomerDetails customerDetails = new CustomerDetails();
//        customerDetails.setPhoneNumber("1234"); // Invalid phone number
//
//        boolean result = registrationService.createCustomer(customerDetails);
//
//        assertFalse(result);
//    }
//
//    @Test
//    public void testCreateCustomerWithInvalidAadharNumber() {
//        CustomerDetails customerDetails = new CustomerDetails();
//        customerDetails.setPhoneNumber("12345678909");
//        customerDetails.setAadharNumber("12345"); // Invalid Aadhar number
//        customerDetails.setEmailAddress("nj12@gmail.com");
//        boolean result = registrationService.createCustomer(customerDetails);
////        boolean result=false;
//
//        assertFalse(result);
//    }
//
//    @Test
//    public void testCreateCustomerWithInvalidEmailAddress() {
//        CustomerDetails customerDetails = new CustomerDetails();
//        customerDetails.setPhoneNumber("12345678909");
//        customerDetails.setAadharNumber("123451234512"); // Invalid Aadhar number
//        customerDetails.setEmailAddress("invalid-email"); // Invalid email address
//
//        boolean result = registrationService.createCustomer(customerDetails);
//
//        assertFalse(result);
//    }
//
//    @Test
//    public void testCreateCustomerWithValidDetailsAndRepositoryFailure() {
//        CustomerDetails customerDetails = new CustomerDetails();
//        customerDetails.setPhoneNumber("123456709");
//        customerDetails.setAadharNumber("1234514512");
//        customerDetails.setEmailAddress("nj12@gmailcom");
//        // Set valid customer details here
//
//        // Mock the save method to throw a RuntimeException
//        Mockito.doThrow(new RuntimeException("Repository error")).when(customerRepository).save(any(Customer.class));
//
//        boolean result = registrationService.createCustomer(customerDetails);
//
//        assertFalse(result);
//    }
//
//}


package com.teamb.bankmanagementsystem.TDD.service;

import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerDetailsException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.model.CustomerDetails;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import com.teamb.bankmanagementsystem.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class RegistrationServiceTest {

    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCustomerWithValidDetails() {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setFirstName("John");
        customerDetails.setLastName("Doe");
        customerDetails.setPhoneNumber("1234567890");
        customerDetails.setAadharNumber("123456789012");
        customerDetails.setEmailAddress("john.doe@example.com");
        customerDetails.setPassword("password");
        customerDetails.setAddress("123 Main St");

        // Mock the customerRepository to return null for the given aadharNumber, simulating a new customer
        Mockito.when(customerRepository.findByAccountNumber("123456789012")).thenReturn(null);

        // Call the createCustomer method
        boolean result = registrationService.createCustomer(customerDetails);

        // Verify that the customer was saved and registration was successful
        assertTrue(result);

        // Verify that the customer was saved with the expected values
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    public void testCreateCustomerWithInvalidPhoneNumber() {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setPhoneNumber("12345"); // Invalid phone number

        // Call the createCustomer method with invalid phone number
        boolean result = registrationService.createCustomer(customerDetails);

        // Verify that the registration failed due to an invalid phone number
        assertFalse(result);
    }

    @Test
    public void testCreateCustomerWithInvalidAadharNumber() {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setPhoneNumber("12345678909");
        customerDetails.setAadharNumber("12345"); // Invalid Aadhar number
        customerDetails.setEmailAddress("nj12@gmail.com");
        boolean result = registrationService.createCustomer(customerDetails);
//        boolean result=false;

        assertFalse(result);
    }

    @Test
    public void testCreateCustomerWithInvalidEmailAddress() {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setPhoneNumber("12345678909");
        customerDetails.setAadharNumber("123451234512"); // Invalid Aadhar number
        customerDetails.setEmailAddress("invalid-email"); // Invalid email address

        boolean result = registrationService.createCustomer(customerDetails);

        assertFalse(result);
    }


    @Test
    public void testCreateCustomerWithValidDetailsAndRepositoryFailure() {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setPhoneNumber("123456709");
        customerDetails.setAadharNumber("1234514512");
        customerDetails.setEmailAddress("nj12@gmailcom");
        // Set valid customer details here

        // Mock the save method to throw a RuntimeException
        Mockito.doThrow(new RuntimeException("Repository error")).when(customerRepository).save(any(Customer.class));

        boolean result = registrationService.createCustomer(customerDetails);

        assertFalse(result);
    }
}

