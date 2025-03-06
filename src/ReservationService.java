import java.io.*;
import java.util.ArrayList;

public class ReservationService {
    public static ArrayList<Reservation> reservations = new ArrayList<>();

    public ReservationService() {
        reservations = new ArrayList<>();
    }

    public void viewSpaces() {
        try {
            if (WorkSpaceService.coworkingSpaces.isEmpty()) {
                throw new ApplicationException("No coworking spaces are available.");
            } else {
                System.out.println("-------------List of Coworking Spaces----------");
                for (CoworkingSpaces it : WorkSpaceService.coworkingSpaces) {
                    if (it.getIsAvailable()) {
                        System.out.println("Space ID: " + it.getSpaceID() +
                                " | Space Type: " + it.getSpaceType() +
                                " | Price Per Hour: " + it.getPricePerHour() +
                                " | Status: " + (it.getIsAvailable() ? "Available" : "Not Available"));
                    }
                }
            }
        } catch (ApplicationException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\nSelect '1' to go back to the Customer Menu");
        System.out.print("Enter your choice: ");
        int opt = Main.input.nextInt();
        Main.customerMenu();
    }

    public void bookSpace() {
        System.out.println("------------Make A Reservation-------------");
        System.out.print("Enter your reservation ID: ");
        int resID = Main.input.nextInt();
        Main.input.nextLine();

        boolean status = true;

        for (Reservation it : ReservationService.reservations) {
            if (it.bookingID == resID) {
                status = false;
                break;
            }
        }
        try {
            if (status) {
                System.out.print("Enter your name: ");
                String name = Main.input.nextLine();
                System.out.print("Enter reservation date: ");
                String date = Main.input.nextLine();
                System.out.print("Enter start time: ");
                String start = Main.input.nextLine();
                System.out.print("Enter end time: ");
                String end = Main.input.nextLine();

                Reservation newRes = new Reservation(resID, name, date, start, end);
                ReservationService.reservations.add(newRes);

                for (int i = 0; i < WorkSpaceService.coworkingSpaces.size(); i++) {
                    if (WorkSpaceService.coworkingSpaces.get(i).getSpaceID() == resID) {
                        CoworkingSpaces temp = WorkSpaceService.coworkingSpaces.get(i);
                        temp.setIsAvailable(false);
                        WorkSpaceService.coworkingSpaces.set(i, temp);
                        break;
                    }
                }
                System.out.println("-------------------------------------------------------");
                System.out.println("Reservation accepted! Space " + resID + "  has been booked for you.");
                System.out.println("\nSelect '1' to go back to the Customer Menu");
                System.out.print("Enter your choice: ");
                int opt = Main.input.nextInt();
                Main.customerMenu();
            } else {
                throw new ApplicationException("Sorry, this space ID has already been taken. Please select a different space.");
            }
        } catch (ApplicationException e) {
            System.out.println("Error: " + e.getMessage());
            bookSpace();
        }
    }


    public void myBookings() {
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
        int opt = Main.input.nextInt();
        Main.customerMenu();
    }


    public void cancelBooking() {
        System.out.println("-----------Cancel Your Booking-----------");
        System.out.print("Enter your reservation ID:");
        int canID = Main.input.nextInt();

        try {
            if (reservations.isEmpty()) {
                System.out.println("------------------------------");
                throw new ApplicationException("You don't have a booking. \n");
            } else {

                boolean status = false;

                for (int i = 0; i < reservations.size(); i++) {
                    if (reservations.get(i).bookingID == canID) {
                        reservations.remove(i);
                        status = true;
                    }
                }

                for (int i = 0; i < WorkSpaceService.coworkingSpaces.size(); i++) {
                    if (WorkSpaceService.coworkingSpaces.get(i).getSpaceID() == canID) {
                        CoworkingSpaces temp = WorkSpaceService.coworkingSpaces.get(i);
                        temp.setIsAvailable(true);
                        WorkSpaceService.coworkingSpaces.set(i, temp);
                        break;
                    }
                }

                if (status) {
                    System.out.println("------------------------------");
                    System.out.println("Your booking was successfully canceled!");
                } else {
                    System.out.println("\n------------------------------");
                    throw new ApplicationException("Enter correct booking ID. \n");
                }
            }
        } catch (ApplicationException e) {
            System.out.println("Error: " + e.getMessage());
            cancelBooking();
        }
        System.out.println("\nSelect '1' to go back to the Customer Menu");
        System.out.print("Enter your choice: ");
        int opt = Main.input.nextInt();
        Main.customerMenu();
    }

}

