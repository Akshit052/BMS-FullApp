package com.teamb.bankmanagementsystem.exceptions;

public class InvalidCustomerCredentialsException extends RuntimeException{

    public InvalidCustomerCredentialsException(){
        super("Invalid Customer Credentials");
    }
}
