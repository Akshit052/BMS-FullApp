package com.teamb.bankmanagementsystem.controller;

import com.teamb.bankmanagementsystem.exceptions.InvalidAmountException;
import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerDetailsException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import com.teamb.bankmanagementsystem.service.DepositService;
import com.teamb.bankmanagementsystem.service.TransferService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/login")
@CrossOrigin(origins = "http://localhost:3000")
public class TransferController {

    @Autowired
    private TransferService transferService;
    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestParam("accountNumber") String accountNumber, @RequestParam("beneficiaryAccount") String beneficiaryAccount, @RequestParam("amount") Double amount, @RequestParam("description") String description) {

        boolean isSuccess = transferService.transferMoney(accountNumber, beneficiaryAccount, amount, description);

        if (isSuccess) {
            String message = "Amount Transferred Successfully";
            System.out.println(message);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            String message = "Failed to transfer the amount";
            System.out.println(message);
            Customer currentUser = customerRepository.findByAccountNumber(accountNumber);
            if (amount > currentUser.getAccountBalance()) {
                throw new InvalidAmountException("Not Sufficient Balance");
            }
            if(amount<0){
                throw new InvalidAmountException("Amount cannot be negative");
            }
            Customer beneficiary = null;
            beneficiary = customerRepository.findByAccountNumber(beneficiaryAccount);
            if(beneficiary==null)
                throw new InvalidCustomerDetailsException("Beneficiary Account Do not Exist");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

}
