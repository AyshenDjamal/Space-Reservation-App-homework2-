import java.util.ArrayList;
import java.util.Scanner;

public class ReservationService {
    public  ArrayList<CoworkingSpaces> coworkingSpaces = new ArrayList<>();
    public ArrayList<Reservation> reservations = new ArrayList<>();

public ReservationService(){

}

    public void viewAllBookings() {
        Scanner input = new Scanner(System.in);
        if (reservations.isEmpty()) {
            System.out.println("------------------------------");
            System.out.println("No reservations were found.\n");
            System.out.println("Select '1' to go back to the Admin Menu");
            System.out.print("Enter your choice: ");
            int opt = input.nextInt();
            adminMenu();
        } else {
            System.out.println("-------------List Of Reservations-----------");
            for (Reservation it : reservations) {
                System.out.println("ID: " + it.bookingID +
                        " | Name: " + it.customerName +
                        " | Date: " + it.date +
                        " | Start Time: " + it.startTime +
                        " | End Time: " + it.endTime);
            }
            System.out.println("\nSelect '1' to go back to the Admin Menu");
            System.out.print("Enter your choice: ");
            int opt = input.nextInt();
            if (opt == 1) {
                adminMenu();
            }
        }

    }

    public void bookSpace(ArrayList<Reservation> reservations) {
        Scanner input = new Scanner(System.in);
        System.out.println("------------Make A Reservation-------------");
        System.out.print("Enter your reservation ID: ");
        int resID = input.nextInt();
        input.nextLine();
        boolean status = true;

        for (Reservation it : reservations) {
            if (it.bookingID == resID) {
                status = false;
                break;
            }
        }

        if (status) {
            System.out.print("Enter your name: ");
            String name = input.nextLine();
            System.out.print("Enter reservation date: ");
            String date = input.nextLine();
            System.out.print("Enter start time: ");
            String start = input.nextLine();
            System.out.print("Enter end time: ");
            String end = input.nextLine();

            Reservation newRes = new Reservation(resID, name, date, start, end);
            reservations.add(newRes);

            for (int i = 0; i < coworkingSpaces.size(); i++) {
                if (coworkingSpaces.get(i).getSpaceID() == resID) {
                    CoworkingSpaces temp = coworkingSpaces.get(i);
                    temp.setIsAvailable(false);
                    coworkingSpaces.set(i, temp);
                    break;
                }
            }
            System.out.println("-------------------------------------------------------");
            System.out.println("Reservation accepted! Space " + resID + "  has been booked for you.");
            System.out.println("\nSelect '1' to go back to the Customer Menu");
            System.out.print("Enter your choice: ");
            int opt = input.nextInt();
            customerMenu();
        } else {
            System.out.println("Sorry, this space ID has already been taken. Please select a different space.");
            bookSpace();
        }
    }


    public void myBookings() {
        Scanner input = new Scanner(System.in);
        System.out.println("------------Display My Bookings-------------");

        if (reservations.isEmpty()) {
            System.out.println("You don't have a booking. ");
        } else {
            for (Reservation it : reservations) {
                System.out.println(
                        "ID: " + it.bookingID +
                                " | Name: " + it.customerName +
                                " | Date: " + it.date +
                                " | Start Time: " + it.startTime +
                                " | End Time: " + it.endTime
                );
            }
        }
        System.out.println("\nSelect '1' to go back to the Customer Menu");
        System.out.print("Enter your choice: ");
        int opt = input.nextInt();
        customerMenu();
    }

    public void cancelBooking() {
        System.out.println("-----------Cancel Your Booking-----------");
        System.out.print("Enter your reservation ID:");
        Scanner input = new Scanner(System.in);
        int canID = input.nextInt();


        if (reservations.isEmpty()) {
            System.out.println("------------------------------");
            System.out.println("You don't have a booking. \n");
        } else {

            boolean status = false;

            for (int i = 0; i < reservations.size(); i++) {
                if (reservations.get(i).bookingID == canID) {
                    reservations.remove(i);
                    status = true;
                }
            }

            for (int i = 0; i < coworkingSpaces.size(); i++) {
                if (coworkingSpaces.get(i).getSpaceID() == canID) {
                    CoworkingSpaces temp = coworkingSpaces.get(i);
                    temp.setIsAvailable(true);
                    coworkingSpaces.set(i, temp);
                    break;
                }
            }

            if (status) {
                System.out.println("------------------------------");
                System.out.println("Your booking was successfully canceled!");
            } else {
                System.out.println("\n------------------------------");
                System.out.println("Enter correct booking ID. \n");
                cancelBooking();
            }
        }
        System.out.println("\nSelect '1' to go back to the Customer Menu");
        System.out.print("Enter your choice: ");
        int opt = input.nextInt();
        customerMenu();
    }
}
