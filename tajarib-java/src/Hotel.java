import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hotel.Reservation;

public class Hotel {
    private List<Reservation> reservations = new ArrayList<Reservation>();
    private int nextReservationId = 1 ;

    public void creatReservation (Scanner scanner){
        System.out.println("Welcome to the Hotel Management System");
        System.out.println("Please enter the following details to create a reservation:");

        System.out.println("Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.println("Room Type : (please write it with a correct word 'Single , Double , Suite')" );
        String roomType = scanner.nextLine();

        System.out.println(" checkInDate (YYYY-MM-DD) : ") ;
        String checkInDate = scanner.nextLine();

        System.out.println(" checkOutDate (YYYY-MM-DD) : ") ;
        String checkOutDate = scanner.nextLine();

        Reservation newReservation = new Reservation(nextReservationId++ , customerName , roomType , checkInDate , checkOutDate);
        reservations.add(newReservation);
        System.out.println("New Reservation added : " + newReservation);
    }

    public void TestingResult (){
        for (Reservation reservation : reservations){
            System.out.println(reservation);        }
    }

    public void listReservations (){
        if(reservations.isEmpty()){
            System.out.println("No Reservation found");
            return;
        }

        for(int i = 0 ; i < reservations.size() ; i++){
            System.out.println((i+1) + " . " + reservations.get(i));
        }

    }

    public void modifyReservation(Scanner scanner){
        listReservations();
        if(reservations.isEmpty()){
            System.out.println("No Reservation found");
            return;
        }
        System.out.println("Enter the number of the reservation you want to modify: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index <= 0 || index > reservations.size()){
            System.out.println("Invalid reservation number");
            return;
        }

        Reservation reservation = reservations.get(index-1);
        System.out.println("Modifying Reservation : " + reservation);

        System.out.println("Enter new room type (or press Enter to keep)" + reservation.getRoomType());
        String newRoomType = scanner.nextLine();

        if (!newRoomType.isEmpty()){
            reservation.setRoomType(newRoomType);
        }

        System.out.println("Enter new checkin date  (or press Enter to keep) " + reservation.getCheckInDate());
        String newCheckIn = scanner.nextLine();

        if (!newCheckIn.isEmpty()){
            reservation.setCheckInDate(newCheckIn);
        }

        System.out.println("Enter new checkout date  (or press Enter to keep) " + reservation.getCheckOutDate());
        String newCheckOut = scanner.nextLine();

        if (!newCheckOut.isEmpty()){
            reservation.setCheckOutDate(newCheckOut);
        }

        System.out.println("Reservation Modified successfully");
    }

    public void canselReservation(Scanner scanner){
        listReservations();
        if(reservations.isEmpty()){
            System.out.println("No Reservation found");
            return;
        }

        System.out.println("Enter the number of the reservation you want to cancel: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index <= 0 || index > reservations.size()){
            System.out.println("Invalid reservation number");
            return;
        }

        Reservation reservation = reservations.get(index-1);
        System.out.println("Canceling Reservation : " + reservation);
        System.out.println("Are you sure de cansel this reservation ? (Y/N)");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("Y")){
            Reservation cancelledReservation = reservations.remove(index -1);
            System.out.println("Reservation cancelled successfully: " + cancelledReservation);
        }

        else {
            System.out.println("Back to Home");
            return;
        }

    }
}
