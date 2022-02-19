package com.company.pages.program.tablemodels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.company.App;
import com.company.classes.Appointment;

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
        return appointment.getStartTable() + " - " + appointment.getEndTable() + ": " + appointment.getPatient();
        // TODO this is not initials yet
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
}
