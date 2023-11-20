package com.teamb.bankmanagementsystem.TDD.service;

import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerCredentialsException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import com.teamb.bankmanagementsystem.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAuthenticateCustomerWithValidCredentials() {
        Customer customer = new Customer();
        customer.setCustomerID("CS0237749");
        customer.setPassword("password");

        Mockito.when(customerRepository.findByCustomerIDAndPassword("CS0237749", "password")).thenReturn(customer);

        Customer authenticatedCustomer = loginService.authenticateCustomer("CS0237749", "password");

        assertNotNull(authenticatedCustomer);
        assertEquals("CS0237749", authenticatedCustomer.getCustomerID());
        assertEquals("password", authenticatedCustomer.getPassword());
    }
    @Test
    public void testAuthenticateCustomerWithInvalidCredentials() {
        // Simulate a scenario where the customer is not found in the repository
        Mockito.when(customerRepository.findByCustomerIDAndPassword(anyString(), anyString())).thenReturn(null);

        // Call the authenticateCustomer method with invalid credentials
        Customer authenticatedCustomer = loginService.authenticateCustomer("CS0237749", "password");

        assertEquals(authenticatedCustomer,null);

    }
}
