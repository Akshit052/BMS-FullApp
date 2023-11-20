package com.teamb.bankmanagementsystem.TDD.model;

import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer(
                "CS123456",
                "AC789012",
                "John",
                "Doe",
                "1234567890",
                "123456789012",
                "123 Main St",
                "john.doe@example.com",
                "password",
                1000.0,
                "IFSC123456"
        );
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters
        assertEquals("CS123456", customer.getCustomerID());
        assertEquals("AC789012", customer.getAccountNumber());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("1234567890", customer.getPhoneNumber());
        assertEquals("123456789012", customer.getAadharNumber());
        assertEquals("123 Main St", customer.getAddress());
        assertEquals("john.doe@example.com", customer.getEmailAddress());
        assertEquals("password", customer.getPassword());
        assertEquals(1000.0, customer.getAccountBalance());
        assertEquals("IFSC123456", customer.getIfscCode());

        // Test setters
        customer.setCustomerID("CS654321");
        customer.setAccountNumber("AC987654");
        customer.setFirstName("Jane");
        customer.setLastName("Smith");
        customer.setPhoneNumber("9876543210");
        customer.setAadharNumber("987654321098");
        customer.setAddress("456 Elm St");
        customer.setEmailAddress("jane.smith@example.com");
        customer.setPassword("newpassword");
        customer.setAccountBalance(2000.0);
        customer.setIfscCode("IFSC987654");

        assertEquals("CS654321", customer.getCustomerID());
        assertEquals("AC987654", customer.getAccountNumber());
        assertEquals("Jane", customer.getFirstName());
        assertEquals("Smith", customer.getLastName());
        assertEquals("9876543210", customer.getPhoneNumber());
        assertEquals("987654321098", customer.getAadharNumber());
        assertEquals("456 Elm St", customer.getAddress());
        assertEquals("jane.smith@example.com", customer.getEmailAddress());
        assertEquals("newpassword", customer.getPassword());
        assertEquals(2000.0, customer.getAccountBalance());
        assertEquals("IFSC987654", customer.getIfscCode());
    }

    @Test
    public void testAddTransaction() {
        Transaction transaction = new Transaction();
        customer.addTransaction(transaction);

        // Ensure the transaction was added to the customer's list of transactions
        assertEquals(1, customer.getTransactions().size());
    }
}
