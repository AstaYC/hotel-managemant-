package com.hotelmanagement.DAO;
import com.hotelmanagement.Models.Room;
import java.sql.SQLException;
import java.util.List;


public interface RoomDAO {
        Room getRoomById(int id) throws SQLException;
        void updateRoom(Room room) throws SQLException;
        void deleteRoom(int id) throws SQLException;
        List<Room> getAllRooms() throws SQLException;
}



