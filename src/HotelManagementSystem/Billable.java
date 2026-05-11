package HotelManagementSystem;

public interface Billable {
    double calculateTotal();
    void printBill();
    String getBillSummary();
}
