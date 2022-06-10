package com.company.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreator {
    private static final String sqlCreatorFilePath = "src\\sql\\dbcreator.sql";
    private static final String createDatabaseSql;

    static {
        String sql = "";
        try {
            byte[] fileBytes = new FileInputStream(sqlCreatorFilePath).readAllBytes();
            sql = new String(fileBytes);
        } catch (IOException ignored) {}

        createDatabaseSql = sql;
    }

    public void create(Statement statement) throws SQLException {
        statement.execute(createDatabaseSql);
    }
}
