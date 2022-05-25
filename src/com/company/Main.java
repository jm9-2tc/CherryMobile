package com.company;

import com.company.controllers.CustomerController;
import com.company.data.DatabaseManager;
import com.company.model.customer.IndividualCustomer;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        CustomerController customerController = new CustomerController(db);

        IndividualCustomer customer1 = new IndividualCustomer(
                "+48 123 123 123",
                "email@gmail.com",
                "abcd1234",
                "97495682847",
                new Date(2000, Calendar.JUNE, 3)
        );

        customerController.saveCustomer(customer1);

        db.close();
    }
}
