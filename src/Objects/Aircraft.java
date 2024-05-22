package Objects;

public class Aircraft {
    private Integer IdAircraft;
    private String Name;
    private String Description;
    private String Type;
    private Integer totalCapacity, Seats;

    public Aircraft(String name, String description, String type, Integer totalCapacity, Integer Seats) {
        this.Name = name;
        this.Description = description;
        this.Type = type;
        this.totalCapacity = totalCapacity;
        this.Seats = Seats;
    }

    @Override
    public String toString() {
        return "Name: " + Name +
                "\nType: " + Type +
                "\nL " + Description;
    }
}
