package com.teamb.bankmanagementsystem.stepdefinitions;

import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerCredentialsException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.service.LoginService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class LoginStepDefinition {

    LoginService loginService;
    Customer customer;
    String customerId;
    String password;

    boolean isException=false;
    Customer newCustomer;

    public LoginStepDefinition(){
        loginService = Mockito.mock(LoginService.class);
    }



    @Given("a customer with ID {string} and password {string}")
    public void a_customer_with_ID_and_password(String id, String password) {
        // Write code here that turns the phrase above into concrete actions
        customerId = id;
        this.password = password;
        newCustomer = new Customer(id,password);
        when(loginService.authenticateCustomer(id,password)).thenReturn(newCustomer);

//        throw new cucumber.api.PendingException();
    }

    @When("I authenticate the customer")
    public void i_authenticate_the_customer() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
        try{
            customer = loginService.authenticateCustomer(customerId,password);
        }
        catch (InvalidCustomerCredentialsException e){
            isException = true;
        }

    }

    @Then("I should get a customer object")
    public void i_should_get_a_customer_object() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
        assertNotNull(customer);
        System.out.println(customer.getCustomerID());
    }

}
