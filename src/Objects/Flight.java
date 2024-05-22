package Objects;

import Status.FlightStatus;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class Flight {
    private Integer flightNumber;
    private Airline airline;
    private Airport departureAirport, arrivalAirport;
    private LocalDate departureDate, arrivalDate;
    private Duration flightDuration;
    private FlightStatus status;
    private Set<Aircraft> aircrafts;
    private List<Passenger> passengers;
    private List <Baggage> baggages;

    public Flight(Integer flightNumber, Airline airline, Airport departureAirport, LocalDate departureDate,
                  Airport arrivalAirport, LocalDate arrivalDate, Duration flightDuration, FlightStatus status) {
        // Liste
        this.baggages = new ArrayList<>();
        this.passengers = new ArrayList<>();
        // Set
        this.aircrafts = new TreeSet<>();
        // Integer
        this.flightNumber = flightNumber;
        // Airlien
        this.airline = airline;
        // Departure (Airport, LocalDate)
        this.departureAirport = departureAirport;
        this.departureDate = departureDate;
        // Arrival (Airport, LocalDate)
        this.arrivalAirport = arrivalAirport;
        this.arrivalDate = arrivalDate;
        // Duration
        this.flightDuration = flightDuration;
        // FlightStatus (EnumClass)
        this.status = status;
    }

    // adaugare pasageri
    public void addPassenger(Passenger passenger, Baggage baggage) {
        baggages.add(baggage);
        passengers.add(passenger);
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    // adaugare aeronava spre rută (pot fi mai multe aeronave)
    public void addAircraft(Aircraft aircraft) {
        aircrafts.add(aircraft);
    }

    @Override
    public String toString() {
        return "Flight Number: " + flightNumber +
                "\nAriline: " + airline;
    }
}
