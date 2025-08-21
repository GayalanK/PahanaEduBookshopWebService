package service;

import com.bookshop.model.Customer;
import com.bookshop.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for testing CustomerService class.
 */
public class CustomerServiceTest {

    private CustomerService customerService;

    // This method will run before each test to set up necessary objects
    @BeforeEach
    public void setUp() {
        customerService = new CustomerService();  // Initialize the service to be tested
    }

    // Test case to add a customer and verify if it was added
    @Test
    public void testAddCustomer() throws SQLException {
        // Create a new customer
        Customer customer = new Customer(6, "Michael Scott", "123 Scranton St", "555-111-2222", 300.0);
        customerService.addCustomer(customer);  // Add customer to database

        // Retrieve the customer by account number to verify it was added
        Customer retrievedCustomer = customerService.getCustomerById(6);
        assertNotNull(retrievedCustomer);  // Ensure the customer is not null
        assertEquals("Michael Scott", retrievedCustomer.getName());  // Check the name
    }

    // Test case to retrieve a customer by account number
    @Test
    public void testGetCustomerById() throws SQLException {
        // Assume customer with account number 1 exists
        Customer customer = customerService.getCustomerById(1);
        assertNotNull(customer);  // Ensure the customer is found
        assertEquals("John Doe", customer.getName());  // Verify the customer name
    }

    // Test case to update an existing customer
    @Test
    public void testUpdateCustomer() throws SQLException {
        // Create an updated customer object
        Customer updatedCustomer = new Customer(1, "Johnathan Doe", "123 Elm St", "123-456-7890", 160.0);
        customerService.updateCustomer(1, updatedCustomer);  // Update the customer with account number 1

        // Retrieve the updated customer from the database
        Customer retrievedCustomer = customerService.getCustomerById(1);
        assertEquals("Johnathan Doe", retrievedCustomer.getName());  // Ensure the name is updated
    }

    // Test case to delete a customer and verify it was deleted
    @Test
    public void testDeleteCustomer() throws SQLException {
        // Delete customer with account number 6
        customerService.deleteCustomer(6);

        // Try to retrieve the customer to verify it has been deleted
        Customer customer = customerService.getCustomerById(6);
        assertNull(customer);  // Ensure the customer is null (deleted)
    }
}
