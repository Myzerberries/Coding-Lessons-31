import dev.lpa.domain.Employee;
import dev.lpa.domain.StoreEmployee;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(10001, "Ralph", 2015),
                new Employee(10005, "Carole", 2021),
                new Employee(10022, "Jane", 2013),
                new Employee(13151, "Laura", 2020),
                new Employee(10050, "Jim", 2018)));

        //Next, we sort the list with our Comparator.
        //First we create a comparator local variable, whose type can be inferred when we assign a new Employee
        //Comparator instance to it.
//        var comparator = new EmployeeComparator<>();
//        employees.sort(comparator);

        //Below we are accessing our Comparator through the Employee class, hence the Employee.EmployeeComparator<>()
        //notation.

        employees.sort(new Employee.EmployeeComparator<>("yearStarted")
                .reversed());

        for(Employee e : employees) {
            System.out.println(e);
        }

        System.out.println("Store Members");



        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10015, "Meg", 2019, "Target"),
                new StoreEmployee(10515, "Joe", 2021, "Walmart"),
                new StoreEmployee(10105, "Tom", 2020, "Macys"),
                new StoreEmployee(10215, "Marty", 2018, "Walmart"),
                new StoreEmployee(10322, "Bud", 2016, "Target")));

        //In the code below, we're not using the StoreComparator we created on StoreEmployee. Instead, we're using the
        //static EmployeeComparator on Employee, which you can see if you hover over the local variable, comparator.

        //This means we've accessed the comparator on Employee through the StoreEmployee class, so static nested classes
        //are inherited by subclasses.

        var comparator = new Employee.EmployeeComparator<>();
        storeEmployees.sort(comparator);

        //An inner class requires an instance of the enclosing class to be used, to instantiate an inner class.
        //So we create a new variable, and assign it a new StoreEmployee instance, using the no args constructor.

        var genericEmployee = new StoreEmployee();
        //To access the inner class, to create a new instance of it, we have to use a special syntax on our outer class
        //instance, .new:
        var comparator2 = genericEmployee.new StoreComparator<>();
        storeEmployees.sort(comparator2);

        //If we didn't want to use a local variable, we can chain the instantiations:

        //var comparator = new StoreEmployee().new StoreComparator<>();

        //In either case, we are first instantiating an instance of StoreEmployee. The .new syntax isn't calling a
        //method, but it will create an instance of an inner class, which we've declared on Store Employee.

        for (StoreEmployee e : storeEmployees){
            System.out.println(e);
        }

    }

}

//Nesting classes(or types) within another class (or type):

//A class can contain other types within the class body, such as other classes, interfaces, enums, and records.

//These are called nested types, or nested classes.

//You might want to use nested classes when your classes are tightly coupled, meaning their functionality is interwoven.

//The four different types of nested classes you can use in Java are: the static nested class, the inner class, and the
//local and anonymous classes.

//                      Type                    Description
//                      static nested class     Declared in class body. Much like a static field, access to this class
//                                              is through the Class name identifier.

//                      instance/inner class    Declared in class body. This type of class can only be accessed through
//                                              an instance of the outer class.
//
//                      local class             Declared within a method body.
//
//                      anonymous class         Unnamed class, declared and instantiated in same statement.
//
//The enum and record are nested types, as well as the interface.

//Before JDK16, only static classes were allowed to have static methods.

//As of JDK16, all four types of nested classes can have static members of any type, including static methods.

//STATIC NESTED CLASS:

//The static nested class is a class enclosed in the structure of another class, declared as static.

//This means the class, if accessed externally, requires the outer class name as part of the qualifying name.

//This class has the advantage of being able to access private attributes on the outer class.

//The enclosing class can access any attributes on the static nested class, also including private attributes.

//INNER CLASS:

//Inner classes are non-static classes, declared on an enclosing class at the member lever.

//Inner classes can have any of the four valid access modifiers.

//An inner class has access to instance members, including private members, of the enclosing class.

//Instantiating an inner class from external code can be a bit tricky.

//As of JDK16, static members ofa ll types are supported on inner classes.

//To create an instance of an inner class, you first must have an instance of the Enclosing Class.

//From that instance, you call .new, followed by the inner class name and the parentheses, taking any constructor
//arguments:

//EnclosingClass outerClass = new EnclosingClass();
//EnclosingClass.InnerClass innerClass = outerClass.new InnerClass();

//Many times, an inner class is never accessed or instantiated from outside the enclosing class.
