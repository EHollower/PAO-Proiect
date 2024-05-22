package Objects;

public class Runway {
    private String id; // Unique identifier for the runway
    private int length; // Length of the runway in meters
    private int width; // Width of the runway in meters
    private String surfaceType; // Type of runway surface (e.g., Asphalt, Concrete)
    private Airport airport; // Reference to the airport this runway belongs to

    public Runway(String id, int length, int width, String surfaceType) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.surfaceType = surfaceType;
    }

    // Getters and Setters (omitted for brevity)

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    @Override
    public String toString() {
        return "Runway: " + id + "\n" +
                "Length: " + length + " meters" + "\n" +
                "Width: " + width + " meters" + "\n" +
                "Surface Type: " + surfaceType + "\n";
    }
}
