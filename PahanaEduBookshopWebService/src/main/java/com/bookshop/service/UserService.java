package com.bookshop.service;

import utils.DatabaseUtils;

import java.sql.*;

/**
 * Service class for managing user operations (authentication and registration).
 */
public class UserService {

    /**
     * Authenticate the user by checking the username and password.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return true if valid credentials, false otherwise.
     * @throws SQLException If any database error occurs.
     */
    public boolean authenticate(String username, String password) throws SQLException {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);  // In a real app, hash the password here!
            ResultSet rs = stmt.executeQuery();

            return rs.next();  // If the result set contains a row, authentication is successful
        }
    }

    /**
     * Register a new user by inserting their username and password into the database.
     *
     * @param username The username to be registered.
     * @param password The password to be registered.
     * @throws SQLException If any database error occurs.
     */
    public void registerUser(String username, String password) throws SQLException {
        String query = "INSERT INTO Users (username, password) VALUES (?, ?)";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);  // In a real app, hash the password here!
            stmt.executeUpdate();
        }
    }
}
