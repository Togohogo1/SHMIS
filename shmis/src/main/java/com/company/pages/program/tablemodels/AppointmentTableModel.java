package com.company.pages.program.tablemodels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.company.App;
import com.company.classes.Appointment;

/**
 * Customized table model for displaying appointments.
 */
public class AppointmentTableModel extends AbstractTableModel {
    private String[] columnNames = {"ID", "Date", "From", "To", "Status"};  // TODO change to arraylist to get rid of the switch?
    private ArrayList<Long> appiontmentIDs;

    @Override
    public int getRowCount() {
        return (appiontmentIDs == null ? 0 : appiontmentIDs.size());
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        long appId = appiontmentIDs.get(rowIndex);
        Appointment appointment = App.dsm.query(appId);

        switch (columnIndex) {
            case 0:
                return ""+appointment.getId();
            case 1:
                return appointment.getDate();
            case 2:
                return appointment.getStartTable();
            case 3:
                return appointment.getEndTable();
            case 4:
                return appointment.getStatus();
        }

        return null;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void setAppointmentList(ArrayList<Long> appointmentIDs) {
        this.appiontmentIDs = appointmentIDs;
    }

    public ArrayList<Long> getAppointmentList() {
        return appiontmentIDs;
    }
}
