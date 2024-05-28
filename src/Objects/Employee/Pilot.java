package Objects.Employee;

import CRUD.CRUD;
import Serivice.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pilot extends BaseEmployee implements CRUD<Pilot> {
    private String licenseNumber;

    public Pilot(String name, double salary, String department, String licenseNumber) {
        super(name, salary, department);
        this.licenseNumber = licenseNumber;
    }

    public String getName() {
        return super.getName();
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return super.toString() + ", Pilot{" +
                "licenseNumber='" + licenseNumber + '\'' +
                '}';
    }

    @Override
    public int create() throws SQLException {
        String query = "INSERT INTO pilots (id, name, salary, department, license_number) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setInt(1, getId());
        statement.setString(2, this.getName());
        statement.setDouble(3, this.getSalary());
        statement.setString(4, this.getDepartment());
        statement.setString(5, this.getLicenseNumber());
        return statement.executeUpdate();
    }

    @Override
    public Pilot read() throws SQLException {
        String query = "SELECT FROM pilots WHERE id=?";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setInt(1, this.getId()); // Assuming the Pilot object has an ID field
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Pilot pilot1 = new Pilot(resultSet.getString("name"), resultSet.getDouble("salary"), resultSet.getString("department"), resultSet.getString("licenseNumber"));
            pilot1.setId(resultSet.getInt("id"));
            return pilot1;
        }
        return null;
    }

    @Override
    public int update() throws SQLException {
        String query = "UPDATE pilots SET name=?, salary=?, department=?, license_number=? WHERE id=?";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setString(1, this.getName());
        statement.setDouble(2, this.getSalary());
        statement.setString(3, this.getDepartment());
        statement.setString(4, this.getLicenseNumber());
        statement.setInt(5, this.getId());
        return statement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        String query = "DELETE FROM pilots WHERE id=?";
        PreparedStatement statement = Service.getInstance().getConnection().prepareStatement(query);
        statement.setInt(1, this.getId()); // Assuming the Pilot object has an ID field
        return statement.executeUpdate();
    }

    @Override
    public Pilot inRead() throws SQLException {
        return null;
    }

    @Override
    public int inDelete() throws SQLException {
        return 0;
    }
}

