package com.company.classes;

/**
 * Employees requires less information than the person, making them less specific.
 */
public class Employee extends Person {
    /**
     * Initializes an employee. Values are hard coded since all employees serve the same purpose in terms of my application.
     */
    public Employee() {
        super(34, "admin", "admin", "N/A", "0", "admin");
    }

    /**
     * Returns the type of person.
     */
    @Override
    public String getDesignation() {
        return "Employee";
    }
}
