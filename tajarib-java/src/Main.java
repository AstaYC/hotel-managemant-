import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Hotel Management System");
        System.out.println("Please enter the following details to create a reservation:");

        System.out.print("Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.println("Room Type : ") ;
        String roomType = scanner.nextLine();

        System.out.println(" checkInDate (YYYY-MM-DD) : ") ;
        String checkInDate = scanner.nextLine();

        System.out.println(" checkOutDate (YYYY-MM-DD) : ") ;
        String checkOutDate = scanner.nextLine();

        hotel.creatReservation(customerName, roomType, checkInDate, checkOutDate);
        hotel.TestingResult();
    }
}