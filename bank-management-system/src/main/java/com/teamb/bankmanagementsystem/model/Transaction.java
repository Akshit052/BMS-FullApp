package com.teamb.bankmanagementsystem.model;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    private String accountNumber;
    private String transactionType;
    private double amount;
    private String beneficiaryAccount;
    private String narration;
    private String dbcrType;
    private Timestamp transactionDate;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Transaction() {
    }
    public int getTransactionId() {
        return transactionId;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public double getAmount() {
        return amount;
    }
    public String getBeneficiaryAccount() {
        return beneficiaryAccount;
    }
    public String getNarration() {
        return narration;
    }
    public Timestamp getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setBeneficiaryAccount(String beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
    }
    public String getDbcrType() {
        return dbcrType;
    }
    public void setDbcrType(String dbcrType) {
        this.dbcrType = dbcrType;
    }
    public void setNarration(String narration) {
        this.narration = narration;
    }
    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
