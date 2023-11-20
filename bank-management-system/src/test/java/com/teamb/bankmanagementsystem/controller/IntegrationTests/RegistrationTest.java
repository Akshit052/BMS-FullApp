package com.teamb.bankmanagementsystem.controller.IntegrationTests;

import com.teamb.bankmanagementsystem.controller.RegistrationController;
import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerDetailsException;
import com.teamb.bankmanagementsystem.model.CustomerDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationTest{
    @Autowired
    RegistrationController registrationController;

    @Test
    public void testRegisterValidCustomer(){
        CustomerDetails customerDetails = new CustomerDetails("Admin","User","7455886482","123456789120","AdminAddress","example@gmail.com","testPassword");
        ResponseEntity<String> responseEntity = registrationController.registerCustomer(customerDetails);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        assertEquals(httpStatus,HttpStatus.OK);
    }

    @Test(expected = InvalidCustomerDetailsException.class)
    public void testRegisterInvalidPhoneNumberCustomer(){
        CustomerDetails customerDetails = new CustomerDetails("Admin","User","7455886","123456789120","MyAddress","example@gmail.com","testPassword");
        ResponseEntity<String> responseEntity = registrationController.registerCustomer(customerDetails);

    }

    @Test(expected = InvalidCustomerDetailsException.class)
    public void testRegisterInvalidAadhaarNumberCustomer(){
        CustomerDetails customerDetails = new CustomerDetails("Admin","User","7455886496","1234567120","MyAddress","example@gmail.com","testPassword");
        ResponseEntity<String> responseEntity = registrationController.registerCustomer(customerDetails);

    }

    @Test(expected = InvalidCustomerDetailsException.class)
    public void testRegisterInvalidEmailCustomer(){
        CustomerDetails customerDetails = new CustomerDetails("Admin","User","7455806400","123456789120","MyAddress","example.com","testPassword");
        ResponseEntity<String> responseEntity = registrationController.registerCustomer(customerDetails);

    }

}
