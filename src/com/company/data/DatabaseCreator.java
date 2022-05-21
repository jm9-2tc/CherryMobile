package com.company.data;

import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreator {
    private static final String createDatabaseSql = "";

    public void create(Statement statement) throws SQLException {
        statement.execute(createDatabaseSql);
    }
}
