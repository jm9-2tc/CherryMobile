package com.company.controllers;

import com.company.controllers.base.InheritanceController;
import com.company.data.DatabaseManager;
import com.company.exception.ObjectNotSavedException;
import com.company.model.customer.Address;
import com.company.model.customer.BusinessCustomer;
import com.company.model.customer.base.Customer;
import com.company.model.customer.IndividualCustomer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController extends InheritanceController<Customer> {
    private final AddressController addressController;

    public CustomerController(DatabaseManager database, AddressController addressController) {
        super(database);
        this.addressController = addressController;
    }


    public void saveIndividual(IndividualCustomer customer) {
        Address address = customer.getAddress();

        if (address.getId() == null) {
            throw new ObjectNotSavedException("address");
        }

        database.execute("INSERT INTO customer_address VALUES ("
                + customer.getId() + "', '"
                + customer.getAddress().getId() + "')"
        );

        int customerId = saveBase(customer);
        database.execute("INSERT INTO individual_customer VALUES ('" + customerId + "', '" + customer.getFirstName() + "', '" + customer.getLastName() + "', '" + customer.getPesel() + "')");
    }

    public void saveBusiness(BusinessCustomer customer) {
        List<Address> addressList = customer.getAddressList();

        for (Address address : addressList) {
            if (address.getId() == null) {
                throw new ObjectNotSavedException("address");
            }

            database.execute("INSERT INTO customer_address VALUES ("
                    + customer.getId() + "', '"
                    + address.getId() + "')"
            );
        }

        int customerId = saveBase(customer);
        database.execute("INSERT INTO business_customer VALUES ('" + customerId + "', '" + customer.getNip() + "', '" + customer.getRegon() + "', '" + customer.getName() + "')");
    }

    @Override
    protected int saveBase(Customer customer) {
        database.execute("INSERT INTO customer VALUES (NULL, '"
                + customer.getEmail() + "', '"
                + customer.getPhoneNumber()
                + "', '"
                + customer.getPassword() + "')"
        );

        int customerId = -1;
        try {
            ResultSet queryResult = database.executeQuery("SELECT id FROM customer ORDER BY id DESC LIMIT 1;");
            customerId = queryResult.getInt(1);
            customer.setId(customerId);
            queryResult.close();
        } catch (SQLException ignored) {}
        return customerId;
    }


    public IndividualCustomer loadIndividual(int id) {
        Customer base = loadBase(id);
        if (base != null) {
            IndividualCustomer customer = null;

            try {
                ResultSet result = database.executeQuery("SELECT * FROM individual_customer WHERE customer_ptr_id = " + id + ";");
                ResultSet address = database.executeQuery("SELECT id FROM customer_address WHERE customer_id = " + id + ";");

                customer = new IndividualCustomer(
                        base.getPhoneNumber(),
                        base.getEmail(),
                        base.getPassword(),
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDate(4),
                        addressController.load(address.getInt(0))
                );
                customer.setId(result.getInt(0));
            } catch (SQLException ignored) {}

            return customer;
        } else{
            return null;
        }
    }

    public BusinessCustomer loadBusiness(int id) {
        Customer base = loadBase(id);
        if (base != null) {
            BusinessCustomer customer = null;

            try {
                ResultSet result = database.executeQuery("SELECT * FROM business_customer WHERE customer_ptr_id = " + id + ";");
                ResultSet customerAddresses = database.executeQuery("SELECT id FROM customer_address WHERE customer_id = " + id + ";");

                List<Address> addressList = new ArrayList<>();

                while (customerAddresses.next()) {
                    addressList.add(addressController.load(customerAddresses.getInt(0)));
                }

                customer = new BusinessCustomer(
                        base.getPhoneNumber(),
                        base.getEmail(),
                        base.getPassword(),
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        addressList
                );
                customer.setId(result.getInt(0));
            } catch (SQLException ignored) {}

            return customer;
        } else{
            return null;
        }
    }

    @Override
    protected Customer loadBase(int id) {
        ResultSet result = database.executeQuery("SELECT * FROM customer WHERE id = " + id + ";");
        Customer customer = null;
        try {
            ResultSet customerAddressId = database.executeQuery("SELECT address_id FROM customer WHERE id = " + id + ";");

            customer = new Customer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3)
            );
            customer.setId(result.getInt(0));
        } catch (SQLException ignored) {}
        return customer;
    }
}
