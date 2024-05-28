package Objects;

import CRUD.CRUD;
import Objects.Aircraft;
import Objects.Baggage;
import Serivice.Service;
import Utility.GenId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Passenger implements CRUD<Passenger> {
    private Integer id;
    private String name;
    private String passportNumber;
    private String nationality;
    private Integer weight;
    private List<Baggage> checkedInBaggage; // List of checked-in baggage for this passenger
    private Aircraft bookedAircraft;

    public Passenger(String name, String passportNumber, String nationality, Integer weight) {
        this.id = Integer.valueOf(GenId.getInstance().generateUniqueId(-1));
        this.name = name;
        this.passportNumber = passportNumber;
        this.nationality = nationality;
        this.weight = weight;
        this.checkedInBaggage = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void addCheckedBaggage(Baggage baggage) {
        checkedInBaggage.add(baggage);
    }

    public List<Baggage> getCheckedInBaggage() {
        return Collections.unmodifiableList(checkedInBaggage); // Return an unmodifiable list
    }

    public Aircraft getBookedAircraft() {
        return bookedAircraft;
    }

    public void setBookedAircraft(Aircraft bookedAircraft) {
        this.bookedAircraft = bookedAircraft;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(id).append("\n");
        sb.append("Passenger: ").append(name).append("\n");
        sb.append("Passport Number: ").append(passportNumber).append("\n");
        sb.append("Nationality: ").append(nationality);
        return sb.toString();
    }

    @Override
    public int create() throws SQLException {
        String query = "INSERT INTO passengers (id, name, passport_number, nationality, weight) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setInt(1, id);
        statement.setString(2, name);
        statement.setString(3, passportNumber);
        statement.setString(4, nationality);
        statement.setInt(5, weight);
        return statement.executeUpdate();
    }

    @Override
    public Passenger read() throws SQLException {
        String query = "SELECT * FROM passengers WHERE id = ?";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String passportNumber = resultSet.getString("passport_number");
            String nationality = resultSet.getString("nationality");
            int weight = resultSet.getInt("weight");
            Passenger passenger = new Passenger(name, passportNumber, nationality, weight);
            passenger.setId(id);
            return passenger;
        }
        return null;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int update() throws SQLException {
        String query = "UPDATE passengers SET name=?, passport_number=?, nationality=?, weight=? WHERE id=?";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, passportNumber);
        statement.setString(3, nationality);
        statement.setInt(4, weight);
        statement.setInt(5, id);
        return statement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        String query = "DELETE FROM passengers WHERE id=?";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    @Override
    public Passenger inRead() throws SQLException {
        return null;
    }

    @Override
    public int inDelete() throws SQLException {
        return 0;
    }
}
