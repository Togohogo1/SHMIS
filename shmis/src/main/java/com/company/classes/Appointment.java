package com.company.classes;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Appointments are the objects that tie patient and employee functionality
 * together.
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

    /**
     * Initializes an appointment from multiple constructor parameters.
     *
     * @param start          The appointment's start time
     * @param span           The appointment's duration
     * @param id             The appointment's ID
     * @param date           The appointment's date
     * @param status         The appointment's status
     * @param patient        The appointment's patient's full name
     * @param referralDoctor The appointment's referral doctor
     * @param notes          The appointment's notes
     * @param imaging        The appointment's imaging details
     */
    public Appointment(long start, long span, long id, String date, String status, String patient,
            String referralDoctor, String notes, ArrayList<Boolean> imaging) {
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
        imaging = (ArrayList<Boolean>) imagingJSON; // convert from JSONArray to ArrayList

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
     * @return The appointment converted to a <code>JSONObject</code>
     */
    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        JSONArray imaging = new JSONArray();
        imaging.addAll(this.imaging);

        obj.put("start", start);
        obj.put("span", span);
        obj.put("id", id); // Key for JSONObject
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
     * @return The start time as a long
     */
    public long getStart() {
        return start;
    }

    /**
     * Returns the start time formatted as HH:MM.
     *
     * @return The start time formatted as HH:MM
     */
    public String getStartTable() {
        long min = start * 30 + 540; // 540 is number of minutes from 00:00 to 9:00
        return String.format("%02d:%02d", min / 60, min % 60);
    }

    /**
     * Returns the appointment's duration as a long.
     *
     * @return The appointment's duration as a long
     */
    public long getSpan() {
        return span;
    }

    /**
     * Returns the appointment's end time formatted as HH:MM.
     *
     * @return The appointment's end time formatted as HH:MM
     */
    public String getEndTable() {
        long min = (start + span) * 30 + 540;
        return String.format("%02d:%02d", min / 60, min % 60);
    }

    /**
     * Returns the appointment's ID.
     *
     * @return The appointment's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the appointment's date.
     *
     * @return The appointment's date
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the appointment's status.
     *
     * @return The appointment's status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the appointment's patient's full name.
     *
     * @return The appointment's patient's full name
     */
    public String getPatient() {
        return patient;
    }

    /**
     * Returns the appointment's referral doctor.
     *
     * @return The appointment's referral doctor
     */
    public String getReferralDoctor() {
        return referralDoctor;
    }

    /**
     * Returns the appointment's notes.
     *
     * @return The appointment's notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Returns the appointment's imaging details.
     *
     * @return The appointment's imaging details
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
