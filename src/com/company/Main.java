package com.company;

import com.company.data.DatabaseConnector;

public class Main {

    public static void main(String[] args) {
        DatabaseConnector db = new DatabaseConnector();

        //db.addIndividualClient();

        db.addIndividualClient();

        System.out.println(db.getClient());

        db.close();
    }
}
