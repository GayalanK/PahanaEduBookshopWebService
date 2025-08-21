package service;

import com.bookshop.model.Item;
import com.bookshop.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for testing ItemService class.
 */
public class ItemServiceTest {

    private ItemService itemService;

    // Set up method to initialize ItemService before each test
    @BeforeEach
    public void setUp() {
        itemService = new ItemService();  // Initialize the ItemService class
    }

    // Test case for adding an item
    @Test
    public void testAddItem() throws SQLException {
        Item item = new Item(5, "Design Patterns Book", 55.99);
        itemService.addItem(item);  // Add the item to the database

        // Retrieve the item by its ID
        Item retrievedItem = itemService.getItemById(5);
        assertNotNull(retrievedItem);  // Ensure the item is not null
        assertEquals("Design Patterns Book", retrievedItem.getItemName());  // Verify item name
    }

    // Test case for retrieving an item by ID
    @Test
    public void testGetItemById() throws SQLException {
        Item item = itemService.getItemById(1);
        assertNotNull(item);  // Ensure the item is found
        assertEquals("Java Programming Book", item.getItemName());  // Verify the item name
    }

    // Test case for updating an item
    @Test
    public void testUpdateItem() throws SQLException {
        Item updatedItem = new Item(1, "Advanced Java Programming", 49.99);
        itemService.updateItem(1, updatedItem);  // Update the item with ID 1

        // Retrieve the updated item from the database
        Item retrievedItem = itemService.getItemById(1);
        assertEquals("Advanced Java Programming", retrievedItem.getItemName());  // Ensure the name is updated
    }

    // Test case for deleting an item
    @Test
    public void testDeleteItem() throws SQLException {
        itemService.deleteItem(5);  // Delete the item with ID 5
        Item item = itemService.getItemById(5);
        assertNull(item);  // Ensure the item is null after deletion
    }
}
