package com.company.classes;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * The patient contains more specific details than a person.
 */
public class Patient extends Person {
    protected String address;
    protected String email;
    protected String telephone;
    protected ArrayList<Long> appointments;

    /**
     * Initiazlies a patient from constructor parameters.
     *
     * @param age Patient age
     * @param firstName Patient first name
     * @param lastName Patient last name
     * @param gender Patient gender
     * @param id Patient ID
     * @param password Patient password
     * @param address Patient address
     * @param email Patient email
     * @param telephone Patient telephone
     * @param appointments Patient appointments
     */
    public Patient(long age, String firstName, String lastName, String gender, String id, String password, String address, String email, String telephone, ArrayList<Long> appointments) {
        super(age, firstName, lastName, gender.toUpperCase(), id, password);
        this.address = address;
        this.email = email.toLowerCase();
        this.telephone = telephone;
        this.appointments = appointments;
    }

    /**
     * Initializes a from a <code>JSONObject</code>.
     *
     * @param obj The <code>JSONObject</code>
     */
    public Patient(JSONObject obj) {
        this.age = (long) obj.get("age");
        this.firstName = (String) obj.get("firstName");
        this.lastName = (String) obj.get("lastName");
        this.gender = ((String)obj.get("gender")).toUpperCase();
        this.id = (String) obj.get("id");
        this.password = (String) obj.get("password");
        this.address = (String) obj.get("address");
        this.email = (String) obj.get("email");
        this.telephone = (String) obj.get("telephone");
        this.appointments = (ArrayList<Long>) obj.get("appointments");  // convert from JSONArray to ArrayList
    }

    /**
     * Converts a patient to a <code>JSONObject</code>.
     *
     * @return the patient converted to a <code>JSONObject</code>
     */
    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        JSONArray appointments = new JSONArray();
        appointments.addAll(this.appointments);  // convert from ArrayList to JSONArray

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
        obj.put("telephone", telephone);
        obj.put("designation", getDesignation());
        obj.put("appointments", appointments);

        return obj;
    }

    /**
     * Returns the patient's address.
     *
     * @return the patient's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the patient's email.
     *
     * @return the patient's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the patient's telephone.
     *
     * @return the patient's telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Returns the patient's appointments.
     *
     * @return the patient's appointments
     */
    public ArrayList<Long> getAppointments() {
        return this.appointments;
    }

    /**
     * Sets the patient's address to a new address.
     *
     * @param address The new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the patient's email to a new email.
     *
     * @param email The new email
     */
    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    /**
     * Sets the patient's telephone to a new telephone.
     *
     * @param telephone The new telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Adds an appointment ID to the patient's appointment list.
     *
     * @param id The appointment ID
     */
    public void addAppointment(long id) {
        appointments.add(id);
    }

    /**
     * Returns the type of person.
     */
    @Override
    public String getDesignation() {
        return "Patient";
    }
}
