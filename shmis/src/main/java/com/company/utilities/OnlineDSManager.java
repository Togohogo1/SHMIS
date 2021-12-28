package com.company.utilities;

import java.util.HashMap;

import com.company.classes.Appointment;
import com.company.classes.Patient;

import org.json.simple.JSONObject;

public class OnlineDSManager {
    public static HashMap<String, Patient> patientMap;  // Includes pointers to appointmentMap
    public static HashMap<String, Appointment> appointmentMap;
    // TODO patientArrayList for the db
    // TODO sortedPatientArrayList for soted db
    // TODO Queue for the queue
    // TODO Waitlist for the waitlist
    // TODO appointmentArrayList for the calendar

    // don't have own impl of hashmap so i put these functions here
    public static HashMap<String, Patient> mapPatients() {
        HashMap<String, Patient> mp = new HashMap<String, Patient>();
        JSONObject obj = ReadWrite.readFile("patients.json");

        for (Object entry : obj.entrySet()) {
            String key = ((HashMap.Entry<String, JSONObject>)entry).getKey();
            JSONObject value = ((HashMap.Entry<String, JSONObject>)entry).getValue();
            mp.put(key, Patient.fromJSONObject(value));
        }

        return mp;
    }

    // not pure JSON hashmap since I may need to create patient objects
    public static HashMap<String, Appointment> mapAppointments() {
        HashMap<String, Appointment> mp = new HashMap<String, Appointment>();
        JSONObject obj = ReadWrite.readFile("appointments.json");

        for (Object entry : obj.entrySet()) {
            String key = ((HashMap.Entry<String, JSONObject>)entry).getKey();
            JSONObject value = ((HashMap.Entry<String, JSONObject>)entry).getValue();
            mp.put(key, Appointment.fromJSONObject(value));
        }

        return mp;
    }
}
