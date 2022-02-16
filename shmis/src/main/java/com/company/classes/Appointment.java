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

    // Ultrasound
    public static final int GENERAL = 0;
    public static final int OBSTETRICAL = 1;
    public static final int MUSKULOSKELETAL = 2;
    public static final int CARDIOVASCULAR = 3;

    private long start;
    private long span;
    private long id;

    private String date;
    private String status;
    private String patient;
    private String referralDoctor;
    private String notes;

    private ArrayList<Boolean> xRay;

    public Appointment(JSONObject obj) {
        ArrayList<Boolean> xRay = new ArrayList<>();
        ArrayList<Boolean> ultrasound = new ArrayList<>();
        JSONArray xRayJSON = (JSONArray) obj.get("xRay");
        JSONArray ultrasoundJSON = (JSONArray) obj.get("ultrasound");

        // convert from JSONArray to ArrayList
        xRay = (ArrayList<Boolean>) xRayJSON;

        this.start = (long) obj.get("start");
        this.span = (long) obj.get("span");
        this.id = (long) obj.get("id");
        this.date = (String) obj.get("date");
        this.status = (String) obj.get("status");
        this.patient = (String) obj.get("patient");
        this.referralDoctor = (String) obj.get("referralDoctor");
        this.notes = (String) obj.get("notes");
        this.xRay = xRay;
    }

    // This constructor used when converting to JSONObject is an extra step
    public Appointment(long start, long span, long id, String date, String status, String patient, String referralDoctor, String notes, ArrayList<Boolean> xRay, ArrayList<Boolean> ultrasound) {
        this.start = start;
        this.span = span;
        this.id = id;
        this.date = date;
        this.status = status;
        this.patient = patient;
        this.referralDoctor = referralDoctor;
        this.notes = notes;
        this.xRay = xRay;
    }

    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        JSONArray xRay = new JSONArray();
        JSONArray ultrasound = new JSONArray();

        // convert from JSONArray to ArrayList
        xRay.addAll(this.xRay);

        obj.put("start", start);
        obj.put("span", span);
        obj.put("id", id);  // Key
        obj.put("date", date);
        obj.put("status", status);
        obj.put("patient", patient);
        obj.put("referralDoctor", referralDoctor);
        obj.put("notes", notes);
        obj.put("xRay", xRay);
        obj.put("ultrasound", ultrasound);

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

    public ArrayList<Boolean> getXRay() {
        return xRay;
    }
}
