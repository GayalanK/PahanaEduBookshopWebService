package service;

import com.bookshop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for testing UserService class.
 */
public class UserServiceTest {

    private UserService userService;

    // Set up method to initialize UserService before each test
    @BeforeEach
    public void setUp() {
        userService = new UserService();  // Initialize the UserService class
    }

    // Test case for authenticating a user (login)
    @Test
    public void testAuthenticateUser() throws SQLException {
        boolean isAuthenticated = userService.authenticate("john_doe", "password123");
        assertTrue(isAuthenticated);  // Verify the user is authenticated successfully
    }

    // Test case for unsuccessful login (invalid credentials)
    @Test
    public void testInvalidAuthenticateUser() throws SQLException {
        boolean isAuthenticated = userService.authenticate("john_doe", "wrongpassword");
        assertFalse(isAuthenticated);  // Verify the user is not authenticated
    }

    // Test case for registering a new user
    @Test
    public void testRegisterUser() throws SQLException {
        // Register a new user
        userService.registerUser("new_user", "new_password");

        // Attempt to login with the new user credentials
        boolean isAuthenticated = userService.authenticate("new_user", "new_password");
        assertTrue(isAuthenticated);  // Verify the new user is authenticated successfully
    }
}
