package com.hotelmanagement.DAO.impl;

import com.hotelmanagement.DAO.Interfaces.ReservationDAO;
import com.hotelmanagement.Models.Reservation;
import com.hotelmanagement.Util.DatabaseConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public void insertReservation(Reservation reservation) throws SQLException {
        String query = "INSERT INTO reservation (customer_id, room_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reservation.getCustomerId());
            preparedStatement.setInt(2, reservation.getRoomId());
            preparedStatement.setDate(3, Date.valueOf(reservation.getCheckInDate()));
            preparedStatement.setDate(4, Date.valueOf(reservation.getCheckOutDate()));

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Reservation getReservationById(int id) throws SQLException {
        String query = "SELECT * FROM reservation WHERE id = ?";
        Reservation reservation = null;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                reservation = new Reservation(
                        resultSet.getInt("id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("room_id"),
                        resultSet.getDate("check_in_date").toLocalDate(),
                        resultSet.getDate("check_out_date").toLocalDate()
                );
            }
        }
        return reservation;
    }

    @Override
    public List<Reservation> getAllReservations() throws SQLException {
        String query = "SELECT * FROM reservation";
        List<Reservation> reservations = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                reservations.add(new Reservation(
                        resultSet.getInt("id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("room_id"),
                        resultSet.getDate("check_in_date").toLocalDate(),
                        resultSet.getDate("check_out_date").toLocalDate()
                ));
            }
        }
        return reservations;
    }

    @Override
    public void updateReservation(Reservation reservation) throws SQLException {
        String query = "UPDATE reservation SET customer_id = ?, room_id = ?, check_in_date = ?, check_out_date = ? WHERE id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reservation.getCustomerId());
            preparedStatement.setInt(2, reservation.getRoomId());
            preparedStatement.setDate(3, Date.valueOf(reservation.getCheckInDate()));
            preparedStatement.setDate(4, Date.valueOf(reservation.getCheckOutDate()));
            preparedStatement.setInt(5, reservation.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteReservation(int id) throws SQLException {
        String query = "DELETE FROM reservation WHERE id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Reservation> getReservationByEmail(String email) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();

        String query = "SELECT r.id, r.customer_id, r.room_id, r.check_in_date, r.check_out_date " +
                "FROM reservation r " +
                "JOIN customer c " +
                "ON r.customer_id = c.id " +
                "WHERE c.email = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int reservationId = resultSet.getInt("id");
                int customerId = resultSet.getInt("customer_id");
                int roomId = resultSet.getInt("room_id");
                LocalDate checkInDate = resultSet.getDate("check_in_date").toLocalDate();
                LocalDate checkOutDate = resultSet.getDate("check_out_date").toLocalDate();

                Reservation reservation = new Reservation(reservationId, customerId, roomId, checkInDate, checkOutDate);
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching reservations by email");
        }

        return reservations;
    }

    @Override
    public int getNumberOfOccupiedRooms() throws SQLException {
        String query = "SELECT COUNT(DISTINCT room_id) FROM reservation WHERE check_in_date <= CURRENT_DATE AND check_out_date >= CURRENT_DATE";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public BigDecimal getTotalRevenue() throws SQLException {
        String query = "SELECT SUM((check_out_date - check_in_date) * r.price)" +
                " AS total_revenue " +
                "FROM reservation AS res JOIN room AS r ON res.room_id = r.id " +
                "WHERE check_in_date <= CURRENT_DATE AND check_out_date >= CURRENT_DATE;";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getBigDecimal("total_revenue");
            }
        }
        return BigDecimal.ZERO;
    }

}
