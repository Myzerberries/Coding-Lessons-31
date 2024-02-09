package dev.lpa.domain;

import java.util.Comparator;

public class EmployeeComparator <T extends Employee> implements Comparator<Employee> {

    //Our class, EmployeeComparator, is external to the Employee class.
    //To get our class to work with yearStarted, we would need to either make the field public or provide a getter for
    //it, but there might be reasons why we don't want to do that.

    //So, we will copy the whole class from beginning to end and paste it in the Employee class.
    @Override
    public int compare(Employee o1, Employee o2) {
       // return o1.yearStarted - o2.yearStarted;
        return o1.getName().compareTo(o2.getName());
    }
}
