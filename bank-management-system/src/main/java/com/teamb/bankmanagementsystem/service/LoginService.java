package com.teamb.bankmanagementsystem.service;

import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerCredentialsException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    CustomerRepository customerRepository;


    public Customer authenticateCustomer(String customerId, String password) {
        Customer customer = null;
        try{
            customer = customerRepository.findByCustomerIDAndPassword(customerId,password);
            if (customer == null){
                throw new InvalidCustomerCredentialsException();
            }
        }
        catch (InvalidCustomerCredentialsException e){
            System.out.println("exception caught: " + e.getMessage());
        }
        return customer;
    }
}
