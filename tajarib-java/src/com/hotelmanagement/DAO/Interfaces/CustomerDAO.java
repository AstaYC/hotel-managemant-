package com.hotelmanagement.DAO.Interfaces;
import com.hotelmanagement.Models.Customer;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    void insertCustomer(Customer customer) throws SQLException;
    Customer getCustomerById(int id) throws SQLException;
    void deleteCustomer(int id) throws SQLException;
    List<Customer> getAllCustomers() throws SQLException;
}
