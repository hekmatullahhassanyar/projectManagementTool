package com.studentmanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class PostgresDatabase implements IDatabase {
    private final String url = "jdbc:postgresql://aws-1-ap-southeast-2.pooler.supabase.com:5432/postgres?sslmode=require";
    private final String user = "postgres.fuguftfekrakhufbmucy";
    private String password;

    public PostgresDatabase() {
        loadPassword();
    }

    private void loadPassword() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties");
            }
            Properties prop = new Properties();
            prop.load(input);
            password = prop.getProperty("db.password");
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load database password", ex);
        }
    }

    @Override
    public Connection connect() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed!", e);
        }
    }
}
