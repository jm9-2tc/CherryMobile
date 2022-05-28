package com.company.controllers;

import com.company.controllers.base.Controller;
import com.company.data.DatabaseManager;
import com.company.model.customer.BusinessCustomer;
import com.company.model.customer.base.Customer;
import com.company.model.customer.IndividualCustomer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController extends Controller<Customer> {
    public CustomerController(DatabaseManager database) {
        super(database);
    }

    public Customer getCustomer(int id) {
        ResultSet queryResult = database.executeQuery("SELECT * FROM customer WHERE id = " + id + ";");
        Customer customer = null;
        return customer;
    }

    public void saveCustomer(IndividualCustomer customer) {
        int customerId = saveBase(customer);
        database.execute("INSERT INTO individual_customer VALUES ('" + customerId + "', '" + customer.getFirstName() + "', '" + customer.getLastName() + "', '" + customer.getPesel() + "')");
    }

    public void saveCustomer(BusinessCustomer customer) {
        int customerId = saveBase(customer);
        database.execute("INSERT INTO business_customer VALUES ('" + customerId + "', '" + customer.getNip() + "', '" + customer.getRegon() + "', '" + customer.getName() + "')");
    }

    @Override
    protected int saveBase(Customer customer) {
        database.execute("INSERT INTO customer VALUES (NULL, '" + customer.getEmail() + "', '" + customer.getPhoneNumber() + "', '" + customer.getPassword() + "')");
        int customerId = -1;
        try {
            ResultSet queryResult = database.executeQuery("SELECT id FROM customer ORDER BY id DESC LIMIT 1;");
            customerId = queryResult.getInt(1);
            customer.setId(customerId);
            queryResult.close();
        } catch (SQLException ignored) {}
        return customerId;
    }
}
