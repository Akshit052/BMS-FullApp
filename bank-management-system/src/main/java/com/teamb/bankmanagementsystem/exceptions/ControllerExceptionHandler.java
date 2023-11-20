package com.teamb.bankmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    ErrorMessage exceptionHandler(ValidationException e) {
        return new ErrorMessage("400", e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidCustomerCredentialsException.class)
    ErrorMessage exceptionHandler(InvalidCustomerCredentialsException e) {
        return new ErrorMessage("400", e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidCustomerDetailsException.class)
    ErrorMessage exceptionHandler(InvalidCustomerDetailsException e) {
        return new ErrorMessage("400", e.getMessage());
    }

    //    InvalidAmountException
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAmountException.class)
    ErrorMessage exceptionHandler(InvalidAmountException e) {
        return new ErrorMessage("400", e.getMessage());
    }

}

