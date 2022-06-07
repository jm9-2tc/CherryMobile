package com.company;

import com.company.controllers.AddressController;
import com.company.controllers.CustomerController;
import com.company.data.DatabaseManager;
import com.company.model.customer.Address;
import com.company.model.customer.IndividualCustomer;
import com.company.model.customer.base.Customer;

import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        AddressController addressController = new AddressController(db);
        CustomerController customerController = new CustomerController(db, addressController);

        Address address = new Address(
                "ul",
                "bimbrowa",
                "32A",
                "",
                "62-1732",
                "Testowice"
        );

        IndividualCustomer customer1 = new IndividualCustomer(
                "+48 622 765 816",
                "email@abc.com",
                "qazwsx",
                "Test", "Testowski", "27439745284",
                new Date(1994, Calendar.JUNE, 3),
                address);

        Customer c2 = customerController.loadIndividual(12);

        //customerController.saveCustomer(customer1);

        db.close();
    }
}
