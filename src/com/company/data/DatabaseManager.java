package com.company.data;

import java.sql.*;

public class DatabaseManager {
    private static final String url = "jdbc:sqlite:/C:\\Users\\janma\\Desktop\\lekcje\\2021_2022\\i20\\2\\workspace\\CherryMobile\\src\\sql\\dbcreator.sql";
    private static final DatabaseCreator creator = new DatabaseCreator();

    private Connection connection;
    private Statement statement;

    public DatabaseManager() {
        try {
            this.connection = DriverManager.getConnection(url);
            this.statement = connection.createStatement();

            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[] {"TABLE"});

            if (tables.next()) {
                creator.create(statement);
            }
        } catch (SQLException ignored) {
            ignored.printStackTrace();
            stopApplication("cannot connect to the database.");
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
            stopApplication("cannot execute SQL: '" + sql + "'.");
        }
    }

    private void stopApplication(String reason) {
        System.out.println(reason);
        System.exit(64);
    }
}
