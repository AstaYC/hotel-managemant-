package com.hotelmanagement.Controllers;

import com.hotelmanagement.DAO.Interfaces.CustomerDAO;
import com.hotelmanagement.DAO.Interfaces.ReservationDAO;
import com.hotelmanagement.DAO.Interfaces.RoomDAO;
import com.hotelmanagement.DAO.Interfaces.TypeRoomDAO;
import com.hotelmanagement.DAO.impl.CustomerDAOImpl;
import com.hotelmanagement.DAO.impl.ReservationDAOImpl;
import com.hotelmanagement.DAO.impl.RoomDAOImpl;
import com.hotelmanagement.DAO.impl.TypeRoomDAOImpl;
import com.hotelmanagement.Models.Customer;
import com.hotelmanagement.Models.Reservation;
import com.hotelmanagement.Models.Room;
import com.hotelmanagement.Models.TypeRoom;
import com.hotelmanagement.Util.InputValidation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationController {
    private final CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    private final ReservationDAOImpl reservationDAO = new ReservationDAOImpl();
    private final RoomDAO roomDAO = new RoomDAOImpl();
    private final InputValidation inputValidation = new InputValidation();

    // Method to create a reservation
    public void createReservation() {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Collect customer details
        System.out.println("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.println("Enter customer email: ");
        String email = scanner.nextLine();
        System.out.println("Enter customer phone: ");
        String phone = scanner.nextLine();

        CustomerDAOImpl customer = new CustomerDAOImpl();
        Customer exists;
        try {
            exists = customer.getCustomerByEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int customerId ;
        if (exists != null) {
             customerId = exists.getId();
        }
        TypeRoomDAOImpl room = new TypeRoomDAOImpl();
        List<TypeRoom> RoomType = new ArrayList<>();
        RoomType = room.getAllTypeRooms();
        StringBuilder roomTypesDisplay = new StringBuilder();
        for (int i = 0; i < RoomType.size(); i++) {
            TypeRoom roomType = RoomType.get(i);
            roomTypesDisplay.append(roomType.getType());
            if (i < RoomType.size() - 1) {
                roomTypesDisplay.append(" - ");
            }
        }

        System.out.println("This is the Room Types existing Hotel , Please choose a one");
        System.out.printf(roomTypesDisplay.toString());

        String roomType;
        while(true){
            System.out.println("Enter room type: ");
            roomType = scanner.nextLine();
            if(inputValidation.roomTypeValidation(roomType)){
                break;
            }else{
                System.out.println("Types no existing in our hotel");
            }
        }

        System.out.println("Enter check-in date (YYYY-MM-DD): ");
        String checkInDate = scanner.nextLine();
        System.out.println("Enter check-out date (YYYY-MM-DD): ");
        String checkOutDate = scanner.nextLine();

        TypeRoomDAOImpl TypeRoomDAO = new TypeRoomDAOImpl();
        TypeRoom TypeRoom = TypeRoomDAO.getTypeRoomByType(roomType);

        Integer TypeRoomId = TypeRoom.getId();

        RoomDAOImpl RoomDAO = new RoomDAOImpl();
        List<Reservation> rooms = RoomDAO.getAllRoomsByTypeId(TypeRoomId);


        // Step 6: Create the reservation object
        Reservation reservation = new Reservation(customerId, room.getId(), checkInDate, checkOutDate);

        // Step 7: Insert the reservation into the database
        boolean success = reservationDAO.insert(reservation);
        if (success) {
            System.out.println("Reservation created successfully!");
        } else {
            System.out.println("Failed to create reservation.");
        }
    }

    // Optional: Method to modify a reservation
    public void modifyReservation() {
        // Implement modification logic similar to createReservation
    }

    // Optional: Method to cancel a reservation
    public void cancelReservation() {
        // Implement cancellation logic based on the reservation ID
    }
}
