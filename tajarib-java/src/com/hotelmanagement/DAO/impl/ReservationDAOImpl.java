package com.hotelmanagement.DAO.impl;

import com.hotelmanagement.DAO.ReservationDAO;
import com.hotelmanagement.Models.Reservation;
import com.hotelmanagement.Util.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public void insertReservation(Reservation reservation) throws SQLException {
        String query = "INSERT INTO reservation (id, customer_id, room_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reservation.getId());
            preparedStatement.setInt(2, reservation.getCustomerId());
            preparedStatement.setInt(3, reservation.getRoomId());
            preparedStatement.setDate(4, Date.valueOf(reservation.getCheckInDate()));
            preparedStatement.setDate(5, Date.valueOf(reservation.getCheckOutDate()));

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

    }
}
