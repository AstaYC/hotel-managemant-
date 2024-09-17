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
    import com.hotelmanagement.Util.AvailableRoomValidation;
    import com.hotelmanagement.Util.InputValidation;

    import java.sql.SQLException;
    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;


    public class ReservationController {
        private final CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        private final ReservationDAOImpl reservationDAO = new ReservationDAOImpl();
        private final RoomDAO roomDAO = new RoomDAOImpl();
        private final InputValidation inputValidation = new InputValidation();
        private final AvailableRoomValidation availableRoomValidation = new AvailableRoomValidation();

        // Method to create a reservation
        public void createReservation(Scanner scanner) throws SQLException {

            // Step 1: Collect customer details
            System.out.println("Enter customer name: ");
            String name = scanner.nextLine();
            System.out.println("Enter customer email: ");
            String email = scanner.nextLine();
            System.out.println("Enter customer phone: ");
            String phone = scanner.nextLine();

            // Check if the customer exists
            Customer exists = customerDAO.getCustomerByEmail(email);
            int customerId;
            if (exists != null) {
                customerId = exists.getId();
            } else {
                Customer newCustomer = new Customer(name, email, phone);
                customerDAO.insertCustomer(newCustomer);
                customerId = newCustomer.getId(); // Make sure this is retrieved correctly
            }

            // Display available room types
            TypeRoomDAOImpl typeroomDAOimpl = new TypeRoomDAOImpl();
            List<TypeRoom> roomTypes = typeroomDAOimpl.getAllTypeRooms();
            StringBuilder roomTypesDisplay = new StringBuilder();
            for (int i = 0; i < roomTypes.size(); i++) {
                TypeRoom roomType = roomTypes.get(i);
                roomTypesDisplay.append(roomType.getType());
                if (i < roomTypes.size() - 1) {
                    roomTypesDisplay.append(" - ");
                }
            }

            System.out.println("Room Types: " + roomTypesDisplay.toString());

            // Step 2: Get room type input from user
            String roomType;
            while (true) {
                System.out.println("Enter room type: ");
                roomType = scanner.nextLine();
                if (inputValidation.roomTypeValidation(roomType)) {
                    break;
                } else {
                    System.out.println("Invalid room type. Please try again.");
                }
            }

            // Step 3: Get check-in and check-out dates
            String checkInDateStr;
            while (true) {
                System.out.println("Enter check-in date (YYYY-MM-DD): ");
                checkInDateStr = scanner.nextLine();
                if (inputValidation.checkDateValidation(checkInDateStr)) {
                    break;
                } else {
                    System.out.println("Invalid Check-In Date.");
                }
            }
            LocalDate checkInDate = LocalDate.parse(checkInDateStr);

            String checkOutDateStr;
            while (true) {
                System.out.println("Enter check-out date (YYYY-MM-DD): ");
                checkOutDateStr = scanner.nextLine();
                if (inputValidation.checkDateValidation(checkOutDateStr)) {
                    break;
                } else {
                    System.out.println("Invalid Check-Out Date.");
                }
            }
            LocalDate checkOutDate = LocalDate.parse(checkOutDateStr);

            // Step 4: Check available rooms
            TypeRoomDAOImpl typeRoomDAO = new TypeRoomDAOImpl();
            TypeRoom selectedTypeRoom = typeRoomDAO.getTypeRoomByType(roomType);
            Integer typeRoomId = selectedTypeRoom.getId();

            List<Room> rooms = roomDAO.getAllRoomsByTypeId(typeRoomId);
            List<Room> availableRooms = availableRoomValidation.IsAvailable(rooms, checkInDate, checkOutDate);

            if (availableRooms.isEmpty()) {
                System.out.println("Sorry, no rooms are available for the selected type and dates.");
                return; // Exit method if no rooms are available
            }

            // Step 5: Display available rooms and select one
            System.out.println("Available rooms:");
            for (Room room : availableRooms) {
                System.out.println("Room ID: " + room.getId() + ", Price: $" + room.getPrice());
            }

            System.out.println("Select a room by typing its ID: ");
            int roomId = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline

            // Step 6: Insert reservation
            Reservation reservation = new Reservation(customerId, roomId, checkInDate, checkOutDate);
            reservationDAO.insertReservation(reservation);

            System.out.println("Reservation created successfully.");
        }



        public void modifyReservation(Scanner scanner) throws SQLException {
            new ReservationController().displayCustomerReservation(scanner);

            System.out.println("Enter the Reservation ID you want to modify: ");
            int reservationId = scanner.nextInt();
            scanner.nextLine();

            Reservation reservationToModify = reservationDAO.getReservationById(reservationId);
            if (reservationToModify == null) {
                System.out.println("No reservation found with the given ID.");
                return;
            }

            System.out.println("Enter the new room type you want to select: ");
            String roomType = scanner.nextLine();

            TypeRoomDAOImpl typeRoomDAO = new TypeRoomDAOImpl();
            TypeRoom selectedTypeRoom = typeRoomDAO.getTypeRoomByType(roomType);
            Integer typeRoomId = selectedTypeRoom.getId();

            List<Room> rooms = roomDAO.getAllRoomsByTypeId(typeRoomId);
            List<Room> availableRooms = availableRoomValidation.IsAvailable(rooms,
                    reservationToModify.getCheckInDate(),
                    reservationToModify.getCheckOutDate());

            if (availableRooms.isEmpty()) {
                System.out.println("Sorry, no rooms are available for the selected type and dates.");
                return;
            }

            System.out.println("Available rooms:");
            for (Room room : availableRooms) {
                System.out.println("Room ID: " + room.getId() + ", Price: $" + room.getPrice());
            }

            System.out.println("Enter the new Room ID you want to select: ");
            int newRoomId = scanner.nextInt();
            scanner.nextLine();

            reservationToModify.setRoomId(newRoomId);
            reservationDAO.updateReservation(reservationToModify);

            System.out.println("Reservation updated successfully.");
        }

































        public void displayCustomerReservation(Scanner scanner) throws SQLException {
            System.out.println("Enter your email to view your reservation: ");
            String email = scanner.nextLine();

            Customer customer = customerDAO.getCustomerByEmail(email);
            if (customer == null) {
                System.out.println("No customer found with this email.");
                return;
            }

            List<Reservation> reservations = reservationDAO.getReservationByEmail(email);
            if (reservations.isEmpty()) {
                System.out.println("No reservations found for this customer.");
            } else {
                // Step 4: Display the reservation details
                System.out.println("Reservations for customer " + customer.getName() + ":");
                for (Reservation reservation : reservations) {
                    System.out.println("Reservation ID: " + reservation.getId() +
                            ", Room ID: " + reservation.getRoomId() +
                            ", Check-In: " + reservation.getCheckInDate() +
                            ", Check-Out: " + reservation.getCheckOutDate());
                }
            }
        }

    }

