package com.bookshop.service;

import com.bookshop.model.Customer;
import utils.DatabaseUtils;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Service class for managing customer operations (add, retrieve, update, delete).
 */
public class CustomerService {

    /**
     * Add a new customer to the database.
     * 
     * @param customer The customer object containing the customer data.
     * @throws SQLException If any SQL error occurs.
     */
    public void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customers (accountNumber, name, address, phone, unitsConsumed) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customer.getAccountNumber());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPhone());
            stmt.setDouble(5, customer.getUnitsConsumed());
            stmt.executeUpdate();  // Executes the insert query
        }
    }

    /**
     * Retrieve a customer by account number.
     * 
     * @param accountNumber The unique account number of the customer.
     * @return The customer object or null if the customer is not found.
     * @throws SQLException If any SQL error occurs.
     */
    public Customer getCustomerById(int accountNumber) throws SQLException {
        String query = "SELECT * FROM Customers WHERE accountNumber = ?";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, accountNumber);
            ResultSet rs = stmt.executeQuery();  // Execute query and fetch data

            if (rs.next()) {
                return new Customer(
                        rs.getInt("accountNumber"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getDouble("unitsConsumed")
                );
            }
        }
        return null;  // Return null if customer not found
    }

    /**
     * Retrieve all customers from the database.
     * 
     * @return A list of all customers.
     * @throws SQLException If any SQL error occurs.
     */
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM Customers";

        try (Connection connection = DatabaseUtils.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("accountNumber"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getDouble("unitsConsumed")
                ));
            }
        }
        return customers;  // Return list of all customers
    }

    /**
     * Update the details of a customer.
     * 
     * @param accountNumber The account number of the customer to update.
     * @param updatedCustomer The updated customer object.
     * @throws SQLException If any SQL error occurs.
     */
    public void updateCustomer(int accountNumber, Customer updatedCustomer) throws SQLException {
        String query = "UPDATE Customers SET name = ?, address = ?, phone = ?, unitsConsumed = ? WHERE accountNumber = ?";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedCustomer.getName());
            stmt.setString(2, updatedCustomer.getAddress());
            stmt.setString(3, updatedCustomer.getPhone());
            stmt.setDouble(4, updatedCustomer.getUnitsConsumed());
            stmt.setInt(5, accountNumber);
            stmt.executeUpdate();  // Execute update query
        }
    }

    /**
     * Delete a customer by account number.
     * 
     * @param accountNumber The account number of the customer to delete.
     * @throws SQLException If any SQL error occurs.
     */
    public void deleteCustomer(int accountNumber) throws SQLException {
        String query = "DELETE FROM Customers WHERE accountNumber = ?";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, accountNumber);
            stmt.executeUpdate();  // Execute delete query
        }
    }
}
