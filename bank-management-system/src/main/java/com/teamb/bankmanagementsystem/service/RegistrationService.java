package com.teamb.bankmanagementsystem.service;

import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerDetailsException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.model.CustomerDetails;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RegistrationService {

    @Autowired
    CustomerRepository customerRepository;


    public boolean createCustomer(CustomerDetails customerDTO) {
        boolean result = false;
        String phoneNumber = customerDTO.getPhoneNumber();
        String aadharNumber = customerDTO.getAadharNumber();
        String emailAddress = customerDTO.getEmailAddress();

        try {
            if (!phoneNumber.matches("^\\d{10}$")) {
                throw new InvalidCustomerDetailsException("Invalid Phone number");
            } else if (!aadharNumber.matches("^\\d{12}$")) {
                throw new InvalidCustomerDetailsException("Invalid Aadhar number");

            } else if (!emailAddress.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
                throw new InvalidCustomerDetailsException("Invalid Email Address");

            } else {

                String accountNumber_created = "AC";
                Random rnd = new Random();
                int number = rnd.nextInt(9999999);
                accountNumber_created += String.format("%07d", number);

                String customerID_created = "CS";
                Random rnd1 = new Random();
                int number1 = rnd1.nextInt(9999999);
                customerID_created += String.format("%07d", number1);

                Double accountBalance_initial = 0.0;
                String ifscCode = "IFSC123456";

                Customer customer = new Customer();
                customer.setCustomerID(customerID_created);
                customer.setAccountNumber(accountNumber_created);
                customer.setFirstName(customerDTO.getFirstName());
                customer.setLastName(customerDTO.getLastName());
                customer.setPhoneNumber(customerDTO.getPhoneNumber());
                customer.setAadharNumber(customerDTO.getAadharNumber());
                customer.setEmailAddress(customerDTO.getEmailAddress());
                customer.setPassword(customerDTO.getPassword());
                customer.setIfscCode(ifscCode);
                customer.setAccountBalance(accountBalance_initial);
                customer.setAddress(customerDTO.getAddress());
                customerRepository.save(customer);
                result = true;

            }
        } catch (InvalidCustomerDetailsException e) {
            System.out.println("Exception : " + e.getMessage());
        }
        return result;


    }
}

