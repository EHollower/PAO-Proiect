package Objects;

public interface Employee {

    public String getName();
    public void setName(String name);

    public String getId(); // Employee ID
    public void setId(String id);

    public double getSalary(); // Monthly salary
    public void setSalary(double salary);

    // Additional methods specific to employee roles (optional)
    // public String getDepartment(); // Example for departmental information

    @Override
    public String toString(); // Useful for printing employee details
}
