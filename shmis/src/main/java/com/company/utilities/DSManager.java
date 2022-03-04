package com.company.utilities;

import java.util.ArrayList;

import com.company.classes.Appointment;
import com.company.classes.Patient;
import com.company.classes.Person;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * The utility class for managing every data structure behind the functionality of the program.
 */
public class DSManager {
    private long appointmentID;
    private Person currentUser;

    // Data structures
    private ArrayList<Patient> patientList = new ArrayList<>();
    private ArrayList<Appointment> appointmentList = new ArrayList<>();
    private ArrayList<Long> inCalendar = new ArrayList<>();
    private Queue queue = new Queue();

    /**
     * Initializes a data structure manager.
     */
    public DSManager() {
        initPatients(ReadWrite.readFile("patients.json"));
        initAppointments(ReadWrite.readFile("appointments.json"));
        initOtherdata(ReadWrite.readFile("otherdata.json"));
        SearchSort.mergeSort(appointmentList, appointment -> appointment.getId());  // For binary search
    }

    /**
     * Returns the current appointment ID.
     *
     * @return the current appointment ID
     */
    public long getAppointmentID() {
        return appointmentID;
    }

    /**
     * Increases the current appointment ID by 1.
     */
    public void incrAppointmentID() {
        appointmentID++;
    }

    /**
     * Returns the current user.
     *
     * @return the current user
     */
    public Person getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user to a specified user.
     *
     * @param currentUser the user to be set to
     */
    public void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Returns the ArrayList of patients.
     *
     * @return the ArrayList of patients
     */
    public ArrayList<Patient> getPatientList() {
        return patientList;
    }

    /**
     * Returns the ArrayList of appointments.
     *
     * @return the ArrayList of appointments
     */
    public ArrayList<Appointment> getAppointmentList() {
        return appointmentList;
    }

    /**
     * Returns the ArrayList of appointment IDs in the calendar.
     *
     * @return the ArrayList of appointment IDs in the calendar
     */
    public ArrayList<Long> getInCalendar() {
        return inCalendar;
    }

    /**
     * Returns the queue of appointment IDs.
     *
     * @return the queue of appointment IDs
     */
    public Queue getQueue() {
        return queue;
    }

    /**
     * Returns an appointment that corresponds with an appointment ID.
     *
     * @param appId The appointment ID to be searched
     * @return The appointment that corresponds with the ID
     */
    public Appointment query(Long appId) {
        // SearchSort.binarySearch returns an index
        return appointmentList.get(SearchSort.binarySearch(appointmentList, appointment -> appointment.getId(), appId));
    }

    /**
     * Returns <code>True</code> if the current user is a patient.
     *
     * @return <code>True</Code> if the current user is a patient
     */
    public boolean currUserIsPatient() {
        return getCurrentUser().getDesignation().equals("Patient");
    }

    /**
     * Initializes <code>patientList</code> from a JSONObject.
     *
     * @param obj the JSONObject containing all patients
     */
    public void initPatients(JSONObject obj) {
        for (Object value : obj.values()) {
            patientList.add(new Patient((JSONObject)value));
        }
    }

    /**
     *
     * @param obj the JSONObject containing all appointments
     */
    public void initAppointments(JSONObject obj) {
        for (Object value : obj.values()) {
            appointmentList.add(new Appointment((JSONObject)value));
        }
    }

    /**
     * Initializes <code>queue</code>, <code>inCalendar</code>, and the appointment ID from a JSONObject.
     *
     * @param obj the JSONObject containing containing <code>queue</code>, <code>inCalendar</code>, and the appointment ID information
     */
    public void initOtherdata(JSONObject obj) {
        if (!obj.isEmpty()) {  // Otherwise otherdata.json file won't be created
            queue.fromJSONArray((JSONArray) obj.get("queue"));
            inCalendar = (ArrayList<Long>) obj.get("calendar");
            appointmentID = (long) obj.get("appointmentID");
        }
    }

    /**
     * Converts the ArrayList of patients to a JSONObject.
     *
     * @return the converted JSONObject
     */
    public JSONObject packPatients() {
        JSONObject obj = new JSONObject();

        for (Patient patient : patientList) {
            obj.put(patient.getEmail(), patient.toJSONObject());
        }

        return obj;
    }

    /**
     * Converts the ArrayList of appointments to a JSONObject.
     *
     * @return the converted JSONObject
     */
    public JSONObject packAppointments() {
        JSONObject obj = new JSONObject();

        for (Appointment appointment : appointmentList) {
            obj.put(appointment.getId()+"", appointment.toJSONObject());
        }

        return obj;
    }

    /**
     * Returns a JSONObject containing <code>queue</code>, <code>inCalendar</code>, and the appointment ID.
     *
     * @return the converted JSONObject
     */
    public JSONObject packOtherdata() {
        JSONObject obj = new JSONObject();
        JSONArray calendarJSON = new JSONArray();
        calendarJSON.addAll(inCalendar);

        obj.put("queue", queue.toJSONArray());
        obj.put("calendar", calendarJSON);
        obj.put("appointmentID", appointmentID);  // long

        return obj;
    }
}
