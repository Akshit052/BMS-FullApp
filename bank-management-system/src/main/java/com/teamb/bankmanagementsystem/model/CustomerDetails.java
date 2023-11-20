package com.teamb.bankmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerDetails {
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String aadharNumber;
    public String address;
    public String emailAddress;
    public String password;
}
