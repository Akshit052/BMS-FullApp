
package com.teamb.bankmanagementsystem.service;

import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.model.Transaction;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViewStatementService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TransactionService transactionService;

    public List<Transaction> getStatement(String accountNumber) {
        Customer customer = customerRepository.findByAccountNumber(accountNumber);
        boolean result = false;
        if (customer == null) {
            System.out.println("Customer not logged in !!!");
            List<Transaction> emptyList = new ArrayList<>();
            return emptyList;
        }
        return customer.getTransactions();
    }
}
 