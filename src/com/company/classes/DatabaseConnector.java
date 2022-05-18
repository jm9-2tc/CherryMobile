package com.company.classes;

import java.sql.*;

public class DatabaseConnector {
    private final String url = "jdbc:sqlite:/C:\\Users\\uczen\\Documents\\CherryMobile\\db.sqlite3";

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

    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException ignored) {
            System.out.println("cannot close database connection.");
            System.exit(64);
        }
    }

    public void addIndividualClient() {
        //execute("INSERT INTO customer(id, email, phone_number) VALUES (NULL, 'abcd', '123123123');");
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO customer(id, email, phone_number) VALUES (NULL, 'abcd', '123123123');");
            stmt.execute();
            stmt.close();
        } catch (SQLException ignored) {

        }
    }

    public String getClient() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM customer;");
            ResultSet result = stmt.executeQuery();
            //ResultSet result = executeQuery("SELECT * FROM customer;");
            return result.getString(0);
        } catch (SQLException ignored) {

        }
        return null;
    }

    private ResultSet executeQuery(String sql) {
        try {
            statement.executeQuery(sql);
        } catch (SQLException ignored) {
            System.out.println("cannot execute SQL: '" + sql + "'.");
            System.exit(64);
        }
        return null;
    }

    private void execute(String sql) {
        try {
            statement.execute(sql);
        } catch (SQLException ignored) {
            System.out.println("cannot execute SQL: '" + sql + "'.");
            System.exit(64);
        }
    }
}
