package HotelManagementSystem;

public class FoodDelivery extends HotelService{
    private String roomNumber;
    private String foodItem;
    private int quantity;
    private double itemPrice;

    public FoodDelivery(String serviceId, String roomNumber, String foodItem, int quantity, double itemPrice) {
        super(serviceId, "Food Delivery", itemPrice * quantity);
        this.roomNumber = roomNumber;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    @Override
    public void execute() {
        double total = price;
        System.out.println("\n FOOD DELIVERY SERVICE ");
        System.out.println("Room: " + roomNumber);
        System.out.println("Item: " + foodItem);
        System.out.println("Price per item: " + itemPrice);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total: " + total);
        System.out.println("Status: " + status + " - Preparing food...");
        updateStatus("Delivered");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println(" Room: " + roomNumber + "  Item: " + foodItem + " x" + quantity + " " + itemPrice);
    }
}
