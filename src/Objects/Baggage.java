package Objects;

import Status.BaggageStatus;
import Utility.Dimension;

public class Baggage {
    protected Integer weight;
    protected Dimension dimensions;
    protected String associatedFlightNumber;
    protected Passenger associatedPassenger;
    protected BaggageStatus status;

    public Baggage(Integer weight, Dimension dimensions, String associatedFlightNumber, Passenger associatedPassenger) {
        this.weight = weight;
        this.dimensions = dimensions;
        this.associatedFlightNumber = associatedFlightNumber;
        this.associatedPassenger = associatedPassenger;
        this.status = BaggageStatus.CHECKED_IN;
    }

    public void updateStatus(BaggageStatus status) {
        this.status = status;
    }

    protected Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Baggage: " +
                "\nWeight: " + weight + " kg" +
                "\nDimensions: " + dimensions +
                "\nFlight Number: " + associatedFlightNumber +
                "\nStatus: " + status;
    }
}
