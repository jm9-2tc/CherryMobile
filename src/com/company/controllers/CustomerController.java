package com.company.controllers;

import com.company.controllers.base.InheritanceController;
import com.company.data.DatabaseManager;
import com.company.exceptions.ObjectNotSavedException;
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
    private final ContractController contractController;

    public CustomerController(DatabaseManager database, AddressController addressController, ContractController contractController) {
        super(database);
        this.addressController = addressController;
        this.contractController = contractController;
    }


    public void saveIndividual(IndividualCustomer customer) {
        Address address = customer.getAddress();
        int customerId = saveBase(customer);

        if (address.getId() == null) {
            throw new ObjectNotSavedException("address");
        }

        database.execute("INSERT INTO customer_address VALUES ("
                + customerId + ", '"
                + customer.getAddress().getId() + "', '');"
        );
        database.execute("INSERT INTO individual_customer VALUES ('"
                + customerId + "', '"
                + customer.getFirstName() + "', '"
                + customer.getLastName() + "', '"
                + customer.getPesel() + "', '"
                + customer.getDateOfBirth() + "')"
        );
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
                + customer.getPhoneNumber() + "', '"
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
            ResultSet result = database.executeQuery("SELECT * FROM individual_customer WHERE customer_ptr_id = " + id + ";");
            ResultSet address = database.executeQuery("SELECT id FROM customer_address WHERE customer_id = " + id + ";");

            try {
                customer = new IndividualCustomer(
                        base.getPhoneNumber(),
                        base.getEmail(),
                        base.getPassword(),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getDate(5),
                        addressController.load(address.getInt(1))
                );
                customer.setId(result.getInt(1));
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
            ResultSet result = database.executeQuery("SELECT * FROM business_customer WHERE customer_ptr_id = " + id + ";");
            ResultSet customerAddresses = database.executeQuery("SELECT id FROM customer_address WHERE customer_id = " + id + ";");

            List<Address> addressList = new ArrayList<>();

            try {
                while (customerAddresses.next()) {
                    addressList.add(addressController.load(customerAddresses.getInt(0)));
                }

                customer = new BusinessCustomer(
                        base.getPhoneNumber(),
                        base.getEmail(),
                        base.getPassword(),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        addressList
                );
                customer.setId(result.getInt(1));
            } catch (SQLException ignored) {}

            return customer;
        } else{
            return null;
        }
    }

    public Customer load(int id) {
        ResultSet individual = database.executeQuery("SELECT customer_ptr_id FROM individual_customer WHERE customer_ptr_id = " + id + ";");
        ResultSet business = database.executeQuery("SELECT customer_ptr_id FROM business_customer WHERE customer_ptr_id = " + id + ";");

        try {
            if (individual.next()) {
                return loadIndividual(id);
            } else if (business.next()) {
                return loadBusiness(id);
            }
        } catch (SQLException ignored) {}
        return null;
    }

    @Override
    protected Customer loadBase(int id) {
        ResultSet result = database.executeQuery("SELECT * FROM customer WHERE id = " + id + ";");
        Customer customer = null;
        try {
            customer = new Customer(
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
            customer.setId(result.getInt(1));
        } catch (SQLException ignored) {}
        return customer;
    }
}
