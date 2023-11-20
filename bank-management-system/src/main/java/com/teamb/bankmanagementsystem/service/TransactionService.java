package com.teamb.bankmanagementsystem.service;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.model.Transaction;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import com.teamb.bankmanagementsystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class TransactionService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void createTransaction(Customer customer, String transactionType, double amount, String beneficiaryAccount, String narration, String dbcrType) {


            Transaction transaction = new Transaction();
            transaction.setAccountNumber(customer.getAccountNumber());
            transaction.setTransactionType(transactionType);
            transaction.setAmount(amount);
            transaction.setBeneficiaryAccount(beneficiaryAccount);
            transaction.setNarration(narration);
            transaction.setDbcrType(dbcrType);
            transaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
            transaction.setCustomer(customer);

            customer.addTransaction(transaction);


//            customerRepository.save(customer);

            transactionRepository.save(transaction);
    }
}

