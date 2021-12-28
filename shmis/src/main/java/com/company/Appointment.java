package com.company;

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

    private String date;
    private String status;
    private String id;
    private String patient;
    private String referralDoctor;
    private String notes;

    private boolean[] xRay;
    private boolean[] ultrasound;

    public Appointment(long start, long end, String date, String status, String id, String patient, String referralDoctor, String notes, boolean[] xRay, boolean[] ultrasound) {
        this.start = start;
        this.end = end;
        this.date = date;
        this.status = status;
        this.id = id;
        this.patient = patient;
        this.referralDoctor = referralDoctor;
        this.notes = notes;
        this.xRay = xRay;
        this.ultrasound = ultrasound;
    }

    public static Appointment fromJSONObject(JSONObject obj) {
        // convert from JSONArray to array
        boolean[] xRay = new boolean[7], ultrasound = new boolean[4];
        JSONArray xRayJSON = (JSONArray) obj.get("xRay"), ultrasoundJSON = (JSONArray) obj.get("ultrasound");

        for (int i = 0; i < 7; i++) {
            xRay[i] = (boolean) xRayJSON.get(i);
        }

        for (int i = 0; i < 4; i++) {
            ultrasound[i] = (boolean) ultrasoundJSON.get(i);
        }

        return new Appointment(
            (long) obj.get("start"),
            (long) obj.get("end"),
            (String) obj.get("date"),
            (String) obj.get("status"),
            (String) obj.get("id"),
            (String) obj.get("patient"),
            (String) obj.get("referralDoctor"),
            (String) obj.get("notes"),
            xRay,
            ultrasound
        );
    }

    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        JSONArray xRay = new JSONArray(), ultrasound = new JSONArray();  // TODO separate?

        for (boolean i : this.xRay) {
            xRay.add(i);
        }

        for (boolean i : this.ultrasound) {
            ultrasound.add(i);
        }

        obj.put("start", start);
        obj.put("end", end);
        obj.put("date", date);
        obj.put("status", status);
        obj.put("id", id);  // Key
        obj.put("referralDoctor", referralDoctor);
        obj.put("notes", notes);
        obj.put("xRay", xRay);
        obj.put("ultrasound", ultrasound);

        return obj;
    }

    // Patient class references Appointment ID
    public String getID() {
        return id;
    }

    public String toString() {
        return "fddfd";
    }
}
