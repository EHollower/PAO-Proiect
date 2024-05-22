package Objects;

import java.util.*;

public class Passenger {
    private String name;
    private String passportNumber;
    private String nationality;
    private String bookingReference; // Unique booking reference for the passenger
    private List<Baggage> checkedInBaggage; // List of checked-in baggage for this passenger

    public Passenger(String name, String passportNumber, String nationality) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.nationality = nationality;
        this.checkedInBaggage = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCheckedBaggage(Baggage baggage) {
        checkedInBaggage.add(baggage);
    }

    public List<Baggage> getCheckedInBaggage() {
        return Collections.unmodifiableList(checkedInBaggage); // Return an unmodifiable list
    }

    @Override
    public String toString() {
        return "Passenger: " + name + "\n" +
                "Passport Number: " + passportNumber + "\n" +
                "Nationality: " + nationality;
    }
}
