package com.teamb.bankmanagementsystem.service;

import com.teamb.bankmanagementsystem.exceptions.InvalidAmountException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class WithdrawService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TransactionService transactionService;

    @Transactional
    public boolean withdrawMoney(String accountNumber, Double amount, String description) {
        boolean result = false;
        Customer currentUser = null;
        currentUser = customerRepository.findByAccountNumber(accountNumber);
        if (currentUser == null) {
            System.out.println("Customer not logged in !!!");
            return false;
        }
        try {
            if (amount > currentUser.getAccountBalance()) {
                throw new InvalidAmountException("Not Sufficient Balance");
            }
            if (amount < 0) {
                throw new InvalidAmountException("Amount entered is not valid");
            }
            currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
            customerRepository.save(currentUser);
            result = true;
        } catch (InvalidAmountException e) {
            System.out.println("Exception : " + e.getMessage());
            return false;
        }
        transactionService.createTransaction(currentUser, "Funds Withdrawn", amount, "SELF", description, "Debit");
        return result;
    }
}
