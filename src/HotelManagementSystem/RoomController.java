package HotelManagementSystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RoomController {

    public static void AddNewRoom(ArrayList<Room> rooms, Scanner scanner) {
        try {
            System.out.println("Enter floor(int): ");
            int floor = scanner.nextInt();
            if (floor < 0) {
                throw new InvalidInputException("Floor cannot be negative!");
            }

            System.out.println("Enter capacity (int): ");
            int capacity = scanner.nextInt();
            if (capacity < 1) {
                throw new InvalidInputException("Capacity must be at least 1!");
            }

            scanner.nextLine();
            System.out.println("Enter type (String): ");
            String type = scanner.nextLine();
            System.out.println("Enter description (String): ");
            String description = scanner.nextLine();
            System.out.println("Enter price (double): ");
            double price = scanner.nextDouble();
            if (price <= 0) {
                throw new InvalidInputException("Price must be greater than 0!");
            }

            Room room = new Room(rooms.size(), floor, capacity, type, description, price);
            rooms.add(room);
            System.out.println("Room added successfully!");
            System.out.println();

        } catch (InvalidInputException e) {
            System.out.println(" Error: " + e.getMessage());
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input! Please enter correct data type.");
            scanner.nextLine();
        }
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
        try {
            if (rooms.isEmpty()) {
                throw new RoomNotFoundException("No rooms available to edit!");
            }

            System.out.println("Enter room id (Type -1 to show all rooms): ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (id == -1) {
                showAllRooms(rooms);
                System.out.println("Enter room id: ");
                id = scanner.nextInt();
                scanner.nextLine();
            }

            Room room = null;
            try {
                room = getRoomById(id, rooms);
            } catch (RoomNotFoundException e) {
                System.out.println(" " + e.getMessage());
                return;
            }

            System.out.println("Enter floor(int) (Type -1 to keep it): ");
            int floor = scanner.nextInt();
            scanner.nextLine();
            if (floor == -1) floor = room.getFloor();

            System.out.println("Enter capacity (int) (Type -1 to keep it): ");
            int capacity = scanner.nextInt();
            scanner.nextLine();
            if (capacity == -1) capacity = room.getCapacity();

            System.out.println("Enter room type (String) (Type -1 to keep it): ");
            String type = scanner.nextLine();
            if (type.equals("-1")) type = room.getType();

            System.out.println("Enter room description (String) (Type -1 to keep it): ");
            String description = scanner.nextLine();
            if (description.equals("-1")) description = room.getDescription();

            System.out.println("Enter room price (double) (Type -1 to keep it): ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            if (price == -1) price = room.getPrice();

            room.setFloor(floor);
            room.setCapacity(capacity);
            room.setType(type);
            room.setDescription(description);
            room.setPrice(price);

            System.out.println("Room edited successfully!");

        } catch (RoomNotFoundException e) {
            System.out.println(" " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input! Please enter correct data type.");
            scanner.nextLine();
        }
    }

    public static Room getRoomById(int id, ArrayList<Room> rooms) throws RoomNotFoundException {
        for (Room room : rooms) {
            if (room.getId() == id) {
                return room;
            }
        }
        throw new RoomNotFoundException("Room with ID " + id + " not found!");
    }
}
