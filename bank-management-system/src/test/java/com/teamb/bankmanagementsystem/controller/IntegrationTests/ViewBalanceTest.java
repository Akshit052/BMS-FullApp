package com.teamb.bankmanagementsystem.controller.IntegrationTests;

import com.teamb.bankmanagementsystem.controller.ViewBalanceController;
import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerDetailsException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.model.CustomerView;
import com.teamb.bankmanagementsystem.model.Transaction;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ViewBalanceTest {

    @Autowired
    ViewBalanceController viewBalanceController;
    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testViewBalanceSuccess(){
        Customer customer = customerRepository.findAll().get(0);
        ResponseEntity<CustomerView> responseEntity = viewBalanceController.viewCustomerDetails(customer.getAccountNumber());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test(expected = InvalidCustomerDetailsException.class)
    public void testViewBalanceFailure(){
//        Customer customer = customerRepository.findAll().get(0);
        ResponseEntity<CustomerView> responseEntity = viewBalanceController.viewCustomerDetails("000xx00");
//        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

}
