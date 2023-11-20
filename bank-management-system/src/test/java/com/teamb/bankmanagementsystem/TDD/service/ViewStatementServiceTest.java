package com.teamb.bankmanagementsystem.TDD.service;

import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.model.Transaction;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import com.teamb.bankmanagementsystem.service.ViewStatementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ViewStatementServiceTest {

    @InjectMocks
    private ViewStatementService viewStatementService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetStatementWithValidAccountNumber() {
        // Create a sample customer
        Customer customer = new Customer();
        customer.setAccountNumber("12345");
        // Create a list of sample transactions
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction();
        transaction1.setTransactionType("Credit");
        transaction1.setAmount(100.0);
        // Add more transactions here
        transactions.add(transaction1);

        // Set the transactions in the customer
        customer.setTransactions(transactions);

        // Mock the customerRepository to return the customer
        Mockito.when(customerRepository.findByAccountNumber("12345")).thenReturn(customer);

        // Call the getStatement method
        List<Transaction> statement = viewStatementService.getStatement("12345");

        // Verify that the returned statement matches the customer's transactions
        assertNotNull(statement);
        assertEquals(transactions.size(), statement.size());
        // Add more assertions to compare individual transactions if needed
    }

    @Test
    public void testGetStatementWithInvalidAccountNumber() {
        // Mock the customerRepository to return null, simulating a customer not found
        Mockito.when(customerRepository.findByAccountNumber("12345")).thenReturn(null);

        // Call the getStatement method with an invalid account number
        List<Transaction> statement = viewStatementService.getStatement("12345");

        // Verify that an empty list is returned
        assertNotNull(statement);
        assertTrue(statement.isEmpty());
    }
}
