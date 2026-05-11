package HotelManagementSystem;

import java.util.ArrayList;
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

        while (i != 20) {
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
            System.out.println("17. Request Hotel Service1");
            System.out.println("18. Show All Services");
            System.out.println("19. Generate Final Bill");
            System.out.println("20. Exit");
            System.out.print("Enter your choice: ");

            i = scanner.nextInt();
            scanner.nextLine();

            switch(i) {
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
                default:
                    System.out.println("Invalid choice! Please enter a number between 1-20.");
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

        System.out.println("    AVAILABLE SERVICES        ");
        System.out.println(" 1. Food Delivery             ");
        System.out.println(" 2. Laundry Service           ");
        System.out.println(" 3. Back to Menu              ");
        System.out.print("Choose service: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 3) return;

        System.out.print("Enter room number: ");
        String roomNo = scanner.nextLine();

        String serviceId = "SVC" + (hotelServices.size() + 1);
        HotelService service = null;

        if (choice == 1) {
            System.out.print("Enter food item name: ");
            String food = scanner.nextLine();
            System.out.print("Enter price per item: $");
            double itemPrice = scanner.nextDouble();
            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt();
            service = new FoodDelivery(serviceId, roomNo, food, qty, itemPrice);

        } else if (choice == 2) {
            System.out.print("Enter number of clothing items: ");
            int items = scanner.nextInt();
            System.out.print("Enter price per item: $");
            double pricePerItem = scanner.nextDouble();
            service = new Laundry(serviceId, roomNo, items, pricePerItem);

        } else {
            System.out.println("Invalid choice!");
            return;
        }

        if (service != null) {
            hotelServices.add(service);
            System.out.println("\n Service requested successfully!");
            service.execute();
        }
    }


    public static void generateFinalBill(ArrayList<Reservation> reservations,
                                         ArrayList<HotelService> hotelServices,
                                         Scanner scanner) {
        if (reservations.isEmpty()) {
            System.out.println("\n No reservations found! Create a reservation first.\n");
            return;
        }

        System.out.println("\n===== ALL RESERVATIONS =====");
        for (int j = 0; j < reservations.size(); j++) {
            Reservation r = reservations.get(j);
            System.out.println("Reservation " + j + " | Guest: " + r.getGuest().getName() +
                    " | Room: " + r.getRoom().getId() +
                    " | Status: " + r.getStatus());
        }
        System.out.println("===========================\n");

        System.out.print("Enter Reservation number (0 to " + (reservations.size() - 1) + "): ");
        int resId = scanner.nextInt();

        if (resId >= reservations.size() || resId < 0) {
            System.out.println(" Invalid reservation number!");
            return;
        }

        Reservation reservation = reservations.get(resId);

        // Check if reservation is paid
        if (!reservation.getStatus().equals("Paid")) {
            System.out.println("\n This reservation is not paid yet!");
            System.out.println("Please pay for the reservation first (Option 13)\n");
            return;
        }

        System.out.print("Enter extra charges (if any): $");
        double extra = scanner.nextDouble();


        Billable bill = new HotelBill(reservation, hotelServices, extra);


        bill.printBill();
        System.out.println("Bill Summary: " + bill.getBillSummary());


        hotelServices.clear();
        System.out.println(" Services cleared from system!\n");
    }
}