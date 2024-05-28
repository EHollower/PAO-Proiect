package MyExceptions;


public class NotEnoughSeatsException extends Exception {
    private final int availableSeats;
    private final int requestedPassengers;

    public NotEnoughSeatsException(int availableSeats, int requestedPassengers) {
        super("Not enough seats available on the aircraft.");
        this.availableSeats = availableSeats;
        this.requestedPassengers = requestedPassengers;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " Available seats: " + availableSeats + ", Requested Passengers: " + requestedPassengers;
    }
}
