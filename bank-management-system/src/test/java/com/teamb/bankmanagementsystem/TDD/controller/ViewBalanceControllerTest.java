package com.teamb.bankmanagementsystem.TDD.controller;

import com.teamb.bankmanagementsystem.controller.ViewBalanceController;
import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerDetailsException;
import com.teamb.bankmanagementsystem.model.CustomerView;
import com.teamb.bankmanagementsystem.service.ViewBalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewBalanceControllerTest {

    @Mock
    private ViewBalanceService viewBalanceService;

    @InjectMocks
    private ViewBalanceController viewBalanceController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testViewCustomerDetails_Success() {
        // Arrange
        String accountNumber = "123456";
        CustomerView customerView = new CustomerView();
        customerView.setAccountNumber(accountNumber);
        Mockito.when(viewBalanceService.getCustomerDetails(accountNumber)).thenReturn(customerView);

        // Act
        ResponseEntity<CustomerView> response = viewBalanceController.viewCustomerDetails(accountNumber);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customerView, response.getBody());
    }

    @Test(expected = InvalidCustomerDetailsException.class)
    public void testViewCustomerDetails_NotFound() {
        // Arrange
        String accountNumber = "654321";
        Mockito.when(viewBalanceService.getCustomerDetails(accountNumber)).thenReturn(null);

        // Act
        ResponseEntity<CustomerView> response = viewBalanceController.viewCustomerDetails(accountNumber);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
}
