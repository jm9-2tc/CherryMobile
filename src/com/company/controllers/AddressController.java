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
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7)
            );

            address.setId(result.getInt(1));
        } catch (SQLException ignored) {}
        return address;
    }

    @Override
    public void save(Address object) {
        database.execute("INSERT INTO address VALUES (NULL, '"
                + object.getPrefix() + "', '"
                + object.getStreetName() + "', '"
                + object.getBuildingNumber() + "', '"
                + object.getApartmentNumber() + "', '"
                + object.getZipCode() + "', '"
                + object.getCityName() + "')"
        );

        ResultSet queryResult = database.executeQuery("SELECT MAX(id) AS id FROM address;");

        try {
            object.setId(queryResult.getInt(1));
            queryResult.close();
        } catch(SQLException ignored) {}
    }
}
