package com.company.classes;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Patient extends Person {
    protected String address;
    protected String email;
    protected String birthdate;
    protected String telephone;
    protected String designation = "Patient";
    protected ArrayList<Long> appointments;

    public Patient(long age, String firstName, String lastName, String gender, String id, String password, String address, String email, String birthdate, String telephone, ArrayList<Long> appointments) {
        super(age, firstName, lastName, gender, id, password);
        this.address = address;
        this.email = email;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.appointments = appointments;
    }

    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        JSONArray appointments = new JSONArray();

        // convert from ArrayList to JSONArray
        appointments.addAll(this.appointments);

        // From Person class
        obj.put("age", age);
        obj.put("firstName", firstName);
        obj.put("lastName", lastName);
        obj.put("gender", gender);
        obj.put("id", id);
        obj.put("password", password);

        // From Patient class
        obj.put("address", address);
        obj.put("email", email);  // Key
        obj.put("birthdate", birthdate);
        obj.put("telephone", telephone);
        obj.put("designation", designation);
        obj.put("appointments", appointments);

        return obj;
    }

    public String getID() {
        return id;
    }

    public boolean lessEqualsFirstName(Patient other) {
        return this.firstName.compareTo(other.firstName) <= 0;
    }

    public boolean lessEqualsLastName(Patient other) {
        return this.lastName.compareTo(other.lastName) <= 0;
    }

    public boolean lessEqualEmail(Patient other) {
        return this.email.compareTo(other.email) <= 0;
    }

    public void addAppointment(long appointmentID) {
        appointments.add(appointmentID);
    }
}
