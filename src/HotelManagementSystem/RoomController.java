package HotelManagementSystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RoomController {

    public static void AddNewRoom(ArrayList<Room> rooms, Scanner scanner) {
        int floor = 0;
        int capacity = 0;
        String type = "";
        String description = "";
        double price = 0.0;

        // Get floor with validation
        while (true) {
            try {
                System.out.println("Enter floor(int): ");
                floor = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer number for floor.");
                scanner.nextLine();
            }
        }

        // Get capacity with validation
        while (true) {
            try {
                System.out.println("Enter capacity (int): ");
                capacity = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer number for capacity.");
                scanner.nextLine();
            }
        }

        // Get type (String - no validation needed)
        System.out.println("Enter type (String): ");
        type = scanner.nextLine();

        // Get description (String - no validation needed)
        System.out.println("Enter description (String): ");
        description = scanner.nextLine();

        // Get price with validation
        while (true) {
            try {
                System.out.println("Enter price (double): ");
                String priceInput = scanner.nextLine().trim();
                price = Double.parseDouble(priceInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number (e.g., 99.99 or 140)");
                System.out.println("Use dot (.) not comma for decimal places.");
            }
        }

        int id = 1000 + rooms.size();
        Room room = new Room(rooms.size(), floor, capacity, type, description, price);
        rooms.add(room);
        System.out.println("Room added successfully!");
        System.out.println();
    }

    public static void showAllRooms(ArrayList<Room> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms available!");
            return;
        }
        for (Room room : rooms) {
            System.out.println("---------------------------------");
            room.print();
            System.out.println("---------------------------------");
            System.out.println();
        }
    }

    public static void editRoom(ArrayList<Room> rooms, Scanner scanner) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms available to edit!");
            return;
        }

        int id = -1;

        // Get room id with validation
        while (true) {
            try {
                System.out.println("Enter room id (Type -1 to show all rooms): ");
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer number.");
                scanner.nextLine();
            }
        }

        if (id == -1) {
            showAllRooms(rooms);
            while (true) {
                try {
                    System.out.println("Enter room id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid integer number.");
                    scanner.nextLine();
                }
            }
        }

        // Check if room exists
        if (id >= rooms.size() || id < 0) {
            System.out.println("Room not found!");
            return;
        }

        Room room = getRoomById(id, rooms);

        // Edit floor
        System.out.println("Enter floor(int) (Type -1 to keep it): ");
        int floor = scanner.nextInt();
        scanner.nextLine();
        if (floor == -1) floor = room.getFloor();

        // Edit capacity
        System.out.println("Enter capacity (int) (Type -1 to keep it): ");
        int capacity = scanner.nextInt();
        scanner.nextLine();
        if (capacity == -1) capacity = room.getCapacity();

        // Edit type
        System.out.println("Enter room type (String) (Type -1 to keep it): ");
        String type = scanner.nextLine();
        if (type.equals("-1")) type = room.getType();

        // Edit description
        System.out.println("Enter room description (String) (Type -1 to keep it): ");
        String description = scanner.nextLine();
        if (description.equals("-1")) description = room.getDescription();

        // Edit price with validation
        double price = room.getPrice();
        while (true) {
            try {
                System.out.println("Enter room price (double) (Type -1 to keep it): ");
                String priceInput = scanner.nextLine().trim();
                if (priceInput.equals("-1")) {
                    break;
                }
                price = Double.parseDouble(priceInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number (e.g., 99.99)");
                System.out.println("Use dot (.) not comma for decimal places.");
            }
        }

        room.setFloor(floor);
        room.setCapacity(capacity);
        room.setType(type);
        room.setDescription(description);
        room.setPrice(price);

        System.out.println("Room edited successfully!");
    }

    public static Room getRoomById(int id, ArrayList<Room> rooms) {
        for (Room r : rooms) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }
}