package com.company.pages.program.tablemodels;

import javax.swing.table.AbstractTableModel;

import com.company.App;
import com.company.classes.Appointment;
import com.company.utilities.Queue;

public class QueueTableModel extends AbstractTableModel {
    private String[] columnNames = {"ID", "Date", "From", "To", "Status"};
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
        long idx = queue.get(rowIndex).getAppointmentID() - 1;  //
        Appointment appointment = App.dsm.getAppointmentList().get((int)idx);  // Bsearch appointment

        switch (columnIndex) {
            case 0:
                return appointment.getId();
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
