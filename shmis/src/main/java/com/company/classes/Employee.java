package com.company.classes;

public class Employee extends Person {
    public Employee(long age, String firstName, String lastName, String gender, String id, String password) {
        super(age, firstName, lastName, gender, id, password);
    }

    public String getDesignation() {
        return "Employee";
    }
}
