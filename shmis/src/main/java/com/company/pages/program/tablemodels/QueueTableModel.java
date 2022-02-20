package com.company.pages.program.tablemodels;

import javax.swing.table.AbstractTableModel;

import com.company.App;
import com.company.classes.Appointment;
import com.company.utilities.Queue;

public class QueueTableModel extends AbstractTableModel {
    private String[] columnNames = {"ID", "Patient", "Date", "From", "To"};
    private Queue queue;

    public QueueTableModel(Queue queue) {
        this.queue = queue;
    }

    @Override
    public int getRowCount() {
        return queue.getSize();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        long appId = queue.get(rowIndex).getAppointmentID();
        Appointment appointment = App.dsm.query(appId);  // Bsearch appointment
        // TODO change to arraylist to get rid of the switch?
        switch (columnIndex) {
            case 0:
                return appointment.getId();
            case 1:
                return appointment.getPatient();
            case 2:
                return appointment.getDate();
            case 3:
                return appointment.getStartTable();
            case 4:
                return appointment.getEndTable();
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
