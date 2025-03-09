import java.io.*;
import java.util.HashMap;

public class WorkSpaceService {
    public static HashMap<Integer, CoworkingSpaces> coworkingSpaces = new HashMap<>();

    public WorkSpaceService() {
        coworkingSpaces.put(1,new CoworkingSpaces(1, "Open", 12.4, true));
        coworkingSpaces.put(2,new CoworkingSpaces(2, "Open", 12.4, true));
        coworkingSpaces.put(3,new CoworkingSpaces(3, "Open", 12.4, true));
    }

    public static void addSpace() {
        System.out.print("Enter Space ID: ");
        int id = Main.input.nextInt();
        try {
            if (coworkingSpaces.containsKey(id)) {
                throw new ApplicationException("The ID already exists, please enter a new ID");
            }


            System.out.print("Enter Space Type (open/private): ");
            String spaceType = Main.input.next();

            System.out.print("Enter Price: ");
            double price = Main.input.nextDouble();

            System.out.println("Is this space available? (true/false)");
            System.out.print("Enter your choice: ");
            boolean isAvailable = Main.input.nextBoolean();


            CoworkingSpaces newSpace = new CoworkingSpaces(id, spaceType, price, isAvailable);
            coworkingSpaces.put(id, newSpace);


            System.out.println("----------------------------------");
            System.out.println("New coworking space added successfully!\n");
            System.out.println("Go back to Admin Menu or add another space? (back/add)");
            System.out.print("Enter your choice: ");
            String answer = Main.input.next();

            if (answer.equalsIgnoreCase("Add")) {
                addSpace();
            } else {
                Main.adminMenu();
            }

        } catch (ApplicationException e) {
            System.out.println("Error: " + e.getMessage());
            addSpace();
        }
    }


    public void removeSpace() {
        System.out.print("Enter the Space ID to be removed: ");
        int id = Main.input.nextInt();

        try {
            if (!coworkingSpaces.containsKey(id)) {
                throw new ApplicationException("Enter the correct space ID");
            }

            CoworkingSpaces space = coworkingSpaces.get(id);


        if(space.getIsAvailable()){
            coworkingSpaces.remove(id);
            System.out.println("----------------------------");
            System.out.println("Space removed successfully!");
        }else{
            System.out.println("This coworking space is booked and cannot be removed.");
        }

                    System.out.println("\nSelect '1' to go back to the Admin Menu or '2' to remove a space. (1/2)");
                    System.out.print("Enter your choice: ");
                    int num = Main.input.nextInt();

                    if (num == 2) {
                        removeSpace();
                    } else {
                        Main.adminMenu();
                    }

            } catch (ApplicationException e) {
                System.out.println("Error: " + e.getMessage());
                removeSpace();
            }
    }


    public void viewAllBookings() {
            if (ReservationService.reservations.isEmpty()) {
                System.out.println("------------------------------");
                System.out.println("No reservations were found.\n");
            } else {
                System.out.println("-------------List Of Reservations-----------");
                for (Reservation it : ReservationService.reservations.values()) {
                    System.out.println(it.toString());
                }
            }
            System.out.println("\nSelect '1' to go back to the Admin Menu");
            System.out.print("Enter your choice: ");
            int opt = Main.input.nextInt();
            if (opt == 1) {
                Main.adminMenu();
            }
    }


    public void saveSpaces() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("spaces.ser"))) {
            out.writeObject(coworkingSpaces);
            out.writeObject(ReservationService.reservations);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }


    public void loadSpaces() {
        File file = new File("spaces.ser");
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                coworkingSpaces = (HashMap<Integer, CoworkingSpaces>) in.readObject();
                ReservationService.reservations = (HashMap<Integer, Reservation>) in.readObject();
                System.out.println("Spaces restored successfully");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading spaces: " + e.getMessage());
            }
        } else {
            System.out.println("Spaces haven't been saved. Restore spaces. ");
        }

    }
}



