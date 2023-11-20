package com.teamb.bankmanagementsystem.controller;

import com.teamb.bankmanagementsystem.exceptions.InvalidAmountException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import com.teamb.bankmanagementsystem.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/login")
@CrossOrigin(origins = "http://localhost:3000")
public class WithdrawController {
    @Autowired
    private WithdrawService withdrawService;
    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawFunds(@RequestParam("accountNumber") String accountNumber, @RequestParam("amount") Double amount, @RequestParam("description") String description) {

        boolean isSuccess = withdrawService.withdrawMoney(accountNumber,amount, description);

        if (isSuccess) {
            String message = "Amount Withdraw Successfully";
            System.out.println(message);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            String message = "Failed to withdraw the amount";
            System.out.println(message);
            Customer currentUser = customerRepository.findByAccountNumber(accountNumber);
            if (amount > currentUser.getAccountBalance()) {
                throw new InvalidAmountException("Not Sufficient Balance");
            }
            if (amount < 0) {
                throw new InvalidAmountException("Amount entered is not valid");
            }
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}
