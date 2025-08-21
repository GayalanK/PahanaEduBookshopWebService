package com.bookshop.model;

public class Customer {
    private int accountNumber;
    private String name;
    private String address;
    private String phone;
    private double unitsConsumed;

    // Constructor
    public Customer(int accountNumber, String name, String address, String phone, double unitsConsumed) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.unitsConsumed = unitsConsumed;
    }

    // Getters and Setters
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(double unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    @Override
    public String toString() {
        return "Customer [Account Number: " + accountNumber + ", Name: " + name + 
               ", Address: " + address + ", Phone: " + phone + ", Units Consumed: " + unitsConsumed + "]";
    }
}
