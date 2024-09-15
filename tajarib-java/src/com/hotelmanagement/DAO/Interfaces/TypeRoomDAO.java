package com.hotelmanagement.DAO.Interfaces;
import com.hotelmanagement.Models.TypeRoom;

import java.sql.SQLException;
import java.util.List;

public interface TypeRoomDAO {
    TypeRoom getTypeRoomId(int id) throws SQLException;
    List<TypeRoom> getAllTypeRooms() throws SQLException;
}
