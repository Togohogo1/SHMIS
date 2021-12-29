package com.company.classes;

import java.util.ArrayList;

public class Guest extends Patient {
    public Guest(int age, String firstName, String lastName, String gender, String id, String password, String address, String email, String birthdate, String telephone, ArrayList<Long> appointments) {
        super(age, firstName, lastName, gender, id, password, address, email, birthdate, telephone, appointments);
        designation = "Guest";
    }
}
