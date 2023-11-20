package com.teamb.bankmanagementsystem.controller.IntegrationTests;

import com.teamb.bankmanagementsystem.controller.TransferController;
import com.teamb.bankmanagementsystem.exceptions.InvalidAmountException;
import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerDetailsException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
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
public class TransferTest {

    @Autowired
    TransferController transferController;
    @Autowired
    CustomerRepository customerRepository;



    @Test
    public void testTransferSuccess(){
        List<Customer> customerList = customerRepository.findAll();
        Customer customer1 = customerList.get(0);
        Customer customer2 = customerList.get(1);
        double balance = customer1.getAccountBalance();
        double amount = new Random().nextInt((int)balance);
        System.out.println("bal = "+balance + " amount = "+amount);
        ResponseEntity<String> responseEntity = transferController.transferFunds(customer1.getAccountNumber(),customer2.getAccountNumber(),amount,"test transfer success");
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test(expected = InvalidAmountException.class)
    public void testTransferFailureInsufficientFunds(){
        List<Customer> customerList = customerRepository.findAll();
        Customer customer1 = customerList.get(0);
        Customer customer2 = customerList.get(1);
        double balance = customer1.getAccountBalance();
        double amount = new Random().nextInt((int)balance) + balance + 100;
        ResponseEntity<String> responseEntity = transferController.transferFunds(customer1.getAccountNumber(),customer2.getAccountNumber(),amount,"test transfer failed");
    }

    @Test(expected = InvalidAmountException.class)
    public void testTransferFailureNegativeAmount(){
        List<Customer> customerList = customerRepository.findAll();
        Customer customer1 = customerList.get(0);
        Customer customer2 = customerList.get(1);
        double amount = -100;
        ResponseEntity<String> responseEntity = transferController.transferFunds(customer1.getAccountNumber(),customer2.getAccountNumber(),amount,"test transfer failed");
    }
    @Test(expected = InvalidCustomerDetailsException.class)
    public void testTransferFailureInvalidBeneficiary(){
        List<Customer> customerList = customerRepository.findAll();
        Customer customer1 = customerList.get(0);
//        Customer invalidBeneficiary = new Customer("1234","Ac45","invalid","invalid","97588","Adhar45","Add","email","pswd",200,"ifsc");
        double balance = customer1.getAccountBalance();
        double amount = new Random().nextInt((int)balance);
        ResponseEntity<String> responseEntity = transferController.transferFunds(customer1.getAccountNumber(),"Invalid",amount,"test transfer failed");
    }
}
