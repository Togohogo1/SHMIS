package com.company;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
        appointments = new ArrayList<>();
    }

    public JSONObject toJSONObject() {
        JSONObject outer = new JSONObject(), inner = new JSONObject();
        JSONArray appointments = new JSONArray();

        // Only need appointment id
        for (Appointment i : this.appointments) {
            appointments.add(i.getID());
        }

        // From Person class
        inner.put("age", age);
        inner.put("firstName", firstName);
        inner.put("lastName", lastName);
        inner.put("gender", gender);
        inner.put("id", id);
        inner.put("password", password);

        // From Patient class
        inner.put("address", address);
        inner.put("email", email);  // Key
        inner.put("birthdate", birthdate);
        inner.put("telephone", telephone);
        inner.put("appointments", appointments);

        outer.put(email, inner);
        return outer;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}
