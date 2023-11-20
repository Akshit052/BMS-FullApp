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
public class DepositService {

//    @Autowired
//    DepositRepository depositRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TransactionService transactionService;
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Transactional
    public boolean depositMoney(String accountNumber,Double amount, String description) {
        boolean result = false;
        Customer currentUser = null;
        currentUser = customerRepository.findByAccountNumber(accountNumber);
//        if (authentication != null) {
//            if (authentication.getPrincipal() instanceof Customer) {
//                currentUser = (Customer) authentication.getPrincipal();
//                // Now you have access to the currentUser object
//            }
//        }
        if(currentUser==null){
            System.out.println("Customer not logged in !!!");
            return false;
        }
        try{
            if(amount<0){
                throw new InvalidAmountException("Amount cannot be negative");
            }
            currentUser.setAccountBalance(currentUser.getAccountBalance()+amount);
            customerRepository.save(currentUser);
            result=true;
        }
        catch (InvalidAmountException e){
            System.out.println("Exception : "+ e.getMessage());
            return false;
        }
//        createTransaction(accountNumber, "Funds Added", amount, "-", description, "Credit");
        transactionService.createTransaction(currentUser,"Funds Added",amount,"SELF",description,"Credit");
        return result;
    }
}
