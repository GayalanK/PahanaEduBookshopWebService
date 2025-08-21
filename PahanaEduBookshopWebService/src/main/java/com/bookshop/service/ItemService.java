package com.bookshop.service;

import com.bookshop.model.Item;
import utils.DatabaseUtils;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ItemService {

    // Add a new item to the database
    public void addItem(Item item) throws SQLException {
        String query = "INSERT INTO Items (itemId, itemName, price) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, item.getItemId());
            stmt.setString(2, item.getItemName());
            stmt.setDouble(3, item.getPrice());
            stmt.executeUpdate();  // Executes the insert query
        }
    }

    // Get an item by its ID
    public Item getItemById(int itemId) throws SQLException {
        String query = "SELECT * FROM Items WHERE itemId = ?";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Item(
                        rs.getInt("itemId"),
                        rs.getString("itemName"),
                        rs.getDouble("price")
                );
            }
        }
        return null;  // Return null if item not found
    }

    // Get all items from the database
    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM Items";

        try (Connection connection = DatabaseUtils.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                items.add(new Item(
                        rs.getInt("itemId"),
                        rs.getString("itemName"),
                        rs.getDouble("price")
                ));
            }
        }
        return items;  // Return list of all items
    }

    // Update an item by its ID
    public void updateItem(int itemId, Item updatedItem) throws SQLException {
        String query = "UPDATE Items SET itemName = ?, price = ? WHERE itemId = ?";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedItem.getItemName());
            stmt.setDouble(2, updatedItem.getPrice());
            stmt.setInt(3, itemId);
            stmt.executeUpdate();  // Execute update query
        }
    }

    // Delete an item by its ID
    public void deleteItem(int itemId) throws SQLException {
        String query = "DELETE FROM Items WHERE itemId = ?";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            stmt.executeUpdate();  // Execute delete query
        }
    }
}
