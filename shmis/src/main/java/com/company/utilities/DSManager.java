package com.company.utilities;

import java.util.ArrayList;

import com.company.classes.Appointment;
import com.company.classes.Patient;
import com.company.classes.Person;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DSManager {
    private long appointmentID;
    private Person currentUser;

    private ArrayList<Patient> patientList = new ArrayList<>();
    private ArrayList<Appointment> appointmentList = new ArrayList<>();
    private ArrayList<Long> inCalendar = new ArrayList<>();
    private Queue queue = new Queue();

    public DSManager() {
        initPatients(ReadWrite.readFile("patients.json"));
        initAppointments(ReadWrite.readFile("appointments.json"));
        initOtherdata(ReadWrite.readFile("otherdata.json"));
    }

    public long getAppointmentID() {
        return appointmentID;
    }

    public void incrAppointmentID() {
        appointmentID++;
    }

    public Person getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public ArrayList<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public ArrayList<Long> getInCalendar() {
        return inCalendar;
    }

    public Queue getQueue() {
        return queue;
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
            obj.put(""+appointment.getId(), appointment.toJSONObject());
        }

        return obj;
    }

    public JSONObject packOtherdata() {
        JSONObject obj = new JSONObject();
        JSONArray calendarJSON = new JSONArray();

        calendarJSON.addAll(inCalendar);

        obj.put("queue", queue.toJSONArray());  // JSONArray
        obj.put("calendar", calendarJSON);  // JSONArray
        obj.put("appointmentID", appointmentID);  // long

        return obj;
    }

    // unpack from file to JSONObject to Arraylist
    public void initPatients(JSONObject obj) {
        for (Object value : obj.values()) {
            patientList.add(new Patient((JSONObject)value));
        }
    }

    public void initAppointments(JSONObject obj) {
        for (Object value : obj.values()) {
            appointmentList.add(new Appointment((JSONObject)value));
        }
    }

    public void initOtherdata(JSONObject obj) {
        queue.fromJSONArray((JSONArray) obj.get("queue"));
        inCalendar = (ArrayList<Long>) obj.get("calendar");
        appointmentID = (long) obj.get("appointmentID");
    }

    public Appointment query(Long idx) {
        return appointmentList.get(SearchSort.binarySearch(appointmentList, appointment -> appointment.getId(), idx));
    }
}
