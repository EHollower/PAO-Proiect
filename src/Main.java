import Menu.*;
import Serivice.*;
import Objects.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            Service.getInstance();
            Menu m = Menu.getInstance();
            m.AirlineMenu();
        } catch(Exception e) {
            e.printStackTrace();
        }
        Service.getInstance().clearDatabases();
    }
}