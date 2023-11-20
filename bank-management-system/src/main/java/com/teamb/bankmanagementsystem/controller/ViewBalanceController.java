package com.teamb.bankmanagementsystem.controller;

import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerDetailsException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.model.CustomerView;
import com.teamb.bankmanagementsystem.service.ViewBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/login")
@CrossOrigin(origins = "http://localhost:3000")
public class ViewBalanceController {
    @Autowired
    ViewBalanceService viewBalanceService;

    @GetMapping("/details")
    public ResponseEntity<CustomerView> viewCustomerDetails(@RequestParam("accountNumber") String accountNumber) {
        CustomerView customer = viewBalanceService.getCustomerDetails(accountNumber);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            System.out.println("Account Number not found");
            throw new InvalidCustomerDetailsException("Customer with account number " + accountNumber + " not found");

        }
    }
}
