package Objects;

import CRUD.CRUD;
import Objects.Employee.Pilot;
import Serivice.Service;
import Utility.GenId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Runway implements CRUD<Runway> {
    private Integer id; // Unique identifier for the runway
    private int length; // Length of the runway in meters
    private int width; // Width of the runway in meters
    private String surfaceType; // Type of runway surface (e.g., Asphalt, Concrete)
    private Airport airport; // Reference to the airport this runway belongs to

    public Runway(int length, int width, String surfaceType) {
        this.id = Integer.valueOf(GenId.getInstance().generateUniqueId(-1));
        this.length = length;
        this.width = width;
        this.surfaceType = surfaceType;
    }

    // Getters and Setters (omitted for brevity)
    public void setId(int id) {
        this.id = id;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Airport getAirport() {
        return airport;
    }

    @Override
    public String toString() {
        return "Runway: " + id + "\n" +
                "Length: " + length + " meters" + "\n" +
                "Width: " + width + " meters" + "\n" +
                "Surface Type: " + surfaceType + "\n";
    }

    @Override
    public int create() throws SQLException {
        String query = "INSERT INTO runways (id, length, width, surface_type) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setInt(1, this.id);
        statement.setInt(2, this.length);
        statement.setInt(3, this.width);
        statement.setString(4, this.surfaceType);

        return statement.executeUpdate();
    }

    @Override
    public Runway read() throws SQLException {
        String query = "SELECT FROM runways WHERE id=?";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setInt(1, this.id); // Assuming the Pilot object has an ID field
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Runway runway = new Runway(resultSet.getInt("length"), resultSet.getInt("width"), resultSet.getString("surface_type") );
            runway.setId(resultSet.getInt("id"));
            return runway;
        }

        return null;
    }

    @Override
    public int update() throws SQLException {
        String query = "UPDATE runways SET length=?, width=?, surface_type=? WHERE id=?";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setInt(1, this.length);
        statement.setInt(2, this.width);
        statement.setString(3, this.surfaceType);
        statement.setInt(4, this.id);
        return statement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        String query = "DELETE FROM runways WHERE id=?";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setInt(1, this.id); // Assuming the Pilot object has an ID field
        return statement.executeUpdate();
    }

    @Override
    public Runway inRead() throws SQLException {
        return null;
    }

    @Override
    public int inDelete() throws SQLException {
        return 0;
    }

}
