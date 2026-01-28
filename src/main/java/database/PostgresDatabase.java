package com.studentmanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDatabase implements IDatabase {
    private final String url = System.getProperty("DB_URL");
    private final String user = System.getProperty("DB_USER");
    private final String password = System.getProperty("DB_PASSWORD");

    public PostgresDatabase() {
        if (url == null || user == null || password == null ||
                url.isEmpty() || user.isEmpty() || password.isEmpty()) {
            throw new RuntimeException("❌ Missing DB_URL, DB_USER, or DB_PASSWORD from VM options");
        }
        System.out.println("✅ Loaded DB credentials from VM options");
    }

    @Override
    public Connection connect() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("❌ Database connection failed!", e);
        }
    }
}
