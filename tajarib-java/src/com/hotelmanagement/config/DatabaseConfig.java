package com.hotelmanagement.config;

public class DatabaseConfig {

    public static final String URL = "jdbc:postgresql://localhost:5432/hotel-management";
    public static final String USER = "postgres";
    public static final String PASSWORD = "astayoucode";

    private DatabaseConfig() {
        throw new AssertionError("Instantiating utility class.");
    }
}
