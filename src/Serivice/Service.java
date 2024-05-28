package Serivice;

import java.sql.*;

public class Service {
    private final ConnectionDetails connectionDetails;
    private Connection connection;

    private static Service instance = null;

    private Service(String url, String user, String pass) {
        connectionDetails = new ConnectionDetails(url, user, pass);
        load();
        createConnection();
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service("jdbc:mysql://localhost:3306/Airline", "root", "root");
        }
        return instance;
    }

    private void load() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("ERROR:: could not load driver\n");
            e.printStackTrace();
        }
    }

    private void createConnection() {
        try {
            this.connection = DriverManager.getConnection(this.connectionDetails.getUrl(),
                    this.connectionDetails.getUser(),
                    this.connectionDetails.getPassword());
        } catch(Exception e) {
            System.out.println("ERROR:: could not create connection\n");
            e.printStackTrace();
        }
    }

    public void clearDatabases() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM aircraft");
            statement.executeUpdate("DELETE FROM flights");
            statement.executeUpdate("DELETE FROM pilots");
            statement.executeUpdate("DELETE FROM runways");
        } catch(Exception e) {
            System.out.println("ERROR:: could not clear databases\n");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
