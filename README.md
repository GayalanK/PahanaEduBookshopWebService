# Pahana Edu Bookshop Web Service

## Overview

The **Pahana Edu Bookshop Web Service** is a Java EE-based application for managing a bookshop’s **inventory** and **user authentication**. It provides RESTful APIs for managing **customers**, **items**, and **user authentication** (login and registration). This service supports basic **CRUD** operations for customers and items.

### Key Features:
- **User Authentication**: Allows users to register and login.
- **Customer Management**: Perform CRUD operations for customers.
- **Item Management**: Perform CRUD operations for items in the bookshop.
  
This project uses **JAX-RS** (Java API for RESTful Web Services) for building the **REST APIs**, and **MySQL** as the database to store customer and item data.

---

## Technologies Used
- **Java EE** (JAX-RS) for RESTful API development.
- **MySQL** for the relational database.
- **NetBeans IDE** for development.
- **Tomcat/GlassFish** for deployment.
- **JUnit 5** for unit testing.
- **Postman** for testing API endpoints.

---

## Prerequisites

- **Java 8 or higher**
- **Maven** for building the project.
- **MySQL** installed and running.
  - You should create a **MySQL database** named `BookshopDB`.
  - Use the following SQL queries to create the necessary tables:

    ```sql
    CREATE TABLE Customers (
        accountNumber INT PRIMARY KEY,
        name VARCHAR(100),
        address VARCHAR(255),
        phone VARCHAR(20),
        unitsConsumed DOUBLE
    );

    CREATE TABLE Items (
        itemId INT PRIMARY KEY,
        itemName VARCHAR(255),
        price DOUBLE
    );
    ```

    - Insert sample data:
    ```sql
    INSERT INTO Customers (accountNumber, name, address, phone, unitsConsumed) VALUES
    (1, 'John Doe', '123 Elm St', '555-1234', 200.0),
    (2, 'Jane Smith', '456 Oak St', '555-5678', 300.0);

    INSERT INTO Items (itemId, itemName, price) VALUES
    (1, 'Java Programming Book', 45.99),
    (2, 'Database Design Book', 39.99);
    ```

---

## Installation and Setup

1. **Clone the Repository**:

    ```bash
    git clone https://github.com/yourusername/pahana-edu-bookshop-web-service.git
    cd pahana-edu-bookshop-web-service
    ```

2. **Build the Project**:

    Run **Maven** to clean and build the project.

    ```bash
    mvn clean install
    ```

3. **Deploy to Tomcat**:

    - Copy the generated **WAR file** located in `target/PahanaEduBookshopWebService-1.0-SNAPSHOT.war`.
    - Deploy the **WAR file** to **Tomcat** or your preferred **Java EE server**.

---

## API Endpoints

### **User Authentication**

- **POST /api/users/login**
  - **Description**: Log in a user.
  - **Request**:
    ```json
    {
      "username": "john_doe",
      "password": "password123"
    }
    ```
  - **Response**: Returns a success message if credentials are valid.
  
- **POST /api/users/register**
  - **Description**: Register a new user.
  - **Request**:
    ```json
    {
      "username": "new_user",
      "password": "new_password"
    }
    ```
  - **Response**: Returns a success message after successful registration.

---

### **Customer Management**

- **POST /api/customers**
  - **Description**: Create a new customer.
  - **Request**:
    ```json
    {
      "accountNumber": 3,
      "name": "Michael Scott",
      "address": "123 Scranton St",
      "phone": "555-111-2222",
      "unitsConsumed": 250.0
    }
    ```
  - **Response**: Returns the created customer details.

- **GET /api/customers/{accountNumber}**
  - **Description**: Retrieve a customer by their account number.
  - **Response**: Returns customer details.

- **PUT /api/customers/{accountNumber}**
  - **Description**: Update customer details.
  - **Request**: Updated customer data in JSON format.
  - **Response**: Returns the updated customer details.

- **DELETE /api/customers/{accountNumber}**
  - **Description**: Delete a customer by account number.
  - **Response**: Returns a success message.

---

### **Item Management**

- **POST /api/items**
  - **Description**: Create a new item.
  - **Request**:
    ```json
    {
      "itemId": 3,
      "itemName": "Advanced Java Programming",
      "price": 55.99
    }
    ```
  - **Response**: Returns the created item details.

- **GET /api/items/{itemId}**
  - **Description**: Retrieve an item by its ID.
  - **Response**: Returns item details.

- **PUT /api/items/{itemId}**
  - **Description**: Update item details.
  - **Request**: Updated item data in JSON format.
  - **Response**: Returns the updated item details.

- **DELETE /api/items/{itemId}**
  - **Description**: Delete an item by item ID.
  - **Response**: Returns a success message.

---

## Running the Tests

### **JUnit Tests**

To run unit tests for the service layer (CustomerService, ItemService, UserService):

```bash
mvn test
