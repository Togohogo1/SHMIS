package com.company.classes;

public class Employee extends Person {
    public Employee() {
        super(30, "admin_f", "admin_l", "M/F", "0", "password");
    }

    public String getDesignation() {
        return "Employee";
    }
}
