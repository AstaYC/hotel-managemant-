import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hotel.validationInput;
import hotel.Reservation;


public class Hotel {
    private List<Reservation> reservations = new ArrayList<Reservation>();
    private int nextReservationId = 1 ;

    public void creatReservation (Scanner scanner){
        System.out.println("Welcome to the Hotel Management System");
        System.out.println("Please enter the following details to create a reservation:");
        validationInput validate = new validationInput();

        String customerName;
        while(true){
             System.out.println("Customer Name: ");
             customerName = scanner.nextLine();
             if (validate.custumerValidation(customerName)){
                 break;
             }else{
                 System.out.println("Invalid Customer Name");
         }
         }

        String roomType;
        while(true){
            System.out.println("Room Type : (please write it with a correct word 'Single , Double , Suite')" );
            roomType = scanner.nextLine();
            if (validate.roomTypeValidation(roomType)){
                break;
            }else{
                System.out.println("Invalid Room Type");
            }
        }

        String checkInDateStr;
        while(true){
            System.out.println(" checkInDate (YYYY-MM-DD) : ") ;
            checkInDateStr = scanner.nextLine();

            if(validate.checkDateValidation(checkInDateStr)){
                break;
            }else{
                System.out.println("Invalid CheckIn Date");
            }
        }
        LocalDate checkInDate = LocalDate.parse(checkInDateStr);

        String checkOutDateStr ;

        while(true){
            System.out.println(" checkOutDate (YYYY-MM-DD) : ") ;
            checkOutDateStr = scanner.nextLine();
            if(validate.checkDateValidation(checkOutDateStr)){
                break;
            }else {
                System.out.println("Invalid CheckOut Date");
            }
        }

        LocalDate checkOutDate = LocalDate.parse(checkOutDateStr);

        if (isRoomAvailable(roomType, checkInDate, checkOutDate)) {

            Reservation newReservation = new Reservation(nextReservationId++ , customerName , roomType , checkInDate.toString(), checkOutDate.toString());
            reservations.add(newReservation);
            System.out.println("New Reservation added : " + newReservation);
        }else{
            System.out.println("Room Not Available in this date");
            return;
        }


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
            reservation.setCheckInDate(LocalDate.parse(newCheckIn));
        }

        System.out.println("Enter new checkout date  (or press Enter to keep) " + reservation.getCheckOutDate());
        String newCheckOut = scanner.nextLine();

        if (!newCheckOut.isEmpty()){
            reservation.setCheckOutDate(LocalDate.parse(newCheckOut));
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

    public boolean isRoomAvailable(String roomType , LocalDate checkInDate , LocalDate checkOutDate){
        for (Reservation reservation : reservations){
            if (reservation.getRoomType().equalsIgnoreCase(roomType) && checkInDate.isBefore(reservation.getCheckOutDate()) && checkOutDate.isAfter(reservation.getCheckInDate())) {
                return false ;
            }
        }
        return true ;
    }




    public  getClient(String checkInDateStr , String checkOutDateStr){
        LocalDate checkInDate = LocalDate.parse(checkInDateStr);
        LocalDate checkOutDate = LocalDate.parse(checkOutDateStr);

        ArrayList<String> listCustomer = new ArrayList<>();

        for (Reservation reservation : reservations){
              if (checkInDate.isAfter(reservation.getCheckInDate()) && checkOutDate.isBefore(reservation.getCheckOutDate())) {
                  listCustomer.add(reservation.getCustomerName());
              }
        }

        System.out.println(listCustomer);
    }

    public void testGit (){

    }


}
