package com.company.controllers;

import com.company.controllers.base.InheritanceController;
import com.company.data.DatabaseManager;
import com.company.exception.ObjectNotSavedException;
import com.company.model.customer.BusinessCustomer;
import com.company.model.customer.base.Customer;
import com.company.model.customer.IndividualCustomer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController extends InheritanceController<Customer> {
    private final AddressController addressController;

    public CustomerController(DatabaseManager database, AddressController addressController) {
        super(database);
        this.addressController = addressController;
    }

    public Customer getCustomer(int id) {
        ResultSet queryResult = database.executeQuery("SELECT * FROM customer WHERE id = " + id + ";");
        Customer customer = null;
        return customer;
    }

    public void save(IndividualCustomer customer) {
        if (customer.getAddress().getId() == null) {
            throw new ObjectNotSavedException("address");
        }

        int customerId = saveBase(customer);
        database.execute("INSERT INTO individual_customer VALUES ('" + customerId + "', '" + customer.getFirstName() + "', '" + customer.getLastName() + "', '" + customer.getPesel() + "')");
    }

    public void save(BusinessCustomer customer) {
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

    @Override
    protected Customer loadBase(int id) {
        ResultSet result = database.executeQuery("SELECT * FROM customer WHERE id = " + id + ";");
        Customer customer = null;
        try {
            customer = new Customer(result.getString(1), result.getString(2), result.getString(3), );
            customer.setId(result.getInt(0));
        } catch (SQLException ignored) {}
    }
}
