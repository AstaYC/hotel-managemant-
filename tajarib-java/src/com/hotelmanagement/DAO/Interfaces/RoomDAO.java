package com.hotelmanagement.DAO.Interfaces;
import com.hotelmanagement.Models.Room;
import java.sql.SQLException;
import java.util.List;


public interface RoomDAO {
        Room getRoomById(int id) throws SQLException;
        int getTotalNumberOfRooms() throws SQLException;
        List<Room> getAllRoomsByTypeId(int id) throws SQLException;
}



