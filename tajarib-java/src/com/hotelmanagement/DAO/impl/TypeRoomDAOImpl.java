package com.hotelmanagement.DAO.impl;

import com.hotelmanagement.Models.TypeRoom;
import com.hotelmanagement.DAO.Interfaces.TypeRoomDAO;
import com.hotelmanagement.Util.DatabaseConnectionManager;
import java.sql.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeRoomDAOImpl implements TypeRoomDAO {

    @Override
    public List<TypeRoom> getAllTypeRooms() throws SQLException {
        String query = "select * from typeroom";
        List<TypeRoom> typeRooms = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                typeRooms.add(new TypeRoom(
                        resultSet.getInt("id"),
                        resultSet.getString("type")
                ));
            }
        }
        return typeRooms;
    }


    @Override
    public TypeRoom getTypeRoomId(int id) throws SQLException {
        String query = "select * from typeroom where id = ?";
        TypeRoom typeRoom = null;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                typeRoom = new TypeRoom(
                        resultSet.getInt("id"),
                        resultSet.getString("type")
                );
            }
        }
        return typeRoom;
    }

    public TypeRoom getTypeRoomByType(String Type) throws SQLException{
        String query = "select * from typeroom where type = ?";
        TypeRoom typeRoom = null;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, Type);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                typeRoom = new TypeRoom(
                        resultSet.getInt("id"),
                        resultSet.getString("type")
                );
            }
        }
        return typeRoom;
    }
}

