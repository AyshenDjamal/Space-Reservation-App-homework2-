package org.example;
import java.util.Scanner;


public class Main {
    public static Scanner input = new Scanner(System.in);
    public static WorkSpaceService spaces = new WorkSpaceService();
    public static ReservationService reserves = new ReservationService();

    public static void main(String[] args) {
        spaces.loadSpaces();
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("--------------------Main Menu--------------------");
        System.out.println("Welcome To The Coworking Space Reservation System");
        System.out.println("1. Admin Login");
        System.out.println("2. Customer Login");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                adminMenu();
                break;
            case 2:
                customerMenu();
                break;
            case 0:
                exit();
                break;
            default:
                System.out.println("Invalid choice, try again.");
                mainMenu();
        }
    }


    public static void adminMenu() {
        System.out.println("-----------Admin Menu-----------");
        System.out.println("1. Add a new coworking space");
        System.out.println("2. Remove a coworking space");
        System.out.println("3. View all reservations");
        System.out.println("0. Log out");
        System.out.print("Enter your choice: ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                spaces.addSpace();
                break;
            case 2:
                spaces.removeSpace();
                break;
            case 3:
                spaces.viewAllBookings();
                break;
            case 0:
                mainMenu();
                break;
            default:
                System.out.println("Invalid choice, try again.");
                adminMenu();
        }

    }

    public static void customerMenu() {
        System.out.println("-----------Customer Menu-------------");
        System.out.println("1. Browse available spaces");
        System.out.println("2. Make a reservation");
        System.out.println("3. View my reservations");
        System.out.println("4. Cancel a reservation");
        System.out.println("0. Log Out");
        System.out.print("Enter your choice: ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                reserves.viewSpaces();
                break;
            case 2:
                reserves.bookSpace();
                break;
            case 3:
                reserves.myBookings();
                break;
            case 4:
                reserves.cancelBooking();
                break;
            case 0:
                mainMenu();
                break;
            default:
                System.out.println("Invalid choice, try again.");
                customerMenu();
        }

    }

    public static void exit() {
        spaces.saveSpaces();
        System.out.println("Good Bye!");
        System.exit(0);
    }
}