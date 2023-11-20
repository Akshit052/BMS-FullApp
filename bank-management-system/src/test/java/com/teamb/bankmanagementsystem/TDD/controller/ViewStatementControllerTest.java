package com.teamb.bankmanagementsystem.TDD.controller;

import com.teamb.bankmanagementsystem.controller.ViewStatementController;
import com.teamb.bankmanagementsystem.model.Transaction;
import com.teamb.bankmanagementsystem.service.ViewStatementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewStatementControllerTest {

    @Mock
    private ViewStatementService viewStatementService;

    @InjectMocks
    private ViewStatementController viewStatementController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testViewStatementWithTransactions_Success() {
        // Arrange
        String accountNumber = "123456";
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
        Mockito.when(viewStatementService.getStatement(accountNumber)).thenReturn(transactions);

        // Act
        ResponseEntity<List<Transaction>> response = viewStatementController.viewStatement(accountNumber);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
    }


}
