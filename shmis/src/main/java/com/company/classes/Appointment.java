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
    private long end;
    private long id;

    private String date;
    private String status;
    private String patient;
    private String referralDoctor;
    private String notes;

    private ArrayList<Boolean> xRay;
    private ArrayList<Boolean> ultrasound;

    public Appointment(long start, long end, long id, String date, String status, String patient, String referralDoctor, String notes, ArrayList<Boolean> xRay, ArrayList<Boolean> ultrasound) {
        this.start = start;
        this.end = end;
        this.id = id;
        this.date = date;
        this.status = status;
        this.patient = patient;
        this.referralDoctor = referralDoctor;
        this.notes = notes;
        this.xRay = xRay;
        this.ultrasound = ultrasound;
    }

    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        JSONArray xRay = new JSONArray();
        JSONArray ultrasound = new JSONArray();

        // convert from JSONArray to ArrayList
        xRay.addAll(this.xRay);
        ultrasound.addAll(this.ultrasound);

        obj.put("start", start);
        obj.put("end", end);
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

    public Long getId() {
        return id;
    }

    // Patient class references Appointment ID
    public String getKey() {
        return Long.toString(id);
    }
}
