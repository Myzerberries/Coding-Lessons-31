package dev.lpa.domain;

import java.util.Comparator;

public class Employee {

    //What we're doing below is creating a static nested class inside of Employee.

    //We can access the attributes on instances of the Employee class directly, even the private ones, so we will update
    //the method, and instead of calling getName, we'll just access name directly.

    //To create a static nested class, you add a class as part of another class's body, making it static. This lets you
    //access it via the class name, like other static variables.
    public static class EmployeeComparator <T extends Employee> implements Comparator<Employee> {

        private String sortType;

        public EmployeeComparator() {
            this("name");
        }

        public EmployeeComparator(String sortType) {
            this.sortType = sortType;
        }

        @Override
        public int compare(Employee o1, Employee o2) {
            if(sortType.equalsIgnoreCase("yearStarted")){
                return o1.yearStarted - o2.yearStarted;
            }
            return o1.name.compareTo(o2.name);
        }
    }


    private int employeeId;
    private String name;
    private int yearStarted;

    public Employee(){}

    public Employee(int employeeId, String name, int yearStarted){
        this.employeeId = employeeId;
        this.name = name;
        this.yearStarted = yearStarted;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "%d %-8s %d".formatted(employeeId, name, yearStarted);
    }
}
