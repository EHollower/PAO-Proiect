package Objects.Employee;

public class GroundCrew extends BaseEmployee {
    private String areaOfResponsibility;

    public GroundCrew(String name, double salary, String department, String areaOfResponsibility) {
        super(name, salary, department);
        this.areaOfResponsibility = areaOfResponsibility;
    }

    public String getAreaOfResponsibility() {
        return areaOfResponsibility;
    }

    public void setAreaOfResponsibility(String areaOfResponsibility) {
        this.areaOfResponsibility = areaOfResponsibility;
    }

    @Override
    public String toString() {
        return super.toString() + ", GroundCrew{" +
                "areaOfResponsibility='" + areaOfResponsibility + '\'' +
                '}';
    }
}
