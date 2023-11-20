package com.teamb.bankmanagementsystem.TDD.service;

import com.teamb.bankmanagementsystem.exceptions.InvalidAmountException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import com.teamb.bankmanagementsystem.service.DepositService;
import com.teamb.bankmanagementsystem.service.TransactionService;
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
public class DepositServiceTest {

    @InjectMocks
    private DepositService depositService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDepositMoneyWithValidAmount() {
        // Create a sample currentUser
        Customer currentUser = new Customer();
        currentUser.setAccountNumber("12345");
        currentUser.setAccountBalance(1000.0);

        // Mock the customerRepository to return the currentUser
        Mockito.when(customerRepository.findByAccountNumber("12345")).thenReturn(currentUser);

        // Call the depositMoney method with a valid amount
        boolean result = depositService.depositMoney("12345", 300.0, "Test Deposit");

        // Verify that the deposit was successful, and the currentUser's balance was updated
        assertTrue(result);
        assertEquals(1300.0, currentUser.getAccountBalance());

        // Verify that the transactionService was called for the deposit
        verify(transactionService).createTransaction(currentUser, "Funds Added", 300.0, "SELF", "Test Deposit", "Credit");
    }

    @Test
    public void testDepositMoneyWithNegativeAmount() {
        // Create a sample currentUser
        Customer currentUser = new Customer();
        currentUser.setAccountNumber("12345");
        currentUser.setAccountBalance(1000.0);

        // Mock the customerRepository to return the currentUser
        Mockito.when(customerRepository.findByAccountNumber("12345")).thenReturn(currentUser);

        // Call the depositMoney method with a negative amount
        boolean result = depositService.depositMoney("12345", -100.0, "Test Deposit");

        // Verify that the deposit failed, and the currentUser's balance was not updated
        assertFalse(result);
        assertEquals(1000.0, currentUser.getAccountBalance());
    }

    @Test
    public void testDepositMoneyWithCustomerNotFound() {
        // Mock the customerRepository to return null, simulating a customer not found
        Mockito.when(customerRepository.findByAccountNumber("12345")).thenReturn(null);

        // Call the depositMoney method with an invalid account number
        boolean result = depositService.depositMoney("12345", 300.0, "Test Deposit");

        // Verify that the deposit failed, and the currentUser's balance was not updated
        assertFalse(result);
    }

    @Test
    public void testDepositMoneyWithInvalidAmountException() {
        // Create a sample currentUser
        Customer currentUser = new Customer();
        currentUser.setAccountNumber("12345");
        currentUser.setAccountBalance(1000.0);

        // Mock the customerRepository to return the currentUser
        Mockito.when(customerRepository.findByAccountNumber("12345")).thenReturn(currentUser);

        // Mock the transactionService to throw an InvalidAmountException
        Mockito.doThrow(new InvalidAmountException("Amount cannot be negative")).when(transactionService)
                .createTransaction(currentUser, "Funds Added", -100.0, "SELF", "Test Deposit", "Credit");

        // Call the depositMoney method with a negative amount
        boolean result = depositService.depositMoney("12345", -100.0, "Test Deposit");

        // Verify that the deposit failed, and the currentUser's balance was not updated
        assertFalse(result);
    }
}
