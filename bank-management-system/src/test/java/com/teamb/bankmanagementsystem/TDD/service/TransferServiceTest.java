package com.teamb.bankmanagementsystem.TDD.service;

import com.teamb.bankmanagementsystem.exceptions.InvalidAmountException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import com.teamb.bankmanagementsystem.service.TransactionService;
import com.teamb.bankmanagementsystem.service.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class TransferServiceTest {

    @InjectMocks
    private TransferService transferService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTransferMoneyWithSufficientBalance() {
        // Create a sample currentUser
        Customer currentUser = new Customer();
        currentUser.setAccountNumber("12345");
        currentUser.setAccountBalance(1000.0);

        // Create a sample beneficiaryUser
        Customer beneficiaryUser = new Customer();
        beneficiaryUser.setAccountNumber("54321");
        beneficiaryUser.setAccountBalance(500.0);

        // Mock the customerRepository to return the currentUser and beneficiaryUser
        Mockito.when(customerRepository.findByAccountNumber("12345")).thenReturn(currentUser);
        Mockito.when(customerRepository.findByAccountNumber("54321")).thenReturn(beneficiaryUser);

        // Call the transferMoney method
        boolean result = transferService.transferMoney("12345", "54321", 300.0, "Test Transfer");

        // Verify that the transfer was successful and the accounts were updated
        assertTrue(result);
        assertEquals(700.0, currentUser.getAccountBalance());
        assertEquals(800.0, beneficiaryUser.getAccountBalance());

        // Verify that the transactionService was called for both users
        verify(transactionService).createTransaction(currentUser, "Funds Transferred", 300.0, "54321", "Test Transfer", "Debit");
        verify(transactionService).createTransaction(beneficiaryUser, "Funds Transferred", 300.0, "SELF", "Test Transfer", "Credit");
    }

    // Add more test cases for different scenarios

    @Test
    public void testTransferMoneyWithInsufficientBalance() {
        // Create a sample currentUser with insufficient balance
        Customer currentUser = new Customer();
        currentUser.setAccountNumber("12345");
        currentUser.setAccountBalance(100.0);

        // Mock the customerRepository to return the currentUser
        Mockito.when(customerRepository.findByAccountNumber("12345")).thenReturn(currentUser);

        // Call the transferMoney method with an amount higher than the balance
        boolean result = transferService.transferMoney("12345", "54321", 200.0, "Test Transfer");

        // Verify that the transfer failed and the currentUser's balance was not updated
        assertFalse(result);
        assertEquals(100.0, currentUser.getAccountBalance());
    }
}
