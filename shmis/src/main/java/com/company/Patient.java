package com.company;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Patient extends Person {
    protected String address;
    protected String email;
    protected String birthdate;
    protected String telephone;
    protected String designation = "Patient";

    ArrayList<Appointment> appointments;

    public Patient(int age, String firstName, String lastName, String gender, String id, String password, String address, String email, String birthdate, String telephone) {
        super(age, firstName, lastName, gender, id, password);
        this.address = address;
        this.email = email;
        this.birthdate = birthdate;
        this.telephone = telephone;
        appointments = new ArrayList<>();
    }

    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        JSONArray appointments = new JSONArray();

        // Only need appointment id
        for (Appointment i : this.appointments) {
            appointments.add(i.getID());
        }

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

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}
