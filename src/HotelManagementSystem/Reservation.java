package HotelManagementSystem;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public class Reservation {
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private double price;
    private String status;
    private Guest guest;
    private Room room;
    private DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Reservation(LocalDate arrivalDate, LocalDate departureDate, double price, String status, Guest guest,Room room) {
        this.arrivalDate= arrivalDate;
        this.departureDate= departureDate;
        this.price= price;
        this.status= status;
        this.guest= guest;
        this.room= room;
    }
    public Reservation() {}

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }
    public String getArrivalDatetoString() {
        return arrivalDate.format(formatter);
    }
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    public String getDepartureDatetoString() {
        return departureDate.format(formatter);
    }
    public double getPrice(){
        return price;
    }
    public String getStatus() {
        return status;
    }
    public Room getRoom() {
        return room;
    }
    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate= arrivalDate;
    }
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate= departureDate;
    }
    public void setPrice(double price){
        this.price= price;
    }
    public void setStatus(String status) {
        this.status= status;
    }
    public void setGuest(Guest guest) {
        this.guest= guest;
    }
    public void setRoom(Room room) {
        this.room= room;
    }
    public void print() {
        System.out.println("\n------------------------------");
        System.out.println("Arrival Date: "+ arrivalDate.format(formatter));
        System.out.println("Departure Date: "+ departureDate.format(formatter));

    }
}
