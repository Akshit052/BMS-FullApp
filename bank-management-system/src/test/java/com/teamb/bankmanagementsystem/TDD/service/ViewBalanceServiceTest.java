package com.teamb.bankmanagementsystem.TDD.service;

import com.teamb.bankmanagementsystem.exceptions.InvalidCustomerDetailsException;
import com.teamb.bankmanagementsystem.model.Customer;
import com.teamb.bankmanagementsystem.model.CustomerView;
import com.teamb.bankmanagementsystem.repository.CustomerRepository;
import com.teamb.bankmanagementsystem.service.ViewBalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ViewBalanceServiceTest {

    @InjectMocks
    private ViewBalanceService viewBalanceService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCustomerDetails() {
        // Create a sample customer
        Customer customer = new Customer();
        customer.setCustomerID("C123");
        customer.setAccountNumber("12345");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setAccountBalance(1000.0);
        customer.setIfscCode("IFSC123");

        // Mock the customerRepository to return the customer
        Mockito.when(customerRepository.findByAccountNumber("12345")).thenReturn(customer);

        // Call the getCustomerDetails method
        CustomerView customerView = viewBalanceService.getCustomerDetails("12345");

        // Verify that the customer details are correctly mapped to the view
        assertNotNull(customerView);
        assertEquals("C123", customerView.getCustomerID());
        assertEquals("12345", customerView.getAccountNumber());
        assertEquals("John Doe", customerView.getCustomerName());
        assertEquals(1000.0, customerView.getAccountBalance());
        assertEquals("IFSC123", customerView.getIfscCode());
    }

    @Test
    public void testGetCustomerDetailsWithInvalidAccountNumber() {
        // Mock the customerRepository to return null, simulating a customer not found
        Mockito.when(customerRepository.findByAccountNumber("12345")).thenReturn(null);

        // Call the getCustomerDetails method with an invalid account number
        assertThrows(InvalidCustomerDetailsException.class, () ->
                viewBalanceService.getCustomerDetails("12345")
        );
    }
}
