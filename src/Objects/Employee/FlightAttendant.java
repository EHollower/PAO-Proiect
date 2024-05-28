package Objects.Employee;

public class FlightAttendant extends BaseEmployee {
    private String shift;

    public FlightAttendant(String name, double salary, String department, String shift) {
        super(name, salary, department);
        this.shift = shift;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Override
    public String toString() {
        return super.toString() + ", FlightAttendant{" +
                "shift='" + shift + '\'' +
                '}';
    }
}
