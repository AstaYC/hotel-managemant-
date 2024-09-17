package com.hotelmanagement.Util;


import com.hotelmanagement.DAO.impl.ReservationDAOImpl;
import com.hotelmanagement.Models.Reservation;
import com.hotelmanagement.Models.Room;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvailableRoomValidation {
    private final ReservationDAOImpl Reservations = new ReservationDAOImpl();

    public List<Room> IsAvailable(List<Room> rooms, LocalDate checkIn, LocalDate checkOut) throws SQLException {
        List<Room> AvailableRooms = new ArrayList<>();
        List<Reservation> reservations = Reservations.getAllReservations();
        for (Room room : rooms) {
            boolean isAvailable = true ;
            for (Reservation reservation : reservations) {
                if (reservation.getRoomId() == room.getId()) {
                    if (checkIn.isBefore(reservation.getCheckOutDate()) && checkOut.isAfter(reservation.getCheckInDate())) {
                        isAvailable = false ;
                    }
                }
            }
            if (isAvailable) {
                AvailableRooms.add(room);
            }
        }
        return AvailableRooms ;
    }
}
