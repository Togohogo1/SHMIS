package com.company;

import java.util.ArrayList;

public class Patient extends Person {
    protected String address;
    protected String email;
    protected String birthdate;
    protected String telephone;

    ArrayList<Appointment> appointments;

    public Patient(int age, String firstName, String lastName, String gender, String id, String password, String address, String email, String birthdate, String telephone) {
        super(age, firstName, lastName, gender, id, password);
        this.address = address;
        this.email = email;
        this.birthdate = birthdate;
        this.telephone = telephone;
    }
}
