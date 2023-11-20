package com.teamb.bankmanagementsystem.controller.IntegrationTests;

import com.teamb.bankmanagementsystem.controller.DepositController;
import com.teamb.bankmanagementsystem.exceptions.InvalidAmountException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepositTest {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    DepositController depositController;

    @Test
    public void testDepositSuccess(){
        Customer customer = customerRepository.findAll().get(0);
        ResponseEntity<String> responseEntity = depositController.depositFunds(customer.getAccountNumber(),200.0,"deposit 200");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
    @Test(expected = InvalidAmountException.class)
    public void testDepositFailure(){
        Customer customer = customerRepository.findAll().get(0);
        ResponseEntity<String> responseEntity = depositController.depositFunds(customer.getAccountNumber(),-200.0,"deposit 200");
//        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
