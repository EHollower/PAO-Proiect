package Objects;

import java.util.ArrayList;
import java.util.List;

public class Airport {
    private String iataCode;
    private String name;
    private String city;
    private String country;
    private ArrayList<Runway> runways; // List of runways at the airport

    public Airport(String iataCode, String name, String city, String country) {
        this.iataCode = iataCode;
        this.name = name;
        this.city = city;
        this.country = country;
        this.runways = new ArrayList<>();
    }

    public String getCity() {
        return city;
    }

    public void addRunway(Runway runway) {
        runways.add(runway);
     }

    @Override
    public String toString() {
        return "Airport: " + name + " (" + iataCode + ")" +
                "\nCity: " + city + ", " + country;
    }
}
