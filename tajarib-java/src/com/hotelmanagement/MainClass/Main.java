package com.hotelmanagement.MainClass;

import java.sql.SQLException;
import java.util.Scanner;
import com.hotelmanagement.Controllers.ReservationController;

public class Main {
    public static void main (String[] args) throws SQLException {
        ReservationController reservationController = new ReservationController();
        Scanner scanner = new Scanner(System.in);

        while(true){

            System.out.println("\n Hotel Management System ");
            System.out.println("1. Create a new reservation");
            System.out.println("2. Modify an existing reservation");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. View all reservations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    reservationController.createReservation(scanner);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}