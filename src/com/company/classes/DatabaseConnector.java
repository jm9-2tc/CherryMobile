package com.company.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private final String url = "jdbc:sqlite:/C:\\Users\\uczen\\IdeaProjects\\CherryMobile\\db.sqlite3";

    private Connection connection;
    private Statement statement;

    public DatabaseConnector() {
        try {
            this.connection = DriverManager.getConnection(url);
            this.statement = connection.createStatement();
        } catch (SQLException ignored) {
            System.out.println("cannot connect to the database.");
            System.exit(64);
        }
    }

    public void addIndividualClient() {
        try {
            statement.execute("INSERT INTO customer VALUES (NULL, 'abcd', '123123123');");
        } catch (SQLException ignored) {
            System.out.println("cannot create customer.");
        }
    }
}
