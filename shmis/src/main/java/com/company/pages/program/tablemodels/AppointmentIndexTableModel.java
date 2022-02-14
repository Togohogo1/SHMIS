package com.company.pages.program.tablemodels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.company.App;
import com.company.classes.Appointment;

public class AppointmentIndexTableModel extends AbstractTableModel {
    private String[] columnNames = {"ID", "Date", "From", "To", "Status"};
    private ArrayList<Long> appiontmentIDs;

    public AppointmentIndexTableModel(ArrayList<Long> appointmentIDs) {
        this.appiontmentIDs = appointmentIDs;
    }

    @Override
    public int getRowCount() {
        if (appiontmentIDs == null)
            return 0;

        return appiontmentIDs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Long idx = appiontmentIDs.get(rowIndex);
        Appointment appointment = App.dsm.query(idx);

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
}
