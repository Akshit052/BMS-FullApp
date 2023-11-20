package com.teamb.bankmanagementsystem.TDD.exceptions;

import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerCredentialsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidCustomerCredentialsExceptionTest {

    @Test
    public void testErrorMessage() {
        InvalidCustomerCredentialsException exception = new InvalidCustomerCredentialsException();

        assertEquals("Invalid Customer Credentials", exception.getMessage());
    }
}
