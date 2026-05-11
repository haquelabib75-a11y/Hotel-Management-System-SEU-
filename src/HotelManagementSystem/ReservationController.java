package HotelManagementSystem;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationController {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void createNewReservation(ArrayList<Guest> guests, ArrayList<Room> rooms, ArrayList<Reservation> reservations, Scanner scanner) {

        LocalDate arrivalDate = null;
        LocalDate departureDate = null;


        while (true) {
            try {
                System.out.println("Enter arrival date (yyyy-MM-dd): ");
                String arrDate = scanner.next();

                String[] parts = arrDate.split("-");
                int month = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[2]);

                if (month < 1 || month > 12) {
                    System.out.println("Invalid month! Month must be between 1 and 12.");
                    continue;
                }

                int maxDays = getMaxDaysInMonth(month);
                if (day < 1 || day > maxDays) {
                    System.out.println("Invalid day! This month has only " + maxDays + " days.");
                    continue;
                }

                arrivalDate = LocalDate.parse(arrDate, formatter);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format! Please use yyyy-MM-dd (e.g., 2025-12-16)");
            }
        }


        while (true) {
            try {
                System.out.println("Enter departure date (yyyy-MM-dd): ");
                String depDate = scanner.next();

                String[] parts = depDate.split("-");
                int month = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[2]);

                if (month < 1 || month > 12) {
                    System.out.println("Invalid month! Month must be between 1 and 12.");
                    continue;
                }

                int maxDays = getMaxDaysInMonth(month);
                if (day < 1 || day > maxDays) {
                    System.out.println("Invalid day! This month has only " + maxDays + " days.");
                    continue;
                }

                departureDate = LocalDate.parse(depDate, formatter);

                if (departureDate.isBefore(arrivalDate) || departureDate.isEqual(arrivalDate)) {
                    System.out.println("Departure date must be after arrival date!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format! Please use yyyy-MM-dd (e.g., 2025-12-18)");
            }
        }

        System.out.println("Enter guest id (int): ");
        System.out.println("-1 to search guest by name");
        int guestId = scanner.nextInt();

        if (guestId == -1) {
            GuestController.searchGuestByName(guests, scanner);
            System.out.println("Enter guest id (int): ");
            guestId = scanner.nextInt();
        }

        System.out.println("Enter room id (int): ");
        System.out.println("-1 to show all rooms");
        int roomId = scanner.nextInt();

        if (roomId == -1) {
            RoomController.showAllRooms(rooms);
            System.out.println("Enter room id (int): ");
            roomId = scanner.nextInt();
        }

        Guest guest = guests.get(guestId);
        Room room = RoomController.getRoomById(roomId, rooms);

        if (room.isReserved(arrivalDate, departureDate)) {
            System.out.println("This room is reserved!");
            return;
        } else {
            int days = Period.between(arrivalDate, departureDate).getDays();
            double sum = days * room.getPrice();
            double total = sum - (sum * guest.getDiscount() / 100.0);

            System.out.println("Total before discount = " + sum);
            System.out.println("Total after discount = " + total);
            System.out.println("Will you pay now?\n1. Yes\n2. No");

            int j = scanner.nextInt();
            String status;

            if (j == 1) {
                status = "Paid";
            } else {
                status = "Reserved";
            }

            Reservation r = new Reservation(arrivalDate, departureDate, total, status, guest, room);
            r.print();
            reservations.add(r);
            System.out.println();
        }
    }

    public static void showAllReservations(ArrayList<Reservation> reservations, Scanner scanner) {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found!");
            return;
        }
        for (Reservation r : reservations) {
            System.out.println("\n-------------------------------------");
            System.out.println("Arrival Date: " + r.getArrivalDatetoString());
            System.out.println("Departure Date: " + r.getDepartureDatetoString());
            System.out.println("Guest Name: " + r.getGuest().getName());
            System.out.println("Room id: " + r.getRoom().getId());
            System.out.println("Price: " + r.getPrice());
            System.out.println("Status: " + r.getStatus());
            System.out.println("-------------------------------------");
        }
    }

    public static void getReservationbyGuestName(ArrayList<Reservation> reservations, Scanner scanner) {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found!");
            return;
        }
        System.out.println("Enter guest name: ");
        String guestName = scanner.next();

        boolean found = false;
        for (Reservation r : reservations) {
            String name = r.getGuest().getName();
            if (guestName.equals(name)) {
                r.print();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No reservation found for guest: " + guestName);
        }
    }

    public static void getReservationbyGuestId(ArrayList<Reservation> reservations, Scanner scanner) {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found!");
            return;
        }
        System.out.println("Enter guest id (int): ");
        int guestId = scanner.nextInt();

        boolean found = false;
        for (Reservation r : reservations) {
            int id = r.getGuest().getId();
            if (guestId == id) {
                r.print();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No reservation found for guest ID: " + guestId);
        }
    }

    public static void editReservation(ArrayList<Guest> guests, ArrayList<Room> rooms, ArrayList<Reservation> reservations, Scanner scanner) {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found to edit!");
            return;
        }

        System.out.println("Enter reservation id (int): ");
        System.out.println("Type -1 to show all reservations ids");
        int id = scanner.nextInt();

        if (id == -1) {
            showAllReservations(reservations, scanner);
            System.out.println("Enter reservation id (int): ");
            id = scanner.nextInt();
        }

        if (id >= reservations.size() || id < 0) {
            System.out.println("Reservation not found! Invalid ID: " + id);
            return;
        }

        Reservation reservation = reservations.get(id);

        LocalDate arrivalDate = reservation.getArrivalDate();
        LocalDate departureDate = reservation.getDepartureDate();

        System.out.println("Enter arrival date (yyyy-MM-dd) (Type -1 to keep it): ");
        String arrDate = scanner.next();

        if (!arrDate.equals("-1")) {
            while (true) {
                try {
                    String[] parts = arrDate.split("-");
                    int month = Integer.parseInt(parts[1]);
                    int day = Integer.parseInt(parts[2]);

                    if (month < 1 || month > 12) {
                        System.out.println("Invalid month! Month must be between 1 and 12.");
                        System.out.println("Enter arrival date (yyyy-MM-dd): ");
                        arrDate = scanner.next();
                        continue;
                    }

                    int maxDays = getMaxDaysInMonth(month);
                    if (day < 1 || day > maxDays) {
                        System.out.println("Invalid day! This month has only " + maxDays + " days.");
                        System.out.println("Enter arrival date (yyyy-MM-dd): ");
                        arrDate = scanner.next();
                        continue;
                    }

                    arrivalDate = LocalDate.parse(arrDate, formatter);
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid date format! Please use yyyy-MM-dd");
                    System.out.println("Enter arrival date (yyyy-MM-dd): ");
                    arrDate = scanner.next();
                }
            }
        }

        System.out.println("Enter departure date (yyyy-MM-dd) (Type -1 to keep it): ");
        String depDate = scanner.next();

        if (!depDate.equals("-1")) {
            while (true) {
                try {
                    String[] parts = depDate.split("-");
                    int month = Integer.parseInt(parts[1]);
                    int day = Integer.parseInt(parts[2]);

                    if (month < 1 || month > 12) {
                        System.out.println("Invalid month! Month must be between 1 and 12.");
                        System.out.println("Enter departure date (yyyy-MM-dd): ");
                        depDate = scanner.next();
                        continue;
                    }

                    int maxDays = getMaxDaysInMonth(month);
                    if (day < 1 || day > maxDays) {
                        System.out.println("Invalid day! This month has only " + maxDays + " days.");
                        System.out.println("Enter departure date (yyyy-MM-dd): ");
                        depDate = scanner.next();
                        continue;
                    }

                    departureDate = LocalDate.parse(depDate, formatter);

                    if (departureDate.isBefore(arrivalDate) || departureDate.isEqual(arrivalDate)) {
                        System.out.println("Departure date must be after arrival date!");
                        System.out.println("Enter departure date (yyyy-MM-dd): ");
                        depDate = scanner.next();
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid date format! Please use yyyy-MM-dd");
                    System.out.println("Enter departure date (yyyy-MM-dd): ");
                    depDate = scanner.next();
                }
            }
        }

        System.out.println("Enter room id (int): ");
        System.out.println("Type -1 to keep it");
        System.out.println("Type -2 to show all rooms");
        int roomId = scanner.nextInt();

        if (roomId == -1) {
            roomId = reservation.getRoom().getId();
        } else if (roomId == -2) {
            RoomController.showAllRooms(rooms);
            System.out.println("Enter room id (int): ");
            roomId = scanner.nextInt();
        }

        Guest guest = reservation.getGuest();
        Room room = RoomController.getRoomById(roomId, rooms);

        if (room.isReserved(arrivalDate, departureDate)) {
            System.out.println("This room is reserved!");
            return;
        } else {
            int days = Period.between(arrivalDate, departureDate).getDays();
            double sum = days * room.getPrice();
            double total = sum - (sum * guest.getDiscount() / 100.0);

            System.out.println("Total before discount = " + sum);
            System.out.println("Total after discount = " + total);
            System.out.println("Will you pay now?\n1. Yes\n2. No");

            int j = scanner.nextInt();
            String status;

            if (j == 1) {
                status = "Paid";
            } else {
                status = "Reserved";
            }

            reservation.setArrivalDate(arrivalDate);
            reservation.setDepartureDate(departureDate);
            reservation.setRoom(room);
            reservation.setStatus(status);
            reservation.setPrice(total);

            System.out.println("Reservation updated successfully!");
            reservation.print();
            System.out.println();
        }
    }

    public static void payReservation(ArrayList<Reservation> reservations, Scanner scanner) {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found!");
            return;
        }

        System.out.println("Enter reservation id (int): ");
        System.out.println("Type -1 to show all reservations ids");
        int id = scanner.nextInt();

        if (id == -1) {
            showAllReservations(reservations, scanner);
            System.out.println("Enter reservation id (int): ");
            id = scanner.nextInt();
        }

        if (id >= reservations.size() || id < 0) {
            System.out.println("Reservation not found! Invalid ID: " + id);
            return;
        }

        Reservation reservation = reservations.get(id);

        if (reservation.getStatus().equals("Reserved")) {
            reservation.setStatus("Paid");
            System.out.println("Reservation paid successfully!");
        } else {
            System.out.println("This reservation is already paid!");
        }
    }


    private static int getMaxDaysInMonth(int month) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return 28;
            default:
                return 31;
        }
    }
}
