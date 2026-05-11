package HotelManagementSystem;

import java.util.ArrayList;
import java.time.Period;
public class HotelBill implements Billable {
    private Reservation reservation;
    private ArrayList<HotelService> services;
    private double extraCharges;

    public HotelBill(Reservation reservation, ArrayList<HotelService> services, double extraCharges) {
        this.reservation = reservation;
        this.services = services;
        this.extraCharges = extraCharges;
    }

    @Override
    public double calculateTotal() {
        double roomTotal = 0;
        if (reservation != null) {
            int days = Period.between(reservation.getArrivalDate(), reservation.getDepartureDate()).getDays();
            roomTotal = days * reservation.getRoom().getPrice();

            double discount = roomTotal * (reservation.getGuest().getDiscount() / 100.0);
            roomTotal = roomTotal - discount;
        }

        double serviceTotal = 0;
        for (HotelService service : services) {
            serviceTotal += service.getPrice();
        }

        return roomTotal + serviceTotal + extraCharges;
    }

    @Override
    public void printBill() {
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("        HOTEL MANAGEMENT SYSTEM        ");
        System.out.println("              FINAL BILL               ");
        System.out.println("═══════════════════════════════════════");

        if (reservation != null) {
            Guest guest = reservation.getGuest();
            Room room = reservation.getRoom();
            int days = Period.between(reservation.getArrivalDate(), reservation.getDepartureDate()).getDays();
            double roomTotal = days * room.getPrice();
            double discount = roomTotal * (guest.getDiscount() / 100.0);

            System.out.println("\n ROOM CHARGES:");
            System.out.println("   Guest: " + guest.getName());
            System.out.println("   Room #" + room.getId() + " (" + room.getType() + ")");
            System.out.println("   Days: " + days + " x " + room.getPrice() + " = " + roomTotal);
            System.out.println("   Discount (" + guest.getDiscount() + "%): -" + discount);
            System.out.println("   Room Total: " + (roomTotal - discount));
        }

        if (!services.isEmpty()) {
            System.out.println("\n  SERVICE CHARGES:");
            for (HotelService service : services) {
                System.out.println("   • " + service.getServiceName() + ": " + service.getPrice());
            }
        }

        if (extraCharges > 0) {
            System.out.println("\n EXTRA CHARGES: " + extraCharges);
        }

        System.out.println("\n───────────────────────────────────────");
        System.out.println(" GRAND TOTAL: " + calculateTotal());
        System.out.println("═══════════════════════════════════════\n");
    }

    @Override
    public String getBillSummary() {
        return "Total: " + calculateTotal() + "  Services: " + services.size();
    }

}
