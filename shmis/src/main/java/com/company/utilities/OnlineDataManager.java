package com.company.utilities;

import java.util.ArrayList;

import com.company.classes.Appointment;
import com.company.classes.Patient;

import org.json.simple.JSONObject;

public class OnlineDataManager {
    public static ArrayList<Patient> patientList;
    public static ArrayList<Patient> patientListSort;
    public static ArrayList<Appointment> appointmentList;
    // TODO Queue for the queue
    // TODO Waitlist for the waitlist
    // TODO appointmentArrayList for the calendar

    // don't have own impl of hashmap so i put these functions here

    public OnlineDataManager() {
        patientList = mapPatients();
        appointmentList = mapAppointments();
        patientListSort = (ArrayList<Patient>) patientList.clone();
    }

    public static ArrayList<Patient> mapPatients() {
        ArrayList<Patient> arr = new ArrayList<Patient>();
        JSONObject obj = ReadWrite.readFile("patients.json");

        for (Object value : obj.values()) {
            arr.add(Patient.fromJSONObject((JSONObject)value));
        }

        return arr;
    }

    public static ArrayList<Appointment> mapAppointments() {
        ArrayList<Appointment> arr = new ArrayList<Appointment>();
        JSONObject obj = ReadWrite.readFile("appointments.json");

        for (Object value : obj.values()) {
            arr.add(Appointment.fromJSONObject((JSONObject)value));
        }

        return arr;
    }
}
