package com.studentmanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDatabase implements IDatabase {

    private String url;
    private String user;
    private String password;

    public PostgresDatabase() {
        // Load credentials from VM options
        this.url = System.getProperty("DB_URL");
        this.user = System.getProperty("DB_USER");
        this.password = System.getProperty("DB_PASSWORD");

        if (url == null || user == null || password == null ||
                url.isEmpty() || user.isEmpty() || password.isEmpty()) {
            throw new RuntimeException("❌ Missing DB_URL, DB_USER, or DB_PASSWORD from VM options");
        }

        System.out.println("✅ Loaded DB credentials from VM options");

        // Optional: ensure PostgreSQL driver is loaded
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("❌ PostgreSQL JDBC Driver not found. Add it to your dependencies!", e);
        }
    }

    @Override
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
