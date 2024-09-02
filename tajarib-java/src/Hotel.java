import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hotel.Reservation;

public class Hotel {
    private List<Reservation> reservations = new ArrayList<Reservation>();
    private int nextReservationId = 1 ;

    public void creatReservation (String customerName , String roomType , String checkInDate , String checkOutDate){
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
    }
}
