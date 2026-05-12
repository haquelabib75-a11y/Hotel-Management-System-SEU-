package HotelManagementSystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeController {

    public static void addNewEmployee(ArrayList<Employee> employees, Scanner scanner) {
        try {
            System.out.println("Enter name (String): ");
            String name = scanner.next();
            if (name.trim().isEmpty()) {
                throw new InvalidInputException("Name cannot be empty!");
            }

            System.out.println("Enter salary (double): ");
            double salary = scanner.nextDouble();
            if (salary <= 0) {
                throw new InvalidInputException("Salary must be greater than 0!");
            }

            System.out.println("Enter job (String): ");
            String job = scanner.next();
            if (job.trim().isEmpty()) {
                throw new InvalidInputException("Job cannot be empty!");
            }

            int id = employees.size();
            Employee employee = new Employee(id, name, salary, job);
            employees.add(employee);
            System.out.println("Employee added successfully!");

        } catch (InvalidInputException e) {
            System.out.println(" Error: " + e.getMessage());
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input! Salary must be a number.");
            scanner.nextLine();
        }
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
        try {
            if (employees.isEmpty()) {
                throw new EmployeeNotFoundException("No employees available to edit!");
            }

            System.out.println("Enter id (int): ");
            System.out.println("Type -1 to show all employees");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (id == -1) {
                showAllEmployees(employees);
                System.out.println("Enter id (int): ");
                id = scanner.nextInt();
                scanner.nextLine();
            }

            if (id >= employees.size() || id < 0) {
                throw new EmployeeNotFoundException("Employee with ID " + id + " not found!");
            }

            Employee employee = employees.get(id);

            System.out.println("Enter name (String): ");
            System.out.println("Type -1 to keep it");
            String name = scanner.next();
            if (name.equals("-1")) {
                name = employee.getName();
            }

            System.out.println("Enter salary (double): ");
            System.out.println("Type -1 to keep it");
            double salary = scanner.nextDouble();
            if (salary == -1) {
                salary = employee.getSalary();
            }
            if (salary != -1 && salary <= 0) {
                throw new InvalidInputException("Salary must be greater than 0!");
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

        } catch (EmployeeNotFoundException e) {
            System.out.println(" " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println(" Error: " + e.getMessage());
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input! Please enter correct data type.");
            scanner.nextLine();
        }
    }
}