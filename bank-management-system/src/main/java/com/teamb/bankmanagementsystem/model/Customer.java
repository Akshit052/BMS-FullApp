package com.teamb.bankmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    public String customerID;
    public String accountNumber;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String aadharNumber;
    public String address;
    public String emailAddress;
    public String password;
    public double accountBalance;
    public String ifscCode;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions = new ArrayList<>();



    public Customer(String customerID, String accountNumber, String firstName, String lastName, String phoneNumber, String aadharNumber, String address, String emailAddress, String password, double accountBalance, String ifscCode) {
        this.customerID = customerID;
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.aadharNumber = aadharNumber;
        this.address = address;
        this.emailAddress = emailAddress;
        this.password = password;
        this.accountBalance = accountBalance;
        this.ifscCode = ifscCode;
    }

    public Customer() {

    }

    public Customer(String customerID, String password) {
        this.customerID = customerID;
        this.password = password;
    }


    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

}

