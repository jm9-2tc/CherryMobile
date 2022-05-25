package com.company.data;

import java.sql.*;

public class DatabaseManager {
    private static final String url = "jdbc:sqlite:db.sqlite3";
    private static final DatabaseCreator creator = new DatabaseCreator();

    private Connection connection;
    private Statement statement;

    public DatabaseManager() {
        try {
            Class.forName("org.sqlite.JDBC");

            this.connection = DriverManager.getConnection(url);
            this.statement = connection.createStatement();

            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[] {"TABLE"});

            if (!tables.next()) {
                creator.create(statement);
            }
        } catch (SQLException | ClassNotFoundException ignored) {
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

    public ResultSet executeQuery(String sql) {
        try {
            statement.executeQuery(sql);
        } catch (SQLException ignored) {
            System.out.println("cannot execute SQL: '" + sql + "'.");
            ignored.printStackTrace();
            System.exit(64);
        }
        return null;
    }

    public void execute(String sql) {
        try {
            statement.execute(sql);
        } catch (SQLException ignored) {
            ignored.printStackTrace();
            stopApplication("cannot execute SQL: '" + sql + "'.");
        }
    }

    private void stopApplication(String reason) {
        System.out.println(reason);
        System.exit(64);
    }
}
