package com.teamb.bankmanagementsystem.TDD.exceptions;

import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerDetailsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidCustomerDetailsExceptionTest {

    @Test
    public void testErrorMessage() {
        String errorMessage = "Test exception message";
        InvalidCustomerDetailsException exception = new InvalidCustomerDetailsException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }
}
