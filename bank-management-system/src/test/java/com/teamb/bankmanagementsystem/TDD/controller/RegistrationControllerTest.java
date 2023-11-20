package com.teamb.bankmanagementsystem.TDD.controller;

import com.teamb.bankmanagementsystem.controller.RegistrationController;
import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerCredentialsException;
import com.teamb.bankmanagementsystem.model.CustomerDetails;
import com.teamb.bankmanagementsystem.service.RegistrationService;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationControllerTest {

    @Mock
    private RegistrationService registrationService;

    @InjectMocks
    private RegistrationController registrationController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterCustomer_Success() {
        // Arrange
        CustomerDetails customerDTO = new CustomerDetails();
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Doe");
        Mockito.when(registrationService.createCustomer(customerDTO)).thenReturn(true);

        // Act
        ResponseEntity<String> response = registrationController.registerCustomer(customerDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Welcome John Doe", response.getBody());
    }

    @Test(expected = NullPointerException.class)
    public void testRegisterCustomer_Failure() {
        // Arrange
        CustomerDetails customerDTO = new CustomerDetails();
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Doe");
        Mockito.when(registrationService.createCustomer(customerDTO)).thenReturn(false);

        // Act
        ResponseEntity<String> response = registrationController.registerCustomer(customerDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Customer not created", response.getBody());
    }
}
