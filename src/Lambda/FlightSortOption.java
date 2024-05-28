package Lambda;

import Objects.Flight;
import Status.FlightStatus;

import java.util.Comparator;
import java.util.function.BiFunction;

public enum FlightSortOption implements Comparator<Flight> {
    DEPARTURE_DATE((flight1, flight2) -> flight1.getDepartureDate().compareTo(flight2.getDepartureDate())),
    NUMBER_OF_PASSENGERS((flight1, flight2) -> flight1.getPassengers().size() - flight2.getPassengers().size()),
    NUMBER_OF_BAGGAGES((flight1, flight2) -> flight1.getBaggages().size() - flight2.getBaggages().size()),
    STATUS((flight1, flight2) -> FlightStatus.statusOrder.compare(flight1.getStatus(), flight2.getStatus())),
    FLIGHT_DURATION((flight1, flight2) -> flight1.getFlightDuration().compareTo(flight2.getFlightDuration()));

    private final BiFunction<Flight, Flight, Integer> sortingFunction;

    FlightSortOption(BiFunction<Flight, Flight, Integer> sortingFunction) {
        this.sortingFunction = sortingFunction;
    }

    public BiFunction<Flight, Flight, Integer> getSortingFunction() {
        return sortingFunction;
    }

    @Override
    public int compare(Flight o1, Flight o2) {
        int dateComparison = o1.getDepartureDate().compareTo(o2.getDepartureDate());
        if (dateComparison != 0) {
            return dateComparison;
        }

        return Integer.compare(o1.getFlightId(), o2.getFlightId());
    }
}
