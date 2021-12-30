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
    public long appointmentID;
    public ArrayList<Patient> patientList = new ArrayList<>();
    public ArrayList<Appointment> appointmentList = new ArrayList<>();
    public ArrayList<Appointment> inCalendar = new ArrayList<>();
    public Queue queue = new Queue();
    public DoublyLinkedList waitlist = new DoublyLinkedList();

    public DataStructureManager() {
        initPatients();
        initAppointments();
        initOtherdata();
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
            obj.put(appointment.getKey(), appointment.toJSONObject());
        }

        return obj;
    }

    public JSONObject packOtherdata() {
        JSONObject obj = new JSONObject();
        return obj;
    }

    // unpack from file to JSONObject to Arraylist
    public void initPatients() {
        JSONObject obj = ReadWrite.readFile("patients.json");

        for (Object value : obj.values()) {
            patientList.add(JSONToPatient((JSONObject)value));
        }
    }

    public void initAppointments() {
        JSONObject obj = ReadWrite.readFile("appointments.json");

        for (Object value : obj.values()) {
            appointmentList.add(JSONToAppointment((JSONObject)value));
        }
    }

    public void initOtherdata() {
        JSONObject obj = ReadWrite.readFile("otherdata.json");
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
        ArrayList<Boolean> xRay = new ArrayList<>();
        ArrayList<Boolean> ultrasound = new ArrayList<>();
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
