package com.company;

import org.json.simple.JSONObject;

public class Appointment {
    private int start;
    private int end;

    private String date;
    private String status;
    private String id;
    private String patient;
    private Requisition requisition;

    public Appointment(int start, int end, String date, String status, String id, String patient, Requisition requisition) {
        this.start = start;
        this.end = end;
        this.date = date;
        this.status = status;
        this.id = id;
        this.patient = patient;
        this.requisition = requisition;
    }

    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();

        obj.put("start", start);
        obj.put("end", end);
        obj.put("date", date);
        obj.put("status", status);
        obj.put("id", id);  // Key
        obj.put("patient", patient);
        obj.put("requisition", requisition.toJSONObject());

        return obj;
    }

    public String getID() {
        return id;
    }
}
