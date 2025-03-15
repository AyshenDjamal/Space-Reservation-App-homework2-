package org.example;
import java.util.HashMap;

public class ReservationService {
    public static HashMap<Integer, Reservation> reservations = new HashMap<>();


    public void viewSpaces() {
        if (WorkSpaceService.coworkingSpaces.isEmpty()) {
            System.out.println("No coworking spaces are available.");
        } else {
            System.out.println("-------------List of Coworking Spaces----------");
            WorkSpaceService.coworkingSpaces.values().stream()
                    .filter(CoworkingSpace::getIsAvailable)
                    .forEach(System.out::println);
        }
        System.out.println("\nSelect '1' to go back to the Customer Menu");
        System.out.print("Enter your choice: ");
        int opt = Main.input.nextInt();
        Main.customerMenu();
    }


    public void bookSpace() {
        System.out.println("------------Make A Reservation-------------");
        while (true) {
            System.out.print("Enter your reservation ID: ");
            int resID = Main.input.nextInt();
            Main.input.nextLine();

            try {
                if (reservations.containsKey(resID)) {
                    throw new ApplicationException("Sorry, this space ID has already been taken. \nPlease select a different space.");
                }

                System.out.print("Enter your name: ");
                String name = Main.input.nextLine();
                System.out.print("Enter reservation date: ");
                String date = Main.input.nextLine();
                System.out.print("Enter start time: ");
                String start = Main.input.nextLine();
                System.out.print("Enter end time: ");
                String end = Main.input.nextLine();

                Reservation newRes = new Reservation(resID, name, date, start, end);
                ReservationService.reservations.put(resID, newRes);


                CoworkingSpace space = WorkSpaceService.coworkingSpaces.get(resID);
                space.setIsAvailable(false);
                WorkSpaceService.coworkingSpaces.put(resID, space);

                System.out.println("-------------------------------------------------------");
                System.out.println("Reservation accepted! Space " + resID + "  has been booked for you.");
                System.out.println("\nSelect '1' to go back to the Customer Menu");
                System.out.print("Enter your choice: ");
                int opt = Main.input.nextInt();
                Main.customerMenu();
                break;

            } catch (ApplicationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


    public void myBookings() {
        System.out.println("------------Display My Bookings-------------");
        if (reservations.isEmpty()) {
            System.out.println("You don't have a booking. ");
        } else {
            reservations.values().forEach(System.out::println);
        }
        System.out.println("\nSelect '1' to go back to the Customer Menu");
        System.out.print("Enter your choice: ");
        int opt = Main.input.nextInt();
        Main.customerMenu();
    }


    public void cancelBooking() {
        System.out.println("-----------Cancel Your Booking-----------");
        if (reservations.isEmpty()) {
            System.out.println("You don't have a booking. \n");
            System.out.println("\nSelect '1' to go back to the Customer Menu");
            System.out.print("Enter your choice: ");
            int opt = Main.input.nextInt();
            Main.customerMenu();
        }

        while (true) {
            System.out.print("Enter your reservation ID:");
            int canID = Main.input.nextInt();

            try {
                if (!reservations.containsKey(canID)) {
                    System.out.println("\n------------------------------");
                    throw new ApplicationException("Enter correct booking ID. \n");
                }

                reservations.remove(canID);
                System.out.println("------------------------------");
                System.out.println("Your booking was successfully canceled!");


                CoworkingSpace space = WorkSpaceService.coworkingSpaces.get(canID);
                space.setIsAvailable(true);
                WorkSpaceService.coworkingSpaces.put(canID, space);

                System.out.println("\nSelect '1' to go back to the Customer Menu");
                System.out.print("Enter your choice: ");
                int opt = Main.input.nextInt();
                Main.customerMenu();
                break;

            } catch (ApplicationException e) {
                System.out.println("Error, Please try again: " + e.getMessage());
            }
        }
    }
}


