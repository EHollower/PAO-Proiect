package Objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Airline {
    private String iataCode;
    private String Name;
    private String Country;
    private Set<Aircraft> fleet;
    private Map<Aircraft, Flight> operatedFlights;

    public Airline(String iataCode, String name, String country) {
        this.iataCode = iataCode;
        this.Name = name;
        this.Country = country;
        this.fleet = new TreeSet<>();
        this.operatedFlights = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Name: " + Name +
                "\nCountry: " + Country +
                "\nCode: " + iataCode;
    }
}
