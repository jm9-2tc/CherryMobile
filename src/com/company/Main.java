package com.company;

import com.company.controllers.CustomerController;
import com.company.data.DatabaseManager;
import com.company.model.customer.IndividualCustomer;
import com.company.model.customer.base.Customer;

import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        CustomerController customerController = new CustomerController(db);

        IndividualCustomer customer1 = new IndividualCustomer(
                "+48 622 765 816",
                "email@abc.com",
                "qazwsx",
                "Test", "Testowski", "27439745284",
                new Date(1994, Calendar.JUNE, 3)
        );

        Customer c2 = customerController.getCustomer(12);

        //customerController.saveCustomer(customer1);

        db.close();
    }
}
