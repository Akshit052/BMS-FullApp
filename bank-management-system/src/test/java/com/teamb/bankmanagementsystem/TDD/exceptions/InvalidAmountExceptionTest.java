package com.teamb.bankmanagementsystem.TDD.exceptions;

import com.teamb.bankmanagementsystem.exceptions.InvalidAmountException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidAmountExceptionTest {

    @Test
    public void testErrorMessage() {
        String errorMessage = "Test exception message";
        InvalidAmountException exception = new InvalidAmountException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }
}
