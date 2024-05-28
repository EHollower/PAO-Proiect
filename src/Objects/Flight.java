package Objects;

import CRUD.CRUD;
import MyExceptions.NotEnoughSeatsException;
import MyExceptions.OverweightBaggageException;
import Serivice.Service;
import Status.FlightStatus;
import Utility.GenId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class Flight implements CRUD<Flight> {
    private Integer id;
    private final Airline airline;
    private final Airport departureAirport, arrivalAirport;
    private final LocalDate departureDate, arrivalDate;
    private final Duration flightDuration;
    private final FlightStatus status;
    private final Set<Aircraft> aircrafts;
    private final List<Passenger> passengers;
    private final List<Baggage> baggages;

    public Flight(Airline airline, Airport departureAirport, LocalDate departureDate,
                  Airport arrivalAirport, LocalDate arrivalDate, Duration flightDuration, FlightStatus status) {
        id = Integer.valueOf(GenId.getInstance().generateUniqueId(-1));
        this.baggages = new ArrayList<>();
        this.passengers = new ArrayList<>();
        this.aircrafts = new TreeSet<>();
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.departureDate = departureDate;
        this.arrivalAirport = arrivalAirport;
        this.arrivalDate = arrivalDate;
        this.flightDuration = flightDuration;
        this.status = status;
    }

    public Flight() {
        this.airline = null;
        this.departureAirport = null;
        this.arrivalAirport = null;
        this.departureDate = null;
        this.arrivalDate = null;
        this.flightDuration = null;
        this.status = null;
        this.aircrafts = new TreeSet<>();
        this.passengers = new ArrayList<>();
        this.baggages = new ArrayList<>();

    }

    public void setId(int id) {
        this.id = id;
    }

    public void addPassenger(Passenger passenger, Aircraft aircraft) throws NotEnoughSeatsException, OverweightBaggageException {
        if (passengers.size() >= aircraft.getSeats())
            throw new NotEnoughSeatsException(aircraft.getSeats(), passengers.size() + 1);

        aircraft.addPassenger(passenger);
    }

    public Integer getFlightId() {
        return id;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public Duration getFlightDuration() {
        return flightDuration;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Baggage> getBaggages() {
        return baggages;
    }

    public void addAircraft(Aircraft aircraft) {
        aircrafts.add(aircraft);
    }

    @Override
    public String toString() {
        return "Id: " + id +
                "\nAirline: " + airline.getName() +
                "\nDeparture Airport: " + departureAirport.getName() + " (" + departureAirport.getCountry() + ")" +
                "\nDeparture Date: " + departureDate +
                "\nArrival Airport: " + arrivalAirport.getName() + " (" + arrivalAirport.getCountry() + ")" +
                "\nArrival Date: " + arrivalDate +
                "\nFlight Duration: " + flightDuration +
                "\nStatus: " + status;
    }

    @Override
    public int create() throws SQLException {
        String query = "INSERT INTO flights (id, airline, departure_airport, departure_date, " +
                "arrival_airport, arrival_date, flight_duration, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setInt(1, id);
        statement.setString(2, airline.getName());
        statement.setString(3, departureAirport.getName());
        statement.setString(4, departureDate.toString());
        statement.setString(5, arrivalAirport.getName());
        statement.setString(6, arrivalDate.toString());
        statement.setString(7, flightDuration.toString());
        statement.setString(8, status.toString());
        return statement.executeUpdate();
    }

    @Override
    public Flight read() throws SQLException {
        String query = "SELECT * FROM flights WHERE id = ?";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Airline airline = new Airline(resultSet.getString("airline"), "");
            Airport departureAirport = new Airport(resultSet.getString("departure_airport"), "", "");
            LocalDate departureDate = LocalDate.parse(resultSet.getString("departure_date"));
            Airport arrivalAirport = new Airport(resultSet.getString("arrival_airport"), "", "");
            LocalDate arrivalDate = LocalDate.parse(resultSet.getString("arrival_date"));
            Duration flightDuration = Duration.parse(resultSet.getString("flight_duration"));
            FlightStatus status = FlightStatus.valueOf(resultSet.getString("status"));
            Flight f = new Flight(airline, departureAirport, departureDate, arrivalAirport, arrivalDate, flightDuration, status);
            f.setId(resultSet.getInt("id"));
            return f;
        }
        return null;
    }

    @Override
    public int update() throws SQLException {
        String query = "UPDATE flights SET airline=?, departure_airport=?, departure_date=?, arrival_airport=?, arrival_date=?, flight_duration=?, status=? WHERE id=?";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setString(1, airline.getName());
        statement.setString(2, departureAirport.getName());
        statement.setString(3, departureDate.toString());
        statement.setString(4, arrivalAirport.getName());
        statement.setString(5, arrivalDate.toString());
        statement.setString(6, flightDuration.toString());
        statement.setString(7, status.toString());
        statement.setInt(8, id);
        return statement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        String query = "DELETE FROM flights WHERE id=?";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    @Override
    public Flight inRead() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Intrdu id-ul zborului cautat: ");
        int id = scanner.nextInt();
        Flight flight = new Flight();
        flight.setId(id);
        return flight.read();
    }

    @Override
    public int inDelete() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Intrdu id-ul zborului cautat: ");
        int id = scanner.nextInt();
        Flight flight = new Flight();
        flight.setId(id);
        return flight.delete();
    }
}
