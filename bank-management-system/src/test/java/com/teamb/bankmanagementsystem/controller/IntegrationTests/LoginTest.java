package com.teamb.bankmanagementsystem.controller.IntegrationTests;

import com.teamb.bankmanagementsystem.controller.LoginController;
import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerCredentialsException;
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
public class LoginTest {
    @Autowired
    LoginController loginController;
    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testLoginValidCustomer(){
        Customer customer = customerRepository.findAll().get(0);
//        Customer customer = customerRepository.findFirst();
        ResponseEntity<Customer> responseEntity = loginController.login(customer.getCustomerID(), customer.getPassword());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test(expected = InvalidCustomerCredentialsException.class)
    public void testLoginInvalidCustomer(){
        ResponseEntity<Customer> responseEntity = loginController.login("invalidId", "invalidPassword");
//        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }


}
