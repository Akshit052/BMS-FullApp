package com.teamb.bankmanagementsystem.controller;


import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerDetailsException;
import com.teamb.bankmanagementsystem.model.*;
import com.teamb.bankmanagementsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService; // Inject your LoginService here

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerDetails customerDTO) {
        boolean res = registrationService.createCustomer(customerDTO);
        if (res) {
            String message = "Welcome " + customerDTO.getFirstName() + " " + customerDTO.getLastName();
            System.out.println(message);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        else {
            System.out.println("customer not created");
            if (!customerDTO.getPhoneNumber().matches("^\\d{10}$"))
                throw new InvalidCustomerDetailsException("Invalid Phone number");

            else if (!customerDTO.getAadharNumber().matches("^\\d{12}$"))
                throw new InvalidCustomerDetailsException("Invalid Aadhar number");

            else if (!customerDTO.getEmailAddress().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"))
                throw new InvalidCustomerDetailsException("Invalid Email Address");
        }
        return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
    }


}


