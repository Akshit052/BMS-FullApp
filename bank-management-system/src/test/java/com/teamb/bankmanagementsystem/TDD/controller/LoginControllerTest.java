package com.teamb.bankmanagementsystem.TDD.controller;

import com.teamb.bankmanagementsystem.controller.LoginController;
import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerCredentialsException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.service.LoginService;
//import org.junit.jupiter.api.Before;
//import org.junit.jupiter.api.Test;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginControllerTest {

    @Mock
    private LoginService loginService;

    private LoginController loginController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginController = new LoginController();
        loginController.loginService = loginService;
    }

    @Test
    public void testLogin_Success() {
        // Arrange
        String customerId = "testUser";
        String password = "testPassword";
        Customer customer = new Customer();
        Mockito.when(loginService.authenticateCustomer(customerId, password)).thenReturn(customer);

        // Act
        ResponseEntity<Customer> response = loginController.login(customerId, password);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test(expected = InvalidCustomerCredentialsException.class)
    public void testLogin_Failure() {
        // Arrange
        String customerId = "nonExistentUser";
        String password = "invalidPassword";
        Mockito.when(loginService.authenticateCustomer(customerId, password)).thenReturn(null);

        // Act
        ResponseEntity<Customer> response = loginController.login(customerId, password);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
}
