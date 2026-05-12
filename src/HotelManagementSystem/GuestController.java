package HotelManagementSystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GuestController {

    public static void addNewGuest(ArrayList<Guest> guests, Scanner scanner) {
        try {
            System.out.print("Enter Guest Name: ");
            String name = scanner.next();

            System.out.print("Enter Guest Email: ");
            String email = scanner.next();

            System.out.print("Enter Guest Discount(int): ");
            int discount = scanner.nextInt();
            if (discount < 0 || discount > 100) {
                throw new InvalidInputException("Discount must be between 0 and 100!");
            }

            Guest guest = new Guest(guests.size(), name, email, discount);
            guests.add(guest);
            System.out.println("Guest added successfully!");

        } catch (InvalidInputException e) {
            System.out.println(" Error: " + e.getMessage());
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input! Discount must be a number.");
            scanner.nextLine();
        }
    }

    public static void showAllGuests(ArrayList<Guest> guests) {
        if (guests.isEmpty()) {
            System.out.println("No guests available!");
            return;
        }
        for (Guest guest : guests) {
            System.out.println("----------------");
            guest.print();
            System.out.println("---------------");
            System.out.println();
        }
    }

    public static void searchGuestByName(ArrayList<Guest> guests, Scanner scanner) {
        try {
            if (guests.isEmpty()) {
                throw new GuestNotFoundException("No guests available to search!");
            }

            System.out.print("Enter Guest Name: ");
            String name = scanner.next();
            System.out.println();

            boolean found = false;
            for (Guest guest : guests) {
                if (guest.getName().equalsIgnoreCase(name)) {
                    guest.print();
                    System.out.println();
                    found = true;
                }
            }

            if (!found) {
                throw new GuestNotFoundException("No guest found with name: " + name);
            }

        } catch (GuestNotFoundException e) {
            System.out.println(" " + e.getMessage());
        }
    }

    public static void editGuest(ArrayList<Guest> guests, Scanner scanner) {
        try {
            if (guests.isEmpty()) {
                throw new GuestNotFoundException("No guests available to edit!");
            }

            System.out.println("Enter id (int):");
            System.out.println("(-1 to search by name)");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (id == -1) {
                searchGuestByName(guests, scanner);
                System.out.println("Enter id (int): ");
                id = scanner.nextInt();
                scanner.nextLine();
            }

            if (id >= guests.size() || id < 0) {
                throw new GuestNotFoundException("Guest with ID " + id + " not found!");
            }

            Guest guest = guests.get(id);

            System.out.println("Enter Name (Type -1 to keep it): ");
            String name = scanner.next();
            if (name.equals("-1")) name = guest.getName();

            System.out.println("Enter Email (Type -1 to keep it): ");
            String email = scanner.next();
            if (email.equals("-1")) email = guest.getEmail();

            System.out.println("Enter Discount(int) (Type -1 to keep it): ");
            int discount = scanner.nextInt();
            if (discount == -1) discount = guest.getDiscount();

            guest.setName(name);
            guest.setEmail(email);
            guest.setDiscount(discount);

            System.out.println("Guest edited successfully!");

        } catch (GuestNotFoundException e) {
            System.out.println(" " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input! Please enter correct data type.");
            scanner.nextLine();
        }
    }
}