package com.teamb.bankmanagementsystem.controller.IntegrationTests;
import com.teamb.bankmanagementsystem.controller.ViewStatementController;
import com.teamb.bankmanagementsystem.controller.WithdrawController;
import com.teamb.bankmanagementsystem.exceptions.InvalidAmountException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.model.Transaction;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import lombok.With;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ViewStatementTest {
    @Autowired
    ViewStatementController viewStatementController;
    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testViewStatementSuccess(){
        Customer customer = customerRepository.findAll().get(0);
        ResponseEntity<List<Transaction>> responseEntity = viewStatementController.viewStatement(customer.getAccountNumber());
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void testViewStatementFailure(){
        ResponseEntity<List<Transaction>> responseEntity = viewStatementController.viewStatement("Invalid Account Number");
        assertEquals(responseEntity.getStatusCode(),HttpStatus.BAD_REQUEST);
    }

}
