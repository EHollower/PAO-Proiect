package Objects;

import Status.BookingStatus;

public class Ticket {

    private String ticketNumber;
    private Passenger passenger;
    private Flight flight;
    private String seatClass; // Economy, Business, First Class
    private double price;
    private BookingStatus bookingStatus; // Status of the booking (Booked, Confirmed, Issued)

    public Ticket(String ticketNumber, Passenger passenger, Flight flight, String seatClass, double price) {
        this.ticketNumber = ticketNumber;
        this.passenger = passenger;
        this.flight = flight;
        this.seatClass = seatClass;
        this.price = price;
        this.bookingStatus = BookingStatus.BOOKED; // Set default status to Booked
    }

    // Getters and Setters (omitted for brevity)

    public void confirmBooking() {
        if (bookingStatus == BookingStatus.BOOKED) {
            bookingStatus = BookingStatus.CONFIRMED;
            System.out.println("Ticket Booking Confirmed for: " + passenger.getName());
        } else {
            System.out.println("Ticket already confirmed or issued.");
        }
    }

    public void issueTicket() {
        if (bookingStatus == BookingStatus.CONFIRMED) {
            bookingStatus = BookingStatus.ISSUED;
            System.out.println("Ticket Issued for: " + passenger.getName());
        } else {
            System.out.println("Ticket needs to be confirmed before issuing.");
        }
    }

    @Override
    public String toString() {
        return "Ticket: " + ticketNumber + "\n" +
                "Passenger: " + passenger.getName() + "\n" +
                "Flight: " + flight.getFlightNumber() + " (" + flight.getDepartureAirport().getCity() + " - " + flight.getArrivalAirport().getCity() + ")" + "\n" +
                "Seat Class: " + seatClass + "\n" +
                "Price: $" + price + "\n" +
                "Status: " + bookingStatus;
    }
}