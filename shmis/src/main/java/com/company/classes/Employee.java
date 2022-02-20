package com.company.classes;

public class Employee extends Person {
    public Employee() {
        super(-1, "Admin", "Admin", "M/F/O", "0", "password");
    }

    public String getDesignation() {
        return "Employee";
    }
}
