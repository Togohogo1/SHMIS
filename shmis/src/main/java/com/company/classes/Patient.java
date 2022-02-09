package com.company.classes;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Patient extends Person {
    protected String address;
    protected String email;
    protected String birthdate;
    protected String telephone;
    protected ArrayList<Long> appointments;

    public Patient(long age, String firstName, String lastName, String gender, String id, String password, String address, String email, String birthdate, String telephone, ArrayList<Long> appointments) {
        super(age, firstName, lastName, gender, id, password);
        this.address = address;
        this.email = email;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.appointments = appointments;
    }

    public Patient(JSONObject obj) {
        this.age = (long) obj.get("age");
        this.firstName = (String) obj.get("firstName");
        this.lastName = (String) obj.get("lastName");
        this.gender = (String) obj.get("gender");
        this.id = (String) obj.get("id");
        this.password = (String) obj.get("password");
        this.address = (String) obj.get("address");
        this.email = (String) obj.get("email");
        this.birthdate = (String) obj.get("birthdate");
        this.telephone = (String) obj.get("telephone");
        this.appointments = (ArrayList<Long>) obj.get("appointments");  // convert from JSONArray to ArrayList
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
        obj.put("designation", getDesignation());
        obj.put("appointments", appointments);

        return obj;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getTelephone() {
        return telephone;
    }

    public ArrayList<Long> getAppointments() {
        return this.appointments;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void addAppointment(long id) {
        appointments.add(id);
    }

    public String getDesignation() {
        return "Patient";
    }
}
