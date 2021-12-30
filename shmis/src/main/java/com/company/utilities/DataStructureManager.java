package com.company.utilities;

import java.util.ArrayList;

import com.company.classes.Appointment;
import com.company.classes.Guest;
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
        // initOtherdata();
    }

    // Pack to JSONObject which can then be written to a file
    public JSONObject packPatients() {
        JSONObject obj = new JSONObject();

        for (Patient patient : patientList) {
            obj.put(patient.getId(), patient.toJSONObject());
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
            if (((JSONObject)value).get("designation").equals("Guest"))
                patientList.add(new Guest((JSONObject)value));
            else
                patientList.add(new Patient((JSONObject)value));
        }
    }

    public void initAppointments() {
        JSONObject obj = ReadWrite.readFile("appointments.json");

        for (Object value : obj.values()) {
            appointmentList.add(new Appointment((JSONObject)value));
        }
    }

    public void initOtherdata() {
        JSONObject obj = ReadWrite.readFile("otherdata.json");
    }
}
