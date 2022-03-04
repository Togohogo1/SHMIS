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

    /**
     * Initializes a calendar table model with a day and a list of appointments.
     *
     * @param day The day of the week
     * @param events The list of appointments
     */
    public CalendarTableModel(String day, ArrayList<Appointment> events) {
        this.day = day;
        this.events = events;
    }

    /**
     * Returns the row count.
     */
    @Override
    public int getRowCount() {
        return events.size();
    }

    /**
     * Returns the column count.
     */
    @Override
    public int getColumnCount() {
        return 1;  // Only requires 1 column
    }

    /**
     * Returns the value at a specified row and column.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Appointment appointment = events.get(rowIndex);
        Person currentUser = App.dsm.getCurrentUser();
        String designation = currentUser.getDesignation();  // Not App.dsm.currUserIsPatient() since appointment is needed
        String fromTo = appointment.getStartTable() + " to " + appointment.getEndTable();

        if (designation.equals("Employee"))
            return fromTo  + " - " + appointment.getPatient();
        else {
            if (appointment.getPatient().equals(currentUser.getFirstName() + " " + currentUser.getLastName()))
                return fromTo + " - You";  // Current user's appointment
            else
                return fromTo;
        }
    }

    /**
     * Returns the name of a column.
     */
    @Override
    public String getColumnName(int col) {
        return day;
    }

    /**
     * Makes a cell uneditable.
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     * Returns an appointment's status when the status page is not a column of this table model.
     *
     * @param row The row in the table
     * @return A calendar event's appointment's status
     */
    public String getStatusFromEvents(int row) {
        return events.get(row).getStatus();
    }

    /**
     * Returns the day that corresponds to the current table model.
     *
     * @return the day that corresponds to the current table model
     */
    public String getDay() {
        return day;
    }
}
