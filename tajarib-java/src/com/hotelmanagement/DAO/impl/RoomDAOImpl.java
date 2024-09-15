package com.hotelmanagement.DAO.impl;

import com.hotelmanagement.DAO.Interfaces.RoomDAO;
import com.hotelmanagement.Models.Room;
import com.hotelmanagement.Util.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {


    @Override
    public Room getRoomById(int id) throws SQLException {
        String query = "SELECT * FROM room WHERE id = ?";
        Room room = null;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                room = new Room(
                        resultSet.getInt("id"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("type_room_id")
                );
            }
        }
        return room;
    }

    @Override
    public List<Room> getAllRooms() throws SQLException {
        String query = "SELECT * FROM room";
        List<Room> rooms = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                rooms.add(new Room(
                        resultSet.getInt("id"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("type_room_id")
                ));
            }
        }
        return rooms;
    }

    @Override
    public void updateRoom(Room room) throws SQLException {
        String query = "UPDATE room SET price = ?, type_room_id = ? WHERE id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, room.getPrice());
            preparedStatement.setInt(2, room.getTypeRoomId());
            preparedStatement.setInt(3, room.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteRoom(int id) throws SQLException {
        String query = "DELETE FROM room WHERE id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
