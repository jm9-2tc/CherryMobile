package com.company.data;

import java.sql.*;

public class DatabaseManager {
    private static final String url = "jdbc:sqlite:db.sqlite3";
    private static final DatabaseCreator creator = new DatabaseCreator();

    private Connection connection;

    public DatabaseManager() {
        try {
            Class.forName("org.sqlite.JDBC");

            this.connection = DriverManager.getConnection(url);

            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[] {"TABLE"});

            if (!tables.next()) {
                creator.create(connection.createStatement());
            }
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            stopApplication("cannot connect to the database.");
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ignored) {
            System.out.println("cannot close database connection.");
            System.exit(64);
        }
    }

    public ResultSet executeQuery(String sql) {
        ResultSet resultSet = null;

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException exception) {
            System.out.println("cannot execute SQL: '" + sql + "'.");
            exception.printStackTrace();
            System.exit(64);
        }
        return resultSet;
    }

    public void execute(String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException exception) {
            exception.printStackTrace();
            stopApplication("cannot execute SQL: '" + sql + "'.");
        }
    }

    private void stopApplication(String reason) {
        System.out.println(reason);
        System.exit(64);
    }
}
