package com.hotelmanagement.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.hotelmanagement.config.DatabaseConfig;


    public class DatabaseConnectionManager {

        public static Connection getConnection() throws SQLException {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Database driver not found", e);
            }

            // Establish and return a connection to the database
            return DriverManager.getConnection(
                    DatabaseConfig.URL,
                    DatabaseConfig.USER,
                    DatabaseConfig.PASSWORD
            );
        }
    }

