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
    public List<Room> getAllRoomsByTypeId(int id) throws SQLException {
        String query = "SELECT * FROM room WHERE type_room_id = ?";
        List<Room> rooms = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

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
    public int getTotalNumberOfRooms() throws SQLException {
        String query = "SELECT COUNT(*) FROM room";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

}
