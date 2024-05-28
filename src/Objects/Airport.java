package Objects;

import Objects.Employee.GroundCrew;
import Utility.GenId;

import java.util.ArrayList;
import java.util.List;

public class Airport {
    private Integer id;
    private String name;
    private String city;
    private String country;
    private ArrayList<Runway> runways; // List of runways at the airport
    private List<GroundCrew> groundCrew;

    public Airport(String name, String city, String country) {
        this.id = Integer.valueOf(GenId.getInstance().generateUniqueId(-1));
        this.name = name;
        this.city = city;
        this.country = country;
        this.runways = new ArrayList<>();
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void addRunway(Runway runway) {
        runways.add(runway);
     }

    @Override
    public String toString() {
        return "Airport: " + name + " (" + id + ")" +
                "\nCity: " + city + ", " + country;
    }

    public String getName() {
        return name;
    }
}
