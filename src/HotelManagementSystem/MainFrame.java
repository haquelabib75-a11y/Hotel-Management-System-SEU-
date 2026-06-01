package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private ArrayList<Room> rooms;
    private ArrayList<Guest> guests;
    private ArrayList<Employee> employees;
    private ArrayList<Reservation> reservations;
    private ArrayList<HotelService> hotelServices;

    private JTextArea displayArea;

    public MainFrame() {
        rooms = new ArrayList<>();
        guests = new ArrayList<>();
        employees = new ArrayList<>();
        reservations = new ArrayList<>();
        hotelServices = new ArrayList<>();

        setTitle("Hotel Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createMenuBar();

        setLayout(new BorderLayout());

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.WEST);

        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save Data");
        saveItem.addActionListener(e -> saveData());
        JMenuItem loadItem = new JMenuItem("Load Data");
        loadItem.addActionListener(e -> loadData());
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAbout());
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttonNames = {
                "Add Room", "Show Rooms", "Add Guest", "Show Guests",
                "Add Employee", "Show Employees", "Create Reservation",
                "Show Reservations", "Request Service", "Generate Bill"
        };

        for (String name : buttonNames) {
            JButton btn = new JButton(name);
            btn.addActionListener(e -> handleButtonClick(name));
            panel.add(btn);
        }

        return panel;
    }

    private void handleButtonClick(String action) {
        switch (action) {
            case "Add Room":
                addRoomDialog();
                break;
            case "Show Rooms":
                showRooms();
                break;
            case "Add Guest":
                addGuestDialog();
                break;
            case "Show Guests":
                showGuests();
                break;
            case "Add Employee":
                addEmployeeDialog();
                break;
            case "Show Employees":
                showEmployees();
                break;
            case "Create Reservation":
                createReservationDialog();
                break;
            case "Show Reservations":
                showReservations();
                break;
            case "Request Service":
                requestServiceDialog();
                break;
            case "Generate Bill":
                generateBillDialog();
                break;
        }
    }

    private void addRoomDialog() {
        JTextField floorField = new JTextField();
        JTextField capacityField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField descField = new JTextField();
        JTextField priceField = new JTextField();

        Object[] message = {
                "Floor:", floorField,
                "Capacity:", capacityField,
                "Type:", typeField,
                "Description:", descField,
                "Price:", priceField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Room", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int floor = Integer.parseInt(floorField.getText());
                int capacity = Integer.parseInt(capacityField.getText());
                String type = typeField.getText();
                String description = descField.getText();
                double price = Double.parseDouble(priceField.getText());

                Room room = new Room(rooms.size(), floor, capacity, type, description, price);
                rooms.add(room);
                displayArea.append("Room added successfully!\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Please enter numbers correctly.");
            }
        }
    }

    private void showRooms() {
        displayArea.setText("");
        if (rooms.isEmpty()) {
            displayArea.append("No rooms available.\n");
            return;
        }
        for (Room room : rooms) {
            displayArea.append("---------------------------------\n");
            displayArea.append("Room ID: " + room.getId() + "\n");
            displayArea.append("Floor: " + room.getFloor() + "\n");
            displayArea.append("Capacity: " + room.getCapacity() + "\n");
            displayArea.append("Type: " + room.getType() + "\n");
            displayArea.append("Price: $" + room.getPrice() + "\n");
            displayArea.append("---------------------------------\n\n");
        }
    }
     private void addGuestDialog() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField discountField = new JTextField();
        
        Object[] message = {
            "Name:", nameField,
            "Email:", emailField,
            "Discount (%):", discountField
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, "Add Guest", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String email = emailField.getText();
                int discount = Integer.parseInt(discountField.getText());
                
                Guest guest = new Guest(guests.size(), name, email, discount);
                guests.add(guest);
                displayArea.append("Guest added successfully!\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid discount! Please enter a number.");
            }
        }
    }
    
    private void showGuests() {
        displayArea.setText("");
        if (guests.isEmpty()) {
            displayArea.append("No guests available.\n");
            return;
        }
        for (Guest guest : guests) {
            displayArea.append("----------------\n");
            displayArea.append("ID: " + guest.getId() + "\n");
            displayArea.append("Name: " + guest.getName() + "\n");
            displayArea.append("Email: " + guest.getEmail() + "\n");
            displayArea.append("Discount: " + guest.getDiscount() + "%\n");
            displayArea.append("----------------\n\n");
        }
    }
    
 private void addGuestDialog() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField discountField = new JTextField();
        
        Object[] message = {
            "Name:", nameField,
            "Email:", emailField,
            "Discount (%):", discountField
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, "Add Guest", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String email = emailField.getText();
                int discount = Integer.parseInt(discountField.getText());
                
                Guest guest = new Guest(guests.size(), name, email, discount);
                guests.add(guest);
                displayArea.append("Guest added successfully!\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid discount! Please enter a number.");
            }
        }
    }
    
    private void showGuests() {
        displayArea.setText("");
        if (guests.isEmpty()) {
            displayArea.append("No guests available.\n");
            return;
        }
        for (Guest guest : guests) {
            displayArea.append("----------------\n");
            displayArea.append("ID: " + guest.getId() + "\n");
            displayArea.append("Name: " + guest.getName() + "\n");
            displayArea.append("Email: " + guest.getEmail() + "\n");
            displayArea.append("Discount: " + guest.getDiscount() + "%\n");
            displayArea.append("----------------\n\n");
        }
    }
    
