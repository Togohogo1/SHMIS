package com.company.pages.program.tablemodels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.company.App;
import com.company.classes.Appointment;
import com.company.classes.Person;

/**
 * Customized table model for displaying calendar events.
 */
public class CalendarTableModel extends AbstractTableModel {
    private String day;
    private ArrayList<Appointment> events;

    public CalendarTableModel(String day, ArrayList<Appointment> events) {
        this.day = day;
        this.events = events;
    }

    @Override
    public int getRowCount() {
        return events.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Appointment appointment = events.get(rowIndex);
        Person currentUser = App.dsm.getCurrentUser();
        String designation = currentUser.getDesignation();
        String fromTo = appointment.getStartTable() + " to " + appointment.getEndTable();


        if (designation.equals("Employee"))
            return fromTo  + " - " + appointment.getPatient();
        else {
            if (appointment.getPatient().equals(currentUser.getFirstName() + " " + currentUser.getLastName()))
                return fromTo + " - You";  // Current user's appointment
            else
                return fromTo;
        }
            // TODO diff if patient
    }

    public String getColumnName(int col) {
        return day;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public String getStatusFromEvents(int row) {
        return events.get(row).getStatus();
    }

    public String getDay() {
        return day;
    }
}
