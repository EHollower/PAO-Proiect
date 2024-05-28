package Objects.Employee;

import Utility.GenId;

public abstract class BaseEmployee implements Employee {
    private String name;
    private Integer id;
    private double salary;
    private String department;

    public BaseEmployee(String name, double salary, String department) {
        this.id = Integer.valueOf(GenId.getInstance().generateUniqueId(-1));
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }
}
