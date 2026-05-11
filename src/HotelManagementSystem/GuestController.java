package HotelManagementSystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GuestController {
    public static void addNewGuest(ArrayList<Guest> guests, Scanner scanner) {

        System.out.print("Enter Guest Name: ");
        String name = scanner.next();

        System.out.print("Enter Guest Email: ");
        String email = scanner.next();

        System.out.print("Enter Guest Discount(int): ");
        int discount = scanner.nextInt();

        Guest guest = new Guest(guests.size(), name, email, discount);

        guests.add(guest);
    }
    public static void showAllGuests (ArrayList<Guest> guests) {
        for (Guest guest : guests) {
            System.out.println("----------------");
            guest.print();
            System.out.println("---------------");
            System.out.println();


        }
    }
    public static void searchGuestByName(ArrayList<Guest> guests, Scanner scanner) {
        System.out.print("Enter Guest Name: ");
        String name = scanner.next();
        System.out.println();

        boolean found = false;
        for (Guest guest : guests) {
            if (guest.getName().equals(name)) {
                guest.print();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No guest found with name: " + name);
        }
    }
    public static void editGuest(ArrayList<Guest> guests, Scanner scanner) {
        if (guests.isEmpty()) {
            System.out.println("No guests available to edit!");
            return;
        }

        int id = -1;  // DECLARE ID HERE - outside try-catch

        System.out.println("Enter id (int):");
        System.out.println("(-1 to search by name)");

        try {
            id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid integer number.");
            scanner.nextLine();
            return;
        }

        if (id == -1) {
            searchGuestByName(guests, scanner);
            System.out.println("Enter id (int): ");
            try {
                id = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer number.");
                scanner.nextLine();
                return;
            }
        }

        // NOW this will work because id is declared
        if (id >= guests.size() || id < 0) {
            System.out.println("Guest not found! No guest exists with ID: " + id);
            System.out.println("Available guest IDs: 0 to " + (guests.size() - 1));
            return;
        }
        Guest guest = guests.get(id);
        System.out.println("Enter Name:\nType -1 to keep it ");
        String name = scanner.next();
        if (name.equals("-1"))name = guest.getName();
        System.out.println("Enter Email:\nType -1 to keep it ");
        String email = scanner.next();
        if (email.equals("-1"))email = guest.getEmail();
        System.out.println("Enter Discount(int):\nType -1 to keep it ");
        int discount = scanner.nextInt();
        if (discount == -1) discount = guest.getDiscount();

        guest.setName(name);
        guest.setEmail(email);
        guest.setDiscount(discount);
        guest.setId(id);
    }
}
