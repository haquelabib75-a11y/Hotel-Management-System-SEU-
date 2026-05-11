package HotelManagementSystem;

public abstract class HotelService {
    protected String serviceId;
    protected String serviceName;
    protected double price;
    protected String status;

    public HotelService(String serviceId, String serviceName, double price) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.price = price;
        this.status = "Requested";
    }

    public abstract void execute();

    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Service " + serviceId + " status updated to: " + newStatus);
    }

    public String getServiceId() { return serviceId; }
    public String getServiceName() { return serviceName; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }

    public void displayInfo() {
        System.out.println("Service: " + serviceName + " | Price: " + price + " | Status: " + status);
    }
}
