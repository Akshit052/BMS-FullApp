package com.teamb.bankmanagementsystem.service;

import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerDetailsException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.model.CustomerView;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewBalanceService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerView getCustomerDetails(String accountNumber) {
        Customer customer = customerRepository.findByAccountNumber(accountNumber);
        if (customer != null) {
            CustomerView customerView = new CustomerView();
            customerView.setCustomerID(customer.getCustomerID());
            customerView.setAccountNumber(customer.getAccountNumber());
            customerView.setCustomerName(customer.getFirstName() + " " + customer.getLastName());
            customerView.setAccountBalance(customer.getAccountBalance());
            customerView.setIfscCode(customer.getIfscCode());

            return customerView;
        } else {
            throw new InvalidCustomerDetailsException("Customer with account number " + accountNumber + " not found");
        }
    }
}
