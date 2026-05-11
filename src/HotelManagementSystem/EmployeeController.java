package HotelManagementSystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeController {

    public static void addNewEmployee(ArrayList<Employee> employees, Scanner scanner) {
        System.out.println("Enter name (String): ");
        String name = scanner.next();

        double salary = 0.0;
        while (true) {
            try {
                System.out.println("Enter salary (double): ");
                salary = scanner.nextDouble();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number (e.g., 50000.00)");
                scanner.nextLine();
            }
        }

        System.out.println("Enter job (String): ");
        String job = scanner.next();

        int id = employees.size();
        Employee employee = new Employee(id, name, salary, job);
        employees.add(employee);
        System.out.println("Employee added successfully!");
    }

    public static void showAllEmployees(ArrayList<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }
        for (Employee employee : employees) {
            employee.print();
        }
    }

    public static void editEmployeeData(ArrayList<Employee> employees, Scanner scanner) {
        if (employees.isEmpty()) {
            System.out.println("No employees available to edit!");
            return;
        }

        int id = -1;
        while (true) {
            try {
                System.out.println("Enter id (int): ");
                System.out.println("Type -1 to show all employees");
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer number.");
                scanner.nextLine();
            }
        }

        if (id == -1) {
            showAllEmployees(employees);
            while (true) {
                try {
                    System.out.println("Enter id (int): ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid integer number.");
                    scanner.nextLine();
                }
            }
        }


        if (id >= employees.size() || id < 0) {
            System.out.println("Employee not found! Invalid ID: " + id);
            System.out.println("Available employee IDs: 0 to " + (employees.size() - 1));
            return;
        }

        Employee employee = employees.get(id);

        System.out.println("Enter name (String): ");
        System.out.println("Type -1 to keep it");
        String name = scanner.next();
        if (name.equals("-1")) {
            name = employee.getName();
        }

        double salary = employee.getSalary();
        while (true) {
            try {
                System.out.println("Enter salary (double): ");
                System.out.println("Type -1 to keep it");
                String input = scanner.next();
                if (input.equals("-1")) {
                    break;
                }
                salary = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number (e.g., 50000.00)");
            }
        }

        System.out.println("Enter job (String): ");
        System.out.println("Type -1 to keep it");
        String job = scanner.next();
        if (job.equals("-1")) {
            job = employee.getJob();
        }

        employee.setName(name);
        employee.setSalary(salary);
        employee.setJob(job);
        employees.set(id, employee);
        System.out.println("Employee edited successfully!");
    }
}