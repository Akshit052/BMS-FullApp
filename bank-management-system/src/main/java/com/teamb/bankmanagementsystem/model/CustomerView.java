package com.teamb.bankmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerView {
    private String customerID;
    private String accountNumber;
    private String customerName;
    public double accountBalance;
    public String ifscCode;
}
