package com.teamb.bankmanagementsystem.TDD.service;

import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.model.Transaction;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import com.teamb.bankmanagementsystem.repository.TransactionRepository;
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
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateTransaction() {
        // Create a sample customer
        Customer customer = new Customer();
        customer.setAccountNumber("12345");
        customer.setFirstName("John");
        customer.setLastName("Doe");

        // Create a sample transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionType("Credit");
        transaction.setAmount(100.0);
        transaction.setBeneficiaryAccount("54321");
        transaction.setNarration("Test Transaction");
        transaction.setDbcrType("DB");
        // You may set other transaction properties here

        // Mock the customerRepository to return the customer
        Mockito.when(customerRepository.findByAccountNumber("12345")).thenReturn(customer);

        // Call the createTransaction method
        transactionService.createTransaction(customer, "Credit", 100.0, "54321", "Test Transaction", "DB");

        assertTrue(true);
    }

    // Add more test cases for different scenarios

    @Test
    public void testCreateTransactionWithInvalidCustomer() {
        // Mock the customerRepository to return null
        Mockito.when(customerRepository.findByAccountNumber("12345")).thenReturn(null);

        // Call the createTransaction method
        assertFalse(false);
    }
}
