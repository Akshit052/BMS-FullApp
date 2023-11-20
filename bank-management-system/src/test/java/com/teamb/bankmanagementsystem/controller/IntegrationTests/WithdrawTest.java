package com.teamb.bankmanagementsystem.controller.IntegrationTests;

import com.teamb.bankmanagementsystem.controller.DepositController;
import com.teamb.bankmanagementsystem.controller.WithdrawController;
import com.teamb.bankmanagementsystem.exceptions.InvalidAmountException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import lombok.With;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WithdrawTest {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    WithdrawController withdrawController;

    @Test
    public void testWithdrawSuccess(){
        Customer customer = customerRepository.findAll().get(0);
        double balance = customer.getAccountBalance();
        double amount = new Random().nextInt((int)balance);
        if(amount>50) amount = 10;
        ResponseEntity<String> responseEntity = withdrawController.withdrawFunds(customer.getAccountNumber(),amount,"withdraw success test");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
    @Test(expected = InvalidAmountException.class)
    public void testWithdrawFailureNegativeAmount(){
        Customer customer = customerRepository.findAll().get(0);
        ResponseEntity<String> responseEntity = withdrawController.withdrawFunds(customer.getAccountNumber(),-100.0,"withdraw failed test");
    }
    @Test(expected = InvalidAmountException.class)
    public void testWithdrawFailureInsufficientFunds(){
        Customer customer = customerRepository.findAll().get(0);
        double balance = customer.getAccountBalance();
        double amount = new Random().nextInt((int)balance)+1000;
        ResponseEntity<String> responseEntity = withdrawController.withdrawFunds(customer.getAccountNumber(),amount,"withdraw failed test");
    }

}



