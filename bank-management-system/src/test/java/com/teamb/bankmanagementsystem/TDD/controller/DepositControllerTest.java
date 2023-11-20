package com.teamb.bankmanagementsystem.TDD.controller;

import com.teamb.bankmanagementsystem.controller.DepositController;
import com.teamb.bankmanagementsystem.exceptions.InvalidAmountException;
import com.teamb.bankmanagementsystem.service.DepositService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositControllerTest {

    @Mock
    private DepositService depositService;

    private DepositController depositController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        depositController = new DepositController();
        depositController.depositService = depositService;
    }

    @Test
    public void testDepositFunds_Success() {
        // Arrange
        String accountNumber = "12345";
        Double amount = 100.0;
        String description = "Test deposit";
        Mockito.when(depositService.depositMoney(accountNumber, amount, description)).thenReturn(true);

        // Act
        ResponseEntity<String> response = depositController.depositFunds(accountNumber, amount, description);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Amount Deposited Successfully", response.getBody());
    }

    @Test(expected = InvalidAmountException.class)
    public void testDepositFunds_Failure() {
        // Arrange
        String accountNumber = "12345";
        Double amount = -50.0; // Simulate a failure scenario
        String description = "Test deposit";
        Mockito.when(depositService.depositMoney(accountNumber, amount, description)).thenReturn(false);

        // Act
        ResponseEntity<String> response = depositController.depositFunds(accountNumber, amount, description);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to deposit the amount", response.getBody());
    }
}
