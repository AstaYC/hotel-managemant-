package com.hotelmanagement.DAO.impl;

import com.hotelmanagement.DAO.Interfaces.CustomerDAO;
import com.hotelmanagement.Models.Customer;
import com.hotelmanagement.Util.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public void insertCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO customer (name, email, phone) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhone());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Customer getCustomerById(int id) throws SQLException {
        String query = "SELECT * FROM customer WHERE id = ?";
        Customer customer = null;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
            }
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        String query = "SELECT * FROM customer";
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                ));
            }
        }
        return customers;
    }

    @Override
    public void deleteCustomer(int id) throws SQLException {
        String query = "DELETE FROM customer WHERE id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public Customer getCustomerByEmail(String email) throws SQLException {
        String query = "SELECT * FROM customer WHERE email = ?";
        Customer customer = null;
        try (Connection connection = DatabaseConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
            }
        }
        return customer;
    }
}
