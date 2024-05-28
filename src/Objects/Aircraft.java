package Objects;

import CRUD.CRUD;
import Serivice.Service;
import Utility.GenId;
import Objects.Employee.Pilot;
import Objects.Employee.FlightAttendant;
import MyExceptions.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aircraft implements Comparable<Aircraft>, CRUD<Aircraft> {
    private Integer id;
    private String name;
    private String description;
    private String type;
    private Integer totalCapacity, currentCapacity;
    private Integer seats;
    private Pilot pilot;
    private List<FlightAttendant> flightAttendants;
    private List<Passenger> passengers;
    private List<Baggage> baggage;

    public Aircraft(String name, String description, String type, Integer totalCapacity, Integer seats) {
        this.id = Integer.valueOf(GenId.getInstance().generateUniqueId(-1));
        this.name = name;
        this.description = description;
        this.type = type;
        this.totalCapacity = totalCapacity;
        this.seats = seats;
        this.pilot = new Pilot("John", 2500, "Aviation", "12345");
        this.flightAttendants = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public Integer getSeats() {
        return seats;
    }

    // Add a passenger to the aircraft
    public void addPassenger(Passenger passenger) throws OverweightBaggageException {
        passengers.add(passenger);
        baggage.addAll(passenger.getCheckedInBaggage());

        currentCapacity += passenger.getWeight();
        for (Baggage baggage : passenger.getCheckedInBaggage()) currentCapacity += baggage.getWeight();

        if (currentCapacity > totalCapacity)
            throw new OverweightBaggageException(totalCapacity, currentCapacity);
    }

    // Add a flight attendant to the aircraft
    public void addFlightAttendant(FlightAttendant flightAttendant) {
        flightAttendants.add(flightAttendant);
    }

    // Change the pilot of the aircraft
    public void changePilot(Pilot newPilot) {
        this.pilot = newPilot;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nType: " + type +
                "\n" + description +
                "\nCapacity: " + totalCapacity +
                "\nSeats: " + seats +
                "\nPilot: " + pilot.getName();
    }

    @Override
    public int compareTo(Aircraft other) {
        int seatsCompare = this.seats.compareTo(other.seats);
        if (seatsCompare != 0)
            return seatsCompare;
        int capacityCompare = this.totalCapacity.compareTo(other.totalCapacity);
        if (capacityCompare != 0)
            return capacityCompare;
        return id.compareTo(other.id);
    }

    @Override
    public int create() throws SQLException {
        final String create = "INSERT INTO aircraft (id, name, description, type, total_capacity, seats, pilot_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = Service.getInstance().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, this.id);
        preparedStatement.setString(2, this.name);
        preparedStatement.setString(3, this.description);
        preparedStatement.setString(4, this.type);
        preparedStatement.setInt(5, this.totalCapacity);
        preparedStatement.setInt(6, this.seats);
        preparedStatement.setString(7, this.pilot.getName());
        return preparedStatement.executeUpdate();
    }

    @Override
    public Aircraft read() throws SQLException {
        final String read = "SELECT * FROM aircraft WHERE id = ?";
        PreparedStatement preparedStatement = Service.getInstance().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, this.id);

        ResultSet res = preparedStatement.executeQuery();
        if (res.next()) {
            return new Aircraft(res.getString("name"),
                    res.getString("description"),
                    res.getString("type"),
                    res.getInt("total_capacity"),
                    res.getInt("seats"));
        }
        return null;
    }

    @Override
    public int update() throws SQLException {
        final String update = "UPDATE aircraft SET name=?, description=?, type=?, total_capacity=?, seats=?, pilot_name=? WHERE id=?";
        PreparedStatement preparedStatement = Service.getInstance().getConnection().prepareStatement(update);
        preparedStatement.setString(1, this.name);
        preparedStatement.setString(2, this.description);
        preparedStatement.setString(3, this.type);
        preparedStatement.setInt(4, this.totalCapacity);
        preparedStatement.setInt(5, this.seats);
        preparedStatement.setString(6, this.pilot.getName());
        preparedStatement.setInt(7, this.id);
        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        final String delete = "DELETE FROM aircraft WHERE id = ?";
        PreparedStatement preparedStatement = Service.getInstance().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, this.id);
        return preparedStatement.executeUpdate();
    }

    @Override
    public Aircraft inRead() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Aircraft Id=");
        this.id = scanner.nextInt();
        return this.read();
    }

    @Override
    public int inDelete() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Aircraft Id=");
        this.id = scanner.nextInt();
        return this.delete();
    }
}