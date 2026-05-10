package HotelManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeesController {

    public static void addNewEmployee(ArrayList<Employee> employees, Scanner scanner) {
        System.out.println("Enter name (String): ");
        String name = scanner.next();
        System.out.println("Enter salary (double): ");
        double salary = scanner.nextDouble();
        System.out.println("Enter job (String): ");
        String job = scanner.next();
        int id = employees.size();
        Employee employee = new Employee(id, name, salary, job);
        employees.add(employee);
        System.out.println("Employee added successfully!");
    }

    public static void showAllEmployees(ArrayList<Employee> employees) {
        for (Employee employee : employees) {
            employee.print();

        }
    }

    public static void editEmployeeData(ArrayList<Employee> employees, Scanner scanner) {
        System.out.println("Enter id (int): \n-1 to show all employees");
        int id = scanner.nextInt();
        if (id == -1) {
            showAllEmployees(employees);
            System.out.println("Enter id (int): ");
            id = scanner.nextInt();
        }

        Employee employee = employees.get(id);
        System.out.println("Enter name (String): \n-1 to keep it");
        String name = scanner.next();
        if (name.equals("-1")) {
            name = employee.getName();
        }

        System.out.println("Enter salary (double): \n-1 to keep it");
        double salary = scanner.nextDouble();
        if (salary == -1) {
            salary = employee.getSalary();
        }

        System.out.println("Enter job (String): \n-1 to keep it");
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
