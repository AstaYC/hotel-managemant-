package com.hotelmanagement.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    // Database connection details
    public static final String URL = "jdbc:postgresql://localhost:5432/hotel-management";
    public static final String USER = "postgres";
    public static final String PASSWORD = "astayoucode";

    // Singleton instance of Connection
    private static Connection connection;

    // Private constructor to prevent instantiation
    private DatabaseConfig() {
        throw new AssertionError("Instantiating utility class.");
    }

    // Method to get the Singleton instance of the connection
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (DatabaseConfig.class) {
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                }
            }
        }
        return connection;
    }
}
