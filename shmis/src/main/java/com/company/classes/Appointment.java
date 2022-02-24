package com.company.classes;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Appointments are the objects that tie patient and employee functionality together.
 */
public class Appointment {
    private long start;
    private long span;
    private long id;

    private String date;
    private String status;
    private String patient;
    private String referralDoctor;
    private String notes;

    private ArrayList<Boolean> imaging;
    // TODO rename imaging to concerns

    /**
     * Initializes an appointment from constructor parameters.
     *
     * @param start Appointment start time
     * @param span Appointment duration
     * @param id Appointment ID
     * @param date Appointment date
     * @param status Appointment status
     * @param patient Appointment's patient's full name
     * @param referralDoctor Appointment referral doctor
     * @param notes Appointment notes
     * @param imaging Appointment concerns
     */
    public Appointment(long start, long span, long id, String date, String status, String patient, String referralDoctor, String notes, ArrayList<Boolean> imaging) {
        this.start = start;
        this.span = span;
        this.id = id;
        this.date = date;
        this.status = status;
        this.patient = patient;
        this.referralDoctor = referralDoctor;
        this.notes = notes;
        this.imaging = imaging;
    }

    /**
     * Initializes an appointment from a <code>JSONObject</code>.
     *
     * @param obj The <code>JSONObject</code>
     */
    public Appointment(JSONObject obj) {
        ArrayList<Boolean> imaging = new ArrayList<>();
        JSONArray imagingJSON = (JSONArray) obj.get("imaging");

        // convert from JSONArray to ArrayList
        imaging = (ArrayList<Boolean>) imagingJSON;

        this.start = (long) obj.get("start");
        this.span = (long) obj.get("span");
        this.id = (long) obj.get("id");
        this.date = (String) obj.get("date");
        this.status = (String) obj.get("status");
        this.patient = (String) obj.get("patient");
        this.referralDoctor = (String) obj.get("referralDoctor");
        this.notes = (String) obj.get("notes");
        this.imaging = imaging;
    }

    /**
     * Converts an appointment to a <code>JSONObject</code>.
     *
     * @return the appointment converted to a <code>JSONObject</code>
     */
    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        JSONArray imaging = new JSONArray();

        // Convert from JSONArray to ArrayList
        imaging.addAll(this.imaging);

        obj.put("start", start);
        obj.put("span", span);
        obj.put("id", id);  // Key for JSONObject
        obj.put("date", date);
        obj.put("status", status);
        obj.put("patient", patient);
        obj.put("referralDoctor", referralDoctor);
        obj.put("notes", notes);
        obj.put("imaging", imaging);

        return obj;
    }

    /**
     * Returns the appointment's start time as a long.
     *
     * @return the start time as a long
     */
    public long getStart() {
        return start;
    }

    /**
     * Returns the start time formatted as HH:MM.
     *
     * @return the start time formatted as HH:MM.
     */
    public String getStartTable() {
        long min = start*30 + 540;
        return String.format("%02d:%02d", min/60, min%60);
    }

    /**
     * Returns the appointment's duration as a long.
     *
     * @return the appointment's duration as a long.
     */
    public long getSpan() {
        return span;
    }

    /**
     * Returns the appointment's end time formatted as HH:MM.
     *
     * @return the appointment's end time formatted as HH:MM
     */
    public String getEndTable() {
        long min = (start+span)*30 + 540;
        return String.format("%02d:%02d", min/60, min%60);
    }

    /**
     * Returns the appointment's ID.
     *
     * @return the appointment's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the appointment's date.
     *
     * @return the appointment's date
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the appointment's status.
     *
     * @return the appointment's status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the appointment's patient's full name.
     *
     * @return the appointment's patient's full name
     */
    public String getPatient() {
        return patient;
    }

    /**
     * Returns the appointment's referral doctor.
     *
     * @return the appointment's referral doctor.
     */
    public String getReferralDoctor() {
        return referralDoctor;
    }

    /**
     * Returns the appointment's notes.
     *
     * @return the appointment's notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Returns the appointment's concerns.
     *
     * @return the appointment's concerns
     */
    public ArrayList<Boolean> getImaging() {
        return imaging;
    }

    /**
     * Sets the appointment's status to a new status.
     *
     * @param status The new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Sets the appointment's patient's full name to a new full name.
     *
     * @param patient The new full name
     */
    public void setPatient(String patient) {
        this.patient = patient;
    }
}
