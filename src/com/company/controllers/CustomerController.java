package com.company.controllers;

import com.company.data.DatabaseManager;
import com.company.model.customer.IndividualCustomer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController {
    private final DatabaseManager database;

    public CustomerController(DatabaseManager database) {
        this.database = database;
    }

    public void saveCustomer(IndividualCustomer customer) {
        database.execute("INSERT INTO customer VALUES (NULL, '" + customer.getEmail() + "', '" + customer.getPhoneNumber() + "', '" + customer.getPassword() + "')");
        try {
            ResultSet lastId = database.executeQuery("SELECT id FROM customer ORDER BY id DESC LIMIT 1;");
            int a = 5;
        } catch (Exception ignored) {}
    }
}
