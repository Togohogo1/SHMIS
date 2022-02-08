package com.company.classes;

public class Employee extends Person {
    public Employee() {
        super(30, "admin_f", "admin_l", "M", "0", "password");
    }

    public String getDesignation() {
        return "Employee";
    }
}
