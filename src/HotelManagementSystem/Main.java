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
            System.out.println("9. Show all guest");
            System.out.println("10. Get Reservation by guest name");
            System.out.println("11. Get Reservation by id");
            System.out.println("12. Edit reservation");
            System.out.println("13. Pay for reservation ");
            System.out.println("14. Add a new employee");
            System.out.println("15. Show all employees");
            System.out.println("16. Edit employee data");
            System.out.println("17. Exit");

            Scanner scanner = new Scanner(System.in);
            i=scanner.nextInt();


        }





    }
}
