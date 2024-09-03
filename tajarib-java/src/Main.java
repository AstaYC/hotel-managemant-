import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Hotel hotel = new Hotel();
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
                    hotel.creatReservation(scanner);
                    break;

                case 2:
                    hotel.modifyReservation(scanner);
                    break;

                case 3:
                    hotel.canselReservation(scanner);
                    break;

                case 4:
                    hotel.listReservations();
                    break;

                case 5:
                    System.out.println("Thank you for using Hotel Management");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }

        }

    }
}