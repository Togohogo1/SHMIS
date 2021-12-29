package com.company.utilities;

import java.util.ArrayList;

import com.company.classes.Appointment;
import com.company.classes.Patient;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataStructureManager {
    // TODO make private if not testing
    // TODO think about JSONObject being an intermediate
    // TODO other.json for queue.waitlist/calendar storage
    public ArrayList<Patient> patientList;
    public ArrayList<Appointment> appointmentList;
    // TODO Queue for the queue
    // TODO Waitlist for the waitlist
    // TODO appointmentArrayList for the calendar

    public DataStructureManager() {
        patientList = mapPatients();
        appointmentList = mapAppointments();
    }

    // Pack to JSONObject which can then be written to a file
    public JSONObject packPatients() {
        JSONObject obj = new JSONObject();

        for (Patient patient : patientList) {
            obj.put(patient.getID(), patient.toJSONObject());
        }

        return obj;
    }

    public JSONObject packAppointments() {
        JSONObject obj = new JSONObject();

        for (Appointment appointment : appointmentList) {
            obj.put(appointment.getIDAsKey(), appointment.toJSONObject());
        }

        return obj;
    }

    // unpack from file to JSONObject to Arraylist
    public ArrayList<Patient> mapPatients() {
        ArrayList<Patient> arr = new ArrayList<Patient>();
        JSONObject obj = ReadWrite.readFile("patients.json");

        for (Object value : obj.values()) {
            arr.add(JSONToPatient((JSONObject)value));
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

    public Patient JSONToPatient(JSONObject obj) {
        return new Patient(
            (long) obj.get("age"),
            (String) obj.get("firstName"),
            (String) obj.get("lastName"),
            (String) obj.get("gender"),
            (String) obj.get("id"),
            (String) obj.get("password"),
            (String) obj.get("address"),
            (String) obj.get("email"),
            (String) obj.get("birthdate"),
            (String) obj.get("telephone"),
            (ArrayList<Long>) obj.get("appointments")  // convert from JSONArray to ArrayList
        );
    }

    public Appointment JSONToAppointment(JSONObject obj) {
        ArrayList<Boolean> xRay = new ArrayList<Boolean>();
        ArrayList<Boolean> ultrasound = new ArrayList<Boolean>();
        JSONArray xRayJSON = (JSONArray) obj.get("xRay");
        JSONArray ultrasoundJSON = (JSONArray) obj.get("ultrasound");

          // convert from JSONArray to ArrayList
        xRay = (ArrayList<Boolean>) xRayJSON;
        ultrasound = (ArrayList<Boolean>) ultrasoundJSON;

        return new Appointment(
            (long) obj.get("start"),
            (long) obj.get("end"),
            (long) obj.get("id"),
            (String) obj.get("date"),
            (String) obj.get("status"),
            (String) obj.get("patient"),
            (String) obj.get("referralDoctor"),
            (String) obj.get("notes"),
            xRay,
            ultrasound
        );
    }
}
