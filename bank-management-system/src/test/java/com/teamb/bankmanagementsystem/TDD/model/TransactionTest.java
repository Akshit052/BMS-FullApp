package com.teamb.bankmanagementsystem.TDD.model;

import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        transaction = new Transaction();
        transaction.setTransactionId(1);
        transaction.setAccountNumber("AC123456");
        transaction.setTransactionType("Credit");
        transaction.setAmount(100.0);
        transaction.setBeneficiaryAccount("AC789012");
        transaction.setNarration("Payment");
        transaction.setDbcrType("Credit");
        transaction.setTransactionDate(null);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, transaction.getTransactionId());
        assertEquals("AC123456", transaction.getAccountNumber());
        assertEquals("Credit", transaction.getTransactionType());
        assertEquals(100.0, transaction.getAmount());
        assertEquals("AC789012", transaction.getBeneficiaryAccount());
        assertEquals("Payment", transaction.getNarration());
        assertEquals("Credit", transaction.getDbcrType());
        assertEquals(null, transaction.getTransactionDate());
    }


}