private void addEmployeeDialog() {
        JTextField nameField = new JTextField();
        JTextField salaryField = new JTextField();
        JTextField jobField = new JTextField();
        
        Object[] message = {
            "Name:", nameField,
            "Salary:", salaryField,
            "Job:", jobField
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, "Add Employee", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                double salary = Double.parseDouble(salaryField.getText());
                String job = jobField.getText();
                
                Employee emp = new Employee(employees.size(), name, salary, job);
                employees.add(emp);
                displayArea.append("Employee added successfully!\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid salary! Please enter a number.");
            }
        }
    }
    
    private void showEmployees() {
        displayArea.setText("");
        if (employees.isEmpty()) {
            displayArea.append("No employees available.\n");
            return;
        }
        for (Employee emp : employees) {
            displayArea.append("-------------------\n");
            displayArea.append("ID: " + emp.getID() + "\n");
            displayArea.append("Name: " + emp.getName() + "\n");
            displayArea.append("Salary: $" + emp.getSalary() + "\n");
            displayArea.append("Job: " + emp.getJob() + "\n");
            displayArea.append("-------------------\n\n");
        }
        private void createReservationDialog() {
        if (guests.isEmpty() || rooms.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add guests and rooms first!");
            return;
        }
        
        JComboBox<Guest> guestCombo = new JComboBox<>();
        for (Guest g : guests) {
            guestCombo.addItem(g);
        }
        
        JComboBox<Room> roomCombo = new JComboBox<>();
        for (Room r : rooms) {
            roomCombo.addItem(r);
        }
        
        JTextField arrivalField = new JTextField();
        JTextField departureField = new JTextField();
        
        Object[] message = {
            "Guest:", guestCombo,
            "Room:", roomCombo,
            "Arrival Date (yyyy-MM-dd):", arrivalField,
            "Departure Date (yyyy-MM-dd):", departureField
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, "Create Reservation", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                Guest selectedGuest = (Guest) guestCombo.getSelectedItem();
                Room selectedRoom = (Room) roomCombo.getSelectedItem();
                
                LocalDate arrival = LocalDate.parse(arrivalField.getText());
                LocalDate departure = LocalDate.parse(departureField.getText());
                
                int days = Period.between(arrival, departure).getDays();
                double total = days * selectedRoom.getPrice();
                double discount = total * selectedGuest.getDiscount() / 100.0;
                double finalTotal = total - discount;
                
                Reservation res = new Reservation(arrival, departure, finalTotal, "Reserved", selectedGuest, selectedRoom);
                reservations.add(res);
                
                displayArea.append("Reservation created successfully!\n");
                displayArea.append("Total after discount: $" + finalTotal + "\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format! Use yyyy-MM-dd");
            }
        }
    }
    
    private void showReservations() {
        displayArea.setText("");
        if (reservations.isEmpty()) {
            displayArea.append("No reservations found.\n");
            return;
        }
        for (Reservation r : reservations) {
            displayArea.append("-------------------------------------\n");
            displayArea.append("Arrival: " + r.getArrivalDatetoString() + "\n");
            displayArea.append("Departure: " + r.getDepartureDatetoString() + "\n");
            displayArea.append("Guest: " + r.getGuest().getName() + "\n");
            displayArea.append("Room ID: " + r.getRoom().getId() + "\n");
            displayArea.append("Price: $" + r.getPrice() + "\n");
            displayArea.append("Status: " + r.getStatus() + "\n");
            displayArea.append("-------------------------------------\n\n");
        }
    }
    
    private void requestServiceDialog() {
        String[] services = {"Food Delivery", "Laundry Service"};
        String selected = (String) JOptionPane.showInputDialog(this, "Select Service:", "Request Service",
            JOptionPane.QUESTION_MESSAGE, null, services, services[0]);
        
        if (selected != null) {
            JTextField roomField = new JTextField();
            Object[] message = {"Room Number:", roomField};
            
            int option = JOptionPane.showConfirmDialog(this, message, selected, JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String serviceId = "SVC" + (hotelServices.size() + 1);
                HotelService service = null;
                
                if (selected.equals("Food Delivery")) {
                    JTextField foodField = new JTextField();
                    JTextField priceField = new JTextField();
                    JTextField qtyField = new JTextField();
                    Object[] foodMsg = {"Food Item:", foodField, "Price per item:", priceField, "Quantity:", qtyField};
                    
                    int foodOption = JOptionPane.showConfirmDialog(this, foodMsg, "Food Delivery", JOptionPane.OK_CANCEL_OPTION);
                    if (foodOption == JOptionPane.OK_OPTION) {
                        String food = foodField.getText();
                        double price = Double.parseDouble(priceField.getText());
                        int qty = Integer.parseInt(qtyField.getText());
                        service = new FoodDelivery(serviceId, roomField.getText(), food, qty, price);
                    }
                } else if (selected.equals("Laundry Service")) {
                    JTextField itemsField = new JTextField();
                    JTextField priceField = new JTextField();
                    Object[] laundryMsg = {"Number of items:", itemsField, "Price per item:", priceField};
                    
                    int laundryOption = JOptionPane.showConfirmDialog(this, laundryMsg, "Laundry Service", JOptionPane.OK_CANCEL_OPTION);
                    if (laundryOption == JOptionPane.OK_OPTION) {
                        int items = Integer.parseInt(itemsField.getText());
                        double price = Double.parseDouble(priceField.getText());
                        service = new Laundry(serviceId, roomField.getText(), items, price);
                    }
                }
                
                if (service != null) {
                    hotelServices.add(service);
                    displayArea.append("Service requested successfully!\n");
                    service.execute();
                }
            }
        }
    }
    
    private void generateBillDialog() {
        if (reservations.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No reservations found!");
            return;
        }
        
        JComboBox<Reservation> resCombo = new JComboBox<>();
        for (Reservation r : reservations) {
            if (r.getStatus().equals("Paid")) {
                resCombo.addItem(r);
            }
        }
        
        if (resCombo.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "No paid reservations found!");
            return;
        }
        
        JTextField extraField = new JTextField();
        Object[] message = {"Reservation:", resCombo, "Extra Charges:", extraField};
        
        int option = JOptionPane.showConfirmDialog(this, message, "Generate Bill", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Reservation selected = (Reservation) resCombo.getSelectedItem();
            double extra = Double.parseDouble(extraField.getText());
            
            int days = Period.between(selected.getArrivalDate(), selected.getDepartureDate()).getDays();
            double roomTotal = days * selected.getRoom().getPrice();
            double discount = roomTotal * selected.getGuest().getDiscount() / 100.0;
            double roomAfterDiscount = roomTotal - discount;
            
            double serviceTotal = 0;
            for (HotelService s : hotelServices) {
                serviceTotal += s.getPrice();
            }
            
            double grandTotal = roomAfterDiscount + serviceTotal + extra;
            
            displayArea.setText("");
            displayArea.append("========== HOTEL FINAL BILL ==========\n");
            displayArea.append("Guest: " + selected.getGuest().getName() + "\n");
            displayArea.append("Room: " + selected.getRoom().getId() + "\n");
            displayArea.append("Days: " + days + "\n");
            displayArea.append("Room Total: $" + roomTotal + "\n");
            displayArea.append("Discount: -$" + discount + "\n");
            displayArea.append("Room After Discount: $" + roomAfterDiscount + "\n");
            displayArea.append("Service Charges: $" + serviceTotal + "\n");
            displayArea.append("Extra Charges: $" + extra + "\n");
            displayArea.append("=======================================\n");
            displayArea.append("GRAND TOTAL: $" + grandTotal + "\n");
            displayArea.append("=======================================\n");
            
            hotelServices.clear();
        }
    }
    
    private void saveData() {
        FileManager.saveAllData(rooms, guests, employees, reservations, hotelServices);
        displayArea.append("Data saved successfully!\n");
    }
    
    private void loadData() {
        FileManager.loadAllData(rooms, guests, employees, reservations, hotelServices);
        displayArea.append("Data loaded successfully!\n");
    }
    
    private void showAbout() {
        JOptionPane.showMessageDialog(this, "Hotel Management System\nVersion 1.0\n\nCreated for CSB232");
    }
}

    }
