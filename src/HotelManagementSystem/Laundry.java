package HotelManagementSystem;

public class Laundry extends HotelService {
    private int numberOfItems;
    private String roomNumber;
    private double pricePerItem;

    public Laundry(String serviceId, String roomNumber, int numberOfItems, double pricePerItem) {
        super(serviceId, "Laundry Service", pricePerItem * numberOfItems);
        this.roomNumber = roomNumber;
        this.numberOfItems = numberOfItems;
        this.pricePerItem = pricePerItem;
    }

    @Override
    public void execute() {
        System.out.println("\n LAUNDRY SERVICE ");
        System.out.println("Room: " + roomNumber);
        System.out.println("Items: " + numberOfItems + " pieces");
        System.out.println("Price per item: " + pricePerItem);
        System.out.println("Total: " + price);
        System.out.println("Status: " + status + " - Collecting clothes...");
        updateStatus("Completed");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Room: " + roomNumber + "  Items: " + numberOfItems + "  " + pricePerItem + "/item");
    }
}
