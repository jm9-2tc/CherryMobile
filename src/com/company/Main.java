package com.company;

import com.company.classes.DatabaseConnector;
import com.company.classes.contracts.PostPaidContractTest;

public class Main {

    public static void main(String[] args) {
        DatabaseConnector db = new DatabaseConnector();

        db.addIndividualClient();
    }
}
