package HotelManagementSystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.Period;

public class Main {

    private static ArrayList<Room> rooms;
    private static ArrayList<Guest> guests;
    private static ArrayList<Employee> employees;
    private static ArrayList<Reservation> reservations;
    private static ArrayList<HotelService> hotelServices;

    public static void main(String[] args) {

        rooms = new ArrayList<>();
        guests = new ArrayList<>();
        employees = new ArrayList<>();
        reservations = new ArrayList<>();
        hotelServices = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int i = 0;

        while (i != 23) {
            System.out.println("Welcome to the Hotel Management System");
            System.out.println("1. Add new room");
            System.out.println("2. Show all rooms");
            System.out.println("3. Edit room");
            System.out.println("4. Add new guest");
            System.out.println("5. Show all guest");
            System.out.println("6. Search guest by name");
            System.out.println("7. Edit guest data");
            System.out.println("8. Create new reservation");
            System.out.println("9. Show all reservations");
            System.out.println("10. Get Reservation by guest name");
            System.out.println("11. Get Reservation by id");
            System.out.println("12. Edit reservation");
            System.out.println("13. Pay for reservation");
            System.out.println("14. Add a new employee");
            System.out.println("15. Show all employees");
            System.out.println("16. Edit employee data");
            System.out.println("17. Request Hotel Service");
            System.out.println("18. Show All Services");
            System.out.println("19. Generate Final Bill");
            System.out.println("20. Exit without saving");
            System.out.println("21. Save all data to file");
            System.out.println("22. Load all data from file");
            System.out.println("23. Save and Exit");
            System.out.print("Enter your choice: ");

            while (true) {
                try {
                    i = scanner.nextInt();
                    scanner.nextLine();
                    if (i >= 1 && i <= 23) {
                        break;
                    } else {
                        System.out.println(" Please enter a number between 1 and 23!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println(" Invalid input! Please enter a NUMBER between 1 and 20.");
                    scanner.nextLine();
                }
            }

            switch (i) {
                case 1:
                    RoomController.AddNewRoom(rooms, scanner);
                    break;
                case 2:
                    RoomController.showAllRooms(rooms);
                    break;
                case 3:
                    RoomController.editRoom(rooms, scanner);
                    break;
                case 4:
                    GuestController.addNewGuest(guests, scanner);
                    break;
                case 5:
                    GuestController.showAllGuests(guests);
                    break;
                case 6:
                    GuestController.searchGuestByName(guests, scanner);
                    break;
                case 7:
                    GuestController.editGuest(guests, scanner);
                    break;
                case 8:
                    ReservationController.createNewReservation(guests, rooms, reservations, scanner);
                    break;
                case 9:
                    ReservationController.showAllReservations(reservations, scanner);
                    break;
                case 10:
                    ReservationController.getReservationbyGuestName(reservations, scanner);
                    break;
                case 11:
                    ReservationController.getReservationbyGuestId(reservations, scanner);
                    break;
                case 12:
                    ReservationController.editReservation(guests, rooms, reservations, scanner);
                    break;
                case 13:
                    ReservationController.payReservation(reservations, scanner);
                    break;
                case 14:
                    EmployeeController.addNewEmployee(employees, scanner);
                    break;
                case 15:
                    EmployeeController.showAllEmployees(employees);
                    break;
                case 16:
                    EmployeeController.editEmployeeData(employees, scanner);
                    break;
                case 17:
                    requestHotelService(scanner, hotelServices);
                    break;
                case 18:
                    showAllServices(hotelServices);
                    break;
                case 19:
                    generateFinalBill(reservations, hotelServices, scanner);
                    break;
                case 20:
                    System.out.println("Exiting Hotel Management System. Goodbye!");
                    scanner.close();
                    break;
                case 21:
                    FileManager.saveAllData(rooms, guests, employees, reservations, hotelServices);
                    break;
                case 22:
                    FileManager.loadAllData(rooms, guests, employees, reservations, hotelServices);
                    break;
                case 23:
                    System.out.println("Saving data before exit...");
                    FileManager.saveAllData(rooms, guests, employees, reservations, hotelServices);
                    System.out.println("Exiting Hotel Management System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1-23.");
            }
        }
    }

    public static void showAllServices(ArrayList<HotelService> hotelServices) {
        if (hotelServices.isEmpty()) {
            System.out.println("\n No services requested yet!\n");
            return;
        }

        System.out.println("\n═══════════════════════════════════════");
        System.out.println("         REQUESTED SERVICES             ");
        System.out.println("═══════════════════════════════════════");

        for (HotelService service : hotelServices) {
            service.displayInfo();
            System.out.println("───────────────────────────────────────");
        }
    }


    public static void requestHotelService(Scanner scanner, ArrayList<HotelService> hotelServices) {

        System.out.println("       AVAILABLE SERVICES       ");
        System.out.println(" ══════════════════════════════ ");
        System.out.println("  1. Food Delivery              ");
        System.out.println("  2. Laundry Service            ");
        System.out.println("  3. Back to Menu               ");
        System.out.println(" ══════════════════════════════ ");

        int choice = 0;
        while (true) {
            try {
                System.out.print("Choose service: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println(" Please enter 1, 2, or 3!");
                }
            } catch (InputMismatchException e) {
                System.out.println(" Invalid input! Please enter a NUMBER (1, 2, or 3).");
                scanner.nextLine();
            }
        }

        if (choice == 3) return;

        System.out.print("Enter room number: ");
        String roomNo = scanner.nextLine();

        String serviceId = "SVC" + (hotelServices.size() + 1);
        HotelService service = null;

        if (choice == 1) {
            System.out.print("Enter food item name: ");
            String food = scanner.nextLine();

            double itemPrice = 0;
            while (true) {
                try {
                    System.out.print("Enter price per item: ");
                    itemPrice = scanner.nextDouble();
                    if (itemPrice > 0) {
                        break;
                    } else {
                        System.out.println("  Price must be greater than 0!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("  Invalid input! Please enter a valid number (e.g., 10.99)");
                    scanner.nextLine();
                }
            }

            int qty = 0;
            while (true) {
                try {
                    System.out.print("Enter quantity: ");
                    qty = scanner.nextInt();
                    if (qty > 0) {
                        break;
                    } else {
                        System.out.println("  Quantity must be at least 1!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("  Invalid input! Please enter a valid NUMBER.");
                    scanner.nextLine();
                }
            }

            service = new FoodDelivery(serviceId, roomNo, food, qty, itemPrice);

        } else if (choice == 2) {
            int items = 0;
            while (true) {
                try {
                    System.out.print("Enter number of clothing items: ");
                    items = scanner.nextInt();
                    if (items > 0) {
                        break;
                    } else {
                        System.out.println("  Number of items must be at least 1!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("  Invalid input! Please enter a valid NUMBER.");
                    scanner.nextLine();
                }
            }

            double pricePerItem = 0;
            while (true) {
                try {
                    System.out.print("Enter price per item: $");
                    pricePerItem = scanner.nextDouble();
                    if (pricePerItem > 0) {
                        break;
                    } else {
                        System.out.println("  Price must be greater than 0!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("  Invalid input! Please enter a valid number (e.g., 5.99)");
                    scanner.nextLine();
                }
            }

            service = new Laundry(serviceId, roomNo, items, pricePerItem);
        }

        if (service != null) {
            hotelServices.add(service);
            System.out.println("\n  Service requested successfully!");
            service.execute();
        }
    }


    public static void generateFinalBill(ArrayList<Reservation> reservations,
                                         ArrayList<HotelService> hotelServices,
                                         Scanner scanner) {
        if (reservations.isEmpty()) {
            System.out.println("\n No reservations found! Create a reservation first (Option 8).\n");
            return;
        }

        // Show all reservations first
        System.out.println("\n===== ALL RESERVATIONS =====");
        for (int j = 0; j < reservations.size(); j++) {
            Reservation r = reservations.get(j);
            System.out.println(j + ". Guest: " + r.getGuest().getName() +
                    "   Room: " + r.getRoom().getId() +
                    "   Status: " + r.getStatus());
        }
        System.out.println("===========================\n");


        int resId = -1;
        while (true) {
            try {
                System.out.print("Enter Reservation number (0 to " + (reservations.size() - 1) + "): ");
                resId = scanner.nextInt();
                scanner.nextLine();

                if (resId >= 0 && resId < reservations.size()) {
                    break;
                } else {
                    System.out.println(" Invalid number! Please enter between 0 and " + (reservations.size() - 1));
                }
            } catch (InputMismatchException e) {
                System.out.println(" Invalid input! Please enter a valid NUMBER.");
                scanner.nextLine();
            }
        }

        Reservation reservation = reservations.get(resId);


        if (!reservation.getStatus().equals("Paid")) {
            System.out.println("\n This reservation is NOT paid yet!");
            System.out.println("Please pay for this reservation first (Option 13)\n");
            return;
        }


        double extra = 0;
        while (true) {
            try {
                System.out.print("Enter extra charges (if any, press Enter if there is no extra charge): ");
                String extraInput = scanner.nextLine().trim();


                if (extraInput.isEmpty()) {
                    extra = 0;
                    break;
                }

                extra = Double.parseDouble(extraInput);
                if (extra >= 0) {
                    break;
                } else {
                    System.out.println(" Extra charges cannot be negative!");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input! Please enter a valid NUMBER (e.g., 10.50) or press Enter for 0");
            }
        }


        int days = Period.between(reservation.getArrivalDate(), reservation.getDepartureDate()).getDays();
        double roomTotal = days * reservation.getRoom().getPrice();
        double discount = roomTotal * (reservation.getGuest().getDiscount() / 100.0);
        double roomAfterDiscount = roomTotal - discount;


        double serviceTotal = 0;
        System.out.println("\n===== SERVICES REQUESTED =====");
        if (hotelServices.isEmpty()) {
            System.out.println("No services requested.");
        } else {
            for (HotelService service : hotelServices) {
                System.out.println("- " + service.getServiceName() + ": " + service.getPrice());
                serviceTotal += service.getPrice();
            }
        }
        System.out.println("=============================\n");

        double grandTotal = roomAfterDiscount + serviceTotal + extra;


        System.out.println("\n═══════════════════════════════════════");
        System.out.println("           HOTEL FINAL BILL             ");
        System.out.println("═══════════════════════════════════════");
        System.out.println("\n ROOM CHARGES:");
        System.out.println("   Guest: " + reservation.getGuest().getName());
        System.out.println("   Room #" + reservation.getRoom().getId());
        System.out.println("   Days: " + days + " x " + reservation.getRoom().getPrice() + " = " + roomTotal);
        System.out.println("   Discount (" + reservation.getGuest().getDiscount() + "%): -" + discount);
        System.out.println("   Room Total after discount: " + roomAfterDiscount);

        if (serviceTotal > 0) {
            System.out.println("\n  SERVICE CHARGES: " + serviceTotal);
        }

        if (extra > 0) {
            System.out.println("\n EXTRA CHARGES: " + extra);
        }

        System.out.println("\n───────────────────────────────────────");
        System.out.println("  GRAND TOTAL: " + grandTotal);
        System.out.println("═══════════════════════════════════════\n");


        hotelServices.clear();
        System.out.println("  Bill generated successfully! Services cleared.\n");
    }
}