package HotelManagementSystem;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Room> rooms;
    private static ArrayList<Guest> guests;
    private static ArrayList<Employee> employees;
    private static ArrayList<Reservation> reservations;

    public static void main(String[] args) {

        rooms= new ArrayList<>();
        guests= new ArrayList<>();
        employees= new ArrayList<>();
        reservations= new ArrayList<>();
        int i=0;
        Scanner scanner = new Scanner(System.in);
        while (i!=17)
        {
            System.out.println("Welcome to the Hotel Management System");
            System.out.println("1. Add new room");
            System.out.println("2. Show all rooms");
            System.out.println("3. Edit room");
            System.out.println("4. Add new guest");
            System.out.println("5. Show all guest");
            System.out.println("6. Search guest by name");
            System.out.println("7. Edit guest data");
            System.out.println("8. Create new reservation");
            System.out.println("9. Show all reservation");
            System.out.println("10. Get Reservation by guest name");
            System.out.println("11. Get Reservation by id");
            System.out.println("12. Edit reservation");
            System.out.println("13. Pay for reservation ");
            System.out.println("14. Add a new employee");
            System.out.println("15. Show all employees");
            System.out.println("16. Edit employee data");
            System.out.println("17. Exit");

            i=scanner.nextInt();
            scanner.nextLine();
            switch(i){
                case 1:
                    RoomController.AddNewRoom(rooms,scanner);
                    break;
                case 2:
                    RoomController.showAllRooms(rooms);
                    break;
                case 3:
                    RoomController.editRoom(rooms,scanner);
                    break;
                case 4:
                    GuestController.addNewGuest(guests,scanner);
                    break;
                case 5:
                    GuestController.showAllGuests(guests);
                    break;
                case 6:
                    GuestController.searchGuestByName(guests, scanner);
                    break;
                case 7:
                    GuestController.editGuest(guests,scanner);
                    break;
                case 8:
                    ReservationController.createNewReservation(guests,rooms, reservations, scanner);
                    break;
                case 9:
                    ReservationController.showAllReservations(reservations,scanner);
                    break;
                case 10:
                    ReservationController.getReservationbyGuestName(reservations, scanner);
                    break;
                case 11:
                    ReservationController.getReservationbyGuestId(reservations, scanner);
                    break;
                case 12:
                    ReservationController.editReservation(guests , rooms, reservations, scanner);
                    break;
                case 13:
                    ReservationController.payReservation(reservations,scanner);
                    break;
                case 14:
                    EmployeeController.addNewEmployee(employees,scanner);
                    break;
                case 15:
                    EmployeeController.showAllEmployees(employees);
                    break;
                case 16:
                    EmployeeController.editEmployeeData(employees, scanner);
                    break;
                case 17:
                    break;

            }

        }

        scanner.close();

    }
}
