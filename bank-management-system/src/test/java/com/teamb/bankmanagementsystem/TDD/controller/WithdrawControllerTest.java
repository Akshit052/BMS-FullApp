package com.teamb.bankmanagementsystem.TDD.controller;

import com.teamb.bankmanagementsystem.controller.WithdrawController;
import com.teamb.bankmanagementsystem.service.WithdrawService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawControllerTest {

    @Mock
    private WithdrawService withdrawService;

    @InjectMocks
    private WithdrawController withdrawController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWithdrawFundsSuccess() {
        // Arrange
        String accountNumber = "123456";
        Double amount = 100.0;
        String description = "Withdraw Test";
        Mockito.when(withdrawService.withdrawMoney(accountNumber, amount, description)).thenReturn(true);

        // Act
        ResponseEntity<String> response = withdrawController.withdrawFunds(accountNumber, amount, description);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Amount Withdraw Successfully", response.getBody());
    }

    @Test(expected = NullPointerException.class)
    public void testWithdrawFundsFailure() {
        // Arrange
        String accountNumber = "654321";
        Double amount = 200.0;
        String description = "Withdraw Test";
        Mockito.when(withdrawService.withdrawMoney(accountNumber, amount, description)).thenReturn(false);

        // Act
        ResponseEntity<String> response = withdrawController.withdrawFunds(accountNumber, amount, description);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to withdraw the amount", response.getBody());
    }
}
