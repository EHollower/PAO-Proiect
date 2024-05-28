package Objects;

import Utility.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Airline {
    private Integer id;
    private String iataCode;
    private String Name;
    private String Country;
    private Set<Aircraft> fleet;
    private Map<Aircraft, Flight> operatedFlights;

    public Airline(String name, String country) {
        this.id = Integer.valueOf(GenId.getInstance().generateUniqueId(-1));
        this.Name = name;
        this.Country = country;
        this.fleet = new TreeSet<>();
        this.operatedFlights = new HashMap<>();
    }

    public void addToFleet(Aircraft aircraft) {
        fleet.add(aircraft);
    }

    public void removeFromFleet(Aircraft aircraft) {
        fleet.remove(aircraft);
    }

    public Boolean existsInFleet(Aircraft aircraft) {
        return fleet.contains(aircraft);
    }

    public Set<Aircraft> getFleet() {
        return fleet;
    }

    @Override
    public String toString() {
        return "Name: " + Name +
                "\nCountry: " + Country +
                "\nCode: " + id;
    }

    public String getName() {
        return Name;
    }
}
