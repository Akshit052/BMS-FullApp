package com.teamb.bankmanagementsystem.TDD.controller;

import com.teamb.bankmanagementsystem.controller.TransferController;
import com.teamb.bankmanagementsystem.service.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferControllerTest {

    @Mock
    private TransferService transferService;

    @InjectMocks
    private TransferController transferController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTransferFunds_Success() {
        // Arrange
        String accountNumber = "123456";
        String beneficiaryAccount = "654321";
        Double amount = 100.0;
        String description = "Transfer to friend";
        Mockito.when(transferService.transferMoney(accountNumber, beneficiaryAccount, amount, description)).thenReturn(true);

        // Act
        ResponseEntity<String> response = transferController.transferFunds(accountNumber, beneficiaryAccount, amount, description);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Amount Transferred Successfully", response.getBody());
    }

    @Test(expected = NullPointerException.class)
    public void testTransferFunds_Failure() {
        // Arrange
        String accountNumber = "123456";
        String beneficiaryAccount = "654321";
        Double amount = 1000.0;
        String description = "Transfer to friend";
        Mockito.when(transferService.transferMoney(accountNumber, beneficiaryAccount, amount, description)).thenReturn(false);

        // Act
        ResponseEntity<String> response = transferController.transferFunds(accountNumber, beneficiaryAccount, amount, description);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to transfer the amount", response.getBody());
    }
}

