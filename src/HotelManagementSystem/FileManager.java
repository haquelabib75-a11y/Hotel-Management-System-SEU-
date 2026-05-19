package HotelManagementSystem;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    // ========== SAVE METHODS ==========

    public static void saveRooms(ArrayList<Room> rooms, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(rooms);
            System.out.println(" Rooms saved to " + filename);
        } catch (IOException e) {
            System.out.println(" Error saving rooms: " + e.getMessage());
        }
    }

    public static void saveGuests(ArrayList<Guest> guests, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(guests);
            System.out.println(" Guests saved to " + filename);
        } catch (IOException e) {
            System.out.println(" Error saving guests: " + e.getMessage());
        }
    }

    public static void saveEmployees(ArrayList<Employee> employees, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(employees);
            System.out.println(" Employees saved to " + filename);
        } catch (IOException e) {
            System.out.println(" Error saving employees: " + e.getMessage());
        }
    }

    public static void saveReservations(ArrayList<Reservation> reservations, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(reservations);
            System.out.println(" Reservations saved to " + filename);
        } catch (IOException e) {
            System.out.println(" Error saving reservations: " + e.getMessage());
        }
    }

    public static void saveServices(ArrayList<HotelService> services, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(services);
            System.out.println(" Services saved to " + filename);
        } catch (IOException e) {
            System.out.println(" Error saving services: " + e.getMessage());
        }
    }

    // ========== LOAD METHODS ==========

    @SuppressWarnings("unchecked")
    public static ArrayList<Room> loadRooms(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<Room>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" No saved rooms found. Starting fresh.");
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Guest> loadGuests(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<Guest>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" No saved guests found. Starting fresh.");
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Employee> loadEmployees(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" No saved employees found. Starting fresh.");
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Reservation> loadReservations(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<Reservation>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" No saved reservations found. Starting fresh.");
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<HotelService> loadServices(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<HotelService>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" No saved services found. Starting fresh.");
            return new ArrayList<>();
        }
    }

    // ========== SAVE ALL DATA ==========

    public static void saveAllData(ArrayList<Room> rooms, ArrayList<Guest> guests,
                                   ArrayList<Employee> employees, ArrayList<Reservation> reservations,
                                   ArrayList<HotelService> services) {
        saveRooms(rooms, "rooms.dat");
        saveGuests(guests, "guests.dat");
        saveEmployees(employees, "employees.dat");
        saveReservations(reservations, "reservations.dat");
        saveServices(services, "services.dat");
        System.out.println("\n All data saved successfully!");
    }

    // ========== LOAD ALL DATA ==========

    public static void loadAllData(ArrayList<Room> rooms, ArrayList<Guest> guests,
                                   ArrayList<Employee> employees, ArrayList<Reservation> reservations,
                                   ArrayList<HotelService> services) {
        rooms.clear();
        rooms.addAll(loadRooms("rooms.dat"));

        guests.clear();
        guests.addAll(loadGuests("guests.dat"));

        employees.clear();
        employees.addAll(loadEmployees("employees.dat"));

        reservations.clear();
        reservations.addAll(loadReservations("reservations.dat"));

        services.clear();
        services.addAll(loadServices("services.dat"));

        System.out.println("\n✅ All data loaded successfully!");
        System.out.println(" Current counts:");
        System.out.println("   Rooms: " + rooms.size());
        System.out.println("   Guests: " + guests.size());
        System.out.println("   Employees: " + employees.size());
        System.out.println("   Reservations: " + reservations.size());
        System.out.println("   Services: " + services.size());
    }
}