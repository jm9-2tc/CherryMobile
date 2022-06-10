package com.company;

import com.company.controllers.AddressController;
import com.company.controllers.ContractController;
import com.company.controllers.CustomerController;
import com.company.data.DatabaseManager;
import com.company.generators.InvoiceGenerator;
import com.company.model.contract.PostPaidContract;
import com.company.model.contract.PostPaidPrices;
import com.company.model.customer.Address;
import com.company.model.customer.IndividualCustomer;
import com.company.model.customer.base.Customer;

import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();

        AddressController addressController = new AddressController(db);
        ContractController contractController = new ContractController(db);
        CustomerController customerController = new CustomerController(db, addressController, contractController);

        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(db);

        Address address = new Address(
                "ul",
                "bimbrowa",
                "32A",
                "",
                "62-1732",
                "Testowice"
        );

        addressController.save(address);

        IndividualCustomer customer1 = new IndividualCustomer(
                "+48 622 765 816",
                "email@abc.com",
                "qazwsx",
                "Test", "Testowski", "27439745284",
                new Date(1994, Calendar.JUNE, 3),
                address
        );

        customerController.saveIndividual(customer1);

        //IndividualCustomer customer = customerController.loadIndividual(12);

        PostPaidContract contract1 = new PostPaidContract(
                new Date(),
                new Date(2023, Calendar.JULY, 22),
                312,
                1635,
                48908,
                new PostPaidPrices(
                        499000,
                        250,
                        1200,
                        16384,
                        2000,
                        800,
                        6
                )
        );

        PostPaidContract contract2 = new PostPaidContract(
                new Date(),
                new Date(2024, Calendar.OCTOBER, 15),
                25,
                93,
                63868,
                new PostPaidPrices(
                        245000,
                        100,
                        600,
                        8192,
                        2500,
                        1000,
                        10
                )
        );

        contractController.savePostPaid(contract1);
        contractController.savePostPaid(contract2);

        contractController.assignToCustomer(contract1, customer1);
        contractController.assignToCustomer(contract2, customer1);

        System.out.println(invoiceGenerator.generate(customer1));

        db.close();
    }
}
