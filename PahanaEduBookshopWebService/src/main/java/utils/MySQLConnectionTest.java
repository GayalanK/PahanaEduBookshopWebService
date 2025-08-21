import java.sql.*;

public class MySQLConnectionTest {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/BookshopDB";  // Replace with your database name
        String user = "root";  // Replace with your MySQL username
        String password = "";  // Replace with your MySQL password

        try {
            // Try connecting to the database
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully!");
            
            // Close the connection after test
            conn.close();
        } catch (SQLException e) {
            // Print any SQL exceptions
            e.printStackTrace();
        }
    }
}
