package com.company;

import com.company.data.DatabaseManager;

public class Main {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();

        //db.addIndividualClient();

        System.out.println(db.getClient());

        db.close();
    }
}
