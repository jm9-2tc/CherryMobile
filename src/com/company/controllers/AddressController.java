package com.company.controllers;

import com.company.controllers.base.Controller;
import com.company.data.DatabaseManager;
import com.company.model.customer.Address;
import com.company.model.customer.base.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressController extends Controller<Address> {
    public AddressController(DatabaseManager database) {
        super(database);
    }

    @Override
    public Address load(int id) {
        ResultSet result = database.executeQuery("SELECT * FROM address WHERE id = " + id + ";");
        Address address = null;
        try {
            address = new Address(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6)
            );

            address.setId(result.getInt(0));
        } catch (SQLException ignored) {}
        return address;
    }

    @Override
    public void save(Address object) {

    }
}
