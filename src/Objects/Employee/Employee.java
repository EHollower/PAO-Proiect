package Objects.Employee;

public interface Employee {
    String getName();
    void setName(String name);

    double getSalary(); // Monthly salary
    void setSalary(double salary);

    // Additional methods specific to employee roles
    String getDepartment();

    @Override
    String toString(); // Useful for printing employee details
}

