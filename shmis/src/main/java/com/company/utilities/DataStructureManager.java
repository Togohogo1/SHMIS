package com.company.utilities;

import java.util.ArrayList;

import com.company.classes.Appointment;
import com.company.classes.Patient;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataStructureManager {
    public ArrayList<Patient> patientList;
    public ArrayList<Patient> patientListSort;
    public ArrayList<Appointment> appointmentList;
    // TODO Queue for the queue
    // TODO Waitlist for the waitlist
    // TODO appointmentArrayList for the calendar

    // don't have own impl of hashmap so i put these functions here

    public DataStructureManager() {
        patientList = mapPatients();
        appointmentList = mapAppointments();
        patientListSort = (ArrayList<Patient>) patientList.clone();
    }

    public ArrayList<Patient> mapPatients() {
        ArrayList<Patient> arr = new ArrayList<Patient>();
        JSONObject obj = ReadWrite.readFile("patients.json");

        for (Object value : obj.values()) {
            arr.add(Patient.fromJSONObject((JSONObject)value));
        }

        return arr;
    }

    public ArrayList<Appointment> mapAppointments() {
        ArrayList<Appointment> arr = new ArrayList<Appointment>();
        JSONObject obj = ReadWrite.readFile("appointments.json");

        for (Object value : obj.values()) {
            arr.add(JSONToAppointment((JSONObject)value));
        }

        return arr;
    }

    public Appointment JSONToAppointment(JSONObject obj) {
        // convert from JSONArray to array
        boolean[] xRay = new boolean[7];
        boolean[] ultrasound = new boolean[4];
        JSONArray xRayJSON = (JSONArray) obj.get("xRay");
        JSONArray ultrasoundJSON = (JSONArray) obj.get("ultrasound");  // TODO separate? decide later, also for the method below

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

    public Patient JSONToPatient(JSONObject obj) {
        return null;
    }
}
