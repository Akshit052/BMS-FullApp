package com.teamb.bankmanagementsystem.TDD.service;

import com.teamb.bankmanagementsystem.exceptions.InvalidAmountException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import com.teamb.bankmanagementsystem.service.TransactionService;
import com.teamb.bankmanagementsystem.service.WithdrawService;
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

@SpringBootTest
public class WithdrawServiceTest {

    @InjectMocks
    private WithdrawService withdrawService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWithdrawMoneyWithSufficientBalance() {
        // Create a sample currentUser
        Customer currentUser = new Customer();
        currentUser.setAccountNumber("12345");
        currentUser.setAccountBalance(1000.0);

        // Mock the customerRepository to return the currentUser
        Mockito.when(customerRepository.findByAccountNumber("12345")).thenReturn(currentUser);

        // Call the withdrawMoney method
        boolean result = withdrawService.withdrawMoney("12345", 300.0, "Test Withdraw");

        // Verify that the withdrawal was successful and the currentUser's balance was updated
        assertTrue(result);
        assertEquals(700.0, currentUser.getAccountBalance());

        // Verify that the transactionService was called for the withdrawal
        Mockito.verify(transactionService).createTransaction(currentUser, "Funds Withdrawn", 300.0, "SELF", "Test Withdraw", "Debit");
    }

    @Test
    public void testWithdrawMoneyWithInsufficientBalance() {
        // Create a sample currentUser with insufficient balance
        Customer currentUser = new Customer();
        currentUser.setAccountNumber("12345");
        currentUser.setAccountBalance(100.0);

        // Mock the customerRepository to return the currentUser
        Mockito.when(customerRepository.findByAccountNumber("12345")).thenReturn(currentUser);

        // Call the withdrawMoney method with an amount higher than the balance
        boolean result = withdrawService.withdrawMoney("12345", 200.0, "Test Withdraw");

        // Verify that the withdrawal failed, and the currentUser's balance was not updated
        assertFalse(result);
        assertEquals(100.0, currentUser.getAccountBalance());
    }

    @Test
    public void testWithdrawMoneyWithNegativeAmount() {
        // Create a sample currentUser
        Customer currentUser = new Customer();
        currentUser.setAccountNumber("12345");
        currentUser.setAccountBalance(1000.0);

        // Mock the customerRepository to return the currentUser
        Mockito.when(customerRepository.findByAccountNumber("12345")).thenReturn(currentUser);

        // Call the withdrawMoney method with a negative amount
        boolean result = withdrawService.withdrawMoney("12345", -100.0, "Test Withdraw");

        // Verify that the withdrawal failed, and the currentUser's balance was not updated
        assertFalse(result);
        assertEquals(1000.0, currentUser.getAccountBalance());
    }
}
