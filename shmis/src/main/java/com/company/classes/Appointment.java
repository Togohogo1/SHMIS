package com.company.classes;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Appointment {
    // X-Ray
    public static final int ABDOMEN = 0;
    public static final int HEAD_AND_NECK = 1;
    public static final int CHEST = 2;
    public static final int SKELETAL = 3;
    public static final int SPINE_AND_PELVIS = 4;
    public static final int UPPER_EXTREMETIES = 5;
    public static final int LOWER_EXTREMETIES = 6;

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

    // This constructor used when converting to JSONObject is an extra step
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

    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        JSONArray imaging = new JSONArray();

        // convert from JSONArray to ArrayList
        imaging.addAll(this.imaging);

        obj.put("start", start);
        obj.put("span", span);
        obj.put("id", id);  // Key
        obj.put("date", date);
        obj.put("status", status);
        obj.put("patient", patient);
        obj.put("referralDoctor", referralDoctor);
        obj.put("notes", notes);
        obj.put("imaging", imaging);

        return obj;
    }

    public long getStart() {
        return start;
    }

    public String getStartTable() {
        long min = start*30 + 540;
        return String.format("%02d:%02d", min/60, min%60);
    }

    public long getSpan() {
        return span;
    }

    public String getEndTable() {
        long min = (start+span)*30 + 540;
        return String.format("%02d:%02d", min/60, min%60);
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getPatient() {
        return patient;
    }

    public String getReferralDoctor() {
        return referralDoctor;
    }

    public String getNotes() {
        return notes;
    }

    public ArrayList<Boolean> getImaging() {
        return imaging;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
}
