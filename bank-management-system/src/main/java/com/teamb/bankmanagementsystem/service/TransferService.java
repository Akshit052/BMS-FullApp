package com.teamb.bankmanagementsystem.service;
import com.teamb.bankmanagementsystem.exceptions.InvalidAmountException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransferService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TransactionService transactionService;

    @Transactional
    public boolean transferMoney(String accountNumber,String beneficiaryAccount, Double amount, String description) {
        boolean result = false;
        Customer currentUser = null;
        Customer beneficiaryUser = null;

        currentUser = customerRepository.findByAccountNumber(accountNumber);
        beneficiaryUser = customerRepository.findByAccountNumber(beneficiaryAccount);

        if(currentUser==null){
            System.out.println("Customer not logged in !!!");
            return false;
        }
        if(beneficiaryUser==null){
            System.out.println("Beneficiary does not exist !!!");
            return false;
        }
        try{
            if (amount > currentUser.getAccountBalance()) {
                throw new InvalidAmountException("Not Sufficient Balance");
            }
            if(amount<0){
                throw new InvalidAmountException("Amount cannot be negative");
            }
            currentUser.setAccountBalance(currentUser.getAccountBalance()-amount);
            beneficiaryUser.setAccountBalance(beneficiaryUser.getAccountBalance()+amount);
            customerRepository.save(currentUser);
            customerRepository.save(beneficiaryUser);
            result=true;
        }
        catch (InvalidAmountException e){
            System.out.println("Exception : "+ e.getMessage());
            return false;
        }
        transactionService.createTransaction(currentUser,"Funds Transferred",amount,beneficiaryAccount,description,"Debit");
        transactionService.createTransaction(beneficiaryUser,"Funds Transferred",amount,"SELF",description,"Credit");
        return result;
    }
}
