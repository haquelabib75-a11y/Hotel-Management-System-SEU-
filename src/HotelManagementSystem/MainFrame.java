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
    
