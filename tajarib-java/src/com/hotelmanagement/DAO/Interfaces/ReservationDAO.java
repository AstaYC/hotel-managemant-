package com.hotelmanagement.DAO.Interfaces;
import com.hotelmanagement.Models.Reservation;
import java.sql.SQLException;
import java.util.List;

public interface ReservationDAO {
    void insertReservation(Reservation reservation) throws SQLException;
    void updateReservation(Reservation reservation) throws SQLException;
    void deleteReservation(int id) throws SQLException;
    Reservation getReservationById(int id) throws SQLException;
    List<Reservation> getAllReservations() throws SQLException;
}


