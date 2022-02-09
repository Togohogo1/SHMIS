package com.company.classes;

public class Employee extends Person {
    public Employee() {
        super(99, "Admin", "Nimda", "M/F", "0", "password");
    }

    public String getDesignation() {
        return "Employee";
    }
}
