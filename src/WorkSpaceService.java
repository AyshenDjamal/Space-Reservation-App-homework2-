import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkSpaceService {
    public  ArrayList<CoworkingSpaces> coworkingSpaces = new ArrayList<>();
    public ArrayList<Reservation> reservations = new ArrayList<>();

    public WorkSpaceService () {
        coworkingSpaces.add(new CoworkingSpaces(1, "Open", 12.4, true));
        coworkingSpaces.add(new CoworkingSpaces(2, "Open", 12.4, true));
        coworkingSpaces.add(new CoworkingSpaces(3, "Open", 12.4, true));
    }

    public void addSpace() throws ApplicationException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Space ID: ");
        int id = input.nextInt();

        boolean status = true;

        for (CoworkingSpaces it : coworkingSpaces) {
            if (it.getSpaceID() == id) {
                throw new ApplicationException("The ID already exists, please enter a new ID");
                addSpace();

                /*status = false;
                break;*/
            }
        }

        if (status) {

            System.out.print("Enter Space Type (open/private): ");
            String spaceType = input.next();

            System.out.print("Enter Price: ");
            double price = input.nextDouble();

            System.out.println("Is this space available? (true/false)");
            System.out.print("Enter your choice: ");
            boolean isAvailable = input.nextBoolean();


            CoworkingSpaces newSpace = new CoworkingSpaces(id, spaceType, price, isAvailable);
            coworkingSpaces.add(newSpace);


            System.out.println("----------------------------------");
            System.out.println("New coworking space added successfully!\n");
            System.out.println("Go back to Admin Menu or add another space? (back/add)");
            System.out.print("Enter your choice: ");
            String answer = input.next();

            if (answer.equalsIgnoreCase("Add")) {
                addSpace();
            } else {
                adminMenu();.
            }
        /*} else {
            System.out.println("The ID already exists, please enter a new ID");
            addSpace();
        }*/
        }


        public void removeSpace () {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the Space ID to be removed: ");
            int id = input.nextInt();

            boolean status = false;

            for (CoworkingSpaces space : coworkingSpaces) {
                if (space.getSpaceID() == id) {
                    status = true;
                    break;
                }
            }
            if (status) {
                for (CoworkingSpaces space : coworkingSpaces) {
                    if (id == space.getSpaceID()) {
                        if (space.getIsAvailable()) {
                            coworkingSpaces.remove(space);
                            System.out.println("----------------------------");
                            System.out.println("Space removed successfully!");
                            break;
                        } else {
                            System.out.println("-----------------------------------------");
                            System.out.println("This space is booked and cannot be removed ");
                            break;
                        }
                    }
                }
                System.out.println("\nSelect '1' to go back to the Admin Menu or '2' to remove a space. (1/2)");
                System.out.print("Enter your choice: ");
                int num = input.nextInt();

                if (num == 2) {
                    removeSpace();
                } else {
                    adminMenu();
                }

            } else {
                System.out.println("\nEnter the correct space ID");
                removeSpace();
            }
        }

        public void viewAllBookings () {
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


        public void bookSpace () {
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


        public void viewSpaces () {
            Scanner input = new Scanner(System.in);
            if (coworkingSpaces.isEmpty()) {
                System.out.println("No coworking spaces are available.");
            } else {
                System.out.println("-------------List of Coworking Spaces----------");
                for (CoworkingSpaces it : coworkingSpaces) {
                    if (it.getIsAvailable()) {
                        System.out.println("Space ID: " + it.getSpaceID() +
                                " | Space Type: " + it.getSpaceType() +
                                " | Price Per Hour: " + it.getPricePerHour() +
                                " | Status: " + (it.getIsAvailable() ? "Available" : "Not Available"));
                    }
                }
            }
            System.out.println("\nSelect '1' to go back to the Customer Menu");
            System.out.print("Enter your choice: ");
            int opt = input.nextInt();
            customerMenu();
        }
    }

    public void saveSpaces(){
        File file = new File("spaces.dat");
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
            out.writeObject(coworkingSpaces);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data: "+e.getMessage());
        }
    }

    public void loadSpaces(){
        File file = new File("spaces.dat");
            if(file.exists())
                try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
            coworkingSpaces = (ArrayList<CoworkingSpaces>) in.readObject();
            System.out.println("Spaces restored successfully");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading spaces: "+e.getMessage());
        }
    }else{
        System.out.println("Spaces haven't been saved. Restore spaces. ");
    }

}

