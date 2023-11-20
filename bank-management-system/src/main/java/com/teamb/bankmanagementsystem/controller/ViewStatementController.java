package com.teamb.bankmanagementsystem.controller;

import com.teamb.bankmanagementsystem.model.Transaction;
import com.teamb.bankmanagementsystem.service.ViewStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/login")
@CrossOrigin(origins = "http://localhost:3000")
public class ViewStatementController {
    @Autowired
    private ViewStatementService viewStatementService;

    @PostMapping("/viewstatement")
    public ResponseEntity<List<Transaction>> viewStatement(@RequestParam("accountNumber") String accountNumber) {
        if (!viewStatementService.getStatement(accountNumber).isEmpty()) {
            return new ResponseEntity<>(viewStatementService.getStatement(accountNumber), HttpStatus.OK);
        } else {
            System.out.println("User does not have any transactions.");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
 