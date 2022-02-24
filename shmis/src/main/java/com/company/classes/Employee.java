package com.company.classes;

/**
 * Employees requires less information than the person, making them less specific.
 */
public class Employee extends Person {
    /**
     * Initializes an employee. Values are hardcoded since all employees serve the same purpose in terms of my application.
     */
    public Employee() {
        super(-1, "Admin", "Admin", "M/F/O", "0", "password");
    }

    /**
     * Returns the type of person.
     */
    @Override
    public String getDesignation() {
        return "Employee";
    }
}
