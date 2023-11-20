package com.teamb.bankmanagementsystem;

import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerCredentialsException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.model.CustomerDetails;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.springframework.web.client.HttpClientErrorException;

public class SystemTests {

    @Test(expected = HttpClientErrorException.class)
    public void testCustomer() {
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = new Customer("customerID", "accountNumber", "FirstName", "LastName", "745378692", "123456789130", "Test-Address-1", "testmail@gmail.com", "testPassword", 1000, "IFSC");

        String baseUrl = "http://localhost:8080/customer/";

        //test Login
//        ?customerId=CS3533237&password=password
        String customerId = customer.getCustomerID();
        String password = customer.getPassword();
        String loginUrl = baseUrl + "login" + "?customerId=" + customerId + "&password=" + password;
        ResponseEntity<Customer> entity;

        entity = restTemplate.getForEntity(loginUrl, Customer.class);
        Customer logged = entity.getBody();
        assertNotNull(logged);
        System.out.println("logged one is " + logged.customerID + " send one is " + customerId);
        assertEquals(logged.customerID, customerId);

    }
}
