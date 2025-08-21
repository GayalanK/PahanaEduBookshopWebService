package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class to manage the database connection.
 */
public class DatabaseUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/BookshopDB";
    private static final String USER = "root";  // MySQL username
    private static final String PASSWORD = "password";  // MySQL password

    /**
     * Gets the database connection.
     * 
     * @return A connection to the MySQL database.
     * @throws SQLException If a database error occurs.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load the JDBC driver for MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found.", e);
        }
    }
}
