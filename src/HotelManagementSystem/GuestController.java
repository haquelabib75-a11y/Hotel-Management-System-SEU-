import java.util.ArrayList;
import java.util.Scanner;

public class GuestsController {
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
        for (Guest guest : guests) {
            if (guest.getname().equals(name)) {
                guest.print();
                System.out.println();
            }
        }
    }
    public static void editGuest(ArrayList<Guest> guests, Scanner scanner) {
        System.out.println("Enter id (int):\n-1 to search by name ");
        int id = scanner.nextInt();
        if (id == -1) {
            searchGuestByName(guests, scanner);
            System.out.println("Enter id (int): ");
            id = scanner.nextInt();

        }
        Guest guest = guests.get(id);
        System.out.println("Enter Name:\n-1 to keep it ");
        String name = scanner.next();
        if (name.equals("-1"))name = guest.getname();
        System.out.println("Enter Email:\n-1 to keep it ");
        String email = scanner.next();
        if (email.equals("-1"))email = guest.getEmail();
        System.out.println("Enter Discount(int):\n-1 to keep it ");
        int discount = scanner.nextInt();
        if (discount == -1) discount = guest.getDiscount();

        guest.setName(name);
        guest.setEmail(email);
        guest.setDiscount(discount);
        guest.setId(id);
    }
}
