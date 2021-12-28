package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Requisition {
    // X-Ray
    // 0 = Abdomen
    // 1 = HeadAndNeck
    // 2 = Chest
    // 3 = Skeletal
    // 4 = SpineAndPelvis
    // 5 = UpperExtremeties
    // 6 = LowerExtremeties
    private boolean[] xRay;

    // Ultrasound
    // 0 = General
    // 1 = Obsetrical
    // 2 = Muskuloskeletal
    // 3 = Cardiovascular
    private boolean[] ultrasound;

    // Other
    private String referralDoctor;
    private String notes;

    public Requisition(boolean[] xRay, boolean[] ultrasound, String referralDoctor, String notes) {
        this.xRay = xRay;
        this.ultrasound = ultrasound;
        this.referralDoctor = referralDoctor;
        this.notes = notes;
    }

    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        JSONArray xRay = new JSONArray(), ultrasound = new JSONArray();

        for (boolean i : this.xRay) {
            xRay.add(i);
        }

        for (boolean i : this.ultrasound) {
            ultrasound.add(i);
        }

        obj.put("xRay", xRay);
        obj.put("ultrasound", ultrasound);
        obj.put("referralDoctor", referralDoctor);
        obj.put("notes", notes);

        return obj;
    }
}
