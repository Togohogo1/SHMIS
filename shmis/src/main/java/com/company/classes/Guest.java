package com.company.classes;

import java.util.ArrayList;

import org.json.simple.JSONObject;
// TODO what is the purpose of this class
public class Guest extends Patient {
    public Guest(long age, String firstName, String lastName, String gender, String id, String password, String address, String email, String birthdate, String telephone, ArrayList<Long> appointments) {
        super(age, firstName, lastName, gender, id, password, address, email, birthdate, telephone, appointments);
    }

    public Guest(JSONObject obj) {
        super(obj);
    }

    public String getDesignation() {
        return "Guest";
    }
}
