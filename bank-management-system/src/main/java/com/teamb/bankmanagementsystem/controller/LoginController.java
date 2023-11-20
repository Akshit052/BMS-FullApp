package com.teamb.bankmanagementsystem.controller;


import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerCredentialsException;
import com.teamb.bankmanagementsystem.model.*;
import com.teamb.bankmanagementsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired

    public LoginService loginService;
    @GetMapping("/login")
    public ResponseEntity<Customer> login(@RequestParam("customerId") String customerId, @RequestParam("password") String password){
        System.out.println("ID = " + customerId + " and psswd = " + password);
        Customer customer = loginService.authenticateCustomer(customerId, password);
        if (customer != null) {
            System.out.println("Welcome " + customer.getFirstName() + " " + customer.getLastName());
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        else {
            System.out.println("customer not found");
            throw new InvalidCustomerCredentialsException();
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}


