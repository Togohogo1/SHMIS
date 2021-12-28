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

    ArrayList<Appointment> appointments;

    public Patient(long age, String firstName, String lastName, String gender, String id, String password, String address, String email, String birthdate, String telephone) {
        super(age, firstName, lastName, gender, id, password);
        this.address = address;
        this.email = email;
        this.birthdate = birthdate;
        this.telephone = telephone;
        appointments = new ArrayList<>();
    }

    public static Patient fromJSONObject(JSONObject obj) {
        return new Patient(
            (long) obj.get("age"),
            (String) obj.get("firstName"),
            (String) obj.get("lastName"),
            (String) obj.get("gender"),
            (String) obj.get("id"),
            (String) obj.get("password"),
            (String) obj.get("address"),
            (String) obj.get("email"),
            (String) obj.get("birthdate"),
            (String) obj.get("telephone")
        );
    }

    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        JSONArray appointments = new JSONArray();

        // Only need appointment id
        for (Appointment app : this.appointments) {
            appointments.add(app.getID());
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}
