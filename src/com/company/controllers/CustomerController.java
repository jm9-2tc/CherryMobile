package com.company.controllers;

import com.company.data.DatabaseManager;
import com.company.model.customer.BusinessCustomer;
import com.company.model.customer.base.Customer;
import com.company.model.customer.IndividualCustomer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController {
    private final DatabaseManager database;

    public CustomerController(DatabaseManager database) {
        this.database = database;
    }

    public void saveCustomer(IndividualCustomer customer) {
        int customerId = saveBaseCustomer(customer);
        database.execute("INSERT INTO individual_customer VALUES ('" + customerId + "', '" + customer.getFirstName() + "', '" + customer.getLastName() + "', '" + customer.getPesel() + "')");
    }

    public void saveCustomer(BusinessCustomer customer) {
        int customerId = saveBaseCustomer(customer);
        database.execute("INSERT INTO business_customer VALUES ('" + customerId + "', '" + customer.getNip() + "', '" + customer.getRegon() + "', '" + customer.getName() + "')");
    }

    private int saveBaseCustomer(Customer customer) {
        database.execute("INSERT INTO customer VALUES (NULL, '" + customer.getEmail() + "', '" + customer.getPhoneNumber() + "', '" + customer.getPassword() + "')");
        int customerId = -1;
        try {
            ResultSet queryResult = database.executeQuery("SELECT id FROM customer ORDER BY id DESC LIMIT 1;");
            customerId = queryResult.getInt(0);
            queryResult.close();
        } catch (SQLException ignored) {}

        return customerId;
    }
}
